package study.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private final ConnectionMaker connectionMaker;

    public JdbcContext(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void workWithStatementStrategy(StatementStrategy strategy) {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeConnection();

            ps = strategy.makePreparedStatement(c);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void executeSql(final String query) {
        StatementStrategy strategy = c -> c.prepareStatement(query);
        workWithStatementStrategy(strategy);
    }

}
