package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import persistence.Accomodation;
import persistence.Event;
import persistence.User;

@Remote
@WebService
public interface EventManagementRemote {
	void addEvent(Event event);

	void updateEvent(Event event);
	
	void updateEventById(int id);
	List<Event> findAllEvents2(User u);


	void deleteEventById(int id);

	void deleteEvent(Event event);
	Accomodation findAccoByName(String Name);

	Event findEventById(int id);
	Accomodation findAccoById(int id);
	List<Event> findAllEvents();
	User findUserById(int id);

	List<Accomodation> findAllAccomodation();
	List<Event> findEventByMultiChoices(String location, String Station) ;

	Event findEventByName(String name);
}