package polytech.unice.si3.ihm.firm.managing.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import polytech.unice.si3.ihm.firm.common.model.commercial.Product;
import polytech.unice.si3.ihm.firm.managing.content.ReductionPercentage;
import polytech.unice.si3.ihm.firm.managing.json.JsonGeneratorProduct;

import java.util.ArrayList;
import java.util.List;


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



    private ReductionPercentage reduction = new ReductionPercentage();

    private List<TextField> mustBeCompleted;

    @FXML
    /**
     * {@inheritDoc}
     */
    public void initialize(){

        initializeErrors();
        initializeRadioButton();
        initializeTextField();

        productPromoteNoButton.setSelected(true);

        mustBeCompleted = new ArrayList<>();
        mustBeCompleted.add(productNameTextField);
        mustBeCompleted.add(productPriceTextField);
        mustBeCompleted.add(productReferenceTextField);



    }

    /**
     * Method that make the errors disappear for the first launch
     */
    private void initializeErrors(){
        productPromoteChoiceMissing.setVisible(false);
        productNameMissing.setVisible(false);
        productPriceMissing.setVisible(false);
        productReferenceMissing.setVisible(false);
        productImageSelected.setVisible(false);
        newPriceAfterReduction.setVisible(false);
    }



    private void initializeRadioButton(){
        ToggleGroup group = new ToggleGroup();
        productPromoteNoButton.setToggleGroup(group);
        productPromoteYesButton.setToggleGroup(group);
    }

    private void initializeTextField(){
        productReduction.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    productReduction.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        productNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()){
                    productNameMissing.setVisible(false);
                }
                else {
                    productNameMissing.setVisible(true);
                }
            }
        });
        productPriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()){
                    productPriceMissing.setVisible(false);
                }
                else {
                    productPriceMissing.setVisible(true);
                }
            }
        });

        productReferenceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()){
                    productReferenceMissing.setVisible(false);
                }
                else{
                    productReferenceMissing.setVisible(true);
                }
            }
        });
    }


    @FXML
    void searchComputer(MouseEvent event) {

    }


    @FXML
    void calculatePrice(KeyEvent event) {

        newPriceAfterReduction.setVisible(true);
        if (productPriceTextField.getText().trim().isEmpty()) {
            productPriceMissing.setVisible(true);

        }
        else if (productReduction.getText().trim().equals("")) {
            newPriceAfterReduction.setText("Nouveau prix : " + productPriceTextField.getText());
        }
        else {
            int price = Integer.parseInt(productPriceTextField.getText());
            int percentage = Integer.parseInt(productReduction.getText());
            newPriceAfterReduction.setText("Nouveau prix : " + reduction.calculatePrice(price, percentage));

        }

    }

    @FXML
    void validateFormular(MouseEvent event) {

        if (allValidated()){
            Product product = new Product();
            if (productReduction.getText().trim().isEmpty() || productReduction.getText().trim().equals("")){
                product = new Product(productNameTextField.getText(),
                        productReferenceTextField.getText(),
                        reduction.calculatePrice(Double.parseDouble(productPriceTextField.getText()),0));
            }else {
                product = new Product(productNameTextField.getText(),
                        productReferenceTextField.getText(),
                        reduction.calculatePrice(Double.parseDouble(productPriceTextField.getText()), Double.parseDouble(productReduction.getText())));
            }
            JsonGeneratorProduct gen = new JsonGeneratorProduct(product);
        }
        if (allValidated() && (!productDescriptionTextArea.getText().trim().isEmpty() || !productDescriptionTextArea.getText().trim().equals(""))){

        }



    }

    /**
     * Checks if all the obligatory text fields are not empty to create the product
     * @return true if any of them is empty
     */
    private boolean allValidated(){
        int size = 3;
        if (mustBeCompleted.get(0).getText().trim().equals("")||mustBeCompleted.get(0).getText().trim().isEmpty()){
            productNameMissing.setVisible(true);
            size--;
        }
        else {
            productNameMissing.setVisible(false);
        }
        if (mustBeCompleted.get(1).getText().trim().isEmpty() || mustBeCompleted.get(1).getText().trim().equals("")){
            productPriceMissing.setVisible(true);
            size--;
        }
        else{
            productPriceMissing.setVisible(false);
        }
        if (mustBeCompleted.get(2).getText().trim().isEmpty() || mustBeCompleted.get(2).getText().trim().equals("")){
            productReferenceMissing.setVisible(true);
            size--;
        }
        else{
            productReferenceMissing.setVisible(false);
        }
        if (size==3) return true;
        return false;
    }




}