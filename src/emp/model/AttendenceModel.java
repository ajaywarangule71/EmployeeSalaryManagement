package emp.model;

public class AttendenceModel {
	int empid;
	int aid;
	String date;
	int month;
	int year;
	String name;
	int bestcount;
	public AttendenceModel() {

	}

	public AttendenceModel( int empid,int year,int month,String name,int bestcount) {
		this.date = date;
		this.month=month;
		this.year=year;
		this.name=name;
		this.bestcount=bestcount;
		this.empid=empid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBestcount() {
		return bestcount;
	}

	public void setBestcount(int bestcount) {
		this.bestcount = bestcount;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
