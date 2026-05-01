package com.fitcore.service;

import com.fitcore.dao.MemberDAO;
import com.fitcore.dao.UserDAO;
import com.fitcore.model.Member;
import com.fitcore.model.User;
import com.fitcore.util.Constants;
import com.fitcore.util.DBUtil;
import com.fitcore.util.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    public User login(String username, String password) throws SQLException {
        User u = userDAO.findByUsername(username);
        if (u == null) return null;
        if (!Constants.STATUS_ACTIVE.equals(u.getStatus())) return null;
        if (!PasswordUtil.verify(password, u.getPasswordHash())) return null;
        return u;
    }

    /**
     * Member self-registration. Creates a UserAccount (PENDING) + Member row in one transaction.
     * Admin must approve the user before they can log in.
     */
    public boolean register(Member member, String username, String password) throws SQLException {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            User u = new User();
            u.setUsername(username);
            u.setPasswordHash(PasswordUtil.hash(password));
            u.setRole(Constants.ROLE_MEMBER);
            u.setStatus(Constants.STATUS_PENDING);

            int userId = userDAO.insert(u, con);
            if (userId < 0) { con.rollback(); return false; }

            member.setUserId(userId);
            member.setStatus(Constants.STATUS_ACTIVE);
            int memberId = memberDAO.insert(member, con);
            if (memberId < 0) { con.rollback(); return false; }

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
