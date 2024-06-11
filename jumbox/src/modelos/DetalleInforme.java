package modelos;

public class DetalleInforme {
    private int idDetalle;
    private int idInforme;
    private String tipo;
    private int idTipo;

    public DetalleInforme(int idInforme, String tipo, int idTipo) {
        this.idInforme = idInforme;
        this.tipo = tipo;
        this.idTipo = idTipo;
    }

    // Getters y setters
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
}
