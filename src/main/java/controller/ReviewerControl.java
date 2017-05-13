package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.File;
import repository.FileRepository;
import repository.ReviewerRepository;
import utils.AcceptLevel;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ReviewerControl {
    private ReviewerRepository reviewRepo;
    private FileRepository fileRepo;
    @FXML
    ComboBox<AcceptLevel> acceptCB = new ComboBox<>();
    @FXML
    private TableView<File> fileTable;
    @FXML
    private Button reviewButton;
    private Button openFileBtn;
    private Button button2;

    public ReviewerControl(){
    }
    public void initData(){
        fileTable.getItems().clear();
        for(File f : fileRepo.getAll())
            fileTable.getItems().add(f);
        acceptCB.getItems().addAll(AcceptLevel.values());
    }

    @FXML
    private void openLink(ActionEvent event) {
        String website = fileTable.getSelectionModel().getSelectedItem().getFiledoc();
        openFileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               try {
                   Desktop.getDesktop().browse(new URI(website));
               } catch (IOException e1) {
                   e1.printStackTrace();
               } catch (URISyntaxException e1) {
                   e1.printStackTrace();
               }
           }
       }
        );
    }

    @FXML
    private void reviewHandle(ActionEvent event){
        AcceptLevel currentLevel = fileTable.getSelectionModel().getSelectedItem().getLevel();
        int currentNumber = fileTable.getSelectionModel().getSelectedItem().getReviewCount();
    }

}
