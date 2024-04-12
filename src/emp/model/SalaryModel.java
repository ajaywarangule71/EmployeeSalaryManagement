package emp.model;

public class SalaryModel {
	String name;
	String date;
	String month;
	String year;
	private int datebetweensal;
	private int salid;
	private int daysal;
	private int monthsal;
	private int yearsal;
	private int empid;
    private int monthbonas;
    private int yearbonas;
	public SalaryModel() {

	}

	public SalaryModel(int monthbonas,int yearbonas,String name,String date,String month,String year,int datebetweensal,int salid, int daysal, int monthsal, int yearsal, int empid) {
		this.salid = salid;
		this.datebetweensal=datebetweensal;
		this.daysal = daysal;
		this.monthsal = monthsal;
		this.yearsal = yearsal;
		this.empid = empid;
		this.name=name;
		this.date=date;
		this.month=month;
		this.year=year;
		this.monthbonas=monthbonas;
		this.yearbonas=yearbonas;
	}

	public int getMonthbonas() {
		return monthbonas;
	}

	public void setMonthbonas(int monthbonas) {
		this.monthbonas = monthbonas;
	}

	public int getYearbonas() {
		return yearbonas;
	}

	public void setYearbonas(int yearbonas) {
		this.yearbonas = yearbonas;
	}

	public int getDatebetweensal() {
		return datebetweensal;
	}

	public void setDatebetweensal(int datebetweensal) {
		this.datebetweensal = datebetweensal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getSalid() {
		return salid;
	}

	public void setSalid(int salid) {
		this.salid = salid;
	}

	public int getDaysal() {
		return daysal;
	}

	public void setDaysal(int daysal) {
		this.daysal = daysal;
	}

	public int getMonthsal() {
		return monthsal;
	}

	public void setMonthsal(int monthsal) {
		this.monthsal = monthsal;
	}

	public int getYearsal() {
		return yearsal;
	}

	public void setYearsal(int yearsal) {
		this.yearsal = yearsal;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

}
