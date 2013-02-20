package com.example.asteroides;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Puntuaciones extends ListActivity {
	Bundle savedInstanceState;
	
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puntuaciones);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				Localizacion.almacen.listaPuntuaciones(10)));
	}
}
