package model;

import model.FiltrosEmpresa.cEmpresa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public abstract class SistemaContable {
    private Perfil perfil;
    private List<Empresa>empresas=new ArrayList<>();



    public ArrayList<Empresa>buscarEmpresa(cEmpresa c){
        ArrayList<Empresa>busqueda= new ArrayList<>();
        for(Empresa e: empresas )
            busqueda.addAll(e.buscarEmpresa(c));
        return busqueda;
    }

    public void addEmpresa(Empresa e){
        this.empresas.add(e);
    }

    public List<Empresa> loadDataEmpresas(){
        try{
            FileInputStream empresasLoadData=new FileInputStream("empresas.data");
            ObjectInputStream cargarEmpresas=new ObjectInputStream(empresasLoadData);
            List<Empresa>cargaEmpresas= (List<Empresa>) cargarEmpresas.readObject();
            System.out.println("Se han cargado con exito los datos");
            return cargaEmpresas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveDataEmpresas(){
        try{
            FileOutputStream empresasSaveData= new FileOutputStream("empresas.data");
            ObjectOutputStream guardarEmpresas=new ObjectOutputStream(empresasSaveData);
            guardarEmpresas.writeObject(this.empresas);
            guardarEmpresas.close();
            System.out.println("Se han guardado con exito los datos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
