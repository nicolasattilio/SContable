package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Empresa;
import model.FiltrosEmpresa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static controller.Main.perfilLogged;

public class MainMenuController {
    @FXML
    private AnchorPane AnchorMsg;
    @FXML
    private Label idNombreEmpresa;
    @FXML
    private Label idCuit;
    @FXML
    private Label idDirTel;
    @FXML
    private Label idLabelNombre;
    @FXML
    private Label idLabelCuit;
    @FXML
    private Label idLabelDirTel;
    @FXML
    private TextField textSearchEmpresa;
    @FXML
    private TextField elimCuit;
    @FXML
    private AnchorPane AnchorEliminateEmpresa;
    @FXML
    private Label labelEliminateIdCuit;
    @FXML
    private Label laberEliminateNombreEmpresa;
    @FXML
    private AnchorPane AnchorModifyEmpresa;
    @FXML
    private TextField modCuit;
    @FXML
    private TextField modNombreEmpresa;
    @FXML
    private TextField modDireccionEmpresa;
    @FXML
    private TextField modLocalidadEmpresa;
    @FXML
    private TextField modTelefonoEmpresa;
    @FXML
    private Label TextMainMenu;
    @FXML
    private TextField newCuit;
    @FXML
    private TextField newNameEmpresa;
    @FXML
    private TextField newDirectionEmpresa;
    @FXML
    private TextField newLocationEmpresa;
    @FXML
    private TextField newTelephoneEmpresa;
    @FXML
    private AnchorPane AnchorEmpresa;
    @FXML
    private AnchorPane AnchorNewEmpresa;


    public void loadEmpresas(ActionEvent actionEvent) {
        textSearchEmpresa.setText("");
        idLabelCuit.setVisible(false);
        idCuit.setVisible(false);
        idNombreEmpresa.setVisible(false);
        idDirTel.setVisible(false);
        AnchorNewEmpresa.setVisible(false);
        AnchorEmpresa.setVisible(true);
        TextMainMenu.setVisible(false);
        AnchorModifyEmpresa.setVisible(false);
        AnchorEliminateEmpresa.setVisible(false);
        idLabelNombre.setVisible(false);
        idLabelCuit.setVisible(false);
        idLabelDirTel.setVisible(false);
        AnchorMsg.setVisible(false);
    }


    public void CreateEmpresa(ActionEvent actionEvent) {
        idLabelCuit.setVisible(false);
        idCuit.setVisible(false);
        idNombreEmpresa.setVisible(false);
        idDirTel.setVisible(false);
        AnchorEmpresa.setVisible(false);
        AnchorNewEmpresa.setVisible(true);
        TextMainMenu.setVisible(false);
        AnchorModifyEmpresa.setVisible(false);
        AnchorEliminateEmpresa.setVisible(false);
        AnchorMsg.setVisible(false);

    }

    public void ModifyEmpresa(ActionEvent actionEvent) {
        idLabelCuit.setVisible(false);
        idCuit.setVisible(false);
        idNombreEmpresa.setVisible(false);
        idDirTel.setVisible(false);
        AnchorEmpresa.setVisible(false);
        AnchorNewEmpresa.setVisible(false);
        TextMainMenu.setVisible(false);
        AnchorModifyEmpresa.setVisible(true);
        AnchorEliminateEmpresa.setVisible(false);
        AnchorMsg.setVisible(false);
    }

    public void EliminarEmpresa(ActionEvent actionEvent) {
        idLabelCuit.setVisible(false);
        idCuit.setVisible(false);
        idNombreEmpresa.setVisible(false);
        idDirTel.setVisible(false);
        AnchorEmpresa.setVisible(false);
        AnchorNewEmpresa.setVisible(false);
        TextMainMenu.setVisible(false);
        AnchorModifyEmpresa.setVisible(false);
        AnchorEliminateEmpresa.setVisible(true);
        AnchorMsg.setVisible(false);
    }

