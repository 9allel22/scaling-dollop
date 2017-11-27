/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import persistence.Event;
import services.EventManagementRemote;

/**
 * FXML Controller class
 *
 * @author Kallel
 */
public class OverviewAdminController implements Initializable {

	@FXML
	private HBox groupHolder;
	@FXML
	private JFXButton btnSearch;
	@FXML
	private Group groupRegistered;
	@FXML
	private Group groupTarget;
	@FXML
	private Group groupGents;
	@FXML
	private JFXComboBox<String> comboReg1;
	@FXML
	private JFXComboBox<String> comboStation1;
	@FXML
	private Group groupLadies;
	@FXML
	private ObservableList<String> departments;
	@FXML
	private Button btnAddDepartment;
	@FXML
	private TableView<Event> _tableEvents;
	@FXML
	private TableColumn _name;
	@FXML
	private TableColumn _location;
	@FXML
	private TableColumn _capacity;
	@FXML
	private TableColumn _startdate;
	@FXML
	private TableColumn _enddate;
	@FXML
	private TableColumn _piste;
	@FXML
	private TableColumn _state;

	protected Connection connection;
	Statement stmt = null;
	ResultSet rs = null;
	JFXTextField txtDepartment;
	@FXML
	private StackPane deptStackPane;

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnSearch.setOnAction(event -> {
			try {
				ShowItem();
				_tableEvents.refresh();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		setRipples();

		setUpDepartments();

		comboReg1.getItems().addAll("Alpes du Nord", "Alpes du Sud", "Auvergne", "Pyrénnées", "Vosges Jura");
		// setStationToCombo();

		comboReg1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
				if (newvalue.equals("Alpes du Nord")) {
					comboStation1.getItems().clear();
					comboStation1.getItems().add("Albiez montrond");
					comboStation1.getItems().add("Alpe d'huez");
					comboStation1.getItems().add("Areches beaufort");
					comboStation1.getItems().add("Aussois");
					comboStation1.getItems().add("Avoriaz");
					comboStation1.getItems().add("Bonneval sur arc");
					comboStation1.getItems().add("Bride les bains");

				} else if (newvalue.equals("Alpes du Sud")) {
					comboStation1.getItems().clear();
					comboStation1.getItems().add("Chabanon selonnet");
					comboStation1.getItems().add("Isola 200");
					comboStation1.getItems().add("Les orres");
				} else if (newvalue.equals("Auvergne")) {
					comboStation1.getItems().clear();
					comboStation1.getItems().add("Super Besse");
				} else if (newvalue.equals("Pyrénnées")) {
					comboStation1.getItems().clear();
					comboStation1.getItems().add("Ax les Termes");
					comboStation1.getItems().add("Font Romenu");
					comboStation1.getItems().add("La Mongie");
					comboStation1.getItems().add("Les Angles");

				} else if (newvalue.equals("Vosges Jura")) {
					comboStation1.getItems().clear();
					comboStation1.getItems().add("Gérardmer");
					comboStation1.getItems().add("La Bresse");

				}
			}
		});

	}

	private void setRipples() {
	}

	private void setUpDepartments() {
	}

	@FXML
	private void addDepartment(ActionEvent event) {
	}

	private void saveDepartment() {
	}

	public void ShowItem() throws NamingException {
		Task<List<Event>> task = new Task() {
			Context context = new InitialContext();
			String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

			EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

			@Override
			protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));
				System.out.println(comboReg1.getSelectionModel().getSelectedItem().toString());
				List<Event> event = eventManagementRemote.findEventByMultiChoices(
						comboReg1.getSelectionModel().getSelectedItem().toString(),
						comboStation1.getSelectionModel().getSelectedItem().toString());
				// event = eventManagementRemote.findEventByMultiChoices("test1", "Auvergne");
				return event;
			}
		};
		task.setOnSucceeded(e -> {

			_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			_location.setCellValueFactory(new PropertyValueFactory<>("location"));
			_startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			_piste.setCellValueFactory(new PropertyValueFactory<>("piste"));
			_tableEvents.setItems(FXCollections.observableArrayList(task.getValue()));
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
