package modules;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class adminAppController {

	private static adminAppController instance;

	@FXML
	private AnchorPane boxConteudo;
	@FXML
	private VBox boxEmprestimo;
	@FXML
	private VBox boxVisitas;

	@FXML
	private VBox boxUtilitarios;
	@FXML
	private VBox boxNotas;
	@FXML
	private VBox boxCatalogacao;
	@FXML
	private Label lbUser;
	@FXML
	private Label lbMensagem;

	//////////////////////////////////////////////////////////////////////////////
	// ********************************************Gestion
	////////////////////////////////////////////////////////////////////////////// responsablesBoutique********************************
	private ToggleButton bouton_gestion_responsable_boutique;

	@FXML
	private VBox boxLocalizacao;

	private ToggleButton btAjoutResponsable;

	private ToggleButton btAfficherResponsableBoutique;

	@FXML
	private VBox boxReclamation;

	//////////////////////////////////////////////////////////////////////////
	// ********************************************Gestion offre
	////////////////////////////////////////////////////////////////////////// emploi********************************
	private ToggleButton bouton_Offre_emploi;
	@FXML
	private VBox boxOffreEmploi;
	private ToggleButton btAjoutOffreEmploi;
	private ToggleButton btSupprimerOffreEmploi;
	private ToggleButton btModifierOffreEmploi;
	private ToggleButton btAfficherOffreEmploi;

	//////////////////////////////////////////////
	// ********************************************Gestion
	////////////////////////////////////////////// publicite********************************
	@FXML
	private VBox boxPublicite;

	////////////////////////////////////////////////////////////////////////////////
	// ********************************************Gestion
	//////////////////////////////////////////////////////////////////////////////// reclamation********************************
	@FXML
	private VBox boxEvenement;

	@FXML
	private ToggleGroup grupoMenus;

	/**
	 * Obter instancia do controler
	 */
	public static adminAppController getInstance() {
		return instance;
	}

	@FXML
	private ToggleGroup grupoMenus1;
	@FXML
	private ToggleGroup grupoMenus2;
	@FXML
	private ToggleGroup grupoLocaliacao11;
	@FXML
	private ToggleGroup grupoLocaliacao12;
	@FXML
	private ToggleGroup grupoLocaliacao13;
	@FXML
	private ToggleGroup grupoLocaliacao14;
	@FXML
	private ToggleGroup grupoMenus11;
	@FXML
	private ToggleGroup grupoMenus111;
	@FXML
	private Button btnClose;
	@FXML
	private ToggleButton bouton_gestion_employers_shopowner;
	@FXML
	private ToggleButton bouton_gestion_boutique_shopowner;
	@FXML
	private ToggleButton bouton_CFidelité_shopowner;
	@FXML
	private ToggleButton bouton_reclamation_shopowner;
	@FXML
	private ToggleButton bouton_Offre_emploi_shopowner;
	@FXML
	private ToggleButton btAjoutOffreEmploi_shopowner;
	@FXML
	private ToggleButton btSupprimerOffreEmploi_shopowner;
	@FXML
	private ToggleButton btModifierOffreEmploi_shopowner;
	@FXML
	private ToggleButton btAfficherOffreEmploi_shopowner;
	@FXML
	private ToggleButton bouton_Publicite_shopowner;
	@FXML
	private ToggleButton bouton_Evenement_shopowner;
	@FXML
	private ToggleGroup grupoMenus112;
	@FXML
	private ToggleGroup grupoMenus1111;
	@FXML
	private ToggleButton bouton_Forum_shopowner;
	@FXML
	private ToggleGroup grupoMenus11111;




	public void menu_gestion_boutiques(ActionEvent event) {
		try {
			boxConteudo.getChildren().clear();
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionBoutiqueFXML.fxml"));
			boxConteudo.getChildren().add(newLoadedPane);
		} catch (IOException ex) {
			Logger.getLogger(adminAppController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////



	// close doesnt work need revision
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FXML
	public void CloseWindow(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();

	}

	void menuSeguranca(ActionEvent event) {

	}

	void menuUtilitario(ActionEvent event) {

	}

	@FXML
	void siteMuseu(ActionEvent event) {

	}

	void subDevolucaoEmprestimo(ActionEvent event) {

	}

	void subEmprestimo(ActionEvent event) {

	}

	void subHistoricoEmprestimo(ActionEvent event) {

	}

	void subInstituicao(ActionEvent event) {

	}

	void subItensEmprestimo(ActionEvent event) {

	}

	void subLocal(ActionEvent event) {

	}

	void subLocalizar(ActionEvent event) {

	}

	void subOrganizacao(ActionEvent event) {

	}

	void subSetor(ActionEvent event) {

	}

	void subUsuarios(ActionEvent event) {

	}

	void subVisitantes(ActionEvent event) {

	}

	void initialize() {
		// instance = this;
		// Grupo.notEmpty(grupoMenus, grupoCatalogacao, grupoEmprestimo,
		// grupoLocaliacao, grupoUtilidades, grupoVisitantes);//não permite grupos de
		// menus com menus deselecionados
		// menuDashboard(null);
		// lbUser.setText("Olá, " + LoginController.usuarioLogado.getNome());
	}

	/**
	 * Obter componente para exbição das notas
	 */
	public VBox boxNotas() {
		return boxNotas;
	}

	/**
	 * Obter componente para exibição dos modulos da aplicação
	 */
	public AnchorPane getBoxConteudo() {
		return boxConteudo;
	}

	/**
	 * Exibir e ocultar submenus
	 */
	public void submenus(ToggleButton menu, VBox box, ToggleButton... submenus) {
		if (box.getChildren().isEmpty()) {
			box.getChildren().addAll(submenus);
			SubMenuFade.fade(box);
			estilo(menu, "menu-grupo");
		} else {
			desativarSubmenus(box);
			estilo(menu, "menu-grupo-inativo");
		}
	}

	/**
	 * Desativar e esconder todos submenus
	 */
	public void desativarSubmenus(VBox... boxes) {
		for (VBox box : boxes) {
			box.getChildren().clear();
		}
	}

	/**
	 * Aplicar estilo para mostrar/ocultar submenus
	 */
	public void estilo(Node no, String estilo) {
		no.getStyleClass().remove(3);
		no.getStyleClass().add(estilo);
	}

	@FXML
	private void menu_gestion_employers_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_gestion_boutiques_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_CFidelité_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_reclamation_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_offre_emploi_shopowner(ActionEvent event) {
	}

	@FXML
	private void subAjoutOffreEmploi_shopowner(ActionEvent event) {
	}

	@FXML
	private void subSupprimerOffreEmploi_shopowner(ActionEvent event) {
	}

	@FXML
	private void subModifierOffreEmploi_shopowner(ActionEvent event) {
	}

	@FXML
	private void subAfficherOffreEmploi_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_Publicite_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_Evenement_shopowner(ActionEvent event) {
	}

	@FXML
	private void menu_Forum_shopowner(ActionEvent event) {
	}

}
