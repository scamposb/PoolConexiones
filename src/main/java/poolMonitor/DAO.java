package poolMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pool de conexiones en MySQL
 *
 * @author Sandra Campos
 */
public class DAO {

    public static void main (String [] args){
        try {
            PoolManager pm = new PoolManager();
            Connection con = pm.getConnection();
            String queryString = "select nombre from persona where publicante_uuid=?";
            PreparedStatement preparedStatement = con.prepareStatement(queryString);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            else{
                System.out.printf("nada");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("nada");
        }
    }
}
