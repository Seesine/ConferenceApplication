package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import main.Main;
import model.Conference;
import repository.ConfRepository;
import model.Sections;

import javax.swing.text.TableView;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AttendantControl
{
    final Main loginManager;
    private ConfRepository confRepo = new ConfRepository();
    public AttendantControl(final Main loginManager) {
        this.loginManager = loginManager;
        //this.confRepo = confRepo;
    }
    @FXML
    public void setLogoutAction()
    {
        loginManager.logOut();
    }

    @FXML
    ComboBox<Conference> conferenceComboBox = new ComboBox<>();

    public void initialize(){
        conferenceComboBox.getItems().clear();
        ObservableList<Conference> obs = FXCollections.observableArrayList(confRepo.getAll());
        conferenceComboBox.setItems(obs);
        //conferenceComboBox.getItems().addAll(confRepo.getAll());
        conferenceComboBox.setPromptText("Conferinta");
        conferenceComboBox.setEditable(true);
    }
}
