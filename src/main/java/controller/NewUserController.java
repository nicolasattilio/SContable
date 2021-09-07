package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Perfil;

public class NewUserController {
    @FXML
    private Label newUserMsg;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField checkPass;
    @FXML
    private TextField newUser;

    public void loadNewUser(ActionEvent actionEvent) {
        String user= this.newUser.getText();
        String pass= this.newPass.getText();
        String checkPass= this.checkPass.getText();
        Main m= new Main();
        try {
            if((pass.equals(checkPass)) || !user.isEmpty() || !!pass.isEmpty()) {
                Perfil p = new Perfil(user, pass,2);
                Main.perfilLogged = Main.manager.find(Perfil.class, user);
                if (Main.perfilLogged == null) {
                    m.createNewPerfil(p);
                    this.newUserMsg.setText("Usuario creado con exito");
                    System.out.println("Usuario creado con exito");
                    m.changeScene("src/main/java/view/mainMenuView.fxml", "Menu Principal - "+user);
                } else {
                    this.newUserMsg.setText("Verificar datos");
                    System.out.println("Verificar datos");
                }
            }else{
                    this.newUserMsg.setText("Verificar datos");
                    System.out.println("Verificar datos");
                }

        }catch(Exception e) {
            e.printStackTrace();
            }

        }

    public void backNewUser(ActionEvent actionEvent) {
        try {
            Main m = new Main();
            m.changeScene("src/main/java/view/loginView.fxml", "Sistema Contable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
