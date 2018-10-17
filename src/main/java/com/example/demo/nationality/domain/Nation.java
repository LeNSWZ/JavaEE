package com.example.demo.nationality.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_nation")
public class Nation {
	private int id;
	private String nationName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public String getNationName() {
		return nationName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	
	
}
