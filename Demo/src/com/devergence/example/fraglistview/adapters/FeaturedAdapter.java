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

package com.devergence.example.fraglistview.adapters;

import java.util.ArrayList;
import java.util.List;

import com.devergence.example.fraglistview.R;
import com.devergence.example.fraglistview.fragments.FragmentFeatured;
import com.devergence.example.fraglistview.objects.CommentItem;
import com.devergence.example.fraglistview.objects.FeaturedItem;
import com.devergence.widget.fraglistview.adapter.BaseAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Este adaptador maneja una lista donde hay un elemento destacado (implementado con un fragmento totalmente funcional)
 * extendiendo el BaseAdapter
 * 
 * En este caso concreto solo tenemos un tipo de layout para los elementos de la lista (TYPE_LEFT_ITEM), si tuvieramos
 * m‡s (por ejemplo, elementos distintos para los mensajes propios y ajenos en un chat) bastaria con a–adir (TYPE_XXX_ITEM = 2...)
 * e incrementar el contador en el constructor super de la clase base
 * 
 * @author findemor
 *
 */
public class FeaturedAdapter extends BaseAdapter {
	
    private static final int TYPE_LEFT_ITEM = 1;
    private FragmentActivity mActivity;

	public FeaturedAdapter(FragmentActivity activity, int resource, Object featuredItem, List<CommentItem> items) {
		super(activity, resource, featuredItem, new ArrayList<Object>(items), 1); //solo hay un tipo de elemento en este caso
		mActivity = activity;
	}

	/**
	 * Prepara la vista de cada elemento a partir de los parametros
	 */
	@Override
	public View getViewItem(Object item, int itemType, LayoutInflater inflater,  View convertView) {

		CommentItem o = (CommentItem) item;
		
		switch(itemType)
		{
			case TYPE_LEFT_ITEM:
			convertView = inflater.inflate(R.layout.item_left, null);
			break;
		}
		
		TextView lbAuthor = (TextView) convertView.findViewById(R.id.tv_author);
		TextView lbBody = (TextView) convertView.findViewById(R.id.tv_body);
		
		lbAuthor.setText(o.getAuthor());
		lbBody.setText(o.getBody());
		
		return convertView;
	}


	/**
	 * Para una posicion dada, determina que tipo de vista es (en este ejemplo solo hay una)
	 */
	@Override
	public int getItemType(int position) {
		return TYPE_LEFT_ITEM;
	}

	/**
	 * Implementa el fragmento que va a controlar el elemento destacado
	 */
	@Override
	public Fragment getFeaturedFragment(Object item) {
		return new FragmentFeatured((FeaturedItem)item);
	}

	/**
	 * Devuelve la actividad que va a implementar la interfaz de comunicaciones con el fragmento
	 */
	@Override
	public FragmentActivity getFragmentParentFragmentActivity() {
		return mActivity;
	}


}
