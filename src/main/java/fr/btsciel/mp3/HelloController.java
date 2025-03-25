package fr.btsciel.mp3;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import pk_mp3.Gestion_Mp3;

import java.io.IOException;
import java.nio.file.Path;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button buttonFichier;
    public Label labelFichier;
    public Label labelChemin;
    public Button buttonPlay;
    public Button buttonStop;
    public Media media;
    public MediaPlayer mediaPlayer;
    public String sFichierSelectionne;
    public Path path;
    public Button buttonlireTags;
    public TextField txt_Titre;
    public TextField txt_Artiste;
    public TextField txt_Album;
    public TextField txt_Annee;
    public TextField txt_Commentaires;
    public TextField txt_Genre;
    public TextField txt_Track;
    Gestion_Mp3 gestion_Mp3;
    private SimpleBooleanProperty isEditable;
    private SimpleStringProperty background;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonFichier.setOnAction(e -> {ouvreFichier();});
        buttonPlay.setOnAction(e -> play());
        buttonStop.setOnAction(e -> stop());
        buttonlireTags.setOnAction(e -> lireTags());

        isEditable = new SimpleBooleanProperty(false);

    }

    private void stop() {
        mediaPlayer.stop();
        buttonPlay.setDisable(false);
        buttonStop.setDisable(true);
    }

    private void play() {
        media = new Media(sFichierSelectionne);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        buttonPlay.setDisable(true);
        buttonStop.setDisable(false);
    }

    private void ouvreFichier() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("./src/main/resources/mp3_music"));
        File fichierSelectionne = chooser.showOpenDialog(null);
        System.out.println(fichierSelectionne);
        if (fichierSelectionne != null) {
            path = fichierSelectionne.toPath();
            sFichierSelectionne = path.toUri().toString();
            labelChemin.setText(path.toAbsolutePath().toString());
            labelFichier.setText(path.getFileName().toString());
        }
    }

    private void lireTags(){
        isEditable.set(false);
        background.set("-fx-background-color:#bbbbbb");
        gestion_Mp3 = new Gestion_Mp3(path);
        try {
            gestion_Mp3.litTags();
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture de tags");
        }

        txt_Titre.setText(gestion_Mp3.titre());
        txt_Artiste.setText(gestion_Mp3.artiste());
        txt_Album.setText(gestion_Mp3.album());
        txt_Annee.setText(gestion_Mp3.annee());
        txt_Commentaires.setText(gestion_Mp3.commentaire());
        txt_Track.setText(gestion_Mp3.track());
    }
}