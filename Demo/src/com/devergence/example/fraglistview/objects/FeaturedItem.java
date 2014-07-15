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
package com.devergence.example.fraglistview.objects;

import java.io.Serializable;

/**
 * Esta clase implementa un elemento de demostracion para el objeto principal
 * (elemento destacado) de la lista
 * @author findemor
 *
 */
@SuppressWarnings("serial")
public class FeaturedItem implements Serializable {
	
	private String author;
	private String body;
	
	public FeaturedItem (String author, String body)
	{
		this.author = author;
		this.body = body;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public String getBody()
	{
		return this.body;
	}
	
}
