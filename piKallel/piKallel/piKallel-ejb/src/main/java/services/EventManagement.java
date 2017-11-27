package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Accomodation;
import persistence.Event;
import persistence.User;

/**
 * Session Bean implementation class EventManagement
 */
@Stateless
@WebService(name = "EventPortype",
portName = "EventPort",
serviceName = "EventService",
targetNamespace = "http://ws.Event.tn/")
public class EventManagement implements EventManagementRemote, EventManagementLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EventManagement() {
	}

	@Override
	@WebMethod(operationName="addEventOperation")
	public void addEvent(Event event) {
		entityManager.persist(event);

	}

	@Override
	public void updateEvent(Event event) {
		entityManager.merge(event);
	}
	@Override
	public void updateEventById(int id) {
		entityManager.merge(findEventById(id));
	}

	@Override
	public void deleteEventById(int id) {
		entityManager.remove(findEventById(id));
	}

	@Override
	public void deleteEvent(Event event) {
		entityManager.remove(event);
	}

	@Override
	public Event findEventById(int id) {
		return entityManager.find(Event.class, id);
	}
	@Override
	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}
	@Override
	public Accomodation findAccoById(int id) {
		return entityManager.find(Accomodation.class, id);
	}
	@Override
	public Accomodation findAccoByName(String name) {
		return entityManager.find(Accomodation.class, name);
	}
	
	@Override
	public Event findEventByName(String name) {
		return entityManager.find(Event.class, name);
	}
	


	@Override
	public List<Event> findAllEvents() {
		String jpql = "SELECT u FROM Event u";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	@Override
	public List<Event> findAllEvents2(User u) {
		
		String jpql = "SELECT c FROM Event c WHERE c.user=:p";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("p", u);
		return query.getResultList();
	}

	@Override
	public List<Event> findEventByMultiChoices(String location, String Station) {

		String jpql = "SELECT e FROM Event e WHERE e.location=:t AND e.Station=:l";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("t",location);
		
		query.setParameter("l",Station);

		return query.getResultList();
	}
	
	
	@Override
	public List<Accomodation> findAllAccomodation() {
		String jpql = "SELECT s FROM Accomodation s";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();		
	}
}