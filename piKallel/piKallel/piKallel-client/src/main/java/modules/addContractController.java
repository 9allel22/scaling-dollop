package modules;

import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import services.CompaniesServiceRemote;
import services.ContractServiceRemote;
import services.UserServiceRemote;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import persistence.Company;
import persistence.Contract;
import persistence.User;

public class addContractController implements Initializable {

    @FXML
    private DatePicker d1;

    @FXML
    private DatePicker d2;

    @FXML
    private ComboBox userbox;

    @FXML
    private ComboBox companybox;

    @FXML
    private TextArea t1;

    @FXML
    private TextField t2;

   
    public void InitComp() {
      				
    }
    
    public static Stage window;
	private Screen screen = Screen.getPrimary();
	private Rectangle2D windows = screen.getVisualBounds();
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
       	
    	Context context = null;
		
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	UserServiceRemote serviceRemote = null;
	
		try {
			serviceRemote = (UserServiceRemote) context
					.lookup("theblizzards-ear/theblizzards-ejb/UserService!Service.UserServiceRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<User> l =serviceRemote.findAll();
		for (User c : l) {
			userbox.getItems().add(c.getIdUser());
		}
	
	
	try {
		Context context2 = new InitialContext();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CompaniesServiceRemote serviceRemote2 = null;
	try {
		serviceRemote2 = (CompaniesServiceRemote) context
				.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	List<Company> l2 =serviceRemote2.findAllCompanies();
	
	for (Company c1 : l2) {
		companybox.getItems().add(c1.getIdCompany());
	}
	
    }

    
    
    
    @FXML
    void addcon(ActionEvent event) throws NamingException {
    	
 
    	 LocalDate xmas = d1.getValue();
 		Instant instant = xmas.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
 		Date dd1=	(Date) Date.from(instant);
 		
 		
 		 LocalDate xmas2 = d2.getValue();
 			Instant instant2 = xmas2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
 			Date dd2=	(Date) Date.from(instant2);
    	

    	int a =  Integer.parseInt(t2.getText());
	
	  String st = t1.getText();
    		     

	 //Company c = (Company) companybox.getSelectionModel().getSelectedItem();
	 
    	
    	
    	
		if (dd1.after(dd2))    	{
	    	
    		Alert alert = new Alert(Alert.AlertType.WARNING);
		       alert.setHeaderText("Please A valid time ");
		       Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == ButtonType.OK){
		        
	    			
		        
		        
		        } 
		}else {
			

	    				Context context3 = new InitialContext();
	    		    	ContractServiceRemote serviceRemotecontra =

	    						(ContractServiceRemote) context3
	    								.lookup("theblizzards-ear/theblizzards-ejb/ContractService!Service.ContractServiceRemote");
	    		    	
	    		    	  int nbr = Integer.parseInt( userbox.getValue().toString());
	    		   	   int nbr2 = Integer.parseInt( companybox.getValue().toString());
	    		   	   
	    		   	   
	    		   	   
	    				 serviceRemotecontra.SigneContract (dd1, dd2, a, st, nbr ,nbr2); 
	    				
	    				 t1.setText("");
	    				 t2.setText("");
	    				 
	    				 
	    				 
	    				 
	    				Notifications notbuilder = Notifications.create();
	    				notbuilder.title("Contract Added ");
	    				notbuilder.text("Clic to show Contracts  ");
	    				notbuilder.graphic(null);
	    				notbuilder.hideAfter(Duration.seconds(7));
	    				notbuilder.position(Pos.BOTTOM_RIGHT);
	    				notbuilder.showConfirm();
	    				
		}
	    				
	    			
		
		 
		
		
		
		
    }
    


		

	
    
    

}
