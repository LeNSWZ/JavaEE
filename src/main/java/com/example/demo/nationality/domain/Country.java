package com.example.demo.nationality.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_country")
public class Country {
	private int id;
	/*英文简写*/
	private String abbr;
	/*英文全称*/
	private String english;
	/*中文名称*/
	private String note;
	/*中文拼音缩写*/
	private String pycode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public String getAbbr() {
		return abbr;
	}
	public String getEnglish() {
		return english;
	}
	public String getNote() {
		return note;
	}
	public String getPycode() {
		return pycode;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	
	
}
