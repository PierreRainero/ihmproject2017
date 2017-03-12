package polytech.unice.si3.ihm.firm.managing.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class AddProductController {

    @FXML
    private TextField productNameTextField;


    @FXML
    private TextField productReferenceTextField;


    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productReduction;

    @FXML
    private TextArea productDescriptionTextArea;

    @FXML
    private Button productImageSelectionButton;

    @FXML
    private RadioButton productPromoteYesButton;

    @FXML
    private RadioButton productPromoteNoButton;


    @FXML
    private Label newPriceAfterReduction;

    @FXML
    private Label productPromoteChoiceMissing;

    @FXML
    private Label productNameMissing;

    @FXML
    private Label productPriceMissing;

    @FXML
    private Label productReferenceMissing;

    @FXML
    private Label productImageSelected;

    @FXML
    /**
     * {@inheritDoc}
     */
    public void initialize(){

        initializeErrors();
        initializeRadioButton();



    }

    /**
     * Method that make the errors disappear for the first launch
     */
    public void initializeErrors(){
        productPromoteChoiceMissing.setVisible(false);
        productNameMissing.setVisible(false);
        productPriceMissing.setVisible(false);
        productReferenceMissing.setVisible(false);
        productImageSelected.setVisible(false);
        newPriceAfterReduction.setVisible(false);
    }



    public void initializeRadioButton(){
        ToggleGroup group = new ToggleGroup();
        productPromoteNoButton.setToggleGroup(group);
        productPromoteYesButton.setToggleGroup(group);
    }


    @FXML
    void searchComputer(MouseEvent event) {

    }


    @FXML
    void calculatePrice(ActionEvent event) {

    }


}