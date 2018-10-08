package com.example.demo.notice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.notice.domain.Notice;
import com.example.demo.notice.domain.NoticeQueryDTO;

public class NoticeQueryDTO{
	

	private String noticeNumber;

	@DateTimeFormat(pattern="yyyy/MM/dd")  
	private Date createTimeStart;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")  
	private Date createTimeEnd;
	
	public String getNoticeNumber() {
		return noticeNumber;
	}
	
	public void setOrderNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Notice> getWhereClause(final NoticeQueryDTO noticeQueryDTO) {
		return new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(noticeQueryDTO.getNoticeNumber())) {
					predicate.add(criteriaBuilder.like(root.get("noticeNumber").as(String.class),
							"%" + noticeQueryDTO.getNoticeNumber() + "%"));
				}
				if (null!=noticeQueryDTO.getCreateTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
							noticeQueryDTO.getCreateTimeStart()));
				}
				if (null!=noticeQueryDTO.getCreateTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),
							noticeQueryDTO.getCreateTimeEnd()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
