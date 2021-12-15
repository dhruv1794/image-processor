package com.example.imageplay;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControllerSave {

    @FXML
    ChoiceBox formatChoice;

    @FXML
    TextField fileName;
    @FXML
    private Button saveBtn;

    ImageView preserveImageView ;

    com.example.imageplay.ControllerEdit edit = new com.example.imageplay.ControllerEdit();


    public void choiceFormat(javafx.scene.input.MouseEvent mouseEvent) {
        getFormatChoiceValue();
    }

    public  String getFormatChoiceValue(){
        return (String)formatChoice.getValue();
    }

    public void fileName(MouseEvent actionEvent){
        System.out.println(getFileName()+" File name ");
        getFileName();
    }

    public String getFileName(){
        return fileName.getText();
    }

    public void buttonAction(ActionEvent event) throws IOException {

        saveImage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("final_ui.fxml"));

        Parent saveView = loader.load();
        Scene save = new Scene(saveView);

        //Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(save);
        window.show();

    }

    public void saveImage(){

        preserveImageView= com.example.imageplay.ControllerEdit.editView;
        System.out.println(preserveImageView.getImage().getHeight());

        String format = this.getFormatChoiceValue();
        System.out.println("Choice Format is "+this.getFormatChoiceValue());

        String fileName = this.getFileName();
        System.out.println("File Name is "+ this.getFileName());

        File outputFile = new File("/Users/dhruvinity/IdeaProjects/imagePlay/snaps/"+fileName+format);
        BufferedImage bImage = SwingFXUtils.fromFXImage(preserveImageView.snapshot(null, null),null);

        try {
            ImageIO.write(bImage,"png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ImageSaved");

    }

}