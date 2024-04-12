package emp.service;

import java.util.List;

import adminpanel.AdminLogin;
import emp.model.EmployeeModel;
import emp.repository.EmployeeRepository;

public class EmployeeService<EmployeeUpdate> {

	EmployeeRepository empRepo = new EmployeeRepository();
	

	public int isAddEmployee(EmployeeModel model,String deptname) {
		return (empRepo.isEmployeePresent(model.getName())) ? -1 : (empRepo.isAddEmployee(model, deptname)) ? 1 : 0;
	}
	
	public AdminLogin getAdmin()
	{
	
			return empRepo.getAdmin();
	}
	
	public List<EmployeeModel> isViewEmployee() {
		return  empRepo.isViewEmployee();
	}
	
	public boolean isEmployeeDelete(int id) {
		return empRepo.isEmployeeDelete(id);
	}
	
	public List<EmployeeModel> isSearchEmployee(String name) {
		return empRepo.isSearchEmployee(name);
	}
	
	public boolean isUpdateEmployee(int eid,EmployeeModel emp,String deptname){
		return empRepo.isUpdateEmployee(eid,emp,deptname);
	}
	
	public List<EmployeeModel> ViewSalaryByName(String name) {
		 return empRepo.ViewSalaryByName(name);
	 }

	
}
	
	



