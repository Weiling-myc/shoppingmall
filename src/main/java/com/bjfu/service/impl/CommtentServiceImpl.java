
package com.bjfu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bjfu.dao.CommtentDao;
import com.bjfu.entity.Comment;
import com.bjfu.service.CommtentService;

/**

 * 类说明 :

 */
@Service
public class CommtentServiceImpl implements CommtentService {
	@Autowired
	private CommtentDao commtentDao;
	/**
	 * 分页查询所有
	 */
	@Override
	public Page<Comment> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return commtentDao.findAll(pageable);
	}

}
