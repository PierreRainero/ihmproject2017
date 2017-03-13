package polytech.unice.si3.ihm.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class TextViewController extends BasicController{

    @FXML
    private Label title;

    @FXML
    private Label content;

    public void initialiseView(String title, String content, boolean centerText){
        this.currentStage.setTitle(title);
        this.title.setText(title);
        this.content.setText(content);
        if(centerText)this.content.setTextAlignment(TextAlignment.CENTER);
    }

}
