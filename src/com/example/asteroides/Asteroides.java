package com.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Asteroides extends Activity {

	private Button bAcercaDe;
	private Button bSalir;
	public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();

	// private MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(Asteroides.this, ServicioMusica.class));
		setContentView(R.layout.main);
		bAcercaDe = (Button) findViewById(R.id.Button03);
		bAcercaDe.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				lanzarAcercaDe(null);
			}
		});
		
		bSalir = (Button) findViewById(R.id.Button04);
		bSalir.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				lanzarPuntuaciones(null);
			}
		});
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    	// mp = MediaPlayer.create(this, R.raw.audio);
//    	try {
//			mp.prepare();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// almacen = new AlmacenPuntuacionesPreferencias(this);
		// almacen = new AlmacenPuntuacionesFicheroExterno(this);
	}
	
    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }
    
    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        /** true -> el menu ya esta visible */
        return true;
     }
    
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.salir:
        		finish();
                break;
        	case R.id.config:
         		lanzarPreferencias(null);
         		break;
        }
    	/** true -> consumimos el item, no se propaga */
        return true;
     }
     
     public void lanzarPreferencias(View view){
        Intent i = new Intent(this, Preferencias.class);
        startActivity(i);
     }
     
     public void lanzarPuntuaciones(View view) {
    	Intent i = new Intent(this, Puntuaciones.class);
    	startActivity(i);
     }
     
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode==1234 & resultCode==RESULT_OK & data!=null) {
            int puntuacion = data.getExtras().getInt("puntuacion");
            String nombre = "Yo";
            // Mejor leerlo desde un Dialog o una nueva actividad                       //AlertDialog.Builder
            almacen.guardaPuntuacion(puntuacion, nombre, System.currentTimeMillis());
            lanzarPuntuaciones(null);
         }
      }
     
     public void lanzarJuego(View view) {
    	// Intent i = new Intent(this, Juego.class);
    	// startActivity(i);
    	 Intent i = new Intent(this, Juego.class);
    	 startActivityForResult(i, 1234);
     }
     
     @Override 
     protected void onStart() {
    	super.onStart();
    	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
     }
    	 
     @Override 
     protected void onResume() {
    	super.onResume();
    	int almacenamiento;
    	PreferenceManager.setDefaultValues(this, R.xml.preferencias, false); 
    	SharedPreferences almacenPref = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	switch (Integer.parseInt(almacenPref.getString("almacenamiento", "1"))) {
    		case 0:
    			almacen = new AlmacenPuntuacionesArray();
    			break;
    		case 1:
    			almacen = new AlmacenPuntuacionesPreferencias(this);
    			break;
    		case 2:
    			almacen = new AlmacenPuntuacionesFicheroInterno(this);
    			break;
    		case 3:
    			almacen = new AlmacenPuntuacionesFicheroExterno(this);
    			break;
    		case 4:
    			almacen = new AlmacenPuntuacionesXML_SAX(this);
    			break;
    		case 5:
    			almacen = new AlmacenPuntuacionesSQLite(this);
    			break;
    		}
    	
    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    	// mp.start();
     }
    	 
     @Override 
     protected void onPause() {
    	Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    	super.onPause();
    	// mp.pause();
     }
    	 
     @Override 
     protected void onStop() {
    	Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    	super.onStop();
    	// mp.stop();
     }
    	 
     @Override 
     protected void onRestart() {
    	super.onRestart();
    	Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
//    	try {
//			mp.prepare();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
     }
    	 
     @Override 
     protected void onDestroy() {
    	super.onDestroy();
     	stopService(new Intent(Asteroides.this, ServicioMusica.class));
    	Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
     }
     
     @Override
     protected void onSaveInstanceState(Bundle estadoGuardado){
        super.onSaveInstanceState(estadoGuardado);
//        if (mp != null) {
//        	int pos = mp.getCurrentPosition();
//            estadoGuardado.putInt("posicion", pos);
//        }
     }
   
     @Override
     protected void onRestoreInstanceState(Bundle estadoGuardado){
        super.onRestoreInstanceState(estadoGuardado);
//        if (estadoGuardado != null && mp != null) {
//        	int pos = estadoGuardado.getInt("posicion");
//            mp.seekTo(pos);
//        }
     }
}