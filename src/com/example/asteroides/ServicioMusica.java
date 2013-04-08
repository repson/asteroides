package com.example.asteroides;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;
	private NotificationManager nm;
	private static final int ID_NOTIFICACION_CREAR = 1;

    @Override
    public void onCreate() {
          Toast.makeText(this,"Servicio creado", Toast.LENGTH_SHORT).show();
          reproductor = MediaPlayer.create(this, R.raw.audio);
          nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
          Toast.makeText(this,"Servicio arrancado "+ idArranque, Toast.LENGTH_SHORT).show();
          reproductor.start();
          Notification notificacion = new Notification(
                  R.drawable.ic_launcher,
                  "Creando Servicio de M�sica",
                  System.currentTimeMillis() );
          PendingIntent intencionPendiente = PendingIntent.getActivity(
                  this, 0, new Intent(this, Asteroides.class), 0);
          notificacion.setLatestEventInfo(this, "Reproduciendo m�sica",
               "informaci�n adicional", intencionPendiente);
          nm.notify(ID_NOTIFICACION_CREAR, notificacion);      
          return START_STICKY;
    }

    @Override
    public void onDestroy() {
          Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();
          nm.cancel(ID_NOTIFICACION_CREAR);
          reproductor.stop();
    }

    @Override
    public IBinder onBind(Intent intencion) {
          return null;
    }
}