    public void EliminateEmpresa(ActionEvent actionEvent) {
        if (labelEliminateIdCuit.getText()!="") {
            String cuit = labelEliminateIdCuit.getText();
            int intcuit = Integer.parseInt(cuit);
            Empresa e = Main.searchEmpresa(intcuit);
            Main.elimEmpresa(e);
            AnchorEliminateEmpresa.setVisible(false);
            AnchorMsg.setVisible(true);
            TextMainMenu.setVisible(true);
            TextMainMenu.setText("Eliminada la empresa");
            laberEliminateNombreEmpresa.setText("");
            labelEliminateIdCuit.setText("");
            elimCuit.setText("");
        }else{
            System.out.println("Error en la eliminacion de la empresa");
            AnchorEliminateEmpresa.setVisible(false);
            AnchorMsg.setVisible(true);
            TextMainMenu.setVisible(true);
            TextMainMenu.setText("No se pudo eliminar la empresa");
        }
    }


    public void searchEmpresas(ActionEvent actionEvent) {
        TextMainMenu.setVisible(false);
        idLabelCuit.setVisible(false);
        idCuit.setVisible(false);
        idNombreEmpresa.setVisible(false);
        idDirTel.setVisible(false);
        String valor= textSearchEmpresa.getText();
        String nombreEmpresa="";
        int cuitEmpresa=0;
        if(valor.length()!=0) {
            if (valor.matches("[0-9]+")) {
                cuitEmpresa = Integer.parseInt(valor);
            } else {
                nombreEmpresa = valor.toLowerCase();
            }
            textSearchEmpresa.setText("");
            Main m = new Main();
            Empresa emp = m.searchEmpresas(cuitEmpresa, nombreEmpresa);
            if(emp!=null){
                idLabelCuit.setVisible(true);
                idLabelDirTel.setVisible(true);
                idLabelNombre.setVisible(true);
                idCuit.setVisible(true);
                idLabelCuit.setVisible(true);
                idNombreEmpresa.setVisible(true);
                idDirTel.setVisible(true);
                idLabelNombre.setText(emp.getNombreEmpresa().toUpperCase());
                idLabelCuit.setText(String.valueOf(emp.getCuit()));
                idLabelDirTel.setText(emp.getDireccion().toUpperCase() + ", " + emp.getLocalidad().toUpperCase() + " - " + emp.getTelefono().toUpperCase());
            }else{
                TextMainMenu.setVisible(true);
                idLabelNombre.setVisible(false);
                idLabelCuit.setVisible(false);
                idLabelDirTel.setVisible(false);
                TextMainMenu.setText("No existen empresas");
                System.out.println("Busqueda en filtro que no encontro datos");
            }
        }else{
            TextMainMenu.setVisible(true);
            idLabelNombre.setVisible(false);
            idLabelCuit.setVisible(false);
            idLabelDirTel.setVisible(false);

            TextMainMenu.setText("No existen empresas");
            System.out.println("Busqueda en filtro que no encontro datos");
        }

    }

    public void searchEliminateEmpresas(ActionEvent actionEvent) {
        TextMainMenu.setVisible(false);
        labelEliminateIdCuit.setVisible(false);
        laberEliminateNombreEmpresa.setVisible(false);
        String valor = elimCuit.getText();
        if(!valor.isEmpty()){
            TextMainMenu.setVisible(false);
            String nombreEmpresa="";
            int cuitEmpresa=0;
            if (valor.matches("[0-9]+")) {
                cuitEmpresa = Integer.parseInt(valor);
            } else {
                nombreEmpresa = valor.toLowerCase();
            }
            Main m = new Main();
            Empresa emp = m.searchEmpresas(cuitEmpresa, nombreEmpresa);
            if(emp!=null){
                labelEliminateIdCuit.setVisible(true);
                laberEliminateNombreEmpresa.setVisible(true);
                labelEliminateIdCuit.setText(String.valueOf(emp.getCuit()));
                laberEliminateNombreEmpresa.setText(emp.getNombreEmpresa().toUpperCase());
                elimCuit.setText("");
            }else{
                System.out.println("No existe la empresa");
                TextMainMenu.setVisible(true);
                TextMainMenu.setText("No existe la empresa");
                elimCuit.setText("");
                }
        }else {
            System.out.println("Error en la busqueda de la empresa");
            TextMainMenu.setVisible(true);
            TextMainMenu.setText("No se pudo buscar la empresa");
            elimCuit.setText("");
        }
    }

