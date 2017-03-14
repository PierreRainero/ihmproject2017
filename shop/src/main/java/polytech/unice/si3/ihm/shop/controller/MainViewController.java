package polytech.unice.si3.ihm.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.model.SuperType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainViewController extends BasicController {

    private Product carouselLeft;
    private Product carouselRight;
    private Product carouselCenter;

    @FXML
    private ImageView logo;
    @FXML
    private ImageView nom;
    @FXML
    private ImageView carouselLeftImage;
    @FXML
    private Label carouselLeftName;
    @FXML
    private ImageView carouselCenterImage;

    @FXML
    private Label carouselCenterName;
    @FXML
    private ImageView carouselRightImage;
    @FXML
    private Label carouselRightName;
    @FXML
    private TextField searchBar;
    @FXML
    private HBox carouselContainer;
    @FXML
    private BorderPane shopContents;
    @FXML
    private VBox filters;
    @FXML
    private ComboBox filterChoice;
    @FXML
    private Label sortLabel;
    @FXML
    private Button about;
    @FXML
    private Button legalNotice;
    @FXML
    private Button adress;
    @FXML
    private ImageView promoFirstImage;
    @FXML
    private ImageView promoSecondImage;
    @FXML
    private ImageView promoThirdImage;
    @FXML
    private ImageView promoFourthImage;
    @FXML
    private ImageView promoFifthImage;
    @FXML
    private Label promoFirstText;
    @FXML
    private Label promoSecondText;
    @FXML
    private Label promoThirdText;
    @FXML
    private Label promoFourthText;
    @FXML
    private Label promoFifthText;

    /**
     * Initialise la vue de base du MainViewController
     * @param shop magasin dont on à la gestion
     */
    public void initialiseView(Shop shop){
        this.logo.setImage(new Image(shop.getLogoMin()));
        this.nom.setImage(new Image(shop.getLogoText()));

        filterChoice.setVisible(false);
        sortLabel.setVisible(false);

        this.searchBar.setOnKeyReleased(event -> {
            if(shopContents.getCenter().toString().contains("carouselContainer")){
                switchToResearch(shop);
            }
            createList(shop);
            event.consume();
        });

        this.about.setOnAction(event -> {
            try {
                openTextView("Qui sommes nous", shop.getAbout(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.legalNotice.setOnAction(event -> {
            try {
                openTextView("Mentions légales", shop.getLegalNotice(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.adress.setOnAction(event -> {
            try {
                openTextView("Où nous trouver", shop.getAdress(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.logo.setOnMouseClicked(event ->{
            this.currentStage.close();
        });

        this.nom.setOnMouseClicked(event ->{

        });
    }

    private void openTextView(String title, String content, boolean centerText) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/text_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 500,400);
        scene.getStylesheets().add("/styles/main.css");
        stage.setScene(scene);

        TextViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(title, content, centerText);
        stage.show();
    }

    private void openProductView(Product product) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/product_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 500,600);
        scene.getStylesheets().add("/styles/main.css");
        stage.setScene(scene);

        ProductViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(product);
        stage.show();
    }

    /**
     * Crée les différents éléments relatifs à la recherche et à l'usage de filtres
     * @param shop magasin dont on à la gestion
     */
    public void switchToResearch(Shop shop){
        this.filters = createFilters(shop);

        filterChoice.setVisible(true);
        sortLabel.setVisible(true);
        sortLabel.setText("Trier par : ");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Prix : par ordre croissant","Prix : par ordre décroissant","Popularité"
                );
        filterChoice.setValue("Popularité");
        filterChoice.setItems(options);
        filterChoice.setOnAction(event -> {
            createList(shop);
        });
    }

    /**
     * Affiche sur la gauche de l'écran différents filtres. Il s'agit de tous les filtres ayant été trouvés dans
     * les types des objets présents dans le json.
     * @param shop
     * @return
     */
    private VBox createFilters(Shop shop){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPrefWidth(200);
        Label label = new Label("Choisissez un type de produit : ");
        vBox.getChildren().add(label);

        for(SuperType superType : shop.getTypes()){
            CheckBox checkBox = new CheckBox();
            checkBox.setText(superType.getName());
            checkBox.setFont(new Font(15.0));
            vBox.getChildren().add(checkBox);
            checkBox.setTranslateX(5);
            checkBox.setOnMouseClicked(event -> {
                createList(shop);
            });

            for(String str : superType.getTypes()){
                CheckBox checkBox1 = new CheckBox();
                checkBox1.setFont(new Font(10.0));
                checkBox1.setText(str);
                checkBox1.setTranslateX(25);
                vBox.getChildren().add(checkBox1);
                checkBox1.setOnMouseClicked(event -> {
                    createList(shop);
                });
            }
        }


        return vBox;
    }

    /**
     * Gère les paramètres relatifs à la barre de recherche et filtres en recréant à chaque fois l'arborescence d'objets
     * complète et ensuite supprimant les objets non concernés.
     * @param shop magasin dont on à la gestion
     */
    public void createList(Shop shop){
        VBox vBox = displayAllShopProducts(shop);
        vBox = deleteNonSearchCorrespondingElements(vBox, shop, searchBar.getCharacters().toString());
        vBox = deleteNonFilteredCorrespondingElements(vBox, shop, filters);
        /*vBox = *///applySort(vBox, shop);
        ScrollPane sc = new ScrollPane();
        sc.setContent(vBox);

        shopContents.setLeft(filters);
        shopContents.setCenter(sc);
    }

    /**
     * Création d'une VBox contenant tous les produits dans le shop en question.
     * @param shop magasin dont on à la gestion
     * @return VBox contenant chaque produit présent dans le magasin
     */
    private VBox displayAllShopProducts(Shop shop){
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        switch (filterChoice.getValue().toString()){
            case "Popularité":
                shop.sortByPopularity();
                break;
            case "Prix : par ordre croissant":
                shop.sortByCroissantPrice();
                break;
            case "Prix : par ordre décroissant":
                shop.sortByCroissantPrice();
                break;
            default:
                break;
        }

        List<Product> products = shop.getProducts();

        for(int i=0;i<products.size();i++){
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
            VBox vBox1 = new VBox();
            vBox1.setPrefWidth(500);

            ImageView productPicture = new ImageView(products.get(i).getImageURL());
            productPicture.setPreserveRatio(true);
            productPicture.setFitWidth(150);

            Label gameDescription = new Label(products.get(i).getDescription());
            gameDescription.setWrapText(true);
            gameDescription.setMaxHeight(150);

            Label price = new Label("Prix: " + products.get(i).getPrice() + " €");

            if(products.get(i).getPromotion()!=0){
                price = new Label("Promotion : " + products.get(i).getPromotedPrice() + " €" + "\nau lieu de " + products.get(i).getPrice() + "€\n(-" + products.get(i).getPromotion() + "%)"  );
            }

            price.setFont(new Font(25.0));
            price.setPrefWidth(400);

            Label name = new Label(products.get(i).getName());
            name.setFont(new Font(30.0));

            vBox1.getChildren().addAll(name, gameDescription);

            hBox.getChildren().addAll(productPicture, vBox1, price);

            Product product = products.get(i);
            hBox.setOnMouseClicked(event -> {
                try {
                    openProductView(product);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            hBox.setOnMouseEntered(event -> mouseEntered(event));

            hBox.setOnMouseExited(event -> mouseExited(event));

            vBox.getChildren().add(hBox);
        }

        return vBox;
    }

    /**
     * Suprimme de l'affichage des produits les produits qui ne correspondent pas à ce qui est écrit
     * dans la barre de recherche.
     * @param gamesDisplayed vertical box contenant les différents jeux
     * @param shop magasin que l'on gère
     * @param searchBar barre de recherche
     * @return vBox à laquelle l'on retire les éléments ne correspondants pas
     */
    private VBox deleteNonSearchCorrespondingElements(VBox gamesDisplayed, Shop shop, String searchBar) {
        searchBar = searchBar.toLowerCase();
        List<Product> products = shop.getProducts();

        int i = 0;
        for (Product product : products) {
            if (!product.getName().toLowerCase().contains(searchBar)) {
                gamesDisplayed.getChildren().remove(i);
                i--;
            }
            i++;
        }
        return gamesDisplayed;
    }

    /**
     * Suprimme de l'affichage des produits les produits qui ne correspondent pas à au moins un des filtres sélectionnés
     * @param gamesDisplayed vertical box contenant les différents restants à afficher
     * @param shop magasin que l'on gère
     * @param filters VBox contenant les différentes checkbox présentes faisant office de filtres
     * @return
     */
    private VBox deleteNonFilteredCorrespondingElements(VBox gamesDisplayed, Shop shop, VBox filters){
        for(int i=1;i<filters.getChildren().size();i++){
            CheckBox checkBox = (CheckBox) filters.getChildren().get(i);
            if(checkBox.isSelected()){
                removeElements(gamesDisplayed, checkBox.getText(), shop);
            }
        }
        return gamesDisplayed;
    }

    /**
     * Retire les produits ne correspondant pas
     * @param gamesDisplayed vertical box contenant les différents restants à afficher
     * @param checkBoxName nom de la checkbox
     * @param shop magasin que l'on gère
     */
    private void removeElements(VBox gamesDisplayed, String checkBoxName, Shop shop){
        List<Integer> toRemove = new ArrayList<>();

        for(int j=0; j<gamesDisplayed.getChildren().size();j++){
            HBox hBox = (HBox) gamesDisplayed.getChildren().get(j);
            for(int k=0;k<hBox.getChildren().size();k++){
                if(hBox.getChildren().get(k).getClass().equals(new VBox().getClass())){
                    VBox vBox = (VBox) hBox.getChildren().get(k);
                    Label name = (Label) vBox.getChildren().get(0);
                    Product product = shop.getProduct(name.getText());
                    int index = searchOccurence(product.getProductType(), checkBoxName);
                    if(index==-1){
                        toRemove.add(j);
                    }
                }
            }
        }
        for(int i=gamesDisplayed.getChildren().size();i>=0;i--){
            if(toRemove.contains(i))
                gamesDisplayed.getChildren().remove(i);
        }
    }

    /**
     * Recherche si un produit précis est contenu dans les types et sous types de l'objet
     * @param productType List contenant les types de l'objet
     * @param checkBoxText nom checkbox
     * @return
     */
    private int searchOccurence(List<SuperType> productType, String checkBoxText){
        int i=0;
        for(SuperType st : productType){
            if(st.getName().equals(checkBoxText))
                return i;
            for(String str : st.getTypes()){
                if(str.equals(checkBoxText))
                    return i;
            }
            i++;
        }
        return -1;
    }



    /**
     * Permets l'affichage des trois meilleurs produits (ceux le plus vendu dans le magasin)
     * @param shop magasin contenant les différents produits
     */
    public void initialiseCarousel(Shop shop){
        Product centerProduct = null;
        Product leftProduct = null;
        Product rightProduct = null;

        List<Product> topSales = shop.getTopSales();
        for(int i=0;i<topSales.size();i++){
            switch (i){
                case 0:
                    centerProduct = topSales.get(i);
                case 1:
                    leftProduct = topSales.get(i);
                case 2:
                    rightProduct = topSales.get(i);
            }
        }

        this.carouselLeft = leftProduct;
        this.carouselLeftImage.setImage(new Image(leftProduct.getImageURL()));
        this.carouselLeftName.setText(leftProduct.getName());

        this.carouselCenter = centerProduct;
        this.carouselCenterImage.setImage(new Image(centerProduct.getImageURL()));
        this.carouselCenterName.setText(centerProduct.getName());

        this.carouselRight = rightProduct;
        this.carouselRightImage.setImage(new Image(rightProduct.getImageURL()));
        this.carouselRightName.setText(rightProduct.getName());

        carouselCenterImage.setOnMouseClicked(event -> {
            try {
                openProductView(carouselCenter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        carouselCenterImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselCenterImage.setOnMouseExited(event -> {
            mouseExited(event);
        });

        carouselLeftImage.setOnMouseClicked(event -> {
            try {
                openProductView(carouselLeft);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        carouselLeftImage.setOnMouseEntered(event -> mouseEntered(event));

        carouselLeftImage.setOnMouseExited(event -> mouseExited(event));

        carouselRightImage.setOnMouseClicked(event -> {
            try {
                openProductView(carouselRight);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        carouselRightImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselRightImage.setOnMouseExited(event -> {
            mouseExited(event);
        });

        logo.setOnMouseEntered(event -> mouseEntered(event));

        logo.setOnMouseExited(event -> mouseExited(event));

        nom.setOnMouseEntered(event -> mouseEntered(event));

        nom.setOnMouseExited(event -> mouseExited(event));
    }

    public void initializePromotions(Shop shop){
        List<Product> productsInPromo = new ArrayList<>();

        List<Product> topSales = shop.getTopSales();
        for(int i=0;i<topSales.size();i++){
            if(topSales.get(i).getPromotion() != 0)
                productsInPromo.add(topSales.get(i));
        }

        if(productsInPromo.size() > 0) {
            this.promoFirstImage.setImage(new Image(productsInPromo.get(0).getImageURL()));
            this.promoFirstText.setText("-" + productsInPromo.get(0).getPromotion() + "%");
            this.promoFirstImage.setOnMouseClicked(event -> {
                try {
                    openProductView(productsInPromo.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        if(productsInPromo.size() > 1) {
            this.promoSecondImage.setImage(new Image(productsInPromo.get(1).getImageURL()));
            this.promoSecondText.setText("-" + productsInPromo.get(1).getPromotion() + "%");
            this.promoSecondImage.setOnMouseClicked(event -> {
                try {
                    openProductView(productsInPromo.get(1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        if(productsInPromo.size() > 2) {
            this.promoThirdImage.setImage(new Image(productsInPromo.get(2).getImageURL()));
            this.promoThirdText.setText("-" + productsInPromo.get(2).getPromotion() + "%");
            this.promoThirdImage.setOnMouseClicked(event -> {
                try {
                    openProductView(productsInPromo.get(2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        if(productsInPromo.size() > 3) {
            this.promoFourthImage.setImage(new Image(productsInPromo.get(3).getImageURL()));
            this.promoFourthText.setText("-" + productsInPromo.get(3).getPromotion() + "%");
            this.promoFourthImage.setOnMouseClicked(event -> {
                try {
                    openProductView(productsInPromo.get(3));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        if(productsInPromo.size() > 4) {
            this.promoFifthImage.setImage(new Image(productsInPromo.get(4).getImageURL()));
            this.promoFifthText.setText("-" + productsInPromo.get(4).getPromotion() + "%");
            this.promoFifthImage.setOnMouseClicked(event -> {
                try {
                    openProductView(productsInPromo.get(4));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
