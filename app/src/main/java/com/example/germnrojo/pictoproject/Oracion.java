package com.example.germnrojo.pictoproject;

/**
 * Created by pc on 17-07-2017.
 */

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

@Table(
        database = Database.class,
        cachingEnabled = false,
        orderedCursorLookUp = true, // https://github.com/Raizlabs/DBFlow/blob/develop/usage2/Retrieval.md#faster-retrieval
        cacheSize = Database.CACHE_SIZE
)
public class Oracion extends BaseModel {


    @Column
    private String imagen;

    @Column
    private String respuesta;

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    List<Palabra> palabras;

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "palabras")
    public List<Palabra> getMyAnts() {
        if (palabras == null || palabras.isEmpty()) {
            palabras = SQLite.select().from(Palabra.class)
                    .where(Palabra_Table.oracionID.eq(id))
                    .queryList();
        }
        return palabras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}

