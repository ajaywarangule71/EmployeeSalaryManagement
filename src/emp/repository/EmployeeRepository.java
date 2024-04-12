package emp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import adminpanel.AdminLogin;
import emp.model.EmployeeModel;

public class EmployeeRepository extends DBConfig {
	
	
	LocalDate currentDate = LocalDate.now();
	java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
	public AdminLogin getAdmin() {
		try {
			AdminLogin admin = null;

			stmt = conn.prepareStatement("select *from admin");

			rs = stmt.executeQuery();

			if (rs.next()) {

				admin = new AdminLogin();

				admin.setAdid(rs.getInt("adid"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
			}

			if (admin != null) {
				return admin;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error Genrate When Fetch Info of Admin Model: " + e);
			return null;
		}

	}

	public boolean isEmployeePresent(String name) {
		try {
			stmt = conn.prepareStatement("select *from employee where name=?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			return false;
		}
	}

	public int getIDByDepartment(String deptname) {
		try {
			stmt=conn.prepareStatement("select did from department where deptname=?");
			stmt.setString(1,deptname);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
	}
	
	public boolean isAddEmployee(EmployeeModel model, String deptname) {
		try {
			

				int did = getIDByDepartment(deptname);
			
			stmt = conn.prepareStatement("insert into employee values('0',?,?,?,?,?,?,?)");
			stmt.setString(1, model.getName());
			stmt.setString(2, model.getEmail());
			stmt.setString(3, model.getUsername());
			stmt.setString(4, model.getContact());
			stmt.setInt(5, did);
			stmt.setString(6,model.getJoin_date());
			stmt.setInt(7, model.getSalary());
			int value = stmt.executeUpdate();
			if (value > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {

			System.out.println("Error is" + ex);
			return false;
		}
	}

	public List<EmployeeModel> isViewEmployee() {
		List<EmployeeModel> list = new ArrayList<EmployeeModel>();
		try {
			stmt = conn.prepareStatement("select e.empid,e.name,e.email,e.username,e.contact,d.deptname,e.join_date,e.package from employee e inner join department d on d.did=e.did order by e.empid asc");

			rs = stmt.executeQuery();
			while (rs.next()) {
				EmployeeModel e = new EmployeeModel();
				e.setEid(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setEmail(rs.getString(3));
				e.setUsername(rs.getString(4));
				e.setContact(rs.getString(5));
				e.setDepartment(rs.getString(6));
				e.setJoin_date(rs.getString(7));
				e.setSalary(rs.getInt(8));
				list.add(e);
			}
			return list.size()>0?list:null;
			
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}
   
	public int getIDByName(String name) {
		try {
			stmt=conn.prepareStatement("select Empid from employee where name=?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
	}
	
	public boolean isEmployeeDelete(int eid) {
		try {
			stmt=conn.prepareStatement("delete from employee where Empid=?");
			stmt.setInt(1, eid);
			
			int value=stmt.executeUpdate();
			if (value > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			
			System.out.println("Error is "+ex);
		}
		return false;
	}
	
	public boolean isUpdateEmployee(int eid,EmployeeModel emp,String deptname)
	{
		int did = getIDByDepartment(deptname);
		try {
			stmt=conn.prepareStatement("update employee set name=?,email=?,username=?,contact=?,did=?,join_date=?,package=? where Empid=?");
			
			stmt.setString(1,emp.getName());
			stmt.setString(2,emp.getEmail());
			stmt.setString(3,emp.getUsername());
			stmt.setString(4,emp.getContact());
			stmt.setInt(5, did);
			stmt.setString(6,emp.getJoin_date());
			stmt.setInt(7,emp.getSalary());
			stmt.setInt(8,eid);
			int value=stmt.executeUpdate();
			if(value>0) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	
		
	}

	public List<EmployeeModel> isSearchEmployee(String name) {
		List<EmployeeModel> list = new ArrayList<EmployeeModel>();
		try {
			EmployeeModel emp = new EmployeeModel();
			stmt=conn.prepareStatement("select e.empid,e.name,e.email,e.contact,d.deptname,e.join_date,e.package from employee e inner join department d on d.did=e.did where e.name like ?");
			stmt.setString(1,"%"+name+"%");
		
		rs=stmt.executeQuery();
		if(rs.next())
		{   
		emp.setEid(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setEmail(rs.getString(3));
		emp.setContact(rs.getString(4));
		emp.setDepartment(rs.getString(5));
		emp.setJoin_date(rs.getString(6));
		emp.setSalary(rs.getInt(7));
			list.add(emp);
		}
		return list.size()>0?list:null;
		
	} catch (Exception ex) {
		System.out.println("Error is" + ex);
		return null;
	}
}

	public List<EmployeeModel> ViewSalaryByName(String name) {
		List<EmployeeModel> list = new ArrayList<EmployeeModel>();
		 try {
			 EmployeeModel emp = new EmployeeModel();
			 int eid=getIDByName(name);
			stmt=conn.prepareStatement("select empid,name,package from employee where empid=?");
			stmt.setInt(1, eid);
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				emp.setEid(rs.getInt(1));
			    emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(3));
				list.add(emp);
			}
			return list.size()>0?list:null;
		} catch (Exception ex) {
			System.out.println("Error is "+ex);
		}
		 return null; 
	 }

}
