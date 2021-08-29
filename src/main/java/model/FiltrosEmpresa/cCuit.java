package model.FiltrosEmpresa;

import model.Empresa;

public class cCuit implements cEmpresa {
    private int cuit;

    public cCuit(int cuit) {
        this.cuit = cuit;
    }

    @Override
    public boolean cumple(Empresa e) {
        return e.getCuit()==this.cuit;
    }
}
