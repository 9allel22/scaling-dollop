package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import persistence.Company;
import persistence.Contract;
import persistence.User;

@Remote

public interface ContractServiceRemote {
	
	void SigneContract(Date sdate, Date edate, int montant,String des, User u, Company com);
	void deleteContract(Contract C );

	
	List<Contract> findContractsbyCompanybyID(int com);
	List<Contract> findAllContracts();
	void SigneContract(Date sdate, Date edate, int montant, String des, int iduser, int idCompany);
	void SigneContract(java.util.Date dd1, java.util.Date dd2, int a, String st,int iduser, int idCompany);
	
	void SigneContract(java.util.Date dd1, java.util.Date dd2, int a, String st, User u, Company c);
}