    public void MakeEmpresa(ActionEvent actionEvent) {
        String cuit=this.newCuit.getText();
        String nombreEmpresa=this.newNameEmpresa.getText().toLowerCase();
        String direccion=this.newDirectionEmpresa.getText().toLowerCase();
        String localidad=this.newLocationEmpresa.getText().toLowerCase();
        String telefono=this.newTelephoneEmpresa.getText().toLowerCase();
        Main m=new Main();
        try{
            if((!cuit.isEmpty())||(!nombreEmpresa.isEmpty())||(!direccion.isEmpty())||(!localidad.isEmpty())||(!telefono.isEmpty())){
                if (cuit.matches("[0-9]+")) {
                    int intcuit = Integer.parseInt(cuit);
                    Empresa e = new Empresa(intcuit, nombreEmpresa, direccion, localidad, telefono);
                    if (!m.existEmpresas(intcuit)) {
                        perfilLogged.addEmpresa(e);
                        m.createNewEmpresa(e);
                        System.out.println("Empresa creada");
                        AnchorEmpresa.setVisible(false);
                        AnchorNewEmpresa.setVisible(false);
                        AnchorMsg.setVisible(true);
                        TextMainMenu.setVisible(true);
                        TextMainMenu.setText("Empresa creada con exito");
                    } else {
                        System.out.println("Existe una empresa con los mismos datos");
                        AnchorEmpresa.setVisible(false);
                        AnchorNewEmpresa.setVisible(false);
                        AnchorMsg.setVisible(true);
                        TextMainMenu.setVisible(true);
                        TextMainMenu.setText("Verificar datos por favor");
                    }
                }else{
                        AnchorMsg.setVisible(true);
                        TextMainMenu.setVisible(true);
                        AnchorNewEmpresa.setVisible(false);
                        TextMainMenu.setText("Verificar datos por favor");
                }
            }else{
                AnchorMsg.setVisible(true);
                TextMainMenu.setVisible(true);
                AnchorNewEmpresa.setVisible(false);
                TextMainMenu.setText("Verificar datos por favor");

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        this.newCuit.setText("");
        this.newDirectionEmpresa.setText("");
        this.newNameEmpresa.setText("");
        this.newLocationEmpresa.setText("");
        this.newTelephoneEmpresa.setText("");
    }

    public void ModEmpresa(ActionEvent actionEvent) {
        String cuit=this.modCuit.getText();
        String nombreEmpresa=this.modNombreEmpresa.getText().toLowerCase();
        String direccion=this.modDireccionEmpresa.getText().toLowerCase();
        String localidad=this.modLocalidadEmpresa.getText().toLowerCase();
        String telefono=this.modTelefonoEmpresa.getText().toLowerCase();
        Main m=new Main();
        try{
            if((!cuit.isEmpty())||(!nombreEmpresa.isEmpty())||(!direccion.isEmpty())||(!localidad.isEmpty())||(!telefono.isEmpty())){
                if (cuit.matches("[0-9]+")) {
                int intcuit=Integer.parseInt(cuit);
                Empresa e=new Empresa(intcuit,nombreEmpresa,direccion,localidad,telefono);
                if((m.existEmpresas(intcuit))&& (perfilLogged.getNivel()<=2)&&(m.checkPerfil(e))) {
                        perfilLogged.updateEmpresa(e);
                        m.modEmpresa(e);
                        TextMainMenu.setVisible(true);
                        System.out.println("Empresa modificada");
                        AnchorEmpresa.setVisible(false);
                        AnchorModifyEmpresa.setVisible(false);
                        AnchorMsg.setVisible(true);
                        TextMainMenu.setText("Empresa modificada con exito");
                }else{
                    System.out.println("Existe una empresa con los mismos datos o no tiene permisos de modificacion");
                    AnchorEmpresa.setVisible(false);
                    AnchorMsg.setVisible(true);
                    AnchorModifyEmpresa.setVisible(false);
                    TextMainMenu.setVisible(true);
                    TextMainMenu.setText("Verificar datos por favor");
                    }
                }else {
                    AnchorMsg.setVisible(true);
                    TextMainMenu.setVisible(true);
                    AnchorNewEmpresa.setVisible(false);
                    TextMainMenu.setText("Verificar datos por favor");
                }
            }else{
                AnchorMsg.setVisible(true);
                TextMainMenu.setVisible(true);
                AnchorModifyEmpresa.setVisible(false);
                TextMainMenu.setText("Verificar datos por favor");

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        this.modCuit.setText("");
        this.modNombreEmpresa.setText("");
        this.modDireccionEmpresa.setText("");
        this.modTelefonoEmpresa.setText("");
        this.modLocalidadEmpresa.setText("");

    }



}
