
/**
 * SUPERCLASE Pieza.
 * Atributos comunes a todas las piezas (surgen de la abstraccion del TP N 2).
 * El metodo mover() es el comportamiento comun que cada subclase SOBREESCRIBE
 * (override) -> junto con la herencia son las dos precondiciones del polimorfismo.
 */
public class Pieza {
    private String Tipo;            // 
    private String color;           // "Blanco" / "Negro"
    private String comportamiento;  // ej: "agresor", "homerica", "encarnizada"
    private String velocidad;       // ej: "lenta", "ligero", "libre"
    private String movimiento;      // ej: "ladino", "directo", "oblicuo"
    
    // Constructor vacio (punto 1.c del TP)
    public Pieza() {
        this.color = "";
        this.comportamiento = "";
        this.velocidad = "";
        this.movimiento = "";
        this.Tipo = "";
        
    }
     
    // Constructor con todos los parametros (punto 1.d del TP)
    public Pieza(String color, String comportamiento, String velocidad, String movimiento, String Tipo) {
        this.color = color;
        this.comportamiento = comportamiento;
        this.velocidad = velocidad;
        this.movimiento = movimiento;
        this.Tipo = Tipo;
        
    }

    // Getters y Setters (punto 1.b del TP)
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }



    // Comportamiento comun: cada subclase lo sobreescribe
    public void mover() {
        System.out.println("La pieza se mueve de forma generica.");
    }

    // Simbolo que se dibuja en el tablero (cada subclase lo sobreescribe)
    public String getSimbolo() {
        return "?";
    }

    // Para imprimir los atributos de la pieza (punto 2.d del TP)
    @Override
    public String toString() {
        return String.format("%-8s | color: %-7s | comportamiento: %-12s | velocidad: %-8s | movimiento: %s",
                Tipo, color, comportamiento, velocidad, movimiento);
    }
}