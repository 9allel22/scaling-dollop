
package EventManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import persistence.Event;
import services.EventManagementRemote;

public class TestDeleteEvent {
	


	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		String jndi = "piKallel-ear/piKallel-ejb/EventManagement!services.EventManagementRemote";
						
		EventManagementRemote eventManagementRemote = (EventManagementRemote) context.lookup(jndi);
	
		eventManagementRemote.deleteEventById(1);

}}
		
		 

 