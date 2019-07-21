package Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLOperations {

    public static String getEmail(int userID, ConnectionPool connectionPool) {
        String query = String.format("SELECT name from users where id=%s", userID);
        return executeQuery(1, query, connectionPool);
    }

    public static String getPassword(int userID, ConnectionPool connectionPool) {
        String query = String.format("SELECT password from users where id=%s", userID);
        return executeQuery(1, query, connectionPool);
    }


    private static String executeQuery(int columnIndex, String query, ConnectionPool connectionPool) {
        String result = null;

        try {
            Connection con = connectionPool.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println(connectionPool.getUsedConnections());
            connectionPool.releaseConnection(con);

            if (rs.next()) {
                result = rs.getString(columnIndex);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
