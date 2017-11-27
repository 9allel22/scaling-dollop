package services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Company;
import persistence.User;
import persistence.Winner;

/**
 * Session Bean implementation class WinnerManagement
 */
@Stateless
public class WinnerManagement implements WinnerManagementRemote, WinnerManagementLocal {

    /**
     * Default constructor. 
     */
    private List<Integer> integers = new ArrayList<>();
    
    public WinnerManagement() {    }
    @PersistenceContext
  	private EntityManager entityManager;

    
    
	@Override
	public Boolean isValidWinnerUser(int w_id) {

        User userWinner = new User(3);
        boolean f_return = false;
        
        UserService sc = new UserService();
      
   
        System.out.println(userWinner.getLastName());
        

        List<Winner> winners = new ArrayList<>();
        
        
        
        Boolean userfound = false;
        int index = 0;
        while (userfound == false && index < winners.size()) {
            if (winners.get(index).getId_user() == w_id) {
                userfound = true;
            }

            index++;
        }
        //check if the user with ID randomUserId still exist in the data
        if (userfound == false && userWinner != null) {
            f_return = true;
        }

        return f_return;
		
		
	}

	
	@Override
	public String winnerOfTheDay() {
		 User userMax = null;
	     User userWinner = null;
	        int Min = 1;
	        int randomUserId ;
	        
	        
	        int Max = 7;

	        do {
	            randomUserId = Min + (int) (Math.random() * ((7 - 1) + 1));
	        } 
	        while (!isValidWinnerUser(randomUserId));

	        UserService sc = new UserService();
	        
	        userWinner = sc.findById(randomUserId);
	    
	        //Add the userWinner.getId_user to the winner 
	        
	        
	        
	        return userWinner.getLastName();
	}
	
	
	

	@Override
	public void add(Winner t) {
		entityManager.persist(t);			
	}

	
	
	@Override
	public Date convert(String date) throws ParseException  {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;	}

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
	
	@Override
	public List findbydate(String date) {
		  int winnerId = 0;
		  
		  String jpql = "SELECT e FROM Winner e WHERE e.date=:param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", date);
			return  query.getResultList();                
	}

	
	@Override
	public User findbydate(int id_user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Winner t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer r) {
		entityManager.remove(findById(r));
		
	}

	@Override
	public Winner findById(Integer r) {
		return entityManager.find(Winner.class, r);
	}

	@Override
	public List<Winner> getAll() {
		String jpql = "SELECT com FROM Winner com";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
		}

	
	@Override
	public String maxwinnerdate() {
		return ("2016/11/16");
		
	//	String jpql = "SELECT date FROM Winner com";
		//Query query = entityManager.createQuery(jpql);
		//return query.getMaxResults();

	}

}
