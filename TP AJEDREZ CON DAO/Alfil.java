public class Alfil extends Pieza {

    public static final String TIPO = "Alfil";

    public Alfil() {
        super("", "", "", "", TIPO);
    }

    public Alfil(String color) {
        super(color, "", "", "", TIPO);
    }

    public Alfil(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("El alfil se mueve en diagonal, siempre sobre el casillero del mismo color.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "A" : "a";
    }
}
