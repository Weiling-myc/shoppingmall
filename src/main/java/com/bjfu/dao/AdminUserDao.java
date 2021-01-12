package com.bjfu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjfu.entity.AdminUser;
/**
 * 管理员数据层
 */
public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
	AdminUser findByUsernameAndPassword(String username, String pwd);
}
