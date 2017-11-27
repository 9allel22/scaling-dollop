/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.criteria.Predicate;

import org.controlsfx.control.textfield.TextFields;

import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import persistence.Event;
import persistence.User;
import services.EventManagementRemote;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class ProfileAdminController implements Initializable {

	@FXML
	private Label fabEdit;
	@FXML
	private AnchorPane fabPane;
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

	private ObservableList<Event> list_Events;
	@FXML
	private TextField searchname;
	@FXML
	private TextField searchlocation;
	@FXML
	private AnchorPane holderAnchor;
	@FXML
	private Label lblName;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblLocation;
	@FXML
	private Label lblDepartment;
	@FXML
	private Label lblCourse;
	@FXML
	private Label lblFee;
	@FXML
	private Label lblPaid;
	@FXML
	private Label lblBalance;

	@FXML
	private Label lb;
	@FXML
	private Label lb1;
	@FXML
	private Label lb2;
	@FXML
	private Label lb3;
	@FXML
	private ToggleGroup filter;
	@FXML
	private JFXButton btnCancel;
	@FXML
	private JFXButton btnCa;
	@FXML
	private JFXButton btnAct;
	@FXML
	private JFXButton btnsearch;
	@FXML
	private Label lblLevel;
	private List<Event> event;
	private AnchorPane list;
	Mail m = new Mail();
	final ObservableList<Event> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		initFilter();
		_tableEvents.refresh();
		try {
			Context context;

			context = new InitialContext();
			String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

			EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

		} catch (Exception e) {
			// TODO: handle exception
		}

		btnsearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ev) {
				// TODO Auto-generated method stub

				System.out.println("pppppppppppppppppppppp2");

			}
		});

		btnCancel.setOnAction(event -> {

			Context context;
			try {
				context = new InitialContext();
				String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

				EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

				// Event evennement = new Event();

				_tableEvents.getSelectionModel().selectedItemProperty()
						.addListener((ObservableValue<? extends Event> observable, Event oldValue, Event newValue) -> {
							if (newValue == null) {
								return;
							}
							Event e = eventManagementRemote.findEventById(oldValue.getIdEvent());
							e.setState("Canceled");
							eventManagementRemote.updateEvent(e);

							try {
								ShowItem();
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						});

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// evennement.setState("Canceled");

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Cancel event");
			String s = "Confirm to cancel event !";
			alert.setContentText(s);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setResizable(true);

			Optional<ButtonType> result = alert.showAndWait();

			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information Dialog");
				alert2.setHeaderText(null);
				alert2.setContentText("Event sucsessfully canceled!");
				alert2.initStyle(StageStyle.UNDECORATED);

				alert2.show();

			}

			{
				String[] to = { "mohamed.kallel@esprit.tn" };

				if (m.sendMail("kallelmed22@gmail.com", "Besbes22", "This event has been Canceled", to))
					System.err.println("email sent successfully");
				else
					System.err.println("problem");

			}
		});

		btnCa.setOnAction(event -> {
			System.out.println("pppppppppppppppppppppp");

			Context context;
			try {
				context = new InitialContext();
				String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

				EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

				try {
					ShowItem();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}

			} catch (NamingException e) {
				e.printStackTrace();
			}

		}

		);
		try {
			ShowItem();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private void getUserInfo(Integer id) {
		try {
			if (id == null) {
				return;
			}
			Context context = new InitialContext();
			String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

			EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);
			Event event = eventManagementRemote.findEventById(id);

			lb3.setText(event.getDescriptionEvent());

			// nomLabel.setText(newValue.getTitre());
			// emitLabel.setText(String.valueOf(newValue.getDate_emitation()));

		} catch (Exception ex) {
			// Logger.getLogger(Users1Controller.class.getName()).log(Level.SEVERE, null,
			// ex);
		}

	}

	public void ShowItem() throws NamingException {
		Task<List<Event>> task = new Task() {
			Context context = new InitialContext();
			String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

			EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

			@Override
			protected Object call() {
				// Platform.runLater(() -> prog.setVisible(true));

				event = eventManagementRemote.findAllEvents();
				// event = eventManagementRemote.findEventByMultiChoices("test1", "Auvergne");
				return event;
			}
		};
		task.setOnSucceeded(e -> {

			_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			_location.setCellValueFactory(new PropertyValueFactory<>("location"));
			_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
			_startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			_enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			_piste.setCellValueFactory(new PropertyValueFactory<>("piste"));
			_state.setCellValueFactory(new PropertyValueFactory<>("state"));
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

	public final void initFilter() {

		searchname.setPromptText("Filter");

		searchname.textProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(javafx.beans.Observable observable) {

				if (searchname.textProperty().get().isEmpty()) {

					_tableEvents.setItems(data);

					return;

				}

				ObservableList<Event> tableItems = FXCollections.observableArrayList();
				ObservableList<Event> tableItems2 = FXCollections.observableArrayList();
				data.addAll(_tableEvents.getItems());
				ObservableList<TableColumn<Event, ?>> cols = _tableEvents.getColumns();
				System.out.println(data.size());

				for (int i = 0; i < data.size(); i++) {

					for (int j = 0; j < 1; j++) {

						System.out.println("33333");

						TableColumn col = cols.get(j);

						String cellValue = col.getCellData(data.get(i)).toString();

						cellValue = cellValue.toLowerCase();

						if (cellValue.contains(searchname.textProperty().get().toLowerCase())) {
							System.out.println("444444");
							tableItems.add(data.get(i));

							break;

						}

					}

				}

				tableItems2.addAll(tableItems);
				tableItems.removeAll(tableItems);

				_tableEvents.getItems().removeAll(tableItems);
				_tableEvents.getItems().clear();
				_tableEvents.setItems(tableItems);

			}

		});

	}

}
