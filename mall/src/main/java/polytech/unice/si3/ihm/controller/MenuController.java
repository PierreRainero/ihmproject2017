package polytech.unice.si3.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import polytech.unice.si3.ihm.MainApp;

/**
 * Manages the interactions with the menu of all the scenes.
 *
 * @author Guillaume Casagrande
 */
public class MenuController {
    /**
     * Used to change the scene.
     */
    protected MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Closes the stage in the main application.
     */
    @FXML
    void closeWindow() {
        mainApp.getStage().close();
    }

    /**
     * Creates a popup with questions and their answers.
     */
    @FXML
    void displayFaq() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FAQ");
        alert.setHeaderText("Foire aux questions");
        alert.setContentText(" -  Quels sont les horaires d'ouverture du centre commercial ?\n" +
                "   Agorazur est ouvert du lundi au samedi, de 10 à 20h, et le dimanche de 11 à 19h.\n\n" +
                " -  Je ne reçois pas de newsletter. Que faire pour les recevoir ?\n" +
                "   Ces newsletters vous sont adressées à l’adresse e-mail que vous avez renseignée " +
                "lors de votre inscription au programme. Vérifiez que les e-mails ne sont pas dans " +
                "votre dossier SPAM ou vérifiez sur votre compte client que vous avez renseigné la bonne adresse e-mail.");

        alert.showAndWait();
    }

    /**
     * Creates a popup with a proposition of job at the mall.
     */
    @FXML
    void displayJobs() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Espace emploi");
        alert.setHeaderText("Recherche vendeur/vendeuse (CDD)");
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

    /**
     * Creates a popup which gives information about the mall.
     */
    @FXML
    void displayMoreAboutUs() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact");
        alert.setHeaderText("Contact et localisation");
        alert.setContentText("CENTRE COMMERCIAL AGORAZUR\n" +
                "Avenue Guiken Roulacasa\n" +
                "06140 Biot\n" +
                "Tel : 04.93.31.10.35");

        alert.showAndWait();
    }

    /**
     * Translates the interface in english.
     */
    @FXML
    void translateInEnglish() {

    }

    /**
     * Translates the interface in french.
     */
    @FXML
    void translateInFrench() {

    }
}
