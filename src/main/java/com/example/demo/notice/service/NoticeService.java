package com.example.demo.notice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.notice.domain.Notice;
import com.example.demo.notice.repository.NoticeRepository;


@Service
@Transactional
public class NoticeService implements INoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;

	@Override
	public Notice save(Notice entity) {
		return noticeRepository.save(entity);
	}

	@Override
	public Optional<Notice> findById(Long id) {
		return noticeRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return noticeRepository.existsById(id);
	}

	@Override
	public long count() {
		return noticeRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		noticeRepository.deleteById(id);

	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Notice> notices = (List<Notice>) noticeRepository.findAllById(idLists);
		if(notices!=null) {
			noticeRepository.deleteAll(notices);
		}
	}

	@Override
	public Page<Notice> findAll(Specification<Notice> spec, Pageable pageable) {
		return noticeRepository.findAll(spec, pageable);
	}

}
