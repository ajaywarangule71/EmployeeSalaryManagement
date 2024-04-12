package emp.service;

import java.util.List;

import emp.model.AttendenceModel;
import emp.model.EmployeeModel;
import emp.model.SalaryModel;
import emp.repository.SalaryRepository;

public class SalaryService {
	SalaryRepository salRepo = new SalaryRepository();
	
	public boolean CalculateDaySalary(String username) {
		return salRepo.CalculateDaySalary(username);
	}
	
	public boolean CalculateMonthSalary(String username,int month) {
		return salRepo.CalculateMonthSalary(username,month);
	}
	
	public boolean CalculateYearSalary(String username,int year) {
		return salRepo.CalculateYearSalary(username,year);
	}
	
	public List<SalaryModel> isViewDateWiseSalary() {
		return  salRepo.isViewDateWiseSalary();
	}

	public List<SalaryModel> isViewMonthWiseSalary() {
		return  salRepo.isViewMonthWiseSalary();
	}
	
	public List<SalaryModel> isViewYearWiseSalary() {
		return  salRepo.isViewYearWiseSalary();
	}

	public List<SalaryModel> isViewdatebetweenSalary(String username,String date1, String date2) {
		
		return salRepo.isViewdatebetweenSalary(username,date1,date2);
	}

	public int MonthBonasEmployee(int month) {
		return salRepo.MonthBonasEmployee(month);
	}

	public List<SalaryModel> YearBonasEmployee(int year) {
		return salRepo.YearBonasEmployee(year);
	}

	
}
