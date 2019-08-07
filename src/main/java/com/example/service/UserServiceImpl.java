package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Login;
import com.example.domain.User;
import com.example.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
	
	@Override
    @Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
    @Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
    @Transactional
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
    @Transactional
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	@Override
	@Transactional
	public Boolean isValidUser(Login login) {
		return userDao.isValidUser(login);
	}
	
	@Override
	@Transactional
	public Boolean isValidAdmin(Login login) {
		return userDao.isValidAdmin(login);
	}

	@Override
	@Transactional
	public User findUserByLogin(Login login) {
		return userDao.findUserByLogin(login);
	}

}
