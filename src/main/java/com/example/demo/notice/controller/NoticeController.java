package com.example.demo.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.beans.BeanUtils;
import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.notice.domain.Notice;
import com.example.demo.notice.domain.NoticeQueryDTO;
import com.example.demo.notice.service.INoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private INoticeService noticeService;
	
	@GetMapping
	public Page<Notice> getPage(NoticeQueryDTO noticeQueryDTO , ExtjsPageRequest pageRequest) 
	{
		return noticeService.findAll(null, pageRequest.getPageable());
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String update(@PathVariable("id") Long myId,@RequestBody Notice dto) 
	{
		try {
			Notice entity = noticeService.findById(myId).get();
			BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
			noticeService.save(entity);
			return "success:true";
		} catch (Exception e) {
			return "success:false";
		}
	}	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Notice notice) 
	{
		try {
			noticeService.save(notice);
			return "success:true";
		} catch (Exception e) {
			return "success:false";
		}
	}
	
	@DeleteMapping(value="{id}")
	public String delete(@PathVariable("id") Long id) 
	{
		try {
			if(id!=null) {
				noticeService.deleteById(id);
			}
			return "success:true";
		} catch (Exception e) {
			return "success:false";
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				noticeService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	
}
