package poolJDBC;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pool de conexiones en MySQL con datasource de java y conectado con Tomcat
 *
 * @author Sandra Campos
 */
public class PoolManagerJDBC {

    DataSource _ds;

    public PoolManagerJDBC(){
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)
                    envCtx.lookup("jdbc/myusickdb");
            _ds = ds;

        }catch(NamingException e){}
    }

    private void example(){
        try {
            Connection con = _ds.getConnection();
            String queryString = "select tipoPublicante from publicante where uuid=?";
            PreparedStatement preparedStatement = con.prepareStatement(queryString);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                con.commit();
                System.out.println(resultSet.getBoolean(1));
            }
            else{
                con.rollback();
                System.out.printf("nada");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("nada");
        }
    }

    public static void main(String [] args){
        PoolManagerJDBC p = new PoolManagerJDBC();
        p.example();

    }
}
