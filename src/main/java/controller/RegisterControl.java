package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 5/22/2017.
 */
public class RegisterControl
{
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private PasswordField repeatpassField;
    private Statement statement;
    private Connection connection;

    public void initialize()
    {
        String url = "jdbc:mysql://localhost:3306/cms";
        String user = "root";
        String password = "";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
            statement.execute("USE cms");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void setOnConfirm()
    {

        String userName = userField.getText();
        String password = passField.getText();
        String repeatpass = repeatpassField.getText();

        if (userName == null)
        {
            showErrorMessage("Dati un username");
        }
        else if (password == null)
        {
            showErrorMessage("Dati o parola");
        }
        else if (repeatpass == null)
        {
            showErrorMessage("Repetati parola");
        }
        else if (password.compareTo(repeatpass) != 0)
            {
                showErrorMessage("Parolele trebuie sa fie identice.");
            }
            else
                try {
                    String query = "insert into userdefault values (?, ?)";
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setString (1, userName);
                    preparedStmt.setString (2, password);
                    preparedStmt.execute();
                    connection.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

    }

    private static void showMessage(Alert.AlertType type)
    {
        Alert message=new Alert(type);
        message.setHeaderText("Succes");
        message.setContentText("Succes");
        message.showAndWait();
    }

    private static void showErrorMessage(String text)
    {
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
}
