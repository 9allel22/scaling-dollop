package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import persistence.Company;



@Remote
/*
@WebService
*/
public interface CompaniesServiceRemote {
	 /*@WebMethod(operationName="addingCompanyofInsurance")
	@WebResult(name="addingCompanies")
	void addCompany(@WebParam(name="param1") Company com); */

	void addCompany(Company com);
	void updateCompany(Company com);

	void deleteCompanyById(int id);

	void deleteCompany(Company com);

	Company findCompanyById(int id);

	List<Company> findAllCompanies();
	
	List<Company> findCompanyBYName(String name);
}
