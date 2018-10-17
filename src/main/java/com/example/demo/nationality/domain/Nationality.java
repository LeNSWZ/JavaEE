package com.example.demo.nationality.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 国籍实体类，如果是中国国籍，则可以继续设置民族
 * @author midMoonNight
 *
 */

@Entity
@Table(name="t_nationality")
public class Nationality {
	
	private Long id;
	//国籍
	private Country country;
	//民族
	private Nation nation;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@OneToOne(cascade = CascadeType.ALL)
	public Country getCountry() {
		return country;
	}
	@OneToOne(cascade = CascadeType.ALL)
	public Nation getNation() {
		return nation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}
	

}
