/**
 * Construye la subclase correcta de Pieza a partir del texto guardado en la base.
 * Asi el DAO no tiene un switch gigante y las subclases no saben nada de SQL.
 */
public class PiezaFactory {

    private PiezaFactory() { }

    public static Pieza crear(String tipo, String color, String comportamiento,
                              String velocidad, String movimiento) {
        String t = tipo == null ? "" : tipo.trim().toLowerCase();
        switch (t) {
            case "peon":
            case "peón":
                return new Peon(color, comportamiento, velocidad, movimiento);
            case "torre":
                return new Torre(color, comportamiento, velocidad, movimiento);
            case "caballo":
                return new Caballo(color, comportamiento, velocidad, movimiento);
            case "alfil":
                return new Alfil(color, comportamiento, velocidad, movimiento);
            case "reina":
                return new Reina(color, comportamiento, velocidad, movimiento);
            case "rey":
                return new Rey(color, comportamiento, velocidad, movimiento);
            default:
                Pieza generica = new Pieza(color, comportamiento, velocidad, movimiento, tipo);
                return generica;
        }
    }
}
