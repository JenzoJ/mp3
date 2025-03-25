package pk_mp3;

import javafx.scene.control.Alert;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Gestion_Mp3 {
    private Path fileSource;
    private byte[] tab;
    private TagMp3 tag;
    public Gestion_Mp3(Path p) {
        this.fileSource = p;
        tab = new byte[128];
        this.tag = new TagMp3();
    }

    public void litTags() throws IOException {
        InputStream is = Files.newInputStream(fileSource);
        DataInputStream dis = new DataInputStream(is);
        dis.skipBytes((int)Files.size(fileSource)-128);
        dis.read(tab);
        dis.close();
        if (new String(tab,0,3).equals("TAG")){
            this.tag.setTitre(new String(tab,3,30));
            this.tag.setArtiste(new String(tab,33,30));
            this.tag.setAlbum(new String(tab,63,30));
            this.tag.setAnnee(new String(tab,93,4));
            this.tag.setCommentaires(new String(tab,97,28));
            this.tag.setTrack(tab[126]);
            this.tag.setGenre(tab[127]);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }
    public String titre(){
        return tag.getTitre();
    }
    public String artiste(){
        return tag.getArtiste();
    }
    public String album(){
        return tag.getAlbum();
    }
    public String annee(){
        return tag.getAnnee();
    }
    public String commentaire(){
        return tag.getCommentaires();
    }
    public byte track(){
        return tag.getTrack();
    }
    public byte genre(){
        return tag.getGenre();
    }
}
