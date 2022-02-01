package com.project.service;

import java.util.List;
import com.project.entity.Intern;


public interface InternService {
	
	
		//get employee by email
		public Intern getIntern(String email);
		
		//load all employee

		public List<Intern> getAllIntern();
		
		//delete employee
		public void deleteIntern(int id);
		
		//update Intern
		public Intern updateIntern(Intern intern);
		
		

}
