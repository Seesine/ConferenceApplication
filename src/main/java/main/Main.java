package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.LoginControl;
import controller.ReviewerControl;
import database.Database;
import repository.AttendantRepository;
import repository.AuthorsRepository;
import repository.ComiteeRepository;
import repository.ReviewerRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Dragos on 4/4/2017.
 */

public class Main extends Application
{
    private FXMLLoader loader;
    private FXMLLoader loader2;
    private FXMLLoader loader3;
    private FXMLLoader loader4;
    private FXMLLoader loader5;

    private Stage primaryStage;
    private AnchorPane rootLayout1;
    private AnchorPane rootLayout2;
    private AnchorPane rootLayout3;
    private AnchorPane rootLayout4;
    private AnchorPane rootLayout5;

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private Scene scene5;
    private LoginControl controlLogin;

    private static void execute(String sql)
    {

    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        //sdfdsdfds
        this.primaryStage = primaryStage;
        loader = new FXMLLoader();
        loader2 = new FXMLLoader();
        loader3 = new FXMLLoader();
        loader4 = new FXMLLoader();
        loader5 = new FXMLLoader();

        try
        {
            String pathToFxml = "client/src/main/resources/LoginWindow.fxml";
            URL fxmlUrl = new File(pathToFxml).toURI().toURL();
            loader.setLocation(fxmlUrl);

            LoginControl controlLogin = new LoginControl();
            loader.setController(controlLogin);
            rootLayout1 = loader.load();
            scene1 = new Scene(rootLayout1);
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        try
        {
            String pathToFxml = "client/src/main/resources/MainWindow.fxml";
            URL fxmlUrl = new File(pathToFxml).toURI().toURL();
            loader2.setLocation(fxmlUrl);


            MainClient controlMain = new MainClient(server,this);
            loader2.setController(controlMain);
            rootLayout2 = loader2.load();
            scene2 = new Scene(rootLayout2);
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        LoginView();
    }

    public void authenticated()
    {
        MainView();
    }

    public void logOut() throws RemoteException
    {
        LoginView();
    }

    public void LoginView()
    {
        primaryStage.setScene(scene1);
        primaryStage.show();
        controlLogin.initManager(this);
    }

    private void ReviewerView()
    {
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    private void AuthorView()
    {
        primaryStage.setScene(scene3);
        primaryStage.show();
    }

    private void AdminView()
    {
        primaryStage.setScene(scene4);
        primaryStage.show();
    }

    private void CMView()
    {
        primaryStage.setScene(scene5);
        primaryStage.show();
    }
    private void AttendantView()
    {
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
}
