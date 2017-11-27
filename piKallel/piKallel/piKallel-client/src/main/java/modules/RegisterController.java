/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import persistence.Accomodation;
import persistence.Event;
import persistence.User;
import services.EventManagementRemote;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author danml
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField txtFname;
    @FXML
    private JFXTextArea detailev;
    @FXML
    private JFXTextField txtLoc;
    @FXML
    private JFXTextField txtCapacity;
    @FXML
    private JFXDatePicker start;
    @FXML
    private JFXDatePicker end;
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    private static double progress6 = 0;
    private static double progress7 = 0;
    private static double progress8 = 0;
    private static double progress9 = 0;
    private static double progress10 = 0;
    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXRadioButton rdFemale;
    private ObservableList<String> station_lists;
    @FXML
    private JFXComboBox comboAcco;
    @FXML
    private JFXComboBox<String> comboReg;
    @FXML
    private JFXRadioButton rdGreen;
    @FXML
    private ToggleGroup level;
    @FXML
    private JFXRadioButton rdBlue;
    @FXML
    private JFXRadioButton rdRed;
    @FXML
    private TextField currentTimeTextfield;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXTextField txtMobile111;
    @FXML
    private Label lblComplete;
    private Connection connection;
    private PreparedStatement pst;
    @FXML
    private JFXComboBox<String> comboStation;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnSave;
    @FXML
    private AnchorPane parentPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 		try {
    	Context context;

    	context = new InitialContext();
 		String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

 		EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);
 
 		
 		//combo imbrique
 		comboReg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
 		{
 			public void changed(ObservableValue<? extends String> ov,
 					final String oldvalue, final String newvalue)
 			{
 				if (newvalue.equals("Alpes du Nord")) {
 					comboStation.getItems().clear();
 					comboStation.getItems().add("Albiez montrond");
 					comboStation.getItems().add("Alpe d'huez");
 					comboStation.getItems().add("Areches beaufort");
 					comboStation.getItems().add("Aussois");
 					comboStation.getItems().add("Avoriaz");
 					comboStation.getItems().add("Bonneval sur arc");
 					comboStation.getItems().add("Bride les bains");
 					
 					
 				}
 				else if(newvalue.equals("Alpes du Sud")){
 					comboStation.getItems().clear();
 					comboStation.getItems().add("Chabanon selonnet");
 					comboStation.getItems().add("Isola 200");
 					comboStation.getItems().add("Les orres");
 					}
 				else if(newvalue.equals("Auvergne")){
 					comboStation.getItems().clear();
 					comboStation.getItems().add("Super Besse");
 					}
 				else if(newvalue.equals("Pyrénnées")){
 					comboStation.getItems().clear();
 					comboStation.getItems().add("Ax les Termes");
 					comboStation.getItems().add("Font Romenu");
 					comboStation.getItems().add("La Mongie");
 					comboStation.getItems().add("Les Angles");


 					
 					}
 				else if(newvalue.equals("Vosges Jura")){
 					comboStation.getItems().clear();
 					comboStation.getItems().add("Gérardmer");
 					comboStation.getItems().add("La Bresse");

 					}
 		}});
 	
 	List<Accomodation> a=eventManagementRemote.findAllAccomodation();
 	for (Accomodation acco : a) {
    	comboAcco.getItems().add(acco.getName());

	}


    comboReg.getItems().addAll("Alpes du Nord","Alpes du Sud","Auvergne","Pyrénnées","Vosges Jura");
 		//setStationToCombo();
 		}catch (Exception e) {
			// TODO: handle exception
		}

    	
    	//action button save ajout
        btnSave.setOnAction(event -> {
            
        	
     		Context context;
 			try {
 				context = new InitialContext();
 	    		String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

 	    		EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

 			
     						

     		Event evennement = new Event();
     		evennement.setName(txtFname.getText());
     		evennement.setLocation(comboReg.getSelectionModel().getSelectedItem());
     
     		evennement.setStation(comboStation.getSelectionModel().getSelectedItem());
     		LocalDate dt= start.getValue();
     		Instant instant= dt.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
     		evennement.setStartDate(Date.from(instant));
     		
     		LocalDate dt2= end.getValue();
     		Instant instant2= dt2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
     		evennement.setEndDate(Date.from(instant2));
     		
     		evennement.setDescriptionEvent(detailev.getText());
     		evennement.setPiste(getLevel());

     		User user=eventManagementRemote.findUserById(1);
     		evennement.setUser(user);
     		evennement.setCapacity(Integer.valueOf(txtCapacity.getText()));
     		evennement.setPriceEvent(Integer.valueOf(txtPrice.getText()));
     
     		
     		Alert alert = new Alert(AlertType.CONFIRMATION);
     		alert.setTitle("event addition");
     		String s = "Confirm to add event !";
     		alert.setContentText(s);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setResizable(true);


     		Optional<ButtonType> result = alert.showAndWait();

     		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
     			

         		eventManagementRemote.addEvent(evennement);
         		 Alert alert2 = new Alert(AlertType.INFORMATION);
                 alert2.setTitle("Information Dialog");
                 alert2.setHeaderText(null);
                 alert2.setContentText("Event sucsessfully added!");
                 alert2.initStyle(StageStyle.UNDECORATED);

                 alert2.show();
                 clearFields();
                 
          


     		}
     	
     		
 			} catch (NamingException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
        
 			
 			//tray notification
 			  String title = "Event successfuly added";
 		        String message = "If you want to cancel an event please visit your event list";
 		        NotificationType notification = NotificationType.SUCCESS;
 		        TrayNotification tray = new TrayNotification();
 		        tray.setTitle(title);
 		        tray.setMessage(message);
 		        tray.setNotificationType(notification);
 		        tray.setAnimationType(AnimationType.SLIDE);
 		      tray.showAndWait();
 		        tray.dismiss();
 		        
 		    
 	
 	           
 	            
 }
        
        		
        		
        		);

        
        
    


    	
    	
     //   handler = new DbHandler();
        updateProgress();
      //  setDepartmentsToCombo();
    }

    private void updateProgress() {
    	start.setDayCellFactory(picker-> new DateCell() {
    		@Override
    		public void updateItem(LocalDate date,boolean empty) {
    			super.updateItem(date, empty);
    			setDisable(empty || LocalDate.now().isAfter(date.minusDays(7)));
    			
    		}
    	});
    	end.setDayCellFactory(picker-> new DateCell() {
    		@Override
    		public void updateItem(LocalDate date,boolean empty) {
    			super.updateItem(date, empty);
    			setDisable(empty || date.isBefore(start.valueProperty().get()));
    			
    		}
    	});
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        progressPersonal.setProgress(0.00);       
        double sum_progress = progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9;

        txtFname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.1;

                } else {
                    progress1 = 0.0;

                }

                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

     
        start.valueProperty().addListener((ov,oldValue,newValue)->{
        	if (!(newValue == null)) {
                    progress2 = 0.1;

                } else {
                    progress2 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            
        });

        comboReg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.1;

                } else {
                    progress3 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        txtCapacity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 1) {
                	System.out.println(!newValue.isEmpty());
                    progress4 = 0.1;

                } else {
                    progress4 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        
   

        end.valueProperty().addListener((ov,oldValue,newValue)->{
        	if (!(newValue == null)) {
                progress5 = 0.1;
               

            } else {
                progress5 = 0.0;

            }
            double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
            progressPersonal.setProgress(sum);
            lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
        });
      
        //Course name
        comboAcco.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                	
                    progress6 = 0.1;

                } else {
                    progress6 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        // Amount paid
        txtPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress7 = 0.1;

                } else {
                    progress7 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        
        
        
        detailev.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress8 = 0.1;

                } else {
                    progress8 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });


        //Levels Radio buttons
        rdGreen.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        rdBlue.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        rdRed.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");

            }
        });

        comboStation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue,
                    String newValue) {
                if (!newValue.isEmpty()) {
                    progress10 = 0.1;
                } else {
                    progress10 = 0.0;
                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

    }




 

    @FXML
    public void clearFields() {
        txtPrice.setText(null);
        comboAcco.setValue(null);
        start.setValue(null);
        txtFname.setText(null);
        end.setValue(null);
        txtCapacity.setText(null);
        detailev.setText("");
        comboStation.setValue(null);
        comboReg.setValue(null);
        rdBlue.setSelected(false);
        rdGreen.setSelected(false);
        rdRed.setSelected(false);
        progressPersonal.setProgress(0.00);
        lblComplete.setText("0% complete");
        progress1=0.0;
        progress2=0.0;
        progress3=0.0;
        progress4=0.0;
        progress5=0.0;
        progress6=0.0;
        progress7=0.0;
        progress8=0.0;
        progress9=0.0;
        progress10=0.0;



    }

    @FXML
    private void editStudent(ActionEvent event) {
    }

    @FXML
    private void saveEvent(ActionEvent event)  {
              }

    

    private String getLevel() {
        String lvl = "";
        if (rdRed.isSelected()) {
            lvl = "Red";
        } else if (rdGreen.isSelected()) {
            lvl = "Green";
        } else if (rdBlue.isSelected()) {
            lvl = "Blue";
        }
        return lvl;

    }

}