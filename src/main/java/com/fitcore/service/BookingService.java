package com.fitcore.service;

import com.fitcore.dao.SessionDAO;
import com.fitcore.model.WorkoutSession;
import com.fitcore.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BookingService {

    private final SessionDAO sessionDAO = new SessionDAO();

    /**
     * Books a member into a session inside a transaction with row-level locking
     * so two concurrent requests cannot both grab the last spot.
     */
    public BookingResult book(int memberId, int sessionId) throws SQLException {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            WorkoutSession s = sessionDAO.findById(sessionId, con);
            if (s == null) { con.rollback(); return BookingResult.NOT_FOUND; }
            if (s.isFull()) { con.rollback(); return BookingResult.FULL; }

            try {
                sessionDAO.book(memberId, sessionId, con);
            } catch (SQLException dup) {
                con.rollback();
                return BookingResult.ALREADY_BOOKED;
            }
            sessionDAO.incrementEnrolled(sessionId, con);
            con.commit();
            return BookingResult.SUCCESS;
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

    public enum BookingResult { SUCCESS, FULL, NOT_FOUND, ALREADY_BOOKED }
}
