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
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe Controller g�rant le drag and drop et les �l�ments pr�sents dans l'interface utilisateur.
 * @author Alexandre
 *
 */
public class MainController {
    public Label HelloWorld;

    @FXML
    private Pane ZoneDrag;

    @FXML
    private Label label_fichier;
    
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
     * M�thode �venement qui change le font de la zone de drag lorsqu'on la survole avec un fichier valide.
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
     * M�thode g�rant le drag and drop et gardant en m�moire le chemin et nom du fichier.
     */
    @FXML
    void setOnDragDropped(DragEvent event) {
    	
    	 final Dragboard db = event.getDragboard();
          boolean success = false;
          if (db.hasFiles()) {
              success = true;
              // On prend seulement le premier fichier selectionn� si il y en a plusieurs.
              final File file = db.getFiles().get(0);
              Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                
                      System.out.println(file.getAbsolutePath());
					  String chemin_fichier = file.getAbsolutePath();
					  String nom_fichier = file.getName();
					 
					  label_fichier.setText(nom_fichier);
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
        var teamsProcessor = new TEAMSProcessor(selectedFile,"19/01/2021 à 10:15:00", "19/01/2021 à 11:45:00");
/*
        var allpeople = teamsProcessor.get_allpeople();
        for (People people : allpeople) {
            System.out.println( people );
        }
*/
        System.out.println( teamsProcessor.toHTMLCode() );

    }
}
