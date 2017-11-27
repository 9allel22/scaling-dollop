
package EventManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Event;
import services.EventManagementRemote;
import services.UserServiceRemote;

public class TestUpdateEvent {
	
	/*public static void doUpdateEvent(EventManagementRemote remote) {

		
		Event evennement=remote.findEventById(1);
			evennement.setCapacity(88);
			evennement.setPriceEvent(88);


		}*/

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		String jndi = "piKallel-ear/piKallel-ejb/UserService!services.UserServiceRemote";
						
		UserServiceRemote eventManagementRemote = (UserServiceRemote) context.lookup(jndi);


		System.out.println(eventManagementRemote.findAll());
}}
		
		 

 