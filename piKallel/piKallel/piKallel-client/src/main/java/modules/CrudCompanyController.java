package modules;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



import services.CompaniesServiceRemote;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import persistence.Company;

public class CrudCompanyController implements Initializable {

    @FXML
    private AnchorPane tbIDcarte;

    @FXML
    private TableView<Company> TableCompany;

    @FXML
    private TableColumn<Company, String> Company_Name;

    @FXML
    private TableColumn<Company, String> Company_Offre;

    @FXML
    private Label lbTitulo1;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private ToggleGroup menu;

    @FXML
    private TextField Companyname1;

    @FXML
    private ComboBox<String> TypeOffre;

    @FXML
    void modifier(ActionEvent event) {

       	
  	  try {

            if (!TableCompany.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Can not Update Table is Empty");
                alert.setHeaderText("Update that Company :  "
                        + TableCompany.getSelectionModel().getSelectedItem().getIdCompany()+" with name : "+TableCompany.getSelectionModel().getSelectedItem().getCompanyName());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                   
              	  Context context = new InitialContext();
            		CompaniesServiceRemote companyserviceRemote =

            				(CompaniesServiceRemote) context
            						.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");

                    
            	 Company c = companyserviceRemote.findCompanyById(TableCompany.getSelectionModel().getSelectedItem().getIdCompany());
            	 c.setCompanyName(Companyname1.getText());
            	 String aa = TypeOffre.getValue().toString();
            	 c.setOffreType(aa);
            	 companyserviceRemote.updateCompany(c);
            		ShowItem();
                }

            }
        } catch (Exception ex) {
            System.out.println("erreur lors du chargement des forums " + ex.getMessage());

        }
    	
    }

    @FXML
    void rechercherB(ActionEvent event) throws NamingException {
    	
  		
  		ShowItembyname(txtPesquisar.getText());
  		
  		
    }

    @FXML
    void supprimerCarte(ActionEvent event) {

    	
    	  try {

              if (!TableCompany.getSelectionModel().isEmpty()) {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                  alert.setTitle("Can not Delete Table is Empty");
                  alert.setHeaderText("Delete that Company :  "
                          + TableCompany.getSelectionModel().getSelectedItem().getIdCompany()+" with name : "+TableCompany.getSelectionModel().getSelectedItem().getCompanyName());
                  Optional<ButtonType> result = alert.showAndWait();
                  if (result.get() == ButtonType.OK) {
                     
                	  Context context = new InitialContext();
              		CompaniesServiceRemote companyserviceRemote =

              				(CompaniesServiceRemote) context
              						.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");

                      
              		companyserviceRemote.deleteCompanyById(TableCompany.getSelectionModel().getSelectedItem().getIdCompany());
              		ShowItem();
                  }

              }
          } catch (Exception ex) {
              System.out.println("erreur lors du chargement des forums " + ex.getMessage());

          }

    	
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Full Covered ",
			        "Half Covered",
			        "Full Covered With Equipments "
			    );
        TypeOffre.setItems(options);

       try {
		ShowItem();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       }



	
    public void ShowItem() throws NamingException {
		Task<List<Company>> task = new Task() {
			Context context = new InitialContext();
			CompaniesServiceRemote companyserviceRemote =

					(CompaniesServiceRemote) context
							.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");


			@Override
			protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));

				List<Company> compa = companyserviceRemote.findAllCompanies();
				return compa;
			}
		};
		task.setOnSucceeded(e -> {
			
			Company_Name.setCellValueFactory(new PropertyValueFactory<>("companyName"));

			Company_Offre.setCellValueFactory(new PropertyValueFactory<>("offreType"));
			

			TableCompany.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			try {
				ShowItem();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		Thread th = new Thread(task);
		th.start();
	}
    
    
    public void ShowItembyname(String name) throws NamingException {
		Task<List<Company>> task = new Task() {
			Context context = new InitialContext();
			CompaniesServiceRemote companyserviceRemote =

					(CompaniesServiceRemote) context
							.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");


			@Override
			protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));

				List<Company> compa = companyserviceRemote.findCompanyBYName(name);
				return compa;
			}
		};
		task.setOnSucceeded(e -> {
			
			Company_Name.setCellValueFactory(new PropertyValueFactory<>("companyName"));

			Company_Offre.setCellValueFactory(new PropertyValueFactory<>("offreType"));
			

			TableCompany.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			try {
				ShowItem();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		Thread th = new Thread(task);
		th.start();
	}
    
    @FXML
    void setTextTo(MouseEvent event) {
    	Companyname1.setText(TableCompany.getSelectionModel().getSelectedItem().getCompanyName());
    
    }
    
    
    @FXML
    void ajax(KeyEvent event) throws NamingException {
    	ShowItembyname(txtPesquisar.getText());
    	
    }
    
    
    
    
}
