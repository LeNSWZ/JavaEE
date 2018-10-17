package com.example.demo.nationality.domain;

public class NationalityDTO {
	private int id;
	
	private int countryId;
	/*英文简写*/
	private String abbr;
	/*英文全称*/
	private String english;
	/*中文名称*/
	private String note;
	/*中文拼音缩写*/
	private String pycode;
	
	private int nationId;
	private String nationName;
	
	public static Nationality dtoToEntity(NationalityDTO dto) {
		Nationality entity = new Nationality();
		Country country = new Country();
		country.setNote(dto.getNote());
		entity.setCountry(country);
		if (dto.getNationName() != null) {
			Nation nation = new Nation();
			nation.setNationName(dto.getNationName());
			entity.setNation(nation);
		}
		return entity;
	}
	
	public static NationalityDTO entityToDTO(Nationality entity) {
		NationalityDTO dto = new NationalityDTO();
		dto.setCountryId(entity.getCountry().getId());
		dto.setNote(entity.getCountry().getNote());
		if (entity.getNation() != null) {
			dto.setNationId(entity.getNation().getId());
			dto.setNationName(entity.getNation().getNationName());
		}		
		return dto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNationId() {
		return nationId;
	}
	public void setNationId(int nationId) {
		this.nationId = nationId;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPycode() {
		return pycode;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
}
