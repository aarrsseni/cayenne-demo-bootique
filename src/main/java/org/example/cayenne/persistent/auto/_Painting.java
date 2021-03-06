package org.example.cayenne.persistent.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.example.cayenne.persistent.Artist;

/**
 * Class _Painting was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Painting extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> TITTLE = Property.create("tittle", String.class);
    public static final Property<Artist> ARTIST = Property.create("artist", Artist.class);

    protected String tittle;

    protected Object artist;

    public void setTittle(String tittle) {
        beforePropertyWrite("tittle", this.tittle, tittle);
        this.tittle = tittle;
    }

    public String getTittle() {
        beforePropertyRead("tittle");
        return this.tittle;
    }

    public void setArtist(Artist artist) {
        setToOneTarget("artist", artist, true);
    }

    public Artist getArtist() {
        return (Artist)readProperty("artist");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "tittle":
                return this.tittle;
            case "artist":
                return this.artist;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "tittle":
                this.tittle = (String)val;
                break;
            case "artist":
                this.artist = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.tittle);
        out.writeObject(this.artist);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.tittle = (String)in.readObject();
        this.artist = in.readObject();
    }

}
