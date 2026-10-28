public class Torre extends Pieza {

    public static final String TIPO = "Torre";

    public Torre() {
        super("", "", "", "", TIPO);
    }

    public Torre(String color) {
        super(color, "", "", "", TIPO);
    }

    public Torre(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("La torre se mueve en linea recta: horizontal o vertical, tantos casilleros como quiera.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "T" : "t";
    }
}
