package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Contract
 *
 */
@Entity


public class Contract implements Serializable {

	@EmbeddedId
	private ContractPk contractPk;
	private Date startDate;
	private Date endDate;
	private int Montant;
	private String description;
	private User user;
	private Company company;

	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idUser", referencedColumnName = "idUser",
	insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idCompany", referencedColumnName = "idCompany", insertable = false, updatable = false)
	public Company getCompany() {
		return company;
	}
	
	
	
	public Contract() {
		super();
	}


	public Contract(Date startDate, Date endDate, int montant, String description, User user, Company company) {
		super();
		this.contractPk=new ContractPk(user.getIdUser(),company.getIdCompany());
		this.startDate = startDate;
		this.endDate = endDate;
		this.Montant = montant;
		this.description = description;
		this.user = user;
		this.company = company;
	}

	public Contract(Date startDate, Date endDate, int montant, String description, int iduser, int idcompany) {
		super();
		this.contractPk=new ContractPk(iduser,idcompany);
		this.startDate = startDate;
		this.endDate = endDate;
		this.Montant = montant;
		this.description = description;

	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getMontant() {
		return Montant;
	}

	public void setMontant(int montant) {
		Montant = montant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	private static final long serialVersionUID = 1L;



	@EmbeddedId
	public ContractPk getContractPk() {
		return contractPk;
	}

	public void setContractPk(ContractPk contractPk) {
		this.contractPk = contractPk;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "Contract [contractPk=" + contractPk + ", startDate=" + startDate + ", endDate=" + endDate + ", Montant="
				+ Montant + ", description=" + description + ", user=" + user + ", company=" + company + "]";
	}

}
