/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsch.sample;

/**
 *
 * @author verde
 */
public class UserDTO {
	//private final String transtime;
	private int id;
	private String name;
        private String age;

	public UserDTO(int id, String name,String age) {
		this.id = id;
		this.name = name;
                this.age = age;
		
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

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }
	
}
