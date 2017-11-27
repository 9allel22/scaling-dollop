package services;


import java.util.List;

import javax.ejb.Local;

import persistence.Company;



@Local
public interface CompaniesServiceLocal {
	
	
	void addCompany(Company com);

	void updateCompany(Company com);

	void deleteCompanyById(int id);

	void deleteCompany(Company com);

	Company findCompanyById(int id);

	List<Company> findAllCompanies();
	
	List<Company> findCompanyBYName(String name);
	
	
}
