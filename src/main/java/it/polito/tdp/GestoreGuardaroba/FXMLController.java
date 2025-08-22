package it.polito.tdp.GestoreGuardaroba;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.GestoreGuardaroba.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> CbColore;

    @FXML
    private ChoiceBox<?> CbColoreO;

    @FXML
    private ChoiceBox<?> CbOccasione;

    @FXML
    private ChoiceBox<?> CbOccasioneO;

    @FXML
    private ChoiceBox<?> CbSottotipo;

    @FXML
    private ChoiceBox<?> CbStagione;

    @FXML
    private ChoiceBox<?> CbStagioneO;

    @FXML
    private ChoiceBox<?> CbTipo;

    @FXML
    private TextField TfMarca;

    @FXML
    private Button btnAggiungi;

    @FXML
    private Button btnCrea;

    @FXML
    private Button btnElimina;

    @FXML
    private ChoiceBox<?> cbCapo;

    @FXML
    private Label txtMessaggioAggiungi;

    @FXML
    private Label txtMessaggioElimina;

    @FXML
    private Label txtMessaggioOutfit;

    @FXML
    void aggiungiCapo(ActionEvent event) {

    }

    @FXML
    void creaOutfit(ActionEvent event) {

    }

    @FXML
    void eliminaCapo(ActionEvent event) {

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

    }
    
    public void setModel(Model model) {    	
    	this.model = model;     	    	
    }

}
