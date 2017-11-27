package modules;




import com.sun.nio.sctp.Notification;

import services.UserServiceRemote;
import services.WinnerManagementRemote;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import java.net.URL;
//import java.sql.Date;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import persistence.User;
import persistence.Winner;

import static sun.text.normalizer.NormalizerImpl.convert;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;




public class WinnerController implements Initializable {

    @FXML
    private Button youssef;
    @FXML
    private TextField wintxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
    
    /*
  public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
  */
  
  

    @FXML
    private void randombutt(ActionEvent event) throws ParseException {  
    	
    	
Context context2 = null;
		
		try {
			context2 = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	WinnerManagementRemote win = null;
	
		try {
			win = (WinnerManagementRemote) context2
					.lookup("theblizzards-ear/theblizzards-ejb/WinnerManagement!Service.WinnerManagementRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    	
    	

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
        
        
        
        
        
        User user = serviceRemote.findById(3);//(win.winnerOfTheDay());
        
        
        
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        System.out.println(dateFormat.format(cal.getTime()));


            Winner pp = new Winner(dateFormat.format(cal.getTime()) , user.getIdUser() );
 
   
    String DD = win.maxwinnerdate(); 
    
    
    int MAXWINN = DD.length();
    
    
    
    String bb = dateFormat.format(cal.getTime());  
    int AUTRE = bb.length(); 
        System.out.println("bb"+AUTRE);
        System.out.println("dd"+MAXWINN);
      ////  String carac = bb+"";
      ///  int lasthope = carac.length();
      ////  System.out.println(lasthope);
        System.out.println("ahmedkkkkkkkkkkkkkk"+DD);
        System.out.println("aaaaaaaaaaaaaaaaaaa"+dateFormat.format(cal.getTime()));
  if (bb.equals(DD))
  {
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("sorry");
                alert.setHeaderText("we have already have a winner for today");
                Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK) {
                 wintxt.clear();
                     
                 }
   }   
  else {
       System.out.println("lllllllllllllllllllllllllllllllllllllllllllll");
       win.add(pp);
         wintxt.setText("The winner of the day is: \n"+user.toString());
  
    
  }
  
  


   
    }
}
    
