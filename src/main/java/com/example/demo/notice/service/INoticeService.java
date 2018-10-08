package com.example.demo.notice.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.example.demo.notice.domain.Notice;

public interface INoticeService {
	public Notice save(Notice entity);
	public Optional<Notice> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Page<Notice> findAll(Specification<Notice> spec, Pageable pageable);
	

}

