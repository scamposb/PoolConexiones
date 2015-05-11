package poolMonitor;

import com.sun.jndi.ldap.pool.Pool;

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
        private static List<Connection> conexiones;		//numero de conexiones disponibles
        private static boolean inicializado = false;
        private static PoolManager instancia = new PoolManager();


        /**
         * Constructor vacio y privado para que nadie pueda acceder a el,
         * convencion del patron Singleton
         */
        private PoolManager(){}

        /**
         * En caso de que el array ya este inicializado, no se hara nada.
         * Aseguramos de esta forma que las instancias se creen una sola vez
         * @return instancia del pool manager a manejar, convencion del patron
         * singleton
         */
        public static PoolManager inicializar() throws SQLException{
            if(!inicializado){
                conexiones = new ArrayList<Connection>();
                for(int i=0; i<2;i++){
                    conexiones.add(ConnectionAdmin.getConnection());
                }
                inicializado = true;
            }
            return instancia;
        }
        /**
         * Acción de coger una de las conexiones disponibles en el poolJDBC.
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
