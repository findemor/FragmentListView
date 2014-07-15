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
package com.devergence.example.fraglistview.fragments;

import com.devergence.example.fraglistview.R;
import com.devergence.example.fraglistview.interfaces.FeaturedItemInterface;
import com.devergence.example.fraglistview.objects.FeaturedItem;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragmento que maneja la visualizacion y comportamiento del elemento destacado (el primer elemento)
 * de nuestra lista
 * 
 * @author findemor
 *
 */
public class FragmentFeatured extends Fragment {

	public static final String STATEKEY_ITEM = "com.devergence.example.fraglistview.mFeaturedObject";	
 	
	/**
	 * Objeto que contiene los datos que se van a mostrar en el fragmento
	 */
	FeaturedItem mFeaturedObject;
	/**
	 * Implementaci—n de la interfaz de comunicaciones con la actividad principal
	 */
	FeaturedItemInterface mListener;

	View mView;
	
	/**
	 * Constructor del fragmento que recibe el parametro objeto
	 * @param o
	 */
	public FragmentFeatured(FeaturedItem o)
	{
		this.mFeaturedObject = o;
	}

	/**
	 * El constructor por defecto es imprescindible para el buen comportamiento del framework
	 */
	public FragmentFeatured() {	}
	
	/**
	 * Al enlazarse con la actividad, obtiene la instancia del listener que implementa la interfaz
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mListener = (FeaturedItemInterface)activity;
	}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    }
    
    /**
     * Inicializa la vista (y prepara los eventos)
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
    	restoreInstanceState(savedInstanceState);
        mView = inflater.inflate(R.layout.item_featured, container, false);
        
        /* eventos */
        Button testButton = (Button)mView.findViewById(R.id.bt_toast);
        testButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onTestButtonClick(mFeaturedObject);
			}
		});
        
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        prepareView(); 
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            saveInstanceState(outState);
    }
    
    /**
     * Almacena el estado de las variables globales
     * @param outState
     */
	void saveInstanceState(Bundle outState) {
		if (outState != null) outState.putSerializable(STATEKEY_ITEM, mFeaturedObject);
	};

	/**
	 * Restaura el estado de las variables globales
	 * @param state
	 */
	void restoreInstanceState(Bundle state) {
		if (state != null) mFeaturedObject = (FeaturedItem)state.getSerializable(STATEKEY_ITEM);
	}
	
	/**
	 * Visualiza el objeto en los elementos de la vista
	 */
	public void prepareView()
	{
		TextView author = (TextView)mView.findViewById(R.id.tv_author);
		TextView texto = (TextView)mView.findViewById(R.id.tv_body);
		author.setText(mFeaturedObject.getAuthor());
		texto.setText(mFeaturedObject.getBody());
	}
	
}
