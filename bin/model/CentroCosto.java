package model;

public class CentroCosto {
    int id;
    String Detalle;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDetalle() {
        return Detalle;
    }
    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public CentroCosto(int id, String detalle) {
        this.id = id;
        Detalle = detalle;
    }
    
}
