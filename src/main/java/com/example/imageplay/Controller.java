package com.example.imageplay;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    static String str;
    @FXML
    public TextArea textAreaPane2;

    @FXML
    private Pane pane1;

    @FXML
    public ImageView imageView1Pane1;

    @FXML
    public ImageView imageView2Pane1;

    @FXML
    private HBox hbox;

    @FXML
    public Text imageWidth;

    @FXML
    public Text imageHeight;

    @FXML
    public Text imageProgress;

    public Image setImage;

    @FXML
    void displayimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/Users/dhruvinity/Documents/images"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile!=null) {
            Image image = new Image("file:///"+selectedFile.getAbsolutePath(), 100, 100, false, false);
            imageView1Pane1 = new javafx.scene.image.ImageView(image);
            imageView2Pane1=new ImageView(image);
            str="file:///"+selectedFile.getAbsolutePath();
            this.readPixelsInfo(image);
            imageView1Pane1.setFitWidth(300);
            imageView1Pane1.setFitHeight(300);
            imageView2Pane1.setImage(image);
            pane1.getChildren().add(imageView1Pane1);
            hbox.getChildren().add(imageView2Pane1);
        }
    }

    @FXML
    void readPixelsInfo(Image image){

        PixelReader pixelReader = image.getPixelReader();

        if(pixelReader == null){
            System.out.println("Cannot read the pixels from the image");
        }

        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        double progress = image.getProgress();

        this.writeInfoMessage("Image Width: " + width, "imageWidth");
        this.writeInfoMessage("Image Height: " + height, "imageHeight");
        this.writeInfoMessage("Progress: " + progress, "imageProgress");
    }

    private void writeInfoMessage(String msg, String type) {
        switch(type) {
            case "imageWidth":
                this.imageWidth.setText(msg);
                break;
            case "imageHeight":
                this.imageHeight.setText(msg);
                break;
            case "imageProgress":
                this.imageProgress.setText(msg);
                break;
        }
       // this.textAreaPane2.appendText(msg + "\n");
    }

    //     Change to edit scene
    public void changeScreenButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit_ui.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);

        //Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();

        Image image = new Image(new File(str).toString());
        System.out.println(image.getHeight());
        ControllerEdit controllerEdit=loader.getController();
        controllerEdit.setEditImage(image);

    }

    public void setImages(){
        setImage= new Image(str);
        System.out.println(setImage.getHeight());
        ImageView setImageView = new ImageView(setImage);

        setImageView.setFitWidth(248);
        setImageView.setFitHeight(367);
        setImageView.setLayoutX(59);
        setImageView.setLayoutY(9);
        setImageView.setImage(setImage);
        pane1.getChildren().add(setImageView);

    }
}
