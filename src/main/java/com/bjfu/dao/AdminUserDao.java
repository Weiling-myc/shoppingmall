package com.bjfu.dao;

import com.bjfu.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 管理员数据层
 */
public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
