package sample;

import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
        Parent root = loader.load();
        RegisterController myController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //Set Data to FXML through controller
        myController.promoChoice();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
