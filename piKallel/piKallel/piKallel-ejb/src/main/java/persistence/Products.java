package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Products implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	private String nameP;
	private String categorieP;
	private int stockP;
	private float priceP;
	private String descriptionP;
	private String stateP="available";
	private String imageP;

	@ManyToMany
	private List<User> users;
	
	
	@ManyToOne
	private User user;
	
	
	

	private static final long serialVersionUID = 1L;

	public Products() {
		super();
	}

	public int getIdP() {
		return idProduct;
	}

	public void setIdP(int idP) {
		this.idProduct = idP;
	}

	public String getNameP() {
		return nameP;
	}

	public void setNameP(String nameP) {
		this.nameP = nameP;
	}

	public String getCategorieP() {
		return categorieP;
	}

	public void setCategorieP(String categorieP) {
		this.categorieP = categorieP;
	}

	public int getStockP() {
		return stockP;
	}

	public void setStockP(int stockP) {
		this.stockP = stockP;
	}

	public float getPriceP() {
		return priceP;
	}

	public void setPriceP(float priceP) {
		this.priceP = priceP;
	}

	public String getDescriptionP() {
		return descriptionP;
	}

	public void setDescriptionP(String descriptionP) {
		this.descriptionP = descriptionP;
	}

	public String getStateP() {
		return stateP;
	}

	public void setStateP(String stateP) {
		this.stateP = stateP;
	}

	public String getImageP() {
		return imageP;
	}

	public void setImageP(String imageP) {
		this.imageP = imageP;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

		
}
