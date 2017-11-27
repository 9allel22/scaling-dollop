
package EventManagement;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Event;
import services.EventManagementRemote;

public class TestAddEvent {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";

		EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);

		/*List<Event> evennements = eventManagementRemote.findEventByMultiChoices("Alpes du Sud", "Isola 200");
for (Event event : evennements) {
	System.out.println(event.getName());*/
}
		// evennement.setCapacity(22);
		// evennement.setPriceEvent(22);

		// eventManagementRemote.addEvent(evennement);

		// Accomodation acc=eventManagementRemote.findAccoByName("a");
		// System.out.println(acc);

	

}
