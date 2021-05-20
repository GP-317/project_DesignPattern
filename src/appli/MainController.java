package appli;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.util.*;


/**
 * Classe Controller gérant le drag and drop et les éléments présents dans l'interface utilisateur.
 * @author Alexandre
 *
 */
public class MainController {
    public Label HelloWorld;
    
    public String Chemin_Fichier;
    
    public String Nom_Fichier;
    
    public Stage primaryStage;
    @FXML
    private Pane ZoneDrag;

    @FXML
    private Label label_fichier;
    
    @FXML
    private Label label_fichier1;
    
    @FXML
    private Label label_nomfichier;
    
    @FXML
    private Label label_date;

    @FXML
    private Label label_hmin;

    @FXML
    private Label label_hmax;
    
    @FXML
    private RadioButton par_ID;

    @FXML
    private RadioButton par_nom;

    @FXML
    private CheckBox sans_nom;

    @FXML
    private CheckBox sans_id;

    @FXML
    private CheckBox sans_planning;

    @FXML
    private Button b_generer;

    @FXML
    void method_generer(ActionEvent event) {

    }
    /**
     * Méthode évenement qui gère l'event Click sur la zone de drag and drop, ouvrant ainsi une fenêtre parcourir.
     * @param event
     */
    @FXML
    void parcourir(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Sélectionnez votre fichier");
    	File fichier = fileChooser.showOpenDialog(primaryStage);
    	if (fichier != null) {
    		Chemin_Fichier = fichier.getAbsolutePath();
    		  Nom_Fichier = fichier.getName();
			  label_fichier.setText(Nom_Fichier);
			  label_fichier1.setText("");
        }
    	
    }
   
    
    /**
     * Méthode évenement qui change le font de la zone de drag lorsqu'on la survole avec un fichier valide.
     * @param event
     */
    @FXML
    void setOnDragOver(DragEvent event) {
    	
    	final Dragboard db = event.getDragboard();
   	 
        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith("");
 
        if (db.hasFiles()) {
            if (isAccepted) {
                ZoneDrag.setStyle("-fx-border-color: grey;"
              + "-fx-border-width: 5;"
              + "-fx-background-color: #C6C6C6;"
              + "-fx-border-style: solid;");
                event.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            event.consume();
        }
       	
    }
    
    /**
     *@author Alexandre
     * Méthode gérant le drag and drop et gardant en mémoire le chemin et nom du fichier.
     */
    @FXML
    void setOnDragDropped(DragEvent event) {
    	
    	 final Dragboard db = event.getDragboard();
          boolean success = false;
          if (db.hasFiles()) {
              success = true;
              // On prend seulement le premier fichier selectionné si il y en a plusieurs.
              final File file = db.getFiles().get(0);
              Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                
                      System.out.println(file.getAbsolutePath());
					  Chemin_Fichier = file.getAbsolutePath();
					  Nom_Fichier = file.getName();
					  label_fichier1.setText("");
					  label_fichier.setText(Nom_Fichier);
					  ZoneDrag.setStyle(null);
                  }
              });
          }
          event.setDropCompleted(success);
          event.consume();
        }
      
    
   
/**
 * 
 * @param actionEvent
 */
    public void sayHelloWorld(ActionEvent actionEvent) {


        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        // process the file, and limit periods to given time interval
        var teamsProcessor = new TEAMSProcessor(selectedFile,"19/01/2021 Ã  10:15:00", "19/01/2021 Ã  11:45:00");
/*
        var allpeople = teamsProcessor.get_allpeople();
        for (People people : allpeople) {
            System.out.println( people );
        }
*/
        System.out.println( teamsProcessor.toHTMLCode() );

    }
}
