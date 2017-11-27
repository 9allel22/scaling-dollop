package persistence;

import java.io.Serializable;
//import java.util.List;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Accommodation
 *
 */
@Entity

public class Accomodation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAccomodation;
	private String name;
	private float price;
	private int capacity;
	private String description;
	private String accomodationType;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="accomodation")
	private List<Event>evens;
	

	public Accomodation() {
		super();
	}

	public int getIdAccomodation() {
		return this.idAccomodation;
	}

	public void setIdAccomodation(int idAccomodation) {
		this.idAccomodation = idAccomodation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccomodationType() {
		return this.accomodationType;
	}

	public void setAccomodationType(String accomodationType) {
		this.accomodationType = accomodationType;
	}

	public List<Event> getEvens() {
		return evens;
	}

	public void setEvens(List<Event> evens) {
		this.evens = evens;
	}

}
