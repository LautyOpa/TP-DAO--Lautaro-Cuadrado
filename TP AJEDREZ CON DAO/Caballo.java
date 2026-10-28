public class Caballo extends Pieza {

    public static final String TIPO = "Caballo";

    public Caballo() {
        super("", "", "", "", TIPO);
    }

    public Caballo(String color) {
        super(color, "", "", "", TIPO);
    }

    public Caballo(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("El caballo se mueve en forma de L y es la unica pieza que puede saltar por sobre las demas.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "C" : "c";
    }
}
