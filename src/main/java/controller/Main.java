package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Empresa;
import model.FiltrosEmpresa.cCuit;
import model.FiltrosEmpresa.cEmpresa;
import model.FiltrosEmpresa.cNombreEmpresa;
import model.FiltrosEmpresa.cOr;
import model.Perfil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static Stage stg;
    public static EntityManager manager;
    public static EntityManagerFactory emf;
    public static Perfil perfilLogged;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(true);
        URL url = new File("src/main/java/view/loginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Sistema Contable");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml, String titulo) throws IOException {
        URL url = new File(fxml).toURI().toURL();
        Parent pane = FXMLLoader.load(url);
        stg.getScene().setRoot(pane);
        stg.setTitle(titulo);
    }

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("SContable");
        manager = emf.createEntityManager();
        Perfil rootExistente = manager.find(Perfil.class, "admin");
        if (rootExistente == null) {
            Perfil root = new Perfil("admin", "admin", 1);
            manager.getTransaction().begin();
            manager.persist(root);
            manager.getTransaction().commit();
            manager.close();
        }
        launch(args);

    }

    public void createNewPerfil(Perfil p) {
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
    }

    public void createNewEmpresa(Empresa e) {
        manager.getTransaction().begin();
        manager.persist(e);
        manager.getTransaction().commit();
        ;
    }

    public boolean existEmpresas(int e) {
        if (manager.find(Empresa.class, e) != null)
            return true;
        return false;
    }

    public void modEmpresa(Empresa e) {
        manager.getTransaction().begin();
        manager.merge(e);
        manager.getTransaction().commit();

    }

    public Empresa searchEmpresas(int cuitEmpresa,String nombreEmpresa) {
        if(cuitEmpresa!=0){
            return manager.find(Empresa.class,cuitEmpresa);
        }else{
            Query q=manager.createNativeQuery("SELECT idcuit from empresas where nombreempresa=?");
            q.setParameter(1,nombreEmpresa);
            List<Object>empresas=q.getResultList();
            if(empresas.size()>0) {
                int idcuit = (int) empresas.get(0);
                return manager.find(Empresa.class, idcuit);
            }
            else
                return null;
        }
    }

    public static Empresa searchEmpresa(int intcuit) {
        return manager.find(Empresa.class,intcuit);
    }

    public boolean checkPerfil(Empresa e){
        int cuit=e.getCuit();
        Query q =manager.createNativeQuery("SELECT empresasPerfil from empresas where idcuit=?");
        q.setParameter(1,cuit);
        List<Object> perfiles=q.getResultList();
        if((perfiles.contains(perfilLogged))||(perfilLogged.getLogin()=="admin"))
            return true;
        return false;
    }

    public static void elimEmpresa(Empresa e){
        manager.getTransaction().begin();
        manager.remove(e);
        manager.getTransaction().commit();
    }


}
