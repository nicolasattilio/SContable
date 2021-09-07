package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Perfil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Stage stg;
    public static EntityManager manager;
    public static EntityManagerFactory emf;
    public static Perfil perfilLogged;

    @Override
    public void start(Stage primaryStage)  throws IOException {
        stg=primaryStage;
        primaryStage.setResizable(true);
        URL url=new File("src/main/java/view/loginView.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        primaryStage.setTitle("Sistema Contable");
        primaryStage.setScene(new Scene(root,600,400));
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
        manager=emf.createEntityManager();
        Perfil rootExistente=manager.find(Perfil.class,"admin");
        if (rootExistente==null){
            Perfil root = new Perfil("admin","admin",1);
            manager.getTransaction().begin();
            manager.persist(root);
            manager.getTransaction().commit();
        }
        launch(args);
    }

    public void createNewPerfil(Perfil p) {
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
    }
}
