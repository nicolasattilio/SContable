package model.FiltrosEmpresa;

import model.Empresa;

public class cOr implements cEmpresa {
    private cEmpresa c1;
    private cEmpresa c2;

    public cOr(cEmpresa c1, cEmpresa c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean cumple(Empresa e) {
        return (c1.cumple(e)||(c2.cumple(e)));
    }
}
