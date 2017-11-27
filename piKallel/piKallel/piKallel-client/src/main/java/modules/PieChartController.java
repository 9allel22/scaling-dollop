package modules;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.CompaniesServiceRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import persistence.Company;

public class PieChartController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList<Data> pieChartData=FXCollections.
            observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			Context context = new InitialContext();
			CompaniesServiceRemote companyserviceRemote =

					(CompaniesServiceRemote) context
							.lookup("theblizzards-ear/theblizzards-ejb/CompaniesService!Service.CompaniesServiceRemote");
			
			List<Company> compa = companyserviceRemote.findAllCompanies();
			
			ObservableList<PieChart.Data> lis =FXCollections.observableArrayList();

			companyserviceRemote.findAllCompanies().stream().collect(Collectors.groupingBy
					(f->f.getCompanyName() , Collectors.counting())).forEach((t,count)->lis.add(new PieChart.Data(t, count)));
			piechart.setData(lis);      
			piechart.setAnimated(true);
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	



		
		piechart.setData(pieChartData);
		
		piechart.setStartAngle(90);
		
		
		
		
		/*
		public class CategoryStatController implements Initializable  {
			@FXML
			private PieChart pi;
			ObservableList<Data> list=FXCollections.
		            observableArrayList();

			@Override
			public void initialize(URL location, ResourceBundle resources) {
				Context context = null;
				try {
					
					context = new InitialContext();
					ItemServiceRemote itemServicesRemote = (ItemServiceRemote)context.lookup
					("ItemManagment-ear/ItemManagment-ejb/ItemService!tn.esprit.ItemManagment.services.ItemServiceRemote");
					List<Item> pers = null;
					pers = itemServicesRemote.findAllProducts();
					ObservableList<PieChart.Data> lis =FXCollections.observableArrayList();
					itemServicesRemote.findAllProducts().stream().collect(Collectors.groupingBy(f->f.getCategory(), Collectors.counting())).forEach((t,count)->lis.add(new PieChart.Data(t, count)));
				        pi.setData(lis);      
				        pi.setAnimated(true);
				} catch (NamingException e) {
					e.printStackTrace();
				}
				
					
				
			
				
				
			}
			
			@FXML
			public void goBack(ActionEvent event)  {
				

		    try {
		            Parent page2 = FXMLLoader.load(getClass().getResource("/ItemTest/Test.fxml"));
		            Scene scene = new Scene(page2);
		            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            stage.setScene(scene);
		            stage.show();
		        } catch (IOException ex) {
		        }
				
			
			}

		}*/
		
		
	}

    
    
    
    
}
