package at.tugraz.sw20asd.lang.ui.Controller;


import at.tugraz.sw20asd.lang.ui.VocabularyAccess;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

import javafx.scene.paint.Color;

public class StudyInterface extends VBox {

    @FXML
    private Label from_label;
    @FXML
    private Label to_label;
    @FXML
    private Label category_label;
    @FXML
    private Label category_name;
    @FXML
    private Label vocab1;
    @FXML
    private Label vocab2;
    @FXML
    private Button return_btn;
    @FXML
    private Label user_info;
    @FXML
    private Button next_btn;
    @FXML
    private Button hide_src;
    @FXML
    private Button hide_target;


    private VocabularyAccess vocab;
    private boolean source_flag = true;
    private boolean target_flag = true;

    private ObservableList<String> vocabulary_list = FXCollections.observableArrayList();
    FXMLLoader loader = new FXMLLoader();

    public StudyInterface(VocabularyAccess vocab){

        this.vocab = vocab;
        URL location = getClass().getResource("/study_interface.fxml");
        loader.setControllerFactory(c -> this);
        loader.setRoot(this);
        try {
            loader.load(location.openStream());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void initialize(){

        category_name.setVisible(false);
        user_info.setVisible(false);
        ObservableList<String> languages =
                FXCollections.observableArrayList("German", "English");

        //TODO replace from and to label to the respective language chosen in StudyVocab
        //TODO list the Vocabs

        hide_src.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(source_flag){
                    if(vocab2.isVisible()) {
                        vocab1.setVisible(false);
                        hide_src.setText("Show Source");
                        source_flag = false;
                        user_info.setVisible(false);
                    }
                    else{
                        updateUserInformation("hide_both");
                    }
                }
                else{
                    vocab1.setVisible(true);
                    hide_src.setText("Hide Source");
                    source_flag = true;
                }

            }
        });

        hide_target.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(target_flag){
                    if(vocab1.isVisible()) {
                        vocab2.setVisible(false);
                        hide_target.setText("Show Target");
                        target_flag = false;
                        user_info.setVisible(false);
                    }
                    else{
                        updateUserInformation("hide_both");
                    }
                }
                else{
                    vocab2.setVisible(true);
                    hide_target.setText("Hide Target");
                    target_flag = true;

                }

            }
        });

        next_btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //TODO change vocab1 and vocab2 to next vocab
            }
        });

//        return_btn.setOnAction(new EventHandler<ActionEvent>() {
////
////            public void handle(ActionEvent event) {
////                StudyVocab studyVocab = new StudyVocab(vocab);
////                getScene().setRoot(studyVocab);
////            }
//        });

    }


    private void updateUserInformation(String code){
        user_info.setVisible(true);
        user_info.setTextFill(Color.RED);
        switch (code){
            case "hide_both":
                user_info.setText("Not possible to hide both languages!");
                break;
            default:
                user_info.setText("Sorry, something went wrong");
        }
    }

}