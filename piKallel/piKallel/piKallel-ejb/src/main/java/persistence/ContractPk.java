package persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: ContractPk
 *
 */
@Embeddable
@SuppressWarnings("serial")
public class ContractPk implements Serializable {

	

	private int idUser;
	private int idCompany;
	private static final long serialVersionUID = 1L;

	

	

	public ContractPk() {
		super();
	}

	public ContractPk(int idUser, int idCompany) {
		super();
		this.idUser = idUser;
		this.idCompany = idCompany;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCompany;
		result = prime * result + idUser;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractPk other = (ContractPk) obj;
		if (idCompany != other.idCompany)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return " The Contract Between the user with is " + idUser + ", and company id" + idCompany + "]"   ;
	}

	public int getIdCompany() {
		return this.idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

}
