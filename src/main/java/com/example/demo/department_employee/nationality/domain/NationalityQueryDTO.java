package com.example.demo.department_employee.nationality.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class NationalityQueryDTO {
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
	
	public static Specification<Nationality> getWhereClause(final NationalityQueryDTO queryDTO) {
		return new Specification<Nationality>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Nationality> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				if (queryDTO.getCountryId() > 0) {
					predicates.add(criteriaBuilder.equal(root.get("country").get("id").as(Integer.class), queryDTO.getCountryId()));
				}
				if (StringUtils.isNotBlank(queryDTO.getNote())) {
					predicates.add(criteriaBuilder.like(root.get("country").get("note").as(String.class), "%"+queryDTO.getNote()+"%"));
				}
				if (queryDTO.getNationId() > 0) {
					predicates.add(criteriaBuilder.equal(root.get("nation").get("id").as(Integer.class), queryDTO.getNationId()));
				}
				if (StringUtils.isNotBlank(queryDTO.getNationName())) {
					predicates.add(criteriaBuilder.like(root.get("nation").get("nationName").as(String.class), "%"+queryDTO.getNationName()+"%"));
				}
				
				Predicate[] pre = new Predicate[predicates.size()];
				
				return query.where(predicates.toArray(pre)).getRestriction();
			}
			
		};
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
	
	
}
