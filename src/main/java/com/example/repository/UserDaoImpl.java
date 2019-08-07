package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.Login;
import com.example.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
	private User user;
	
    public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
	@Override
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from User", User.class).getResultList();
	}

	@Override
	public void saveUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
	}

	@Override
	public User getUser(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
	}

	@Override
	public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.byId(User.class).load(id);
        session.delete(user);
	}

	@Override
	public Boolean isValidUser(Login login) {
		if(findUserByLogin(login) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isValidAdmin(Login login) {
		user = findUserByLogin(login);
		if(user != null && user.getRoleType().equals("admin")) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public User findUserByLogin(Login login) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM User where username = :u AND password = :p");
		query.setParameter("u", login.getUsername());
		query.setParameter("p", login.getPassword());
		return (User) query.uniqueResult();
	}
}
