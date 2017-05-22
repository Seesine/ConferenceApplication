package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
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
    private FileRepository fileRepo = new FileRepository();
    @FXML
    ComboBox<AcceptLevel> acceptCB = new ComboBox<>();
    @FXML
    private TableView<File> fileTable = new TableView<>();
    @FXML
    private TableColumn<String,File> tableC = new TableColumn<>();
    @FXML
    private TableColumn<String,File> tableC2 = new TableColumn<>();



    @FXML
    private Button reviewButton;
    @FXML
    private Button openFileBtn;
    public ObservableList<File> fileList = FXCollections.observableArrayList();
    final Main loginManager;

    public ReviewerControl(final Main loginManager)
    {
        this.loginManager = loginManager;
    }
    public void initData(){
        fileTable.getItems().clear();
        //fileTable.setEditable(true);

        //tableC2.setCellValueFactory(new PropertyValueFactory<String,File>("filedoc"));
        //fileTable.getColumns().addAll(tableC, tableC2);
        for(File f : fileRepo.getAll()) {
            fileList.add(f);
        }
        //asdf deletegfhg
        fileTable.setItems(fileList);
        acceptCB.getItems().addAll(AcceptLevel.values());
        openFileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    String website = fileTable.getSelectionModel().getSelectedItem().getFiledoc();
                    Desktop.getDesktop().browse(new URI(website));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                  catch(NullPointerException el){
                    showErrorMessage("Selectati ceva!");
                }
            }
        });
    }

    public void showErrorMessage(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.showAndWait();
    }
//    @FXML
//    private void openLink(ActionEvent event) {
//        String website = fileTable.getSelectionModel().getSelectedItem().getFiledoc();
//        openFileBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//               try {
//                   Desktop.getDesktop().browse(new URI(website));
//               } catch (IOException e1) {
//                   e1.printStackTrace();
//               } catch (URISyntaxException e1) {
//                   e1.printStackTrace();
//               }
//           }
//       }
//        );
//    }

    @FXML
    private void reviewHandle(ActionEvent event){
        String currentLevel = fileTable.getSelectionModel().getSelectedItem().getLevel();
        int currentNumber = fileTable.getSelectionModel().getSelectedItem().getReviewCount();
        int selectedFileId = fileTable.getSelectionModel().getSelectedItem().getIdF();
        File f = fileRepo.getById(selectedFileId);
        f.setLevel("Accept");
        //initData();
    }

    @FXML
    private void setLogoutAction(){

        loginManager.logOut();
    }

}
