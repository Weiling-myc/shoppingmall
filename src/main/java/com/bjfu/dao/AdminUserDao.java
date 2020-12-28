package com.bjfu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjfu.entity.AdminUser;
/**
 * 管理员数据层
 * @author bjfu
 * @2019年10月7日下午10:22:21
 */
public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
	AdminUser findByUsernameAndPassword(String username, String pwd);
}
