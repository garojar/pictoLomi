package com.example.germnrojo.pictoproject;

/**
 * Created by pc on 17-07-2017.
 *
 * @author Kevin Araya, Diego Saavedra
 */


@com.raizlabs.android.dbflow.annotation.Database(name = Database.NAME, version = Database.VERSION)
public class Database {

    /**
     * Key de la base de datos
     */
    public static final String NAME = "Database";

    /**
     * Version de la BD
     */
    public static final int VERSION = 2;

    /**
     * Tamanio del cache
     */
    public static final int CACHE_SIZE = 100;
}
