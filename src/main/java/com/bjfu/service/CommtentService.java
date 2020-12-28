
package com.bjfu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bjfu.entity.Comment;
/**

 * 类说明 :评论服务接口

 */

public interface CommtentService {
	/**
	 * 分页查询所有
	 *
	 * @param pageable
	 * @return
	 */
	Page<Comment> findAll(Pageable pageable);
}
