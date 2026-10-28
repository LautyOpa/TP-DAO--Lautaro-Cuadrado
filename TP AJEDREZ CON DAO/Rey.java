public class Rey extends Pieza {

    public static final String TIPO = "Rey";

    public Rey() {
        super("", "", "", "", TIPO);
    }

    public Rey(String color) {
        super(color, "", "", "", TIPO);
    }

    public Rey(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("El rey se mueve un solo casillero en cualquier direccion.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "R" : "r";
    }
}
