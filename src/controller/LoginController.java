package controller;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import db.DbConnect;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {
    static  int session = 0;
    static int sessionApp = 0;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessage;

    @FXML
    private ImageView imgHeadView;
    @FXML
    private ImageView imgSimplonView;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File imgPathSimplon = new File("img/simplon.jpeg");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());
        imgSimplonView.setImage(imgPath);

        File imgPathCode = new File("img/code.png");
        Image imgPathHead = new Image(imgPathCode.toURI().toString());
        imgHeadView.setImage(imgPathHead);
    }

    public void LoginButtonOnAction(){
        if(!usernameField.getText().isBlank() &&
                !passwordField.getText().isBlank()){

            validateLogin();


        }else{
            loginMessage.setText("Veuiller entrer votre surnom et votre mot de passe!");

        }
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void toRegisterForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 550));
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void validateLogin(){

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String verifyLogin = "SELECT * FROM staff WHERE nomStaff = '"
                + usernameField.getText() +"' AND pswdStaff= '"+passwordField.getText()+"'";
//        System.out.println(verifyLogin);

        String verifyLoginApp = "SELECT * FROM apprenant WHERE nomApprenant = '"
                + usernameField.getText() +"' And passeword= '"+passwordField.getText()+"'";
        System.out.println(verifyLoginApp);
        // Student Login
        try {
            Statement statementApp = connectDb.createStatement();
            ResultSet queryResultApp = statementApp.executeQuery(verifyLoginApp);

            while(queryResultApp.next()){
                //System.out.println(usernameField.getText().length());
                //System.out.println(queryResultApp.getString("nomApprenant").length());
                //System.out.println(usernameField.getText().equals(queryResultApp.getString("nomApprenant")));

                if(queryResultApp.getString("nomApprenant").equals(usernameField.getText())){
                    loginMessage.setText("success");
                    sessionApp = queryResultApp.getInt("idApprenant");
                    System.out.println("session of student: "+ sessionApp);

                    createStudentPage();
                    remplirListeApprenant();
                }else{
                    loginMessage.setText("invalid mot de passe ou surnom, reessaye");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        // Staff Login
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getString("nomStaff").equals(usernameField.getText())){
                    loginMessage.setText("success");
                    session = queryResult.getInt("idStaff");
                    System.out.println("session of Staff"+session);
                    createStaffPage();
                    remplirListeApprenant();

                }else{
                    loginMessage.setText("invalid mot de passe ou surnom, reessaye");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createStudentPage(){
        try{

        Parent root = FXMLLoader.load(getClass().getResource("/view/student.fxml"));
        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 750, 400));
        registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createStaffPage(){
        try{

        Parent root = FXMLLoader.load(getClass().getResource("/view/staff.fxml"));
        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 1520, 1095));
        registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    /*public void createStaffPage(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/view/staff.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 400));
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public void remplirListeApprenant() {

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupApp = "select a.nomApprenant from apprenant a , promoapprenant p , " +
                "staff s where s.id_Promo = p.id_Promo AND s.idStaff ='"
                +session+ "' AND a.idApprenant = p.idApprenant";
//        System.out.println(recupApp);
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(recupApp);
            while(queryResult.next()){
                String x = queryResult.getString(1);
//                System.out.println(x);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
