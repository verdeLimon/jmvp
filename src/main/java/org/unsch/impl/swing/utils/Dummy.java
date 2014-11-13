/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsch.impl.swing.utils;


public class Dummy {
	//private final String transtime;
	private int id;
	private String name;
        

	public Dummy(int id, String name) {
		this.id = id;
		this.name = name;
                
		
		//this.transtime = f.format(d);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
        @Override
        public String toString(){
        return "name: "+ this.name+" , id: "+ this.id;
        }

   
	
}
