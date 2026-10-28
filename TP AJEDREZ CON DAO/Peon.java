public class Peon extends Pieza {

    public static final String TIPO = "Peon";

    public Peon() {
        super("", "", "", "", TIPO);
    }

    public Peon(String color) {
        super(color, "", "", "", TIPO);
    }

    public Peon(String color, String comportamiento, String velocidad, String movimiento) {
        super(color, comportamiento, velocidad, movimiento, TIPO);
    }

    @Override
    public void mover() {
        System.out.println("El peon avanza un casillero hacia delante (dos en su primer movimiento) y captura en diagonal.");
    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "P" : "p";
    }
}
