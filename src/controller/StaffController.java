package controller;


import db.DbConnect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static controller.LoginController.session;


public class StaffController implements Initializable {

    @FXML
    private BorderPane rootPane;


    @FXML
    public  Label NomCompApp;
    @FXML
    public Button app;

    @FXML
    public  HBox HbApp;
    @FXML
    public  VBox VbApp;
    @FXML
    public  AnchorPane AnchorListes;
    @FXML
    public  AnchorPane AnchorRef;
    @FXML
    public  AnchorPane AnchorPromo;
    @FXML
    public    Label promoLabel;
    @FXML
    public    Label refLabel;
    @FXML
    private ImageView imageIcon;
    @FXML
    private ImageView simplonIcon;
    @FXML
    private Label cancelButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        File imgPathProfile = new File("img/profile.png");
        Image imgPath = new Image(imgPathProfile.toURI().toString());
        imageIcon.setImage(imgPath);

        File imgPathSimplon = new File("img/simplo.png");
        Image imgPathP = new Image(imgPathSimplon.toURI().toString());
        simplonIcon.setImage(imgPathP);



        remplirPromoLabel();
        remplirListeApprenant();


    }

    int idApp = 0;



    public int parcourirCompAppNiv(int Appx , int Comp){
        int AppCompX =-1;
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        try {
            Statement st5 = connectDb.createStatement();
            ResultSet reqRefAppCompNiv = st5.executeQuery("SELECT idNiveau from compapprenant where idApprenant = "+Appx+" AND idComp = "+Comp+" ORDER BY idNiveau DESC LIMIT 1");
            while (reqRefAppCompNiv.next()){

               AppCompX = reqRefAppCompNiv.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return  AppCompX;

        }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void remplirListeApprenant() {
        int i = 0;

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupApp = "select a.nomApprenant ,a.prenomApprenant ,a.idApprenant from apprenant a , promoapprenant p , staff s where s.id_Promo = p.id_Promo AND s.idStaff ='"+session+"' AND a.idApprenant = p.idApprenant";
        System.out.println(recupApp);
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(recupApp);

            while (queryResult.next()) {
                i+=100;
                Button app1 = new Button();


                app1.setMinWidth(200);
                app1.setMinHeight(100);


                app1.setId(""+queryResult.getInt(3));
                System.out.println(queryResult.getString(1));
                app1.setText(queryResult.getString(1)+" "+queryResult.getString(2));


                app1.setLayoutY(i);
                app1.setLayoutX(100);
                AnchorListes.getChildren().add(app1);


                app1.setOnAction(
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println(Integer.parseInt(app1.getId()));
                                idApp = Integer.parseInt(app1.getId());
                                DbConnect connectNow = new DbConnect();
                                Connection connectDb = connectNow.getConnect();
                                String recupApp = "select a.nomApprenant ,a.prenomApprenant ,a.idApprenant from apprenant a , promoapprenant p , staff s where s.id_Promo = p.id_Promo AND s.idStaff ='"+session+"' AND a.idApprenant = p.idApprenant";
                                System.out.println(recupApp);
                                AnchorRef.getChildren().clear();
                                remplirCompetenceApp();


                                }
                            }
                );

                i += 30;
            }



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        try {
            connectDb.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void remplirPromoLabel() {

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();

        String recupPromo = "SELECT p.titre_Promo FROM promotion p ,staff s WHERE p.id_Promo = s.id_Promo AND s.idStaff="+session;
        try {
            Statement st1 = connectDb.createStatement();
            ResultSet reqPromo = st1.executeQuery(recupPromo);
            while (reqPromo.next()) {
                System.out.println(reqPromo.getString("titre_Promo"));
                promoLabel.setText(reqPromo.getString(1));


            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void remplirCompetenceApp(){
        //
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();


        int positionYHbox = 50;
        int cpt =0;
        int idRef = 0;
        int s= 10;
        int idComp =0;

        ScrollPane sc = new ScrollPane();

        try {
            Statement st3 = connectDb.createStatement();
            ResultSet reqRefApp = st3.executeQuery("SELECT DISTINCT ref.titreRef , ref.idRef from refereniel ref , apprenant a , promotion p  , promoapprenant pr WHERE pr.id_Promo = p.id_Promo AND ref.id_Promo = p.id_Promo AND a.idApprenant = pr.idApprenant AND a.idApprenant ="+idApp);

            while (reqRefApp.next()) {

                AnchorPane AnchorComp = new AnchorPane();
                AnchorComp.setMinWidth(1255);
                AnchorComp.setMinHeight(400);
                idRef = reqRefApp.getInt(2);

                Label RefApp = new Label();
                RefApp.setMinWidth(300);
                RefApp.setMinHeight(40);
                RefApp.setStyle("-fx-font-size: 28px");

                RefApp.setText(reqRefApp.getString(1));
                AnchorComp.getChildren().add(RefApp);
                AnchorComp.setLayoutY(s);
                AnchorComp.setLayoutX(0);



                try {
                    Statement st4 = connectDb.createStatement();
                    ResultSet reqComApp = st4.executeQuery("SELECT c.titreComp ,c.idComp from competence c ,compapprenant ca ,apprenant a , refereniel ref where ca.idApprenant = a.idApprenant And ca.idComp = c.idComp AND a.idApprenant ="+idApp+" AND ref.idRef = c.idRef AND ref.idRef = "+idRef);

                    while (reqComApp.next()){


                        idComp = reqComApp.getInt(2);
                        cpt++;

                        AnchorPane AnchorCompApp = new AnchorPane();
                        AnchorComp.setMinWidth(600);
                        AnchorComp.setMinHeight(250);

                        if(cpt%2 == 1){

                            Label Nomc = new Label();

                            Button b1 =new Button();
                            Button b2 =new Button();
                            Button b3 =new Button();
                            b1.setMinWidth(100);
                            b1.setMinHeight(20);
                            b2.setMinWidth(100);
                            b2.setMinHeight(20);
                            b3.setMinWidth(100);
                            b3.setMinHeight(20);
                            b1.setText("");
                            b2.setText("");
                            b3.setText("");
                            b1.setLayoutX(20);
                            b1.setLayoutY(40);
                            b2.setLayoutX(120);
                            b2.setLayoutY(40);
                            b3.setLayoutX(220);
                            b3.setLayoutY(40);

                            switch (parcourirCompAppNiv(idApp,idComp)){
                                case 1: {
                                    b1.setStyle("-fx-background-color: green");
                                    break;
                                }
                                case 2: {
                                    b1.setStyle("-fx-background-color: green");
                                    b2.setStyle("-fx-background-color: green");
                                    break;
                                }
                                case 3: {
                                    b1.setStyle("-fx-background-color: green");
                                    b2.setStyle("-fx-background-color: green");
                                    b3.setStyle("-fx-background-color: green");
                                    break;
                                }

                            }



                            Nomc.setText(reqComApp.getString(1));

                            AnchorCompApp.getChildren().addAll(Nomc,b1,b2,b3);

                            AnchorCompApp.setLayoutX(20);
                            AnchorCompApp.setLayoutY(positionYHbox);

                            AnchorComp.getChildren().add(AnchorCompApp);


                        }
                        else {

                            Label Nomc = new Label();

                            Button b1 =new Button();
                            Button b2 =new Button();
                            Button b3 =new Button();


                            b1.setMinWidth(100);
                            b1.setMinHeight(20);
                            b2.setMinWidth(100);
                            b2.setMinHeight(20);
                            b3.setMinWidth(100);
                            b3.setMinHeight(20);
                            b1.setText("");
                            b2.setText("");
                            b3.setText("");

                            b1.setLayoutX(20);
                            b1.setLayoutY(40);
                            b2.setLayoutX(120);
                            b2.setLayoutY(40);
                            b3.setLayoutX(220);
                            b3.setLayoutY(40);


                            AnchorCompApp.setLayoutX(620);
                            AnchorCompApp.setLayoutY(positionYHbox);

                            switch (parcourirCompAppNiv(idApp,idComp)){
                                case 1: {
                                    b1.setStyle("-fx-background-color: green");
                                    break;
                                }
                                case 2: {
                                    b1.setStyle("-fx-background-color: green");
                                    b2.setStyle("-fx-background-color: green");
                                    break;
                                }
                                case 3: {
                                    b1.setStyle("-fx-background-color: green");
                                    b2.setStyle("-fx-background-color: green");
                                    b3.setStyle("-fx-background-color: green");
                                    break;
                                }

                            }

                            Nomc.setText(reqComApp.getString(1));

                            AnchorCompApp.getChildren().add(Nomc);
                            AnchorCompApp.getChildren().add(b1);
                            AnchorCompApp.getChildren().add(b2);
                            AnchorCompApp.getChildren().add(b3);
                            AnchorComp.getChildren().add(AnchorCompApp);


                        }
                        positionYHbox +=50;
                    }
                    cpt =0;
                    //AnchorComp.getChildren().add(Vbox);
                    //st4.close();
                }catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }


                AnchorRef.getChildren().add(AnchorComp);
                s +=400;
                positionYHbox = 50;
            }

            //st3.close();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        sc.setContent(AnchorPromo);
    }
}

