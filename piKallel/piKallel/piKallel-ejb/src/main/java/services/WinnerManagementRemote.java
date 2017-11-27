package services;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import persistence.User;
import persistence.Winner;

@Remote
public interface WinnerManagementRemote {
	  public Boolean isValidWinnerUser(int w_id);
	  public String winnerOfTheDay();
	  public void add(Winner t);
	  public java.sql.Date convert(String date) throws ParseException;
	  public List findbydate(String date);
	  public User findbydate(int id_user);
	  public void update(Winner t);
	  public void remove(Integer r);
	  public Winner findById(Integer r);
	  public List<Winner> getAll();
	  public String maxwinnerdate();
    
}
