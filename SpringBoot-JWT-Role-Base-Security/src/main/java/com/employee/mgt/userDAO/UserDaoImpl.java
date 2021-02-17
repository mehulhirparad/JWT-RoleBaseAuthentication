package com.employee.mgt.userDAO;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.mgt.entitys.User;


@Repository
public class UserDaoImpl implements UserDao {

	//inject the session factory
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public User findByUserName(String theUserName) {
		//current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		//fetch from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	@Transactional
	public void save(User theUser) {
		// current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		System.out.println("This is from userDAOImpl " +theUser.getMobile());
		// Save to user data
		currentSession.saveOrUpdate(theUser);
	}

}
