package it.polito.tdp.GestoreGuardaroba;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.GestoreGuardaroba.model.Capo;
import it.polito.tdp.GestoreGuardaroba.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> CbColore;

    @FXML
    private ChoiceBox<String> CbColoreO;

    @FXML
    private ChoiceBox<String> CbOccasione;

    @FXML
    private ChoiceBox<String> CbOccasioneO;

    @FXML
    private ChoiceBox<String> CbSottotipo;

    @FXML
    private ChoiceBox<String> CbStagione;

    @FXML
    private ChoiceBox<String> CbStagioneO;

    @FXML
    private ChoiceBox<String> CbTipo;

    @FXML
    private TextField TfMarca;

    @FXML
    private Button btnAggiungi;

    @FXML
    private Button btnCrea;

    @FXML
    private Button btnElimina;

    @FXML
    private ChoiceBox<Capo> cbCapo;

    @FXML
    private Label txtMessaggioAggiungi;

    @FXML
    private Label txtMessaggioElimina;

    @FXML
    private TextArea txtMessaggioOutfit;

    @FXML
    void aggiungiCapo(ActionEvent event) {
    	
    	if(this.TfMarca.getText().trim().isEmpty())
    		this.txtMessaggioAggiungi.setText("Devi scrivere il nome della marca!");
    	else {
    		String tipo = this.CbTipo.getValue();
    		String sottotipo = this.CbSottotipo.getValue();
    		String colore = this.CbColore.getValue();
    		String stagione = this.CbStagione.getValue();
    		String occasione = this.CbOccasione.getValue();
    		String marca = this.TfMarca.getText();
    		
    		if(this.model.esisteCapo(tipo, sottotipo, colore, stagione, occasione, marca))
    			this.txtMessaggioAggiungi.setText("Questo capo d'abbigliamento è già presente!");
    		else {
    			this.model.AggiungiCapo(tipo, sottotipo, colore, stagione, occasione, marca);
    			this.txtMessaggioAggiungi.setText("Capo d'abbigliamento aggiunto correttamente!"); 
    		}
    	}
    }

    @FXML
    void creaOutfit(ActionEvent event) {

    }

    @FXML
    void eliminaCapo(ActionEvent event) {

    }
    
    private void checkCampiAggiungi() {
    	if(this.CbColore.getValue() != null && this.CbOccasione.getValue() != null
    			&& this.CbSottotipo.getValue() != null && this.CbStagione.getValue() != null 
    			&& this.CbTipo.getValue() != null && !this.TfMarca.getText().trim().isEmpty())
    		
    	this.btnAggiungi.setDisable(false);  	
    }
    
    private void aggiornaSottotipi() {
    	if(this.CbTipo.getValue() != null) {
    		this.CbSottotipo.setDisable(false);
    		String tipo = this.CbTipo.getValue();
    		List<String> sottotipi = this.model.getSottotipiByTipo(tipo);
    		this.CbSottotipo.getItems().clear();
    		this.CbSottotipo.getItems().addAll(sottotipi);
    	}
    }
    
    private void checkCampoElimina() {
    	if(this.cbCapo.getValue() != null)    		
    		this.btnElimina.setDisable(false);  	
    }
    
    private void checkCampiOutfit() {
    	if(this.CbOccasioneO.getValue() != null && this.CbStagioneO.getValue() != null)    		
    	this.btnCrea.setDisable(false);  	
    }

    @FXML
    void initialize() {
        assert CbColore != null : "fx:id=\"CbColore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbColoreO != null : "fx:id=\"CbColoreO\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbOccasione != null : "fx:id=\"CbOccasione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbOccasioneO != null : "fx:id=\"CbOccasioneO\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbSottotipo != null : "fx:id=\"CbSottotipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbStagione != null : "fx:id=\"CbStagione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbStagioneO != null : "fx:id=\"CbStagioneO\" was not injected: check your FXML file 'Scene.fxml'.";
        assert CbTipo != null : "fx:id=\"CbTipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TfMarca != null : "fx:id=\"TfMarca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCrea != null : "fx:id=\"btnCrea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbCapo != null : "fx:id=\"cbCapo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessaggioAggiungi != null : "fx:id=\"txtMessaggioAggiungi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessaggioElimina != null : "fx:id=\"txtMessaggioElimina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessaggioOutfit != null : "fx:id=\"txtMessaggioOutfit\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.CbColore.setOnAction(e -> checkCampiAggiungi());
        this.CbOccasione.setOnAction(e -> checkCampiAggiungi());
        this.CbSottotipo.setOnAction(e -> checkCampiAggiungi());
        this.CbStagione.setOnAction(e -> checkCampiAggiungi());
        this.CbTipo.setOnAction(e -> checkCampiAggiungi());
        this.TfMarca.textProperty().addListener((obs, oldVal, newVal) -> checkCampiAggiungi());
        
        this.CbTipo.setOnAction(e -> aggiornaSottotipi());
        
        this.cbCapo.setOnAction(e -> checkCampoElimina());
        
        this.CbOccasioneO.setOnAction(e -> checkCampiOutfit());
        this.CbStagioneO.setOnAction(e -> checkCampiOutfit());
    }
    
    public void setModel(Model model) {    	
    	this.model = model;
    	this.CbColore.getItems().addAll(this.model.getColori());
    	this.CbColoreO.getItems().addAll(this.model.getColori());
    	this.cbCapo.getItems().addAll(this.model.getCapi());
    	this.CbOccasione.getItems().addAll("Casual", "Formale", "Sportivo");
    	this.CbOccasioneO.getItems().addAll("Casual", "Formale", "Sportivo");
    	this.CbStagione.getItems().addAll("Autunno/Primavera", "Estate", "Inverno");
    	this.CbStagioneO.getItems().addAll("Autunno/Primavera", "Estate", "Inverno");
    	this.CbTipo.getItems().addAll("Capospalla", "Inferiore", "Intero", "Scarpe", "Superiore");    	
    }

}
	

