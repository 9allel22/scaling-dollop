package modules;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.CompaniesServiceRemote;
import services.ContractServiceRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import persistence.Company;
import persistence.Contract;
import persistence.ContractPk;
import persistence.User;

public class ViewContractController  implements Initializable {

    @FXML
    private AnchorPane tbIDcarte;

    @FXML
    private TableView<Contract> TableContract;

    @FXML
    private TableColumn<Contract, Integer> idcompany;

    /*
    @FXML
    private TableColumn<Contract, Integer> iduser;
*/
    @FXML
    private TableColumn<Contract, String> description;

    @FXML
    private TableColumn<Contract, Date> enddate;

    @FXML
    private TableColumn<Contract, Integer> montant;

    @FXML
    private TableColumn<Contract, Date> startdate;

    @FXML
    private Label lbTitulo1;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private ToggleGroup menu;

    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		

       try {
		ShowItem();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       }
    
    
    
    
    @FXML
    void rechercherB(ActionEvent event) {

    
    	
    }

    @FXML
    void supprimerCarte(ActionEvent event) {

    	
  	  try {

            if (!TableContract.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Can not Delete Table is Empty");
                alert.setHeaderText("Delete that Company :  "
                        + TableContract.getSelectionModel().getSelectedItem().getContractPk());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                   
                	Context context = new InitialContext();
                	ContractServiceRemote serviceRemote =

            				(ContractServiceRemote) context
            						.lookup("theblizzards-ear/theblizzards-ejb/ContractService!Service.ContractServiceRemote");
                    
                	serviceRemote.deleteContract(TableContract.getSelectionModel().getSelectedItem());
            		ShowItem();
                }

            }
        } catch (Exception ex) {
            System.out.println("DATA can not be loaded " + ex.getMessage());

        }
    	
    	
    }

    
    public void ShowItem() throws NamingException {
    	
		Task<List<Contract>> task = new Task() {
			
			Context context = new InitialContext();
			
			ContractServiceRemote serviceRemote =

					(ContractServiceRemote) context
							.lookup("theblizzards-ear/theblizzards-ejb/ContractService!Service.ContractServiceRemote");

			
			@Override
			protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));

				List<Contract> compa =	serviceRemote.findAllContracts();			
				return compa;
			}
		};
		
		task.setOnSucceeded(e -> {
			
			//iduser.setCellValueFactory(new PropertyValueFactory<>("ContractPk"));
			idcompany.setCellValueFactory(new PropertyValueFactory<>("ContractPk"));
			
			description.setCellValueFactory(new PropertyValueFactory<>("description"));
			enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			montant.setCellValueFactory(new PropertyValueFactory<>("Montant"));
			startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));


			TableContract.setItems( 
					FXCollections.observableArrayList(task.getValue()));
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
}
