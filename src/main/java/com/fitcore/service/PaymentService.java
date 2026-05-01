package com.fitcore.service;

import com.fitcore.dao.MemberDAO;
import com.fitcore.dao.PaymentDAO;
import com.fitcore.dao.PlanDAO;
import com.fitcore.model.MembershipPlan;
import com.fitcore.model.Payment;
import com.fitcore.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Records a payment AND extends the member's plan_expiry inside a single
 * JDBC transaction so the database can never end up in a half-paid state.
 */
public class PaymentService {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final PlanDAO planDAO = new PlanDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    public boolean recordPaymentAndExtendPlan(int memberId, int planId, String method) throws SQLException {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            MembershipPlan plan = planDAO.findById(planId, con);
            if (plan == null) { con.rollback(); return false; }

            Payment p = new Payment();
            p.setMemberId(memberId);
            p.setPlanId(planId);
            p.setAmount(plan.getPrice());
            p.setDate(Date.valueOf(LocalDate.now()));
            p.setStatus("PAID");
            p.setPaymentMethod(method == null ? "CASH" : method);
            paymentDAO.insert(p, con);

            Date newExpiry = Date.valueOf(LocalDate.now().plusDays(plan.getDuration()));
            memberDAO.updatePlan(memberId, planId, newExpiry, con);

            con.commit();
            return true;
        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }
}
