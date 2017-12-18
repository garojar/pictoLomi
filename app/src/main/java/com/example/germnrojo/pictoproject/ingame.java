package com.example.germnrojo.pictoproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

public class ingame extends AppCompatActivity implements View.OnTouchListener {
    private ImageView img1, img2, img3, frame1, frame2, frame3, morfoSint;

    private Pictograma picto1, picto2, picto3;
    private ImageButton verificar;
    Drawable imgAux1;
    //private Button botonVerif;
    private float x1, y1, x2, y2, x3, y3, contVerify = 0;
    private Pictograma pictosJugador[] = new Pictograma[3];
    private Pictograma pictosORM[] = new Pictograma[3];
    private GifImageButton damper;
    private GifImageView damperexito;
    private boolean isOK = false;
    private ConstraintLayout fondo1;
    int cont = 0;
    private MediaPlayer player,win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        player = MediaPlayer.create(this, R.raw.cancionpp);
        win = MediaPlayer.create(this,R.raw.yay);
        player.setLooping(true);
        player.start();
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);

        // Se relacionan las variables con los elementos de la XML

        morfoSint = (ImageView) findViewById(R.id.pictoFoto);
        img1 = (ImageView) findViewById(R.id.picto1);
        img2 = (ImageView) findViewById(R.id.picto2);
        img3 = (ImageView) findViewById(R.id.picto3);


        frame1 = (ImageView) findViewById(R.id.frame1);

        frame2 = (ImageView) findViewById(R.id.frame2);

        frame3 = (ImageView) findViewById(R.id.frame3);
        verificar = (GifImageButton) findViewById(R.id.verificar);
       // frame1.setX(frame1.getX() - 70);
       // frame3.setX(frame3.getX() - 70);
        damperexito = (GifImageView) findViewById(R.id.exito);


        //codigo Diego Saavedra, Kevxar
        //selecciono la lista de oraciones
        final List<Oracion> oracionList = SQLite.select().from(Oracion.class).queryList();
        Collections.shuffle(oracionList);//desordenamos la lista de oraciones para que al iniciar el juego no se repitan
        final int size = oracionList.size();//largo de la lista


        //selecciono la oracion que esta guardada en la lista
        //donde esta la imagen morfosintactica en la imagenView coloco la nueva imagen
        morfoSint.setImageBitmap(getDecodeImage(oracionList.get(0).getImagen()));
        //tambien agrego los correspondientes pictos relacionados con la nueva imagen
        img1.setImageBitmap(getDecodeImage(oracionList.get(0).palabras.get(0).getImagen()));
        img2.setImageBitmap(getDecodeImage(oracionList.get(0).palabras.get(1).getImagen()));
        img3.setImageBitmap(getDecodeImage(oracionList.get(0).palabras.get(2).getImagen()));

        // Se instancian los objetos pictogramas con su respectiva ImageView del XML
        picto1 = new Pictograma();
        picto2 = new Pictograma();
        picto3 = new Pictograma();
        picto1.setImagen((ImageView) findViewById(R.id.picto1));
        picto2.setImagen((ImageView) findViewById(R.id.picto2));
        picto3.setImagen((ImageView) findViewById(R.id.picto3));

        // El arreglo B representa
        pictosORM[0] = picto1;
        pictosORM[1] = picto2;
        pictosORM[2] = picto3;

        x1 = img1.getX();
        y1 = img1.getY();
        x2 = img2.getX();
        y2 = img2.getY();
        x3 = img3.getX();
        y3 = img3.getY();

        // Se le asigna la funncion para el arrastre
        img1.setOnTouchListener(this);
        img2.setOnTouchListener(this);
        img3.setOnTouchListener(this);
        /**
         * Al tocar un frame se eliminara el pictograma asignado , retornandolo a los pictogramas para arrastrar
         */
        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame1); // metodo que borra el picto
                frame1.setImageResource(R.drawable.boxborde); // vuelve asignar el Drawable de cuadrado bordeado
                pictosJugador[0] = null;
            }
        });
        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame2);
                frame2.setImageResource(R.drawable.boxborde);
                pictosJugador[1] = null;
            }
        });
        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame3);
                frame3.setImageResource(R.drawable.boxborde);
                pictosJugador[2] = null;
            }
        });


        /**
         * Boton que verificara si la oracion esta correcta
         * de manera que comparara el arreglo ORM con el arreglo
         * que almacena los pictos que el usuario arrastro a las frames
         */


        verificar.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             for (int i = 0; i < 3; i++) {
                                                 try {
                                                     // Comparamos los ImageView de ambos arreglos ( al tener el ORM se comparara la ID )
                                                     if (pictosJugador[i].getImagen().equals(pictosORM[i].getImagen())) {
                                                         contVerify++;

                                                         Log.d("CONTADOR VERIFICACION", Float.toString(contVerify));

                                                     }
                                                 } catch (Exception e) {
                                                     // Toast.makeText(ingame.this, "ERROR LISTA INCOMPLETA", Toast.LENGTH_LONG).show();
                                                     Log.d("ERROR", e.toString());
                                                 }
                                             }

                                             // si los 3 son iguales el ejercicio esta resuelto
                                             if (contVerify == 3) {
                                                 //player.pause();
                                                 ((GifDrawable) damperexito.getBackground()).seekToFrame(0);
                                                 ((GifDrawable) damperexito.getBackground()).start();
                                                 win.start();
                                                 damperexito.setVisibility(View.VISIBLE);

                                                 Handler handler = new Handler();
                                                 handler.postDelayed(new Runnable() {
                                                     public void run() {
                                                         ((GifDrawable) damperexito.getBackground()).reset();
                                                         ((GifDrawable) damperexito.getBackground()).stop();
                                                         damperexito.setVisibility(View.INVISIBLE);

                                                     }
                                                 }, ((GifDrawable) damperexito.getBackground()).getDuration());


                                                 ((GifDrawable) verificar.getBackground()).reset();
                                                 ((GifDrawable) verificar.getBackground()).start();
                                                 ((GifDrawable) verificar.getBackground()).setLoopCount(0);


                                                 //Toast.makeText(ingame.this, "SOLUCIONADO", Toast.LENGTH_LONG).show();
                                                 isOK = true;
                                                 if (cont < 11) {
                                                     pictosJugador[0] = null;
                                                     pictosJugador[1] = null;
                                                     pictosJugador[2] = null;
                                                     cont++;
                                                     nivelSiguiente(cont, oracionList);

                                                 } else {
                                                     Intent dift = new Intent(ingame.this, MainActivity.class);
                                                     startActivity(dift);
                                                     //vamos a decir  "FELICITACIONES"
                                                     //CREDITOS
                                                     // O VA AL MAIN ACTIVITY
                                                 }


                                             } else {
                                                 isOK = false;
                                                 //player.stop();
                                                 frame1.setImageResource(R.drawable.boxborderojo);
                                                 frame2.setImageResource(R.drawable.boxborderojo);
                                                 frame3.setImageResource(R.drawable.boxborderojo);
                                                 pictosJugador[0] = null;
                                                 pictosJugador[1] = null;
                                                 pictosJugador[2] = null;
                                                 //Toast.makeText(ingame.this, "NO SOLUCIONADO", Toast.LENGTH_LONG).show();
                                                 nivelSiguiente(cont, oracionList);//vuelve a poner la misma oracion
                                             }
                                             // siempre se reinicia el contador , en caso de que sea incorrecto y correcto
                                             contVerify = 0;
                                         }
                                     }
        );

    }//end oncreated


    //Metodo que decodifica un String imagen en base64 para obtener su imagen
    public Bitmap getDecodeImage(String imagen) {
        byte[] dataDec = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(dataDec, 0,
                dataDec.length);
        return bitmap;
    }


    /**
     * Metodo que eliminara el pictograma asignado al frame seleccionado;
     * donde "x" e "y" son las coordenadas en las que el pictgrama retornara
     *
     * @param frame // = frame seleccionado con un click por el jugador
     */
    private void borrarPicto(ImageView frame) {

        if (frame.getDrawable().equals(img1.getDrawable())) {

            img1.setVisibility(View.VISIBLE);
            img1.setX(50);
            img1.setY(50);
        } else if (frame.getDrawable().equals(img2.getDrawable())) {
            img2.setVisibility(View.VISIBLE);
            img2.setX(550);
            img2.setY(50);
        } else if (frame.getDrawable().equals(img3.getDrawable())) {
            img3.setVisibility(View.VISIBLE);
            img3.setX(300);
            img3.setY(225);
        }
    }

    /**
     * Metodo que controla el arrastre de los pictogramas
     * donde en
     *
     * @param v
     * @param event
     * @return
     */

    public boolean onTouch(View v, MotionEvent event) {
        PointF StartPT = new PointF();
        PointF DownPT = new PointF();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                StartPT = new PointF(v.getX(), v.getY());
                PointF mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);

                v.setX(StartPT.x + mv.x - 100);
                v.setY(StartPT.y + mv.y - 100);

                break;
            case MotionEvent.ACTION_DOWN:
                DownPT.x = event.getX();
                DownPT.y = event.getY();
                x2 = DownPT.x;
                y2 = DownPT.y;
                Log.d("testeo", x2 + "-" + y2);
                break;
            case MotionEvent.ACTION_UP:

                ImageView img = (ImageView) v;

                int frmLen = 85;
                //si la imagen esta en el magen  X y en Y
                //voy a crear una variable porque al estar en el rango es imprudente y falso, no acertivo indicar que
                //dentro del nuestro frame se encuentra la imagen que deceamos dejar allí.
                //la variable guardala imagen que contiene l frame 1 antes de poner en ella la nuevaimagen

                if (margenX(img.getX(), frame1.getX()) && margenY(img.getY(), frame1.getY())) {

                    //cambiamos la imagen del frame
                    //pero si ya tenemos una imagen en el vector, devlvmos esta a su posicion
                    if (pictosJugador[0] == null) {
                        frame1.setImageDrawable(img.getDrawable());
                        img.setVisibility(View.INVISIBLE);
                        Pictograma p = new Pictograma();
                        p.setNombre(picto1.getNombre());
                        p.setImagen(img);
                        pictosJugador[0] = p;
                    } else {
                        if (img.getDrawable().equals(img1.getDrawable())) {
                            img.setX(50);
                            img.setY(50);
                        } else {
                            if (img.getDrawable().equals(img2.getDrawable())) {
                                img.setY(50);
                                img.setX(550);

                            } else {
                                img.setY(225);
                                img.setX(300);
                            }
                        }

                    }


                } else if (margenX(v.getX(), frame2.getX()) && margenY(v.getY(), frame2.getY())) {
                    if (pictosJugador[1] == null) {
                        frame2.setImageDrawable(img.getDrawable());
                        img.setVisibility(View.INVISIBLE);
                        Pictograma p = new Pictograma();
                        p.setNombre(picto1.getNombre());
                        p.setImagen(img);
                        pictosJugador[1] = p;
                    } else {
                        if (img.getDrawable().equals(img1.getDrawable())) {
                            img.setX(50);
                            img.setY(50);
                        } else {
                            if (img.getDrawable().equals(img2.getDrawable())) {
                                img.setY(50);
                                img.setX(550);

                            } else {
                                img.setY(225);
                                img.setX(300);
                            }
                        }

                    }

                } else if (margenX(v.getX(), frame3.getX()) && margenY(v.getY(), frame3.getY())) {
                    if (pictosJugador[2] == null) {
                        frame3.setImageDrawable(img.getDrawable());
                        img.setVisibility(View.INVISIBLE);
                        Pictograma p = new Pictograma();
                        p.setNombre(picto1.getNombre());
                        p.setImagen(img);
                        pictosJugador[2] = p;
                    } else {
                        if (img.getDrawable().equals(img1.getDrawable())) {
                            img.setX(50);
                            img.setY(50);
                        } else {
                            if (img.getDrawable().equals(img2.getDrawable())) {
                                img.setY(50);
                                img.setX(550);

                            } else {
                                img.setY(225);
                                img.setX(300);
                            }
                        }

                    }
                }
                Log.d("testeo", x2 + "-" + y2);
                Log.d("testeo", v.getX() + "-" + v.getY());
                break;
            default:
                break;
        }

        return true;
    }

    private boolean margenX(float imgX, float frameX) {
        if ((imgX <= (frameX + 85)) && (imgX >= (frameX - 85))) {
            return true;

        }
        return false;
    }

    private boolean margenY(float imgY, float frameY) {
        if ((imgY <= (frameY + 85)) && (imgY >= (frameY - 85))) {
            return true;
        }
        return false;
    }

    private void nivelSiguiente(int n, List<Oracion> oracionList) {


        //player = MediaPlayer.create(this, R.raw.cancionpp);
        //player.setLooping(true);
        //player.start();
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
        //selecciono la oracion que esta guardada en la lista
        //donde esta la imagen morfosintactica en la imagenView coloco la nueva imagen
        morfoSint.setImageBitmap(getDecodeImage(oracionList.get(n).getImagen()));
        //tambien agrego los correspondientes pictos relacionados con la nueva imagen
        img1.setImageBitmap(getDecodeImage(oracionList.get(n).palabras.get(0).getImagen()));
        img2.setImageBitmap(getDecodeImage(oracionList.get(n).palabras.get(1).getImagen()));
        img3.setImageBitmap(getDecodeImage(oracionList.get(n).palabras.get(2).getImagen()));
        if (isOK) {
            frame1.setImageResource(R.drawable.boxborde);
            frame2.setImageResource(R.drawable.boxborde);
            frame3.setImageResource(R.drawable.boxborde);

        }


        //frame2.setX(333);
        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.VISIBLE);
        img3.setVisibility(View.VISIBLE);
        int ran1 = (int) (Math.random() * 3) + 1;
        if (ran1 == 1) {
            img1.setY(50);
            img1.setX(50);

            img2.setY(50);
            img2.setX(550);

            img3.setY(225);
            img3.setX(300);


        } else {
            if (ran1 == 2) {
                img2.setY(50);
                img2.setX(50);

                img3.setY(50);
                img3.setX(550);

                img1.setY(225);
                img1.setX(300);


            } else {
                img3.setY(50);
                img3.setX(50);

                img1.setY(50);
                img1.setX(550);

                img2.setY(225);
                img2.setX(300);
            }
        }


        // Se instancian los objetos pictogramas con su respectiva ImageView del XML
        picto1 = new Pictograma();
        picto2 = new Pictograma();
        picto3 = new Pictograma();
        picto1.setImagen((ImageView) findViewById(R.id.picto1));
        picto2.setImagen((ImageView) findViewById(R.id.picto2));
        picto3.setImagen((ImageView) findViewById(R.id.picto3));

        // El arreglo B representa
        pictosORM[0] = picto1;
        pictosORM[1] = picto2;
        pictosORM[2] = picto3;

        x2 = img2.getX();
        y2 = img2.getY();

        // Se le asigna la funncion para el arrastre
        img1.setOnTouchListener(this);
        img2.setOnTouchListener(this);
        img3.setOnTouchListener(this);
        /**
         * Al tocar un frame se eliminara el pictograma asignado , retornandolo a los pictogramas para arrastrar
         */
        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame1); // metodo que borra el picto
                frame1.setImageResource(R.drawable.boxborde); // vuelve asignar el Drawable de cuadrado bordeado
                pictosJugador[0] = null;
            }
        });
        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame2);
                frame2.setImageResource(R.drawable.boxborde);
                pictosJugador[1] = null;
            }
        });
        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPicto(frame3);
                frame3.setImageResource(R.drawable.boxborde);
                pictosJugador[2] = null;
            }
        });
    }//end metodh nivel siguiente
    @Override
    protected void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        player.stop();
        super.onPause();
    }

}//end ingame




