import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Unica responsabilidad: entregar conexiones a la base de datos.
 * No imprime, no hace System.exit() y no sabe nada de piezas: solo conecta.
 * (El driver mysql-connector 8.x se registra solo, no hace falta Class.forName.)
 */
public class AccesoDatos {

    private final String url;
    private final String usuario;
    private final String clave;

    public AccesoDatos() {
        // Idealmente estos valores vienen de un archivo .properties o variables de entorno
        this("localhost", 3306, "Ajedrez", "root", "Bench_333");
    }

    public AccesoDatos(String maquina, int puerto, String bd, String usuario, String clave) {
        this.url = "jdbc:mysql://" + maquina + ":" + puerto + "/" + bd;
        this.usuario = usuario;
        this.clave = clave;
    }

    /** Devuelve una conexion NUEVA; quien la pide es responsable de cerrarla (try-with-resources). */
    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }
}
