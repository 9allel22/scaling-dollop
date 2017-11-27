package services;


import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Company;



/**
 * Session Bean implementation class CompaniesService
 */
@Stateless

/*
@WebService(name="ServiceCompanyPortType",portName="CompaniesPort",serviceName="CompaniesService",targetNamespace="http://iski.tn"
,endpointInterface="Service.CompaniesServiceRemote") */

public class CompaniesService implements CompaniesServiceRemote, CompaniesServiceLocal {

    /**
     * Default constructor. 
     */
	
    public CompaniesService() {}

    @PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addCompany(Company com) {
		entityManager.persist(com);	
	}

	@Override
	public void updateCompany(Company com) {
		entityManager.merge(com);
		
	}

	@Override
	public void deleteCompanyById(int id) {
		entityManager.remove(findCompanyById(id));
		
	}

	@Override
	public void deleteCompany(Company com) {
		entityManager.remove(com);
	}
	

	@Override
	public Company findCompanyById(int id) {
		return entityManager.find(Company.class, id);
	}

	@Override
	public List<Company> findAllCompanies() {
		String jpql = "SELECT com FROM Company com";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	
	@Override
	public  List<Company> findCompanyBYName(String name) {
		String jpql = "SELECT e FROM Company e WHERE e.companyName =:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", name);
		return query.getResultList();
	}

	
}
