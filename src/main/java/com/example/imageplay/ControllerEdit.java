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
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEdit  {

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
    public Button back;
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
        try {
            rotateButton.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent event) {
                    double value = imageView1.getRotate();
                    imageView1.setRotate(value + 30);
                }
            });
        } catch(Exception e) {
            System.out.println("onRotateButtonClicked");
        }


    }
    @FXML
    public void onFlipButtonClicked(ActionEvent event) {

        flipButton.setOnAction(new EventHandler<>() {
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
            case "boxBlur":
                BoxBlur boxBlur = new BoxBlur();
                boxBlur.setWidth(3);
                boxBlur.setHeight(3);
                boxBlur.setIterations(3);
                imageView1.setEffect(boxBlur);
                break;
            case "colorAdjust":
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setContrast(0.1);
                colorAdjust.setHue(-0.05);
                colorAdjust.setBrightness(0.1);
                colorAdjust.setSaturation(0.2);
                imageView1.setEffect(colorAdjust);
                break;
            case "dropShadow":
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(5.0);
                dropShadow.setOffsetX(10.0);
                dropShadow.setOffsetY(5.0);
                dropShadow.setColor(Color.GREY);
                imageView1.setEffect(dropShadow);
                break;
            case "innerShadow":
                InnerShadow innerShadow = new InnerShadow(5.0, 5.0, 5.0, Color.AZURE);
                imageView1.setEffect(innerShadow);
                break;
            case "motionBlur":
                MotionBlur motionBlur = new MotionBlur();
                motionBlur.setRadius(30);
                motionBlur.setAngle(-15.0);
                imageView1.setEffect(motionBlur);
                break;
            case "shadow":
                Shadow shadow = new Shadow(BlurType.THREE_PASS_BOX, Color.BLUE, 10.0);
                imageView1.setEffect(shadow);
                break;
            case "customMade" :
                PerspectiveTransform pTrans = new PerspectiveTransform(100, 110, 40, 160, 40, 94, 100, 106);
                Reflection ref = new Reflection();
                pTrans.setInput(ref);
                imageView1.setEffect(pTrans);
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }
    }

    @FXML
    public void choiceAspectRatioClicked(MouseEvent event) {
        choiceAspectRatio.getSelectionModel().selectedItemProperty().addListener((v,oldVal,newVal)-> aspectRatio(images,(String) newVal));
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
                imageView1.setFitHeight(110);
                imageView1.setImage(image);
                break;
            case "16:9":
                imageView1.setFitWidth(160);
                imageView1.setFitHeight(90);
                imageView1.setImage(image);
                break;
            case "7:5":
                imageView1.setFitWidth(70);
                imageView1.setFitHeight(50);
                imageView1.setImage(image);
                break;
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }

    }
    public void saveImageAction(MouseEvent mouseEvent) throws IOException {


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
        back.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("main.fxml"));
                try {
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
                } catch(Exception e) {
                    System.out.println("There was a problem loading the main.fxml file");
                }
            }


        });
    }

    public void cancelButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();
    }


}
