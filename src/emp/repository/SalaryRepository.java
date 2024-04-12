package emp.repository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import emp.model.AttendenceModel;
import emp.model.EmployeeModel;
import emp.model.SalaryModel;
public class SalaryRepository extends DBConfig{
	AttendenceRepository atRepo=new AttendenceRepository();
	private int daysalaryid;
	private int monthsalaryid;
	private int yearsalaryid;
	private int daysalary;
	
	
	private int getDaySalaryID() {
		try {
			stmt = conn.prepareStatement("select max(dsid) from daysalary");
			rs = stmt.executeQuery();
			if (rs.next()) {
				daysalaryid = rs.getInt(1);
			}
			++daysalaryid;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
		return daysalaryid;
	}
	
	private int getMonthSalaryID() {
		try {
			stmt = conn.prepareStatement("select max(msid) from monthsalary");
			rs = stmt.executeQuery();
			if (rs.next()) {
				monthsalaryid = rs.getInt(1);
			}
			++monthsalaryid;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
		return monthsalaryid;
	}
	
	private int getYearSalaryID() {
		try {
			stmt = conn.prepareStatement("select max(ysid) from yearsalary");
			rs = stmt.executeQuery();
			if (rs.next()) {
				yearsalaryid = rs.getInt(1);
			}
			++yearsalaryid;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
		return yearsalaryid;
	}
	

	public int getIDByUserName(String username) {
		try {
			stmt=conn.prepareStatement("select Empid from employee where username=?");
			stmt.setString(1, username);
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
	
	public int getIDByDate(String username) {
		LocalDate currentDate = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
		int empid=getIDByUserName(username);
		try {
			stmt=conn.prepareStatement("select atid from attendence where date=(select max(date) from attendence where empid=?)");
			//stmt.setDate(1, sqlDate);
			stmt.setInt(1, empid);
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
	
	public int getIDByCurrentDate(String username) {
		LocalDate currentDate = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
		int empid=getIDByUserName(username);
		try {
			stmt=conn.prepareStatement("select atid from attendence where date=? and empid=?");
			stmt.setDate(1, sqlDate);
			stmt.setInt(2, empid);
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
	
	
	public boolean CalculateDaySalary(String username) {
		try {
			int daysalaryid=getDaySalaryID();
			System.out.println("salid is "+daysalaryid);
		    int count=0;
			int empid=getIDByUserName(username);
			
			stmt=conn.prepareStatement("select package from employee where empid=?");
			stmt.setInt(1, empid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int pac=rs.getInt(1);
				int daysal=pac/365;
				daysalary=daysal;
				try {
					int eid=this.getIDByUserName(username);
					//System.out.println("eid is "+eid);
					int atid=getIDByCurrentDate(username);
					//System.out.println("atid is "+atid);
					if(atid!=-1)
					{
					stmt=conn.prepareStatement("insert into daysalary values(?,?,?)");
					stmt.setInt(1, daysalaryid);
					stmt.setInt(2,daysal);
					stmt.setInt(3, eid);
					int value=stmt.executeUpdate();
					if(value>0)
					{
						eid=this.getIDByUserName(username);
						
						if (eid !=-1) {
							stmt = conn.prepareStatement("insert into empatdaysaljoin values (?,?,?)");
							stmt.setInt(1, eid);
							stmt.setInt(2, atid);
							stmt.setInt(3, daysalaryid);
							return stmt.executeUpdate()>0?true:false;
							
						} else if (eid == -1) {
							System.out.println("employee not present.......");
							return false;
						} else {
							System.out.println("Some problem is there.....");
							return false;
						}
					}
					else
					{
						return false;
					}
					}else {
						System.out.println("Employee presenty not register today");
					}
				}catch(Exception ex) {
					System.out.println("Error is  "+ex);
				}	
			} 
			}catch (Exception ex) {
				System.out.println("Error is"+ex);
			}
			
	return false;
	}
	
	
	public boolean CalculateMonthSalary(String username,int month) {
		
		try {
		   int count=0;
		   int empid=this.getIDByUserName(username);
			
		   int monthsalaryid=getMonthSalaryID();
			stmt=conn.prepareStatement("select package from employee where empid=?");
			stmt.setInt(1, empid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int pac=rs.getInt(1);
				int daysal=pac/365;
				daysalary=daysal;
			
			try {
				stmt=conn.prepareStatement("select count(empid) from attendence where empid=? and extract( month from date)=? group by empid");
				stmt.setInt(1, empid);
				stmt.setInt(2, month);
				
				rs=stmt.executeQuery();
				 if(rs.next())
				 {
					count=rs.getInt(1);
				 }
				 else
				 {
					 return false;
				 }
			}
			catch(Exception ex) {
				System.out.println("Error is"+ex);
			}
			
			int monthsal=daysal*count;
			try {
				
				int eid=this.getIDByUserName(username);
				//System.out.println("eid is "+eid);
				int atid=getIDByDate(username);
				//System.out.println("atid is "+atid);
				stmt=conn.prepareStatement("insert into monthsalary (msid,monthsal,empid) values(?,?,?)");
				stmt.setInt(1, monthsalaryid);
				stmt.setInt(2,monthsal);
				stmt.setInt(3, eid);
				int value=stmt.executeUpdate();
				if(value>0)
				{
					eid=this.getIDByUserName(username);
					
					if (eid !=-1) {
						stmt = conn.prepareStatement("insert into empatmonthsaljoin values(?,?,?)");
						stmt.setInt(1, eid);
						stmt.setInt(2, atid);
						stmt.setInt(3, monthsalaryid);
						return stmt.executeUpdate()>0?true:false;
						
					} else if (eid == -1) {
						System.out.println("employee not present.......");
						return false;
					} else {
						System.out.println("Some problem is there......ram ram");
						return false;
					}
				}
				else
				{
					return false;
				}
			}catch(Exception ex) {
				System.out.println("Error is  "+ex);
			}	
		} 
		}catch (Exception ex) {
			System.out.println("Error is"+ex);
		}
		
		return false;
	}
	
	public boolean CalculateYearSalary(String username, int year) {
		try {
			   int count=0;
				int empid=this.getIDByUserName(username);
				int yearsalaryid=getYearSalaryID();
				//System.out.println("yearsalryid is"+yearsalaryid);
				stmt=conn.prepareStatement("select package from employee where empid=?");
				stmt.setInt(1, empid);
				rs=stmt.executeQuery();
				if(rs.next()) {
					int pac=rs.getInt(1);
					int daysal=pac/365;
					daysalary=daysal;
				
				try {
					stmt=conn.prepareStatement("select count(empid) from attendence where empid=? and extract( year from date)=? group by empid");
					stmt.setInt(1, empid);
					stmt.setInt(2, year);
					
					rs=stmt.executeQuery();
					 if(rs.next())
					 {
						count=rs.getInt(1);
					 }
					 else
					 {
						 return false;
					 }
				}
				catch(Exception ex) {
					System.out.println("Error is"+ex);
				}
				
				int yearsal=daysal*count;
				try {
					
					int eid=this.getIDByUserName(username);
					//System.out.println("eid is "+eid);
					int atid=getIDByDate(username);
					//System.out.println("atid is "+atid);
					stmt=conn.prepareStatement("insert into yearsalary (ysid,yearsal,empid) values(?,?,?)");
					stmt.setInt(1, yearsalaryid);
					stmt.setInt(2,yearsal);
					stmt.setInt(3, eid);
					int value=stmt.executeUpdate();
					if(value>0)
					{
						eid=this.getIDByUserName(username);
						
						if (eid !=-1) {
							stmt = conn.prepareStatement("insert into empatyearsaljoin values(?,?,?)");
							stmt.setInt(1, eid);
							stmt.setInt(2, atid);
							stmt.setInt(3, yearsalaryid);
							return stmt.executeUpdate()>0?true:false;
							
						} else if (eid == -1) {
							System.out.println("employee not present.......");
							return false;
						} else {
							System.out.println("Some problem is there......ram ram");
							return false;
						}
					}
					else
					{
						return false;
					}
				}catch(Exception ex) {
					System.out.println("Error is  "+ex);
				}	
			} 
			}catch (Exception ex) {
				System.out.println("Error is"+ex);
			}
			
			return false;
		
	}
	
	public List<SalaryModel> isViewDateWiseSalary() {
		List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			stmt = conn.prepareStatement("select d.date ,e.name,s.daysal from daysalary s inner join empatdaysaljoin em on s.dsid=em.dsid inner  join employee e on e.empid=em.empid inner join attendence d on d.atid=em.atid order by d.date asc");

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				SalaryModel smodel=new SalaryModel();
				smodel.setDate(rs.getString(1));
				smodel.setName(rs.getString(2));
				smodel.setDaysal(rs.getInt(3));
				list.add(smodel);
			}
			return list.size()>0?list:null;
			
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}

	
	public List<SalaryModel> isViewMonthWiseSalary() {
		List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			stmt = conn.prepareStatement("select extract(month from d.date),e.name,s.monthsal from monthsalary s inner join empatmonthsaljoin em on s.msid=em.msid inner  join employee e on e.empid=em.empid inner join attendence d on d.atid=em.atid");

			rs = stmt.executeQuery();
			while(rs.next()) {
				SalaryModel smodel=new SalaryModel();
				smodel.setDate(rs.getString(1));
				smodel.setName(rs.getString(2));
				smodel.setDaysal(rs.getInt(3));
				list.add(smodel);
			}
			return list.size()>0?list:null;
			
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}
	
	public List<SalaryModel> isViewYearWiseSalary() {
		List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			stmt = conn.prepareStatement("select extract(year from d.date),e.name,s.yearsal from yearsalary s inner join empatyearsaljoin em on s.ysid=em.ysid inner  join employee e on e.empid=em.empid inner join attendence d on d.atid=em.atid");

			rs = stmt.executeQuery();
			while(rs.next()) {
				SalaryModel smodel=new SalaryModel();
				smodel.setDate(rs.getString(1));
				smodel.setName(rs.getString(2));
				smodel.setDaysal(rs.getInt(3));
				list.add(smodel);
			}
			return list.size()>0?list:null;
			
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}

	
	public List<SalaryModel> isViewdatebetweenSalary(String username,String date1, String date2) {
		int count=0;
		try {
			int empid=this.getIDByUserName(username);
			stmt=conn.prepareStatement("select package from employee where empid=?");
			stmt.setInt(1, empid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int pac=rs.getInt(1);
				int daysal=pac/365;
				daysalary=daysal;
			
			try {
			stmt=conn.prepareStatement("select count(empid) from attendence where  empid=? and date between ? and ? group by empid");
			stmt.setInt(1, empid);
			stmt.setString(2, date1);
			stmt.setString(3,date2);
			rs=stmt.executeQuery();
			 if(rs.next())
			 {
				count=rs.getInt(1);
			 }
			 else
			 {
				 return null;
			 }
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		
		int datebetweensal=daysalary*count;
		
		List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			stmt = conn.prepareStatement("select d.date,e.name from  employee e inner join empatdaysaljoin em on e.empid=em.empid inner join attendence d on d.atid=em.atid where e.username=? and d.date between ? and ?");
			stmt.setString(1, username);			
			stmt.setString(2, date1);
			stmt.setString(3,date2);
			rs = stmt.executeQuery();
			SalaryModel smodel=new SalaryModel();
			if (rs.next()) {
				smodel.setDate(rs.getString(1));
				smodel.setName(rs.getString(2));
				smodel.setDatebetweensal(datebetweensal);
				list.add(smodel);
			}
			return list.size()>0?list:null;
			
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
			}
	}catch(Exception ex) {
		System.out.println("Error is"+ex);
	}
		return null;
	}

	public int MonthBonasEmployee(int month) {
		//List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			
			stmt=conn.prepareStatement("select e.empid,e.name, count(a.empid) as count from attendence a  inner join employee e on e.empid=a.empid where extract(month from a.date)=? group by a.empid");
			stmt.setInt(1, month);
			rs = stmt.executeQuery();
			int c = 0;
			int pac=0;
			int daysal=0;
			int monthsal=0;
			int bonas=0;
			int addbonas=0;
			while(rs.next()) {
				SalaryModel smodel=new SalaryModel();
				smodel.setEmpid(rs.getInt(1));
				smodel.setName(rs.getString(2));
				smodel.setMonthbonas(rs.getInt(3));
				c=rs.getInt(3);
				if(c>3) {
					try {
						stmt=conn.prepareStatement("select package from employee where empid=?");
						rs=stmt.executeQuery();
						pac=rs.getInt(1);
						stmt.setInt(2,smodel.getEmpid());
						daysal=pac/365;
						bonas=daysal*4;
						
						}catch(Exception ex) {
						System.out.println("Error is "+ex);
						
						try {
							stmt=conn.prepareStatement("select monthsal from monthsalary where empid=?");
							
							stmt.setInt(2,smodel.getEmpid());
							rs=stmt.executeQuery();
							monthsal=rs.getInt(1);
							addbonas=monthsal+bonas;
						}catch(Exception ex1) {
							System.out.println("Error is "+ex1);
						}
						try {
							stmt=conn.prepareStatement("update monthsalary set monthsal=?");
							stmt.setInt(1, addbonas);
							int value=stmt.executeUpdate();
							return value>0?value:0;
						}catch(Exception ex1) {
							System.out.println("Error is "+ex1);
						}
					}
				}
				
			}
			
			
		}catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		
		return 0;
	}

	public List<SalaryModel> YearBonasEmployee(int year) {
		List<SalaryModel> list = new ArrayList<SalaryModel>();
		try {
			
			stmt=conn.prepareStatement("select e.empid,e.name, count(a.empid) as count from attendence a  inner join employee e on e.empid=a.empid where extract(year from a.date)=? group by a.empid");
			stmt.setInt(1, year);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SalaryModel smodel=new SalaryModel();
				smodel.setEmpid(rs.getInt(1));
				smodel.setName(rs.getString(2));
				smodel.setYearbonas(rs.getInt(3));
				list.add(smodel);
			}
			return list.size()>0?list:null;
		}catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return null;
	}
	

}
	
