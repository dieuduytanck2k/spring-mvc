package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

// Để NewController có thể autowired NewService thì cần @Service được thiết kế cho lớp service
// Thiết kế theo kiến trúc 3 layer controller, service và dao
// Nhúng vào tầng service
@Service
public class NewService implements INewService {
	
	//Autowired có sẵn trong spring
	// để sử dụng Autowired trong file applicationContext phải có đoạn context
	// dependency injection nghĩa là các controller khác nhau muốn dùng cùng 1 service này đều được
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent(); // select order by và limit
		for (NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO); 
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOne(id); // hàm findOne do Spring 4.3.x cung cấp
		return newConverter.toDto(entity);
	}

	@Override
	@Transactional
	public NewDTO save(NewDTO dto) { // vừa thêm bài viết vừa update bài viết
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if(dto.getId()!=null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew, dto); // override lại new cũ
		}else {
			newEntity = newConverter.toEntity(dto);
			newEntity.setCategory(category);
		}
		
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			newRepository.delete(id);
		}
	}

}
