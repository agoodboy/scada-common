package com.ht.scada.common.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.scada.common.user.dao.UserDao;
import com.ht.scada.common.user.dao.UserRoleDao;
import com.ht.scada.common.user.entity.User;
import com.ht.scada.common.user.entity.UserRole;
import com.ht.scada.common.user.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private UserRoleDao userRoleDao;

	@Override
	public User getCurrentUser() {
		final Integer currentUserId = (Integer) SecurityUtils.getSubject().getPrincipal();
        if( currentUserId != null ) {
            return getUser(currentUserId);
        } else {
            return null;
        }
	}

	@Override
	public void createUser(String username, String password) {
		User user = new User();
        user.setUsername(username);
        user.setPassword( new Sha256Hash(password).toHex() );
        userDao.save(user);
	}
	
	@Override
	public void addNewUser(User newUser) {
		userDao.save(newUser);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(int userId) {
		return userDao.findOne(userId);
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void deleteUser(int userId) {
		userDao.delete(userId);
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
	}

	@Override
	public void createUserRole(String name, String description) {
		UserRole role = new UserRole();
		role.setName(name);
		role.setDescription(description);
		userRoleDao.save(role);
	}
	
	@Override
	public void updateUserRole(UserRole userRole) {
		userRoleDao.save(userRole);
	}
	
	@Override
	public UserRole getUserRoleByName(String name) {
		return userRoleDao.findByName(name);
	}
}
