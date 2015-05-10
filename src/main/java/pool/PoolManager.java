package pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pool de conexiones en MySQL
 *
 * @author Sandra Campos
 */
public class PoolManager {
        /*
         * Atributos de la clase
         */
        private List<Connection> conexiones;		//numero de conexiones disponibles


        public PoolManager() throws SQLException{
            conexiones = new ArrayList<Connection>();
            for(int i=0; i<100;i++){
                conexiones.add(ConnectionAdmin.getConnection());
            }
        }
        /**
         * Acción de coger una de las conexiones disponibles en el pool.
         * @return instancia de conexion a la BD
         */
        public synchronized Connection getConnection(){
            while(conexiones.isEmpty()){
                try{
                    wait();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            /* Hay al menos un recurso liberado, devolvemos instancia */
            Connection conexion = conexiones.get(0);
            conexiones.remove(0);
            return conexion;
        }
        /**
         * Devolvemos la conexion al array de recursos liberados
         * @param conexion conexion a la BD que queremos liberar
         */
        public synchronized void returnConnection(Connection conexion) {
            conexiones.add(conexion);
            notifyAll();    //avisamos a todos los threads de la devolucion
        }

}
