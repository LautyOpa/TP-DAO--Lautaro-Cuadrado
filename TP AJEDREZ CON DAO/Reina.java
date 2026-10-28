public class Reina extends Pieza {

    public static final String TIPO = "Reina";

    public Reina() {
        super("", "", "", "", TIPO);
    }

    public Reina(String color) {
        super(color, "", "", "", TIPO);
    }

    public Reina(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("La reina combina torre y alfil: se mueve en linea recta y en diagonal sin limite de casilleros.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "Q" : "q";
    }
}
