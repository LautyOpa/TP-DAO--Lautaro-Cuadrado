import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de PiezaDAO para MySQL (JDBC).
 * Es la UNICA clase que conoce SQL y las tablas PIEZA, COLOR y TIPOPIEZA.
 * Recibe AccesoDatos por constructor (inyeccion de dependencias), no lo crea ella.
 */
public class PiezaDAOMySQL implements PiezaDAO {

    private static final String SELECT_BASE =
            "SELECT P.IDPIEZA, TP.DESCRIPCIONDEPIEZA AS TIPOPIEZA, C.DESCRIPCION AS COLOR, "
          + "P.POSICION, P.MOVIMIENTO, P.VELOCIDAD, P.COMPORTAMIENTO "
          + "FROM PIEZA P "
          + "INNER JOIN TIPOPIEZA TP ON TP.IDTIPOPIEZA = P.IDTIPOPIEZA "
          + "INNER JOIN COLOR C ON C.IDCOLOR = P.IDCOLOR ";

    private static final String INSERT =
            "INSERT INTO PIEZA (IDTIPOPIEZA, DESCRIPCION, POSICION, MOVIMIENTO, COMPORTAMIENTO, VELOCIDAD, IDCOLOR) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final AccesoDatos acceso;

    public PiezaDAOMySQL(AccesoDatos acceso) {
        this.acceso = acceso;
    }

    // ------------------------------------------------------------------ CREATE
    @Override
    public int insertar(Pieza pieza, String posicion) throws DAOException {
        try (Connection con = acceso.getConexion()) {
            int idColor = obtenerIdColor(con, pieza.getColor());
            int idTipo = obtenerIdTipoPieza(con, pieza.getTipo());

            try (PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, idTipo);
                ps.setString(2, pieza.getTipo());
                ps.setString(3, posicion);
                ps.setString(4, pieza.getMovimiento());
                ps.setString(5, pieza.getComportamiento());
                ps.setString(6, pieza.getVelocidad());
                ps.setInt(7, idColor);
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    return keys.next() ? keys.getInt(1) : -1;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar la pieza", e);
        }
    }

    // -------------------------------------------------------------------- READ
    @Override
    public PiezaRegistro buscarPorId(int idPieza) throws DAOException {
        String sql = SELECT_BASE + "WHERE P.IDPIEZA = ?";
        try (Connection con = acceso.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPieza);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar la pieza " + idPieza, e);
        }
    }

    @Override
    public List<PiezaRegistro> listarTodas() throws DAOException {
        return listar(SELECT_BASE + "ORDER BY P.IDPIEZA", null);
    }

    @Override
    public List<PiezaRegistro> listarPorColor(String color) throws DAOException {
        return listar(SELECT_BASE + "WHERE C.DESCRIPCION = ? ORDER BY P.IDPIEZA", color);
    }

    // ------------------------------------------------------------------ UPDATE
    @Override
    public boolean actualizarPosicion(int idPieza, String nuevaPosicion) throws DAOException {
        String sql = "UPDATE PIEZA SET POSICION = ? WHERE IDPIEZA = ?";
        try (Connection con = acceso.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevaPosicion);
            ps.setInt(2, idPieza);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la posicion de la pieza " + idPieza, e);
        }
    }

    // ------------------------------------------------------------------ DELETE
    @Override
    public boolean eliminar(int idPieza) throws DAOException {
        String sql = "DELETE FROM PIEZA WHERE IDPIEZA = ?";
        try (Connection con = acceso.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPieza);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la pieza " + idPieza, e);
        }
    }

    // ---------------------------------------------------------------- privados
    private List<PiezaRegistro> listar(String sql, String parametro) throws DAOException {
        List<PiezaRegistro> resultado = new ArrayList<>();
        try (Connection con = acceso.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (parametro != null) {
                ps.setString(1, parametro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar las piezas", e);
        }
        return resultado;
    }

    /** Convierte una fila del ResultSet en objetos del modelo. */
    private PiezaRegistro mapear(ResultSet rs) throws SQLException {
        Pieza pieza = PiezaFactory.crear(
                rs.getString("TIPOPIEZA"),
                rs.getString("COLOR"),
                rs.getString("COMPORTAMIENTO"),
                rs.getString("VELOCIDAD"),
                rs.getString("MOVIMIENTO"));
        return new PiezaRegistro(rs.getInt("IDPIEZA"), pieza, rs.getString("POSICION"));
    }

    private int obtenerIdColor(Connection con, String descripcion) throws SQLException, DAOException {
        String sql = "SELECT IDCOLOR FROM COLOR WHERE DESCRIPCION = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDCOLOR");
                }
            }
        }
        throw new DAOException("No existe el color: " + descripcion);
    }

    private int obtenerIdTipoPieza(Connection con, String descripcion) throws SQLException, DAOException {
        String sql = "SELECT IDTIPOPIEZA FROM TIPOPIEZA WHERE DESCRIPCIONDEPIEZA = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDTIPOPIEZA");
                }
            }
        }
        throw new DAOException("No existe el tipo de pieza: " + descripcion);
    }
}
