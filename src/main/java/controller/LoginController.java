package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Perfil;


public class LoginController {
    @FXML
    private Label loginMsg;
    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtUser;


    public void newUser(ActionEvent actionEvent) {
        try {
            Main m = new Main();
            m.changeScene("src/main/java/view/newUserView.fxml", "Nuevo Usuario");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUser(ActionEvent actionEvent) {
        String user= this.txtUser.getText();
        String pass=this.txtPass.getText();
        Main m= new Main();
        try{
            if(user.isEmpty()||pass.isEmpty())
                loginMsg.setText("Verificar datos");
            else{
                Main.perfilLogged=Main.manager.find(Perfil.class,user);
                if(Main.perfilLogged!=null){
                    if (Main.perfilLogged.getPass().equals(pass)) {
                        loginMsg.setText("Ingreso Exitoso");
                        System.out.println("Ingreso Exitoso");
                        m.changeScene("src/main/java/view/mainMenuView.fxml", "Menu Principal - "+user);
                    }else{
                        loginMsg.setText("Verificar datos");
                        System.out.println("Verificar datos");
                    }
                }else{
                    loginMsg.setText("Verificar datos");
                    System.out.println("Verificar datos");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
