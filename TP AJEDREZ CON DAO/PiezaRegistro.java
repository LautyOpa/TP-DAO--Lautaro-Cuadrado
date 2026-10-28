/**
 * Fila de la tabla PIEZA ya convertida a objetos:
 * agrupa el id y la posicion (que son datos de persistencia / ubicacion)
 * con el objeto Pieza del modelo, sin ensuciar la clase Pieza.
 */
public class PiezaRegistro {

    private final int idPieza;
    private final Pieza pieza;
    private final String posicion;

    public PiezaRegistro(int idPieza, Pieza pieza, String posicion) {
        this.idPieza = idPieza;
        this.pieza = pieza;
        this.posicion = posicion;
    }

    public int getIdPieza() { return idPieza; }
    public Pieza getPieza() { return pieza; }
    public String getPosicion() { return posicion; }

    @Override
    public String toString() {
        return "Pieza Nro: " + idPieza
                + " | Tipo: " + pieza.getTipo()
                + " | Color: " + pieza.getColor()
                + " | Posicion: " + posicion
                + " | Movimiento: " + pieza.getMovimiento()
                + " | Velocidad: " + pieza.getVelocidad()
                + " | Comportamiento: " + pieza.getComportamiento();
    }
}
