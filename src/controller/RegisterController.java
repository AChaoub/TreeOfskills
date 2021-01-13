package controller;

import java.sql.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.stage.StageStyle;
import db.DbConnect;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;

public class RegisterController implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastname;

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private Label registerMsg;

    @FXML
    private ImageView simplonIcon;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label registerSuccess;
    @FXML
    private Label matchPass;
    @FXML
    private ComboBox<String> combo;

    static ObservableList<String> options = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imgPathSimplon = new File("img/simplo.png");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());
        simplonIcon.setImage(imgPath);
    }


    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void toLoginPage(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 550));
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerOnAction() {
        System.out.println("hello");
        if (!firstName.getText().isBlank() &&
                !lastname.getText().isBlank() &&
                !username.getText().isBlank() &&
                !password.getText().isBlank() &&
                !confirmPass.getText().isBlank()) {
            System.out.println("success");
        } else {
            registerMsg.setText("veuiller remplir tout les champs!!!");
        }
    }
    public void promoChoice(){
        System.out.println("hello");
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String queryCombo = "select titre_Promo from promotion";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(queryCombo);
            while(queryResult.next()){
                options.add(queryResult.getString("titre_Promo"));

            }

            combo.setItems(options);

            statement.close();
            queryResult.close();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerOnAction(ActionEvent event) {
        if (password.getText().equals(confirmPass.getText())) {
            registerValidation();
        } else {
            matchPass.setText("le mot de passe ne se correspond pas!!");

        }

    }
    public void registerValidation(){

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String firstname = firstName.getText();
        String lastName = lastname.getText();
        String userName = username.getText();
        String emailAdd = email.getText();
        int tels = Integer.parseInt(tel.getText());
        String passwords = password.getText();

        int idApp = 8;
        String insertField = "INSERT INTO apprenant(idApp, nomApp, prenomApp, surnom,emailApp, tel, password) VALUES ('" ;
        String insertValues= idApp +"','" +firstname +"','"+ lastName +"','"+ userName +"','"+ emailAdd +"','"+ tels +"','"+ passwords + "')";
        String insertToRegister = insertField + insertValues;


        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            registerSuccess.setText("vous etes bien enregister!!");


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }


}

