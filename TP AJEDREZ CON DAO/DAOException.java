/**
 * Excepcion propia de la capa de acceso a datos.
 * Encapsula SQLException para que el resto del programa (Main, Tablero, etc.)
 * no dependa de java.sql -> menor acoplamiento.
 */
public class DAOException extends Exception {

    public DAOException(String mensaje) {
        super(mensaje);
    }

    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
