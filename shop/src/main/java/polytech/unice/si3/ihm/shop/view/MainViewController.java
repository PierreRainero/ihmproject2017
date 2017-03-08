package polytech.unice.si3.ihm.shop.view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import polytech.unice.si3.ihm.shop.MainApp;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;

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

    /**
     * Initialise la vue de base du MainViewController
     * @param shop magasin dont on à la gestion
     */
    public void initialiseView(Shop shop){
        this.logo.setImage(new Image(shop.getLogoMin()));
        this.nom.setImage(new Image(shop.getLogoText()));

        this.searchBar.setOnKeyReleased(event -> {
            if(shopContents.getCenter().toString().contains("carouselContainer")){
                switchToResearch(shop);
            }

            createList(shop);

            event.consume();
        });

    }

    /**
     * Crée les différents éléments relatifs à la recherche et à l'usage de filtres
     * @param shop magasin dont on à la gestion
     */
    public void switchToResearch(Shop shop){
        int childrens = carouselContainer.getChildren().size();

        for(int i=0;i<childrens;i++){
            carouselContainer.getChildren().remove(0);
        }

        this.filters = createFilters(shop);
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
        Label label = new Label("Affiner par : ");
        vBox.getChildren().add(label);
        for(int i=0;i<shop.getTypes().size();i++){
            CheckBox checkBox = new CheckBox();
            checkBox.setText(shop.getTypes().get(i));
            vBox.getChildren().add(checkBox);
            checkBox.setOnMouseClicked(event -> {
                createList(shop);
            });
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
        List<Product> products = shop.getProducts();
        for(int i=0;i<products.size();i++){
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            VBox vBox1 = new VBox();
            vBox1.setPrefWidth(500);

            ImageView productPicture = new ImageView(products.get(i).getImageURL());
            productPicture.setFitHeight(200);
            productPicture.setFitWidth(150);

            Label gameDescription = new Label(products.get(i).getDescription());
            gameDescription.setWrapText(true);
            gameDescription.setMaxHeight(150);

            Label price = new Label("Prix : " + products.get(i).getPrice() + " €");
            price.setFont(new Font(35.0));
            price.setPrefWidth(300);

            Label name = new Label(products.get(i).getName());
            name.setFont(new Font(30.0));

            vBox1.getChildren().addAll(name, gameDescription);

            hBox.getChildren().addAll(productPicture, vBox1, price);

            Product product = products.get(i);
            hBox.setOnMouseClicked(event -> {
                try {
                    MainApp.show(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            hBox.setOnMouseEntered(event -> {
                mouseEntered(event);
            });

            hBox.setOnMouseExited(event -> {
                mouseExited(event);
            });

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

        List<Integer> toRemove = new ArrayList<>();
        List<Integer> toKeep = new ArrayList<>();

        for(int i=1;i<filters.getChildren().size();i++){
            CheckBox checkBox = (CheckBox) filters.getChildren().get(i);
            if(checkBox.selectedProperty().get()){
                for(int j=0; j<gamesDisplayed.getChildren().size();j++){
                    HBox hBox = (HBox) gamesDisplayed.getChildren().get(j);
                    for(int k=0;k<hBox.getChildren().size();k++){
                        if(hBox.getChildren().get(k).getClass().equals(new VBox().getClass())){
                            VBox vBox = (VBox) hBox.getChildren().get(k);
                            Label name = (Label) vBox.getChildren().get(0);
                            Product product = shop.getProduct(name.getText());
                            if(product.getProductType().contains(checkBox.getText())){
                                toKeep.add(j);
                            }else
                                toRemove.add(j);
                        }
                    }
                }

            }
        }
        toRemove.removeAll(toKeep);
        for(int i=gamesDisplayed.getChildren().size();i>=0;i--){
            if(toRemove.contains(i))
                gamesDisplayed.getChildren().remove(i);
        }

        return gamesDisplayed;
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
                MainApp.show(carouselCenter);
            } catch (Exception e) {
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
                MainApp.show(carouselLeft);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        carouselLeftImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselLeftImage.setOnMouseExited(event -> {
            mouseExited(event);
        });

        carouselRightImage.setOnMouseClicked(event -> {
            try {
                MainApp.show(carouselRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        carouselRightImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselRightImage.setOnMouseExited(event -> {
            mouseExited(event);
        });
    }
}
