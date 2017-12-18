package com.example.germnrojo.pictoproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton dif, jugar;//boton jugar
    private  MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //jugar.setEnabled(false);//boton desactivado
        //inicializacion de la base de datos
        {
            FlowManager.init(new FlowConfig.Builder(getApplicationContext())
                    .openDatabasesOnInit(true)
                    .build());
        }

        //Vemos si la base de datos ya tiene oraciones
        List<Oracion> oracionList = SQLite.select().from(Oracion.class).queryList();
        if (oracionList.size() == 0) {
            //cargamos la imagen morfosisntactica
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.niniopinta);
            Oracion oracion = new Oracion();
            oracion.setRespuesta("El niño pinta");
            oracion.setImagen(getEncodedImage(image));
            oracion.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image = BitmapFactory.decodeResource(getResources(), R.drawable.pictoel);
            Palabra palabra = new Palabra();
            palabra.setImagen(getEncodedImage(image));
            palabra.setNombre("El");
            palabra.oracionID = oracion.getId();
            palabra.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninio);
            palabra = new Palabra();
            palabra.setImagen(getEncodedImage(image));
            palabra.setNombre("niño");
            palabra.oracionID = oracion.getId();
            palabra.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.
            image = BitmapFactory.decodeResource(getResources(), R.drawable.pictopinta);
            palabra = new Palabra();
            palabra.setImagen(getEncodedImage(image));
            palabra.setNombre("pinta");
            palabra.oracionID = oracion.getId();
            palabra.save();
            //cargamos la segunda oracion en la base de datos
            Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.ninalimpia);
            Oracion oracion1 = new Oracion();
            oracion1.setRespuesta("La niña limpia");
            oracion1.setImagen(getEncodedImage(image1));
            oracion1.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.pictola);
            Palabra palabra1 = new Palabra();
            palabra1.setImagen(getEncodedImage(image1));
            palabra1.setNombre("La");
            palabra1.oracionID = oracion1.getId();
            palabra1.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra1 = new Palabra();
            palabra1.setImagen(getEncodedImage(image1));
            palabra1.setNombre("niña");
            palabra1.oracionID = oracion1.getId();
            palabra1.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.pictolimpia);
            palabra1 = new Palabra();
            palabra1.setImagen(getEncodedImage(image1));
            palabra1.setNombre("limpia");
            palabra1.oracionID = oracion1.getId();
            palabra1.save();
            //cargamos la tercera oracion

            Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.ninasjuegan);
            Oracion oracion2 = new Oracion();
            oracion2.setRespuesta("Las niñas juegan");
            oracion2.setImagen(getEncodedImage(image2));
            oracion2.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image2 = BitmapFactory.decodeResource(getResources(), R.drawable.pictolas);
            Palabra palabra2 = new Palabra();
            palabra2.setImagen(getEncodedImage(image2));
            palabra2.setNombre("Las");
            palabra2.oracionID = oracion2.getId();
            palabra2.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image2 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninias);
            palabra2 = new Palabra();
            palabra2.setImagen(getEncodedImage(image2));
            palabra2.setNombre("niñas");
            palabra2.oracionID = oracion2.getId();
            palabra2.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image2 = BitmapFactory.decodeResource(getResources(), R.drawable.pictojuegan);
            palabra2 = new Palabra();
            palabra2.setImagen(getEncodedImage(image2));
            palabra2.setNombre("juegan");
            palabra2.oracionID = oracion2.getId();
            palabra2.save();

            //cargamos la cuarta oracion

            Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.laninarecorta);
            Oracion oracion3 = new Oracion();
            oracion3.setRespuesta("La niña recorta");
            oracion3.setImagen(getEncodedImage(image3));
            oracion3.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image3 = BitmapFactory.decodeResource(getResources(), R.drawable.pictola);
            Palabra palabra3 = new Palabra();
            palabra3.setImagen(getEncodedImage(image3));
            palabra3.setNombre("La");
            palabra3.oracionID = oracion3.getId();
            palabra3.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image3 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra3 = new Palabra();
            palabra3.setImagen(getEncodedImage(image3));
            palabra3.setNombre("niña");
            palabra3.oracionID = oracion3.getId();
            palabra3.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image3 = BitmapFactory.decodeResource(getResources(), R.drawable.recorta);
            palabra3 = new Palabra();
            palabra3.setImagen(getEncodedImage(image3));
            palabra3.setNombre("recorta");
            palabra3.oracionID = oracion3.getId();
            palabra3.save();
            //oracion 5

            Bitmap image4 = BitmapFactory.decodeResource(getResources(), R.drawable.niniobebe);
            Oracion oracion4 = new Oracion();
            oracion4.setRespuesta("El niño bebe");
            oracion4.setImagen(getEncodedImage(image4));
            oracion4.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image4 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoel);
            Palabra palabra4 = new Palabra();
            palabra4.setImagen(getEncodedImage(image4));
            palabra4.setNombre("El");
            palabra4.oracionID = oracion4.getId();
            palabra4.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image4 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninio);
            palabra4 = new Palabra();
            palabra4.setImagen(getEncodedImage(image4));
            palabra4.setNombre("niño");
            palabra4.oracionID = oracion4.getId();
            palabra4.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image4 = BitmapFactory.decodeResource(getResources(), R.drawable.pictotoma);
            palabra4 = new Palabra();
            palabra4.setImagen(getEncodedImage(image4));
            palabra4.setNombre("bebe");
            palabra4.oracionID = oracion4.getId();
            palabra4.save();
            //oracion 6


            Bitmap image5 = BitmapFactory.decodeResource(getResources(), R.drawable.niniocome);
            Oracion oracion5 = new Oracion();
            oracion5.setRespuesta("El niño come");
            oracion5.setImagen(getEncodedImage(image5));
            oracion5.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image5 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoel);
            Palabra palabra5 = new Palabra();
            palabra5.setImagen(getEncodedImage(image5));
            palabra5.setNombre("El");
            palabra5.oracionID = oracion5.getId();
            palabra5.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image5 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninio);
            palabra5 = new Palabra();
            palabra5.setImagen(getEncodedImage(image5));
            palabra5.setNombre("niño");
            palabra5.oracionID = oracion5.getId();
            palabra5.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image5 = BitmapFactory.decodeResource(getResources(), R.drawable.pictocome);
            palabra5 = new Palabra();
            palabra5.setImagen(getEncodedImage(image5));
            palabra5.setNombre("come");
            palabra5.oracionID = oracion5.getId();
            palabra5.save();
            //oracion 7


            Bitmap image6 = BitmapFactory.decodeResource(getResources(), R.drawable.elninoduerme);
            Oracion oracion6 = new Oracion();
            oracion6.setRespuesta("El niño duerme");
            oracion6.setImagen(getEncodedImage(image6));
            oracion6.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image6 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoel);
            Palabra palabra6 = new Palabra();
            palabra6.setImagen(getEncodedImage(image6));
            palabra6.setNombre("El");
            palabra6.oracionID = oracion6.getId();
            palabra6.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image6 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninio);
            palabra6 = new Palabra();
            palabra6.setImagen(getEncodedImage(image6));
            palabra6.setNombre("niño");
            palabra6.oracionID = oracion6.getId();
            palabra6.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image6 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoduerme);
            palabra6 = new Palabra();
            palabra6.setImagen(getEncodedImage(image6));
            palabra6.setNombre("duerme");
            palabra6.oracionID = oracion6.getId();
            palabra6.save();

            //oracion8
            Bitmap image7 = BitmapFactory.decodeResource(getResources(), R.drawable.niniojuega);
            Oracion oracion7 = new Oracion();
            oracion7.setRespuesta("El niño juega");
            oracion7.setImagen(getEncodedImage(image7));
            oracion7.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image7 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoel);
            Palabra palabra7 = new Palabra();
            palabra7.setImagen(getEncodedImage(image7));
            palabra7.setNombre("El");
            palabra7.oracionID = oracion7.getId();
            palabra7.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image7 = BitmapFactory.decodeResource(getResources(), R.drawable.pictoninio);
            palabra7 = new Palabra();
            palabra7.setImagen(getEncodedImage(image7));
            palabra7.setNombre("niño");
            palabra7.oracionID = oracion7.getId();
            palabra7.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image7 = BitmapFactory.decodeResource(getResources(), R.drawable.pictojuega);
            palabra7 = new Palabra();
            palabra7.setImagen(getEncodedImage(image7));
            palabra7.setNombre("juega");
            palabra7.oracionID = oracion7.getId();
            palabra7.save();
            //oracion 9
            Bitmap image8 = BitmapFactory.decodeResource(getResources(), R.drawable.pictofoto);
            Oracion oracion8 = new Oracion();
            oracion8.setRespuesta("La niña nada");
            oracion8.setImagen(getEncodedImage(image8));
            oracion8.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image8 = BitmapFactory.decodeResource(getResources(), R.drawable.la);
            Palabra palabra8 = new Palabra();
            palabra8.setImagen(getEncodedImage(image8));
            palabra8.setNombre("La");
            palabra8.oracionID = oracion8.getId();
            palabra8.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image8 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra8 = new Palabra();
            palabra8.setImagen(getEncodedImage(image8));
            palabra8.setNombre("niña");
            palabra8.oracionID = oracion8.getId();
            palabra8.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image8 = BitmapFactory.decodeResource(getResources(), R.drawable.nada);
            palabra8 = new Palabra();
            palabra8.setImagen(getEncodedImage(image8));
            palabra8.setNombre("nada");
            palabra8.oracionID = oracion8.getId();
            palabra8.save();
            //oracion 10
            Bitmap image9 = BitmapFactory.decodeResource(getResources(), R.drawable.laninabaila);
            Oracion oracion9 = new Oracion();
            oracion9.setRespuesta("La niña baila");
            oracion9.setImagen(getEncodedImage(image9));
            oracion9.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image9 = BitmapFactory.decodeResource(getResources(), R.drawable.pictola);
            Palabra palabra9 = new Palabra();
            palabra9.setImagen(getEncodedImage(image9));
            palabra9.setNombre("La");
            palabra9.oracionID = oracion9.getId();
            palabra9.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image9 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra9 = new Palabra();
            palabra9.setImagen(getEncodedImage(image9));
            palabra9.setNombre("niña");
            palabra9.oracionID = oracion9.getId();
            palabra9.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image9 = BitmapFactory.decodeResource(getResources(), R.drawable.baila);
            palabra9 = new Palabra();
            palabra9.setImagen(getEncodedImage(image9));
            palabra9.setNombre("baila");
            palabra9.oracionID = oracion9.getId();
            palabra9.save();

            //imagen 11
            Bitmap image10 = BitmapFactory.decodeResource(getResources(), R.drawable.laninahuele);
            Oracion oracion10 = new Oracion();
            oracion10.setRespuesta("La niña huele");
            oracion10.setImagen(getEncodedImage(image10));
            oracion10.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image10 = BitmapFactory.decodeResource(getResources(), R.drawable.la);
            Palabra palabra10 = new Palabra();
            palabra10.setImagen(getEncodedImage(image10));
            palabra10.setNombre("La");
            palabra10.oracionID = oracion10.getId();
            palabra10.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image10 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra10 = new Palabra();
            palabra10.setImagen(getEncodedImage(image10));
            palabra10.setNombre("niña");
            palabra10.oracionID = oracion10.getId();
            palabra10.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image10 = BitmapFactory.decodeResource(getResources(), R.drawable.huele);
            palabra10 = new Palabra();
            palabra10.setImagen(getEncodedImage(image10));
            palabra10.setNombre("huele");
            palabra10.oracionID = oracion10.getId();
            palabra10.save();
            //oracion 12
            Bitmap image11 = BitmapFactory.decodeResource(getResources(), R.drawable.laninateje);
            Oracion oracion11 = new Oracion();
            oracion11.setRespuesta("La niña teje");
            oracion11.setImagen(getEncodedImage(image11));
            oracion11.save();
            //cargamos el primer pictograma(palabra) asociado a la oracion.
            image11 = BitmapFactory.decodeResource(getResources(), R.drawable.la);
            Palabra palabra11 = new Palabra();
            palabra11.setImagen(getEncodedImage(image11));
            palabra11.setNombre("La");
            palabra11.oracionID = oracion11.getId();
            palabra11.save();//guardamos el primer pictograma asociado a la imagen
            //cargamos el segundo pictograma(palabra) asociado a la oracion.
            image11 = BitmapFactory.decodeResource(getResources(), R.drawable.ninia);
            palabra11 = new Palabra();
            palabra11.setImagen(getEncodedImage(image11));
            palabra11.setNombre("niña");
            palabra11.oracionID = oracion11.getId();
            palabra11.save();
            //cargamos el tercer pictograma(palabra) asociado a la oracion.

            image11 = BitmapFactory.decodeResource(getResources(), R.drawable.teje);
            palabra11 = new Palabra();
            palabra11.setImagen(getEncodedImage(image11));
            palabra11.setNombre("teje");
            palabra11.oracionID = oracion11.getId();
            palabra11.save();
        }//end if
        //se cargo l base de datos

        //musica
        player = MediaPlayer.create(this, R.raw.cancionpp);
        player.start();

        dif = (ImageButton) findViewById(R.id.botonCfg);
        jugar = (ImageButton) findViewById(R.id.botonJugar);
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dift = new Intent(MainActivity.this, ingame.class);
                player.stop();
                startActivity(dift);

            }
        });
        {
            dif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dift = new Intent(MainActivity.this, acercaDe.class);
                    startActivity(dift);

                }
            });
            {

            }

        }

    }


    //Codifica una imagen en base64 para guardarla en la base de datoss
    public String getEncodedImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onDestroy() {
        player.stop();
        super.onDestroy();
    }
}
