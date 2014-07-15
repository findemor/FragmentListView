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
package com.devergence.widget.fraglistview.adapter;

import java.util.List;
import com.devergence.widget.fraglistview.R;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Adaptador que controla una lista de elementos que se mostrar‡n a continuaci—n de un elemento destacado 
 * (manejado por un fragmento independiente de la lista).
 * El efecto producido es el mismo que el de tener una vista con un fragmento y un listview a continuaci—n,
 * ambos dentro de un scroll view.
 * 
 * @author findemor
 */
public abstract class BaseAdapter extends ArrayAdapter<Object> {

	/**
	 * Identificador del tipo de elemento para el elemento destacado
	 */
    private static final int TYPE_DETAIL = 0;
    
    private int TYPE_MAX_COUNT;
	private LayoutInflater mInflater;
	private Object mFeaturedItem;
	private List<Object> mItems;
	private View mView0Cached;
	
	/**
	 * Adaptador que gestiona una lista donde su primer elemento es un elemento destacado y manejado por un Fragmento
	 * 
	 * @param ctx
	 * @param resource ID del recurso del layout que contiene la vista de los items
	 * @param featuredItem Elemento que va a destacarse
	 * @param items Lista de elementos secundarios
	 * @param itemTypesCount cantidad de tipos de elemento de la lista secundaria
	 */
	public BaseAdapter(Context ctx, int resource, Object featuredItem, List<Object> items, int itemTypesCount) {
		super(ctx, resource, items);
		
		this.mItems = items;
		this.mFeaturedItem = featuredItem;
		this.TYPE_MAX_COUNT = itemTypesCount + 1;
		
		mInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * Devuelve el tipo de vista en funci—n de la posici—n de toda la listView
	 */
	@Override
	public int getItemViewType(int position) {
		if (position == 0)
		{
			return TYPE_DETAIL;
		}else
		{
			//Object o = mItems.get(position - 1);
			return getItemViewTypeOnlyItem(position - 1);
		}
	}
	
	/**
	 * Devuelve el tipo de la vista en funci—n de la posici—n de la lista de elmentos secundarios
	 * @param position
	 * @return
	 */
	public int getItemViewTypeOnlyItem(int position) {
		//Object o = mItems.get(position);
		//return TYPE_ITEM;
		return getItemType(position);
	}
	
	/**
	 * Devuelve el numero total de elementos de la lista incluyendo el destacado
	 */
	@Override
	public int getCount() {
		int count = super.getCount() + 1; //detail item
		return count;
	}
	
	/**
	 * Devuelve el numero de tipos de elementos en la lista
	 */
	@Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }
	
	/**
	 * Devuelve la vista apropiada para cada tipo de elemento, almacenando una cache con el destacado
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (position == 0)
		{
			if (mView0Cached == null) mView0Cached = getCustomViewFeatured(convertView);
			return mView0Cached;
		}else
		{
			return getCustomViewItem(position - 1, convertView);
		}
	}
	
	/**
	 * Devuelve la vista para el elemento destacado
	 * 
	 * @param convertView
	 * @return
	 */
	private View getCustomViewFeatured(View convertView)
	{		
		View fakeView = mInflater.inflate(R.layout.frame_fragment_featured, null);

		FragmentActivity activity = getFragmentParentFragmentActivity();
		Fragment fragment = getFeaturedFragment(mFeaturedItem);
		
		FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_fragment_featured, fragment); //frame should exists
        fragmentTransaction.commit(); 	
		
		return fakeView;
	}
	
	/**
	 * Devuelve la vista para los elementos secundarios
	 * 
	 * @param position
	 * @param convertView
	 * @return
	 */
	private View getCustomViewItem(int position, View convertView)
	{
		int type = getItemViewTypeOnlyItem(position);
		Object item = mItems.get(position);
		return getViewItem(item, type, mInflater, convertView);
	}
	
	/**
	 * Devuelve la vista personalizada para el elemento de la lista secundaria
	 * @param item elemento de la lista
	 * @param itemType identificador del tipo de elemento
	 * @param convertView 
	 * @return
	 */
	public abstract View getViewItem(Object item, int itemType, LayoutInflater inflater, View convertView);
	
	/**
	 * Devuelve la instancia inicializada del fragmento para el elemento destacado
	 * @param item
	 * @param convertView
	 * @return
	 */
	public abstract Fragment getFeaturedFragment(Object item);
	
	public abstract FragmentActivity getFragmentParentFragmentActivity();
	
	/**
	 * Devuelve un identificador para el tipo de elemento de la lista secundario. No esta permitido devolver 0
	 * @param position
	 * @return
	 */
	public abstract int getItemType(int position);

}
