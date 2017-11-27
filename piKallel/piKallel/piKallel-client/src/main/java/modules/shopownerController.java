/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class shopownerController implements Initializable {

    @FXML
    private ToggleButton gestioncompany;
    @FXML
    private ToggleButton AjouterCompany;
    @FXML
    private ToggleButton affichecontract;

    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane boxConteudo;

    @FXML
    private ImageView c;
    
    @FXML
    private ImageView helloworld;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	File f1= new File("src\\main\\java\\AdminFX\\img\\logo-TM-1.png");
    	Image image1 = new Image(f1.toURI().toString());
    	helloworld.setImage(image1);
    }    

    @FXML
    private void action_butt_click(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("CrudCompany.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }
    	
    	//add contract
    @FXML
    private void addcontract(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AddContract.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }


    @FXML
    private void showstat(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Stat.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }


    @FXML
    private void showstat2(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Winner.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }

    
    @FXML
    private void action_ajout_company(ActionEvent event) {

        try {
              
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionCompany.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            //Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
//ism methode gestion contact
    @FXML
    private void affiche_contract(ActionEvent event) {

        try {
              
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionContract.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            //Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    
    @FXML
    private void menu_CFidelité_shopowner(ActionEvent event) {
    	/*
          try {
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionCFideliteFXML.fxml"));
            boxConteudo.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
          */
          
    }

    @FXML
    private void menu_reclamation_shopowner(ActionEvent event) {
    }

    @FXML
    private void menu_offre_emploi_shopowner(ActionEvent event) {
    }

    @FXML
    private void subAjoutOffreEmploi_shopowner(ActionEvent event) {
    }

    @FXML
    private void subSupprimerOffreEmploi_shopowner(ActionEvent event) {
    }

    @FXML
    private void subModifierOffreEmploi_shopowner(ActionEvent event) {
    }

    @FXML
    private void subAfficherOffreEmploi_shopowner(ActionEvent event) {
    }

    @FXML
    private void menu_Publicite_shopowner(ActionEvent event) {
    }

    @FXML
    private void menu_Evenement_shopowner(ActionEvent event) {
    }

    @FXML
    private void menu_Forum_shopowner(ActionEvent event) {
    }

    @FXML
    private void CloseWindow(ActionEvent event) {
         Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void siteMuseu(ActionEvent event) {
    }
    
}
