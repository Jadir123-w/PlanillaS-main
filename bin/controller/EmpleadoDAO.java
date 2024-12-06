package controller;

import db.Db;
import model.Empleado;

public class EmpleadoDAO {
    Db db = new Db("planillas");

    public boolean Login(Empleado empleado) {
        db.Sentencia("");
        empleado.setRegistro( db.getRegistro() );
        return empleado.getId() > 0;
    }

}
