package model;

public class Departamento {
    int id;
    String RazonSocial, Cuenta;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRazonSocial() {
        return RazonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }
    public String getCuenta() {
        return Cuenta;
    }
    public void setCuenta(String cuenta) {
        Cuenta = cuenta;
    }

    public Departamento(int id, String razonSocial, String cuenta) {
        this.id = id;
        RazonSocial = razonSocial;
        Cuenta = cuenta;
    }
    
}
