package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Company;
import persistence.User;




/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
@WebService(name = "UserPortype",
portName = "UserPort",
serviceName = "UserService",
targetNamespace = "http://ws.Event.tn/")
public class UserService implements UserServiceRemote, UserServiceLocal {


    @PersistenceContext
	private EntityManager entityManager;

	@Override
	public User add(User u) {
		entityManager.persist(u);
		entityManager.flush();
		return u;
	}

	@Override
	public User update(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean remove(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		String jpql = "SELECT com FROM User com";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
		}

	@Override
	public User findById(Integer id) {
		String jpql = "SELECT e FROM User e WHERE e.idUser =:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", id);
		return (User) query.getSingleResult();
		
	}
	@Override
	public User findByEmail(String l) {
		User user = null;
		String jpql = "SELECT u FROM User u WHERE u.email = :param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", l);
		try {
			user = (User) query.getSingleResult();
			System.out.println("User found " + user.getEmail());
			return user;
		} catch (Exception e) {
			System.err.println("User Not found");
		}

		return null;

	}

}
