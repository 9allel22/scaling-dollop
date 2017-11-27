package services;
import java.util.List;

import javax.ejb.Local;

import persistence.User;



@Local
public interface UserServiceLocal {
	
	public User add(User u);
	public User update(User u);
	public Boolean remove(Integer id);
	public List<User> findAll();
	public User findById(Integer id);
	public User findByEmail(String l);
}
