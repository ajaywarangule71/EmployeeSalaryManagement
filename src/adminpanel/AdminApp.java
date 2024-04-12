package adminpanel;
import java.sql.SQLException;
import java.util.Scanner;

import emp.model.EmployeeModel;
import emp.service.EmployeeService;
public class AdminApp {

	public static void main(String[] args) {
		
               int choice,choice1;
               int id,eid,salary,did;
               String join_date;
               String name, email, contact,username,Userpassword;
				
				AdminLogin admin = null;
				Scanner sc = new Scanner(System.in);

				EmployeeModel model;

				do {

					System.out.println("Enter 1: For Add Information.");

					System.out.println("Enter your Choice..........");
					choice = sc.nextInt();

					EmployeeService es = new EmployeeService();

					switch (choice) {

					case 1:
						sc.nextLine();
						System.err.println("Enter UserName:");
						String user = sc.nextLine();// admin
						
						System.err.println("Enter Password:");
						String password = sc.nextLine();// 1212

						if (es.getAdmin() != null) 
						{
							System.out.println("Verification Steps:");
							
							admin = es.getAdmin();

							if (admin.getUsername().equals(user) && admin.getPassword().equals(password))
								
							{
								System.err.println("Verified");
								System.out.println();

								do 
								{
									System.out.println("Enter 1: Add Employee................");
									System.out.println("Enter 2: Show Information of Employee...........");
									
									System.out.println("Update");
									System.out.println("Delete");

									System.out.println("Enter Your Choice........");
									
									 choice1 = sc.nextInt();

									switch (choice1)
									{
									  case 1:
										  sc.nextLine();
										  System.out.println("Enter   Eid  Econtact    EName     EEmail    EGender       EAdress  Username  Password");
										  
										   eid=sc.nextInt();
										   sc.nextLine();
										   name=sc.nextLine();
										   email=sc.nextLine();
										   contact=sc.nextLine();
										   join_date=sc.nextLine();
										   salary=sc.nextInt();
										   sc.nextLine();
										   username=sc.nextLine();
										   Userpassword=sc.nextLine();
										   
										   
										//EmployeeModel modell=new EmployeeModel(name,email,username,contact,did,join_date,salary);
										   System.out.println();
										   //int result=es.isAddEmployee(modell,name);
												   
										 // if(result==1)
										  {
											  System.out.println("Successfully Added............................");
										  }
										 // else
										  {
											  System.out.println("Some problem is HERE............................");
										  }
										  
										break;
										
									  case 2:
							
										  break;

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

						break;

					case 2:

					

						break;
					}

				} while (true);
			}

	}
	

	


