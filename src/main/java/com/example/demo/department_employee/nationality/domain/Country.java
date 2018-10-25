package com.example.demo.department_employee.nationality.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * <p>
 * Title: Country
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author LiangZ
 * 
 * @date 2018年10月16日
 * 
 */
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
	/** 该国家对应的民族 */
	private List<Nation> nations;
	
	/* getter */
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Nation> getNations() {
		return nations;
	}

	/* setter */
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

	public void setNations(List<Nation> nations) {
		this.nations = nations;
	}
	
	
}
