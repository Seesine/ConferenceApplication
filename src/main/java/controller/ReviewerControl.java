package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.File;
import repository.FileRepository;
import repository.ReviewerRepository;
import utils.AcceptLevel;

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



}
