package com.project.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.User;
import com.project.entity.UserRole;
import com.project.repo.InternRepository;
import com.project.repo.UserRepository;
import com.project.service.InternService;
@Service
public class InternServiceImpl implements InternService {

@Autowired
private InternRepository repository;



	@Override
	public Intern getIntern(String email) {
		
		return this.repository.findByEmail(email);
	}

	@Override
	public List<Intern> getAllIntern() {
		
		return this.repository.findAll();
	}

	@Override
	public void deleteIntern(int id) {
	 this.repository.deleteById(id);
		
	}

	@Override
	public Intern updateIntern(Intern intern) {
		
		return this.repository.save(intern);
	}

	

}
