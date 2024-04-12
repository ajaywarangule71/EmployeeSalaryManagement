package emp.repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import emp.model.AttendenceModel;
public class AttendenceRepository extends DBConfig{
	private int attendenceid;
	
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
	
	public int getAttendenceId() {
		try {
			
			LocalDate currentDate = LocalDate.now();
			java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
			stmt = conn.prepareStatement("select max(atid) from attendence");
			rs = stmt.executeQuery();
			if (rs.next()) {
				attendenceid = rs.getInt(1);
			}
			++attendenceid;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
		return attendenceid;
	}
	
	public boolean isTakeAttendence(String username) {
		try {
			int atid = this.getAttendenceId();
            //System.out.println("atid is "+aid);
			LocalDate currentDate = LocalDate.now();
			java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

			int empid=this.getIDByUserName(username);
			
			PreparedStatement checkStatement = conn.prepareStatement("SELECT * FROM attendence WHERE empid = ? AND date = ?");
            checkStatement.setInt(1, empid);
            checkStatement.setDate(2, java.sql.Date.valueOf(currentDate));
            ResultSet rs= checkStatement.executeQuery();
                if (rs.next()) {
                System.out.println("Attendance already marked for employee " + empid + " on " + currentDate);
            }
            else {
			stmt=conn.prepareStatement("insert into attendence values(?,?,?)");
			stmt.setInt(1, atid);
			stmt.setDate(2,sqlDate );
			stmt.setInt(3,empid);
			int value = stmt.executeUpdate();
			//System.out.println("atid is"+atid);
			if (value > 0) {
				int eid = this.getIDByUserName(username);
        		if (atid !=-1) {
				stmt = conn.prepareStatement("insert into empatdaysaljoin (empid,atid) values(?,?)");
					stmt.setInt(1, eid);
					stmt.setInt(2, atid);
					return stmt.executeUpdate()>0?true:false;
				} else if (eid == -1) {
					System.out.println("employee not present.......");
					return false;
				} else {
					System.out.println("Some problem is there......");
					return false;
				}
			} else {
				return false;
			}
            }
		}
		catch(Exception ex) {
			System.out.println("Employee not present....."+ex);
		}
		return false;
	}
	
	public List<AttendenceModel>  BestAttendedMonthEmployee(int month){
		List<AttendenceModel> list = new ArrayList<AttendenceModel>();
		try {
			stmt=conn.prepareStatement("SELECT e.empid,e.name,COUNT(a.empid) AS bestcount FROM attendence a INNER JOIN employee e ON e.empid = a.empid "
					+ "WHERE EXTRACT(month FROM a.date) = ? GROUP BY a.empid HAVING COUNT(a.empid) = (SELECT MAX(cnt) "
					+ "FROM (SELECT COUNT(empid) AS cnt  FROM attendence  WHERE EXTRACT(month FROM date)=?  GROUP BY empid) AS max_counts); ");
			stmt.setInt(1, month);
			stmt.setInt(2, month);
			rs=stmt.executeQuery();
			while(rs.next()) {
			AttendenceModel amodel=new AttendenceModel();
			amodel.setEmpid(rs.getInt(1));
			amodel.setName(rs.getString(2));
			amodel.setBestcount(rs.getInt(3));
			list.add(amodel);
			}
			return list.size()>0?list:null;

		}catch(Exception ex){
			System.out.println("Error is"+ex);
		}
		return null;
	}

	public List<AttendenceModel> BestAttendedYearEmployee(int year) {
		List<AttendenceModel> list = new ArrayList<AttendenceModel>();
		try {
			stmt=conn.prepareStatement("SELECT e.empid,e.name,COUNT(a.empid) AS bestcount FROM attendence a INNER JOIN employee e ON e.empid = a.empid "
					+ "WHERE EXTRACT(year FROM a.date) = ? GROUP BY a.empid HAVING COUNT(a.empid) = (SELECT MAX(cnt) "
					+ "FROM (SELECT COUNT(empid) AS cnt  FROM attendence  WHERE EXTRACT(year FROM date)=?  GROUP BY empid) AS max_counts); ");
			stmt.setInt(1, year);
			stmt.setInt(2, year);
			rs=stmt.executeQuery();
			while(rs.next()) {
			AttendenceModel amodel=new AttendenceModel();
			amodel.setEmpid(rs.getInt(1));
			amodel.setName(rs.getString(2));
			amodel.setBestcount(rs.getInt(3));
			list.add(amodel);
			}
			return list.size()>0?list:null;

		}catch(Exception ex){
			System.out.println("Error is"+ex);
		}
		return null;
	}

}
