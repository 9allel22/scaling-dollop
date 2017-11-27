package modules;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.ContractServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import persistence.Contract;

public class StatController implements Initializable  {

    @FXML
    private AnchorPane id_stat;

    @FXML
    private BarChart<?, ?> statXY;

    @FXML
    private CategoryAxis AxeX;

    @FXML
    private NumberAxis AxeY;
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		XYChart.Series set1 = new XYChart.Series<>();
		
		Context context = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ContractServiceRemote serviceRemote = null;
		try {
			serviceRemote = (ContractServiceRemote) context
					.lookup("theblizzards-ear/theblizzards-ejb/ContractService!Service.ContractServiceRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Contract> compa =	serviceRemote.findAllContracts();
		
		for (Contract contract : compa) {
			System.out.println(contract.getCompany().getCompanyName());
		
		
		set1.getData().add(new XYChart.Data(contract.getCompany().getCompanyName(),contract.getMontant()));
	
		}
		statXY.getData().addAll(set1);
		
		
		
		
		
	}

}
