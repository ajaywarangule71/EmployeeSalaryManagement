package emp.model;

public class EmployeeModel {

	private int eid;
	private String name;
	private String email;
	private String username;
	private String contact;
	private String join_date;
	private int salary;
	private String department;

	public EmployeeModel() {

	}

	public EmployeeModel( String name, String email,String username, String contact,String department,String join_date, int salary) {
		
		this.name = name;
		this.email = email;
		this.username=username;
		this.contact = contact;
		this.salary = salary;
		this.department=department;
		this.join_date=join_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptname() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
}
