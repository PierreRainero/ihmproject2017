package polytech.unice.si3.ihm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuController {
    protected Stage stage;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem reset;

    @FXML
    private MenuItem faq;

    @FXML
    private MenuItem aboutUs;

    @FXML
    private MenuItem jobMenu;

    @FXML
    private MenuItem enMenu;

    @FXML
    private MenuItem frMenu;

    public void loadStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
        stage.close();
    }

    @FXML
    void displayFaq(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FAQ");
        alert.setHeaderText("Foire aux questions");
        alert.setContentText("   Quels sont les horaires d'ouverture du centre commercial ?\n" +
                "   Le centre est ouvert du lundi au samedi, de 10 à 20h, et le dimanche de 11 à 19h.\n\n" +
                "   Je ne reçois pas de newsletter. Que faire pour les recevoir ?\n" +
                "   Ces newsletters vous sont adressées à l’adresse e-mail que vous avez renseignée " +
                "lors de votre inscription au programme. Vérifiez que les e-mails ne sont pas dans " +
                "votre dossier SPAM ou vérifiez sur votre compte client que vous avez renseigné la bonne adresse e-mail.");

        alert.showAndWait();
    }

    @FXML
    void displayJobs(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Espace emploi");
        alert.setHeaderText("Recherche vendeur/vendeuse (CDD");
        alert.setContentText("   Rattaché(e) au Responsable du Magasin, vous travaillez en équipe " +
                "avec pour objectif commun le développement du chiffre d'affaires et la fidélisation du client.\n" +
                "   Vos missions consistent à :\n" +
                "- Accueillir et conseiller nos clients en garantissant l'image de notre enseigne,\n" +
                "- Participer à la mise en place d'opérations commerciales et au merchandising, \n" +
                "- Participer à la mise en place des nouvelles collections en veillant à la bonne tenue du magasin, \n" +
                "- Contribuer activement à l'augmentation du chiffre d'affaires du magasin.\n\n" +
                "Enseigne : Mellow Yellow\n" +
                "Contrat : CDD de 35H\n" +
                "Disponibilité : dès le 01/04/2017");
        alert.showAndWait();
    }

    @FXML
    void displayMoreAboutUs(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact");
        alert.setHeaderText("Contact et localisation");
        alert.setContentText("CENTRE COMMERCIAL CAP3000\n" +
                "Avenue Eugène Donadeï\n" +
                "06700 Saint-Laurent du Var\n" +
                "Tel : 04.93.31.10.35");

        alert.showAndWait();
    }

    @FXML
    void translateInEnglish(ActionEvent event) {

    }

    @FXML
    void translateInFrench(ActionEvent event) {

    }
}
