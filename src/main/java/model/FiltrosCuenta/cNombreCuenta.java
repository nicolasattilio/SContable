package model.FiltrosCuenta;

import model.Cuenta;

public class cNombreCuenta implements cCuenta{
    private String nombre;

    public cNombreCuenta(String nombre) {
        this.nombre = nombre.toLowerCase();
    }

    @Override
    public boolean cumple(Cuenta c) {
        return c.getNombre().toLowerCase().contains(this.nombre);
    }
}
