/**
 * Copyright 2014-present findemor.es
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devergence.example.fraglistview;

import java.util.LinkedList;
import java.util.List;

import com.devergence.example.fraglistview.adapters.FeaturedAdapter;
import com.devergence.example.fraglistview.interfaces.FeaturedItemInterface;
import com.devergence.example.fraglistview.objects.CommentItem;
import com.devergence.example.fraglistview.objects.FeaturedItem;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

/**
 * La actividad principal (y unica) de la aplicacion, hereda de FragmentActivity para poder utilizar la
 * libreria de soporte y ser retrocompatible
 * 
 * implementa la interfaz de comunicaciones con el Fragmento del elemento destacado de la lista que contiene
 * 
 * @author findemor
 *
 */
public class MainActivity extends FragmentActivity
implements FeaturedItemInterface {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Inicializamos los objetos de ejemplo
		List<CommentItem> comments = new LinkedList<CommentItem>();
		comments.add(new CommentItem("Francisco", "comentario 1"));
		comments.add(new CommentItem("Alex", "comentario 2"));
		comments.add(new CommentItem("Miguel", "comentario 3"));
		comments.add(new CommentItem("Raquel", "comentario 4"));
		comments.add(new CommentItem("Chema", "comentario 5"));
		comments.add(new CommentItem("Alberto", "comentario 6"));
		comments.add(new CommentItem("Francisco", "comentario 7"));
		comments.add(new CommentItem("Maria", "comentario 8"));
		FeaturedItem featuredItem = new FeaturedItem("Manuel", "Este es el cuerpo del mensaje del elemento destacado.");
		
		//Preparamos el adaptador
		FeaturedAdapter commentsAdapter = new FeaturedAdapter(this, R.layout.item_left, featuredItem, comments);
		
		//Aplicamos el adaptador
		ListView lista = (ListView)findViewById(R.id.listView);
		lista.setAdapter(commentsAdapter);
	}

	/**
	 * Implementamos los metodos de la interfaz de comunicaciones con los fragmentos manejados
	 * como FeaturedIntemInterface
	 */
	
	
	@Override
	public void onTestButtonClick(FeaturedItem o) {
		Toast.makeText(this, "Este mensaje se genera en el fragmento pero lo maneja la actividad", Toast.LENGTH_SHORT).show();		
	}

}
