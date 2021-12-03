package com.example.imageplay;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class ControllerEdit  {

    //   String st[]= {"Sepia","Bloom","Glow","Blend"};
    String str;
    @FXML
    Slider slider;
    @FXML
    javafx.scene.image.ImageView imageView1 = new javafx.scene.image.ImageView();
    static ImageView editView;
    @FXML
    ChoiceBox  choices;
    @FXML
    ChoiceBox  choiceAspectRatio;
    @FXML
    public Image images;
    @FXML
    public Button rotateButton;
    @FXML
    public Button flipButton;
    @FXML
    private Pane pane1;
    @FXML
    void setEditImage(Image image) {
        images = image;
        imageView1.setImage(image);
        pane1.getChildren().add(imageView1);
    }
    @FXML
    public void onChoiceClicked(MouseEvent event) {

        String  value;
        choices.getSelectionModel().selectedItemProperty()
                .addListener((v,oldVal,newVal)-> effect(images,(String) newVal));


    }
    @FXML
    public void onRotateButtonClicked(ActionEvent event) {

        rotateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double value=imageView1.getRotate();
                imageView1.setRotate(value+30);
            }
        });

    }
    @FXML
    public void onFlipButtonClicked(ActionEvent event) {

        flipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double value=imageView1.getRotate();
                imageView1.setRotate(value+180);
            }
        });

    }

    public void effect(Image image,String value){

        imageView1.setImage(image);
        switch(value){
            case "Sepia": SepiaTone sepiaTone = new SepiaTone();
                imageView1.setEffect(sepiaTone);
                break;
            case "Bloom": Bloom bloomView = new Bloom();
                imageView1.setEffect(bloomView);
                break;
            case "Glow":  Glow glowView = new Glow();
                imageView1.setEffect(glowView);
                break;
            case "Blend": Blend blendView = new Blend();
                imageView1.setEffect(blendView);
                break;
            case "GaussianBlur":  GaussianBlur gaussianBlurView= new GaussianBlur();
                imageView1.setEffect(gaussianBlurView);
                break;
            case "Lighting":    Lighting lightingView = new Lighting();
                imageView1.setEffect(lightingView);
                break;
            case "Reflection":  Reflection reflectionView = new Reflection();
                imageView1.setEffect(reflectionView);
                break;
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }

    }

    @FXML
    public void choiceAspectRatioClicked(MouseEvent event) {


        choiceAspectRatio.getSelectionModel().selectedItemProperty()
                .addListener((v,oldVal,newVal)-> aspectRatio(images,(String) newVal));


    }
    public void aspectRatio(Image image,String value){


        switch(value){
            case "Square":
                imageView1.setFitWidth(100);
                imageView1.setFitHeight(100);
                imageView1.setImage(image);
                break;
            case "Wide":
                imageView1.setFitWidth(200);
                imageView1.setFitHeight(300);
                imageView1.setImage(image);
                break;
            case "16:9":
                imageView1.setFitWidth(160);
                imageView1.setFitHeight(90);
                imageView1.setImage(image);
                break;
            case "7:5":
                imageView1.setFitWidth(70);
                imageView1.setFitHeight(5);
                imageView1.setImage(image);
                break;
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }

    }
    public void saveImageAction(MouseEvent mouseEvent) throws IOException {

        System.out.println("This is when save button pressed and image height is:"+imageView1.getImage().getHeight());
        ControllerEdit.editView = imageView1;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("save_ui.fxml"));

        Parent saveView = loader.load();
        Scene save = new Scene(saveView);

        //Stage Information
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(save);
        window.show();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));

        Parent displayView = loader.load();
        Scene display = new Scene(displayView);

        //Stage Information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(display);
        window.show();

        Controller controller=loader.getController();
        controller.setImages();
        Image image=controller.setImage;
        controller.readPixelsInfo(image);
    }

    public void cancelButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editUI.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);

        //Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();

        FXMLLoader loaders = new FXMLLoader();
        loaders.setLocation(getClass().getResource("sample.fxml"));

        Controller controller= loaders.getController();

        Image image = new Image(new File(controller.str).toString());
        ControllerEdit controllerEdit=loader.getController();
        controllerEdit.setEditImage(image);
    }


}
