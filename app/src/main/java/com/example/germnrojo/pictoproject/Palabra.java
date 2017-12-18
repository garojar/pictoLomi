package com.example.germnrojo.pictoproject;

/**
 * Created by pc on 17-07-2017.
 *
 * @author Diego Saavedra Tapia, Kevin Araya Reigada
 */

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;


@Table(
        database = Database.class,
        cachingEnabled = false,
        orderedCursorLookUp = true, // https://github.com/Raizlabs/DBFlow/blob/develop/usage2/Retrieval.md#faster-retrieval
        cacheSize = Database.CACHE_SIZE
)
public class Palabra extends BaseModel {
    @Column
    private String nombre;

    @Column
    private String imagen;

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    int oracionID;

    public int getOracionID() {
        return oracionID;
    }

    public void setOracionID(int oracionID) {
        this.oracionID = oracionID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

