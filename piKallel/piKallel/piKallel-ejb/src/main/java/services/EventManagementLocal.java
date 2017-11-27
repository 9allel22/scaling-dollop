package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Accomodation;
import persistence.Event;
import persistence.User;

@Local
public interface EventManagementLocal {
	void addEvent(Event event);

	void updateEvent(Event event);
	
	void updateEventById(int id);


	void deleteEventById(int id);

	void deleteEvent(Event event);

	Event findEventById(int id);

	List<Event> findAllEvents();


	List<Event> findEventByMultiChoices(String location, String Station) ;

	List<Accomodation> findAllAccomodation();

	Event findEventByName(String name);

	Accomodation findAccoById(int id);

	Accomodation findAccoByName(String Name);

	User findUserById(int id);

	List<Event> findAllEvents2(User u);

	

}