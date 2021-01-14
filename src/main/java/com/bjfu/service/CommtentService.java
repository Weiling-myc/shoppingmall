package com.bjfu.service;

import com.bjfu.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
