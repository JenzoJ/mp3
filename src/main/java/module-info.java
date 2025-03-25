module fr.btsciel.mp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens fr.btsciel.mp3 to javafx.fxml;
    exports fr.btsciel.mp3;
    exports pk_mp3;
    opens pk_mp3 to javafx.fxml;
}