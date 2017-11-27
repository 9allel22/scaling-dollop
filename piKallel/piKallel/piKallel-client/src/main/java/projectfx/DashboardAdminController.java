/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfx;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class DashboardAdminController implements Initializable {

    private Label lblDash;
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane sideAnchor;
    @FXML
    private Label lblMenu;
    @FXML
    private JFXToolbar toolBar;
    @FXML
    private HBox toolBarRight;
    @FXML
    private VBox overflowContainer;
    @FXML
    private ToggleButton menuHome;
    @FXML
    private ToggleButton menuAdd;
    @FXML
    private ToggleButton menuList;
    @FXML
    private ToggleButton menuLogg;

    private AnchorPane home, add, list;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnView;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnClose;
    @FXML
    private ToggleButton gestioncompany;
    @FXML
    private ToggleButton AjouterCompany;
    @FXML
    private ToggleButton affichecontract;

    @FXML
    private AnchorPane boxConteudo;

    @FXML
    private ImageView c;
    
    @FXML
    private ImageView helloworld;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXRippler fXRippler = new JFXRippler(lblDash);
        JFXRippler fXRippler2 = new JFXRippler(lblMenu);
        fXRippler2.setMaskType((JFXRippler.RipplerMask.RECT));
        sideAnchor.getChildren().add(fXRippler);
        toolBarRight.getChildren().add(fXRippler2);
     //   openMenus();
        createPages();

    }

  /* private void openMenus() {
        JFXPopup popup = new JFXPopup();
        popup.setContent(overflowContainer);
        popup.setPopupContainer(stackPane);
        popup.setSource(lblMenu);
        lblMenu.setOnMouseClicked((MouseEvent e) -> {
            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -10, 40);
        });

    } */

    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    //Load all fxml files to a cahce for swapping
    private void createPages() {
        try {
            home = FXMLLoader.load(getClass().getResource("/modules/OverviewAdmin.fxml"));
            list = FXMLLoader.load(getClass().getResource("/modules/ProfileAdmin.fxml"));

            //set up default node on page load
            setNode(home);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openHome(ActionEvent event) {
        setNode(home);
    }

    @FXML
    private void openAddStudent(ActionEvent event) {
        setNode(add);
    }

    @FXML
    private void openListStudent(ActionEvent event) {
        setNode(list);
    }

    @FXML
    private void logOff(ActionEvent event) {

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    
    @FXML
    private void action_butt_click(ActionEvent event) {
    	
    	 try {
             
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/CrudCompany.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }
    	
    	//add contract
    @FXML
    private void addcontract(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/AddContract.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }


    @FXML
    private void showstat(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/Stat.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }


    @FXML
    private void showstat2(ActionEvent event) {
    	
    	 try {
             
             boxConteudo.getChildren().clear();
             Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/Winner.fxml"));
         
             boxConteudo.getChildren().add(newLoadedPane);
         } catch (IOException ex) {
          
        	 
         }
     }

    
    @FXML
    private void action_ajout_company(ActionEvent event) {

        try {
              
            boxConteudo.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/GestionCompany.fxml"));
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
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/modules/GestionContract.fxml"));
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
