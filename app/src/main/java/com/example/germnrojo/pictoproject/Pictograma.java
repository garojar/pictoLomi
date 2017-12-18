package com.example.germnrojo.pictoproject;

import android.widget.ImageView;

/**
 * Created by Germán Rojo, Diego Saaevdra, Kevin Araya
 * on 03-07-2017.
 */

public class Pictograma {
    private ImageView imagen;
    private String nombre;
    private int pos;

    public Pictograma() {
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
