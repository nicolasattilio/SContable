package model.FiltrosEmpresa;

import model.Empresa;


public class cNombreEmpresa implements cEmpresa {
    private String nombre;

    public cNombreEmpresa(String nombre){
        this.nombre=nombre.toLowerCase();
    }


    @Override
    public boolean cumple(Empresa e) {
        return e.getNombreEmpresa().toLowerCase().contains(nombre);
    }
}
