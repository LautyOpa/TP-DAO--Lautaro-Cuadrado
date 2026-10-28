import java.util.List;

/**
 * Contrato de acceso a datos para Pieza.
 * El resto del programa depende de ESTA interfaz y no de MySQL:
 * si manana cambia la base (u otra implementacion, por ejemplo en memoria para tests),
 * Main y el modelo no se tocan.
 */
public interface PiezaDAO {

    /** Inserta la pieza en la posicion indicada (ej: "E2") y devuelve el id generado. */
    int insertar(Pieza pieza, String posicion) throws DAOException;

    /** Devuelve la pieza con ese id, o null si no existe. */
    PiezaRegistro buscarPorId(int idPieza) throws DAOException;

    List<PiezaRegistro> listarTodas() throws DAOException;

    List<PiezaRegistro> listarPorColor(String color) throws DAOException;

    /** Devuelve true si se actualizo alguna fila. */
    boolean actualizarPosicion(int idPieza, String nuevaPosicion) throws DAOException;

    /** Devuelve true si se elimino alguna fila. */
    boolean eliminar(int idPieza) throws DAOException;
}
