package emp.service;

import java.util.List;

import emp.model.AttendenceModel;
import emp.repository.AttendenceRepository;

public class AttendenceService {
	AttendenceRepository atRepo = new AttendenceRepository();
	
	
	public boolean isTakeAttendence(String username)
	{
		return atRepo.isTakeAttendence(username);
	}
	
	public List<AttendenceModel>  BestAttendedMonthEmployee(int month){
		return atRepo.BestAttendedMonthEmployee(month);
	}

	public List<AttendenceModel> BestAttendedYearEmployee(int year) {
		return atRepo.BestAttendedYearEmployee(year);
	}

}
