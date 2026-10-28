import java.util.List;

/**
 * Clase principal: solo orquesta. No conoce SQL ni JDBC;
 * habla con la base a traves de la interfaz PiezaDAO.
 */
public class Main {

    public static void main(String[] args) {

        // --- Parte 1: modelo en memoria ---
        // Se omite Tablero de este punto de entrada para evitar depender de una clase
        // que no forma parte de la persistencia administrada por el DAO.

        // --- Parte 2: persistencia a traves del DAO ---
        // Se declara con el tipo de la INTERFAZ; solo aca se elige la implementacion concreta.
        PiezaDAO dao = new PiezaDAOMySQL(new AccesoDatos());

        try {
            System.out.println("=== PIEZAS DESDE LA BASE DE DATOS ===");
            for (PiezaRegistro r : dao.listarTodas()) {
                System.out.println(r);
            }

            // Demostracion del CRUD con una pieza de prueba (se elimina al final)
            System.out.println("\n--- CREATE ---");
            int id = dao.insertar(new Peon("Blanco"), "E2");
            System.out.println("Pieza insertada con id " + id);

            System.out.println("--- READ ---");
            System.out.println(dao.buscarPorId(id));

            System.out.println("--- UPDATE ---");
            dao.actualizarPosicion(id, "E4");
            System.out.println(dao.buscarPorId(id));

            System.out.println("--- DELETE ---");
            System.out.println(dao.eliminar(id) ? "Pieza eliminada" : "No se elimino nada");

            System.out.println("\n--- Piezas negras en la base ---");
            List<PiezaRegistro> negras = dao.listarPorColor("Negro");
            negras.forEach(System.out::println);

        } catch (DAOException e) {
            System.err.println("Error de acceso a datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
