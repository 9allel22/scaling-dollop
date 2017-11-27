package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Company;
import persistence.Contract;
import persistence.User;


/**
 * Session Bean implementation class ContractService
 */
@Stateless
public class ContractService implements ContractServiceRemote, ContractServiceLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
    /**
     * Default constructor. 
     */
	
	@EJB
	private UserServiceRemote basicOpsLocal;
	
	@EJB
	private ContractServiceLocal contractServiceLocal;
    public ContractService() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void deleteContract(Contract C) {
		
		entityManager.remove(C);
		
	}


	@Override
	public List<Contract> findAllContracts() {
		String jpql = "SELECT com FROM Contract com";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}


	@Override
	public void SigneContract(Date sdate, Date edate, int montant, String des, int iduser, int idCompany) {

    Contract contract = new Contract(sdate,edate,montant, des , iduser ,idCompany);
    entityManager.persist(contract);
	}

	

	@Override
	public void SigneContract(Date sdate, Date edate, int montant, String des, User u, Company com) {
		

		   Contract contract = new Contract(sdate,edate,montant, des , u ,com);
		    entityManager.persist(contract);		
	}

	

	@Override
	public List<Contract> findContractsbyCompanybyID(int idcom) {
		String jpql = "SELECT z FROM Contract z WHERE z.company.id=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idcom);
		return query.getResultList();
	}


	@Override
	public void SigneContract(java.util.Date sdate, java.util.Date edate, int montant, String des, int iduser,
			int idCompany) {

	    Contract contract = new Contract(sdate,edate,montant, des , iduser ,idCompany);
	    entityManager.persist(contract);
		
	}


	@Override
	public void SigneContract(java.util.Date sdate, java.util.Date edate, int montant, String des, User u,
			Company com) {
		 Contract contract = new Contract(sdate,edate,montant, des , u ,com);
		    entityManager.persist(contract);		
	}



	
	
	
	/*
	public void assingContracttouser(List<Contract> com, User u) {

		List<Contract> contratcs = contractServiceLocal.findAllContracts();
		
		for (Contract r : contratcs) {
			contratcs.add(r);
		}	
		
		u.setContract(contratcs);

		basicOpsLocal.update(u);
	}
	*/
	
}
