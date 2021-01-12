package com.bjfu.dao;

import com.bjfu.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 评论数据层
 */
public interface CommtentDao extends JpaRepository<Comment, Integer> {
    /**
     * 根据商品查询评论
     *
     * @param mallId
     * @return
     */
    @Override
    Page<Comment> findAll(Pageable pageable);
}
