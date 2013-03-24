package com.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	public static AlmacenPuntuaciones almacen = 
			new AlmacenPuntuacionesArray();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
        /** true -> el menú ya está visible */
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
     
     public void lanzarJuego(View view) {
    	Intent i = new Intent(this, Juego.class);
    	startActivity(i);
     }
     
     @Override 
     protected void onStart() {
    	super.onStart();
    	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
     }
    	 
     @Override 
     protected void onResume() {
    	super.onResume();
    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
     }
    	 
     @Override 
     protected void onPause() {
    	Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    	super.onPause();
     }
    	 
     @Override 
     protected void onStop() {
    	Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    	super.onStop();
     }
    	 
     @Override 
     protected void onRestart() {
    	super.onRestart();
    	Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
     }
    	 
     @Override 
     protected void onDestroy() {
    	Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    	super.onDestroy();
     }
}