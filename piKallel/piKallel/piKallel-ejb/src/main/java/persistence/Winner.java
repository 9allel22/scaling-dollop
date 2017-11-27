package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Winner implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_winner;
	
	private String date ; 
	
	private int id_user;

	public int getId_winner() {
		return id_winner;
	}

	public void setId_winner(int id_winner) {
		this.id_winner = id_winner;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	@Override
	public String toString() {
		return "Winner [id_winner=" + id_winner + ", date=" + date + ", id_user=" + id_user + "]";
	}

	public Winner() {
		super();
	}

	public Winner(int id_winner, String date, int id_user) {
		super();
		this.id_winner = id_winner;
		this.date = date;
		this.id_user = id_user;
	}

	public Winner(String date, int id_user) {
		
		this.date = date;
		this.id_user = id_user;
	}

	
	
	
	
	
}
