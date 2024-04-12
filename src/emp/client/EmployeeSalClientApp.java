package emp.client;

import java.util.List;
import java.util.Scanner;

import adminpanel.AdminLogin;
import emp.model.AttendenceModel;
import emp.model.EmployeeModel;
import emp.model.SalaryModel;
import emp.repository.EmployeeRepository;
import emp.repository.SalaryRepository;
import emp.service.AttendenceService;
import emp.service.EmployeeService;
import emp.service.SalaryService;

public class EmployeeSalClientApp {

	public static <EmployeeUpdate> void main(String[] args) {
		Scanner xyz = new Scanner(System.in);
		int choice;
		AdminLogin admin = null;
		EmployeeRepository empRepo=new EmployeeRepository();
		EmployeeService service = new EmployeeService();
		AttendenceService atService = new AttendenceService();
		SalaryService salser=new SalaryService();
		SalaryRepository salRepo=new SalaryRepository();
		System.out.println("Enter UserName:");
		String user = xyz.nextLine();

		System.out.println("Enter Password:");
		String password = xyz.nextLine();// 1212

		if (service.getAdmin() != null) {
			admin = service.getAdmin();

			if (admin.getUsername().equals(user) && admin.getPassword().equals(password))

			{
				System.out.println("Verified\n");
				

				do {
					System.out.println("1:Add,View,Delete,Update and Search employee ");
					System.out.println("2:Track Attendence");
					System.out.println("3.Calculate salary");
					System.out.println("4.Check salary by name");
					System.out.println("5:Show all employee salary datewise");
					System.out.println("6:Show all employee salary monthwise");
					System.out.println("7:Show all employee salary yearwise");
					System.out.println("8:Show employee salary between two date");
					System.out.println("9:Show best attended employee in month");
					System.out.println("10:Show best attended employee in year");
					System.out.println("11:Monthbonas for employees");
					System.out.println("12:Yearbonas for employees");
					System.out.println("Enter your Choice..........");
					choice = xyz.nextInt();

					switch (choice) {
					case 1:

						do {
							System.out.println("\n1.Add Employee");
							System.out.println("2.View Employee");
							System.out.println("3.Delete Employee");
							System.out.println("4.Update Employee");
							System.out.println("5.Search Employee");
							System.out.println("6.Exit\n");
							System.out.println("Enter your choice");
							choice = xyz.nextInt();

							switch (choice) {
							case 1:
								xyz.nextLine();
								System.out.println("Enter full name,email,username,contact ,department,join_date,salary-package");
								
								String name = xyz.nextLine();
								String email = xyz.nextLine();
								String username=xyz.nextLine();
								String contact = xyz.nextLine();
								String deptname=xyz.nextLine();
								String join_date=xyz.nextLine();
								int salary = xyz.nextInt();
								EmployeeModel empmodel = new EmployeeModel(name, email,username,contact,deptname,join_date,salary);
								int a=empRepo.getIDByDepartment(deptname);
								int result;

								result = service.isAddEmployee(empmodel,deptname);

								String str = (result == 1) ? "Employee Added Successfully..........."
										: (result == -1) ? "Employee already present......."
												: "Some problem is there.........";
								System.out.println(str);
								break;

							case 2:
								List<EmployeeModel> elist = service.isViewEmployee();
								
								if(elist!=null)
								{
                                System.out.println("All Employees are:");
								for (EmployeeModel employeeModel : elist) {
									System.out.println("id: "+employeeModel.getEid() + "\tName: " + employeeModel.getName() + "\tEmail: " +employeeModel.getEmail() +"\tUsername: "+employeeModel.getUsername() +"\tContact: " +employeeModel.getContact() + "\tDepartment: "+employeeModel.getDeptname()+"\tJoining Date: " +employeeModel.getJoin_date() + "\tPackage: " +employeeModel.getSalary());
                                   	}
								}
								else {
									System.out.println("There is no employee data found");
								}
								break;
							case 3:
								xyz.nextLine();
                               System.out.println("Enter Employees id for delete employee");
                                int id=xyz.nextInt();
                               
                                	boolean b =service.isEmployeeDelete(id);
                                	if(b) {
                                	System.out.println("Employee delete successfully......");
                                	}
                                	else
                                	{
                                		System.out.println("Some problem is there...");
                                	}
								break;
							case 4:
                               xyz.nextLine();
                               System.out.println("Enter a employee id for update employee");
                               int eid=xyz.nextInt();
                              
                               xyz.nextLine();
                               System.out.println(" Enter employee full name,email,username,contact,department,join_date,salary");
                               
                               name=xyz.nextLine();
                                email = xyz.nextLine();
                                username=xyz.nextLine();
							    contact = xyz.nextLine();
							    deptname=xyz.nextLine();
							    join_date=xyz.nextLine();
								 salary = xyz.nextInt();
//								 
								 EmployeeModel emp = new EmployeeModel(name,email,username,contact,deptname,join_date,salary);
								 a=empRepo.getIDByDepartment(deptname);
                                b=service.isUpdateEmployee(eid,emp,deptname);
                                if(b) {
                    				System.out.println("Updated succesfully");
                    			}
                    			else {
                    				System.out.println("Not updated");
                    			}
                               
								break;

							case 5:
								xyz.nextLine();
								System.out.println("Enter Employees  name for search employee");
	                                name=xyz.nextLine();
	                                List<EmployeeModel> list = service.isSearchEmployee(name);
	                                if(list!=null)
									{
	                                System.out.println("Employee is found:");
									for (EmployeeModel employeeModel : list) {
										System.out.println("id: "+employeeModel.getEid() + "\tName: " + employeeModel.getName() + "\tEmai: " +employeeModel.getEmail()+"\tUsername: "+employeeModel.getUsername() + "\tContact: " +employeeModel.getContact() + "\tDepartment: "+employeeModel.getDeptname()+"\tJoining Date: " +employeeModel.getJoin_date() + "\tPackage: " +employeeModel.getSalary());
	                                   	}
									}
									else {
										System.out.println("There is no employee data found");
									}
								break;
							
							case 6:
								break;
							
							default:
								System.out.println("Wrong choice");
							}
						} while (choice != 6);

						break;
					
					case 2:
                        xyz.nextLine();
                        System.out.println("Enter a employee username for attendence ");
                        String username=xyz.nextLine();
                        
                        boolean result;
                        result=atService.isTakeAttendence(username);
                        if(result==true)
                        {
                        	System.out.println("Attednce mark successfully...");
                        }
                        else {
                        	System.out.println("Some problem is there....");
                        }
						break;
					
					case 3:
						do {
						xyz.nextLine();
						System.out.println("1.Calculate daywise salary");
						System.out.println("2.Calculate monthwise salary");
						System.out.println("3.Calculate yearwise salary");
						System.out.println("4.Exit......");
						System.out.println("Enter your choice");
						choice=xyz.nextInt();						
						switch(choice) {
						case 1:
							xyz.nextLine();
						System.out.println("Enter a employee username for calculate day salary");
						 username=xyz.nextLine();
						 boolean b=salser.CalculateDaySalary(username);
							if(b)
							{
								System.out.println("Salary calculate successfully.....");
							}
							else {
								System.out.println("Some problem is there");
							}

						break;
						
						case 2:
							xyz.nextLine();
						 System.out.println("Enter a employee username for calculate month salary");
						 username=xyz.nextLine();
						System.out.println("Enter a month number to calculate salary");
						int month=xyz.nextInt();
					  
						 b=salser.CalculateMonthSalary(username,month);
						if(b)
						{
							System.out.println("Salary calculate successfully.....");
						}
						else {
							System.out.println("Some problem is there");
						}
						break;
						
						case 3:
							xyz.nextLine();
							System.out.println("Enter a employee username for calculate year salary");
							 username=xyz.nextLine();
							System.out.println("Enter a year  to calculate salary");
							int year=xyz.nextInt();
						     int a=salRepo.getIDByDate(username);
							 b=salser.CalculateYearSalary(username,year);
								if(b)
								{
									System.out.println("Salary calculate successfully.....");
								}
								else {
									System.out.println("Some problem is there");
								}
							break;
						default:
							System.out.println("Wrong choice");
						}
						}while(choice!=4);
						
						break;
					
					case 4:
						xyz.nextLine();
						System.out.println("Enter a employee name for view salary:");
						 username=xyz.nextLine();
						List<EmployeeModel> list = service.ViewSalaryByName(username);
						
						if(list!=null) {
							System.out.println("Salary of employee is:");
							for (EmployeeModel employeeModel : list) {
								System.out.println("id: "+employeeModel.getEid() + "\tName: " + employeeModel.getName() +"\tPackage: " +employeeModel.getSalary()+"\n");
                               	}
						}else {
								System.out.println("There is no employee data found\n");
							}
						break;
						
					    case 5:
							List<SalaryModel> elist = salser.isViewDateWiseSalary();
							
							if(elist!=null)
							{
                            System.out.println("All Employees datewise salary:");
							for (SalaryModel salModel : elist) {
								System.out.println("Date: "+salModel.getDate()+"\tName:"+salModel.getName() + "\tSalary: " +salModel.getDaysal()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
						
						case 6:
                          List<SalaryModel> emplist = salser.isViewMonthWiseSalary();
							
							if(emplist!=null)
							{
                            System.out.println("All Employees monthwise salary:");
							for (SalaryModel salModel : emplist) {
								System.out.println("Month: "+salModel.getDate()+"\tName: "+salModel.getName()+ "\tSalary: " +salModel.getDaysal()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
						case 7:
                           List<SalaryModel> emlist = salser.isViewYearWiseSalary();
							
							if(emlist!=null)
							{
                            System.out.println("All Employees yearwise salary:");
							for (SalaryModel salModel : emlist) {
								System.out.println("Year: "+salModel.getDate()+"\tName: "+salModel.getName()+ "\tSalary: " +salModel.getDaysal()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
						case 8:
							xyz.nextLine();
							System.out.println("Enter a employee username");
							username=xyz.nextLine();
							System.out.println("Enter two date to check salary in between");
							String date1=xyz.nextLine();
							String date2=xyz.nextLine();
							List<SalaryModel> dlist = salser.isViewdatebetweenSalary(username,date1,date2);
							if(dlist!=null)
							{
                            System.out.println(" Employee salary between two specified date:");
							for (SalaryModel salModel : dlist) {
								System.out.println( "Name: "+salModel.getName() + "\tSalary: " +salModel.getDatebetweensal()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
						case 9:
							xyz.nextLine();
							System.out.println("Enter the month for best find attended employee");
							int month=xyz.nextInt();
							
							List<AttendenceModel> alist = atService.BestAttendedMonthEmployee(month);
							if(alist!=null)
							{
                            System.out.println("Best attended employee in month:");
							for (AttendenceModel aModel : alist) {
								System.out.println(  "id: "+aModel.getEmpid()+"\tname: "+aModel.getName() + "\tpresenty: " +aModel.getBestcount()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
							
						case 10:
							xyz.nextLine();
							System.out.println("Enter the year for best find attended employee");
							int year=xyz.nextInt();
							
							List<AttendenceModel> ylist = atService.BestAttendedYearEmployee(year);
							if(ylist!=null)
							{
                            System.out.println(" Best attended employee in month:");
							for (AttendenceModel aModel : ylist) {
								System.out.println( "id: "+aModel.getEmpid()+"\tname: "+aModel.getName() + "\t presenty: " +aModel.getBestcount()+"\n");
                               	}
							}
							else {
								System.out.println("There is no employee data found");
							}
							break;
							
						case 11:
							xyz.nextLine();
							System.out.println("Enter month for get bonas salary");
							 month=xyz.nextInt();
//							 List<SalaryModel> blist = salser.MonthBonasEmployee(month);
//							 for (SalaryModel sModel : blist) {
//									System.out.println( "id: "+sModel.getEmpid()+"\tname: "+sModel.getName() + "\t presenty: " +sModel.getMonthbonas()+"\n");
//	                               	}
							 int b=salser.MonthBonasEmployee(month);
							 if(b>0) {
								 System.out.println("get bonas successfully");
							 }
							 else {
								 System.out.println("no bonas ");
							 }
							break;
							
						case 12:
							xyz.nextLine();
							System.out.println("Enter year for get bonas salary");
							  year=xyz.nextInt();
							 List<SalaryModel> clist = salser.YearBonasEmployee(year);
							 for (SalaryModel sModel : clist) {
									System.out.println( "id: "+sModel.getEmpid()+"\tname: "+sModel.getName() + "\t presenty: " +sModel.getYearbonas()+"\n");
	                               	}
							break;
					default:
						System.out.println("Wrong choice");
					}
				} while (true);
			} 
			else 
			{
				System.err.println("Incorrect UserName and Password");
			}

		} 
		else
		{
			System.err.println("Please Register..............................!");
		}
	}

}
