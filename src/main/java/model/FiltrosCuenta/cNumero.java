package model.FiltrosCuenta;

import model.Cuenta;

public class cNumero implements cCuenta{
    private int numero;

    public cNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public boolean cumple(Cuenta c) {
        return c.getCodigoCuenta()==this.numero;
    }
}
