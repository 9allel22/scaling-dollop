package modules;

import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.CompaniesServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import persistence.Company;

public class CompanyController {

    @FXML
    private TextField text_name;

    @FXML
    private TextField text_type;

    @FXML
    private Button b1;

    @FXML
    void addcompany(ActionEvent event) throws NamingException {

    	if (text_name.getText().equals("")&& text_type.getText().equals(""))    	{
    	
    		Alert alert = new Alert(Alert.AlertType.WARNING);
		       alert.setHeaderText("Please Set a good property ");
		       Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == ButtonType.OK){
		            
		        	text_name.setText("Set name here");
		        	text_type.setText("Set Type Here ");
		        	
		        	
	    			}
    		        
			
		}else {
			
			Context context = new InitialContext();
			
			CompaniesServiceRemote basicOpsRemote = (CompaniesServiceRemote) context
					.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");
		
			
			Company c =  new Company(text_name.getText(),text_type.getText());

			

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		       alert.setHeaderText("done with success");
		       Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == ButtonType.OK){
		            
		        	try {
	    				basicOpsRemote.addCompany(c);
	    		    	
	    			}catch (Exception e) {
	    				System.out.println(e);
	    			}
	    			   
		        	
		        	text_name.setText("");
		        	text_type.setText("");
		        }
			
		}
    	
    	
           
    	
    	
    }

    
    @FXML
    void aaa(KeyEvent evt) {
    }
     
    	
    	
    	
    
    
    
}
