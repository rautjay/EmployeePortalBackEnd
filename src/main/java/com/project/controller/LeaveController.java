package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.Leaves;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.repo.LeaveRepository;
import com.project.service.LeaveService;


@RestController
@RequestMapping("/leave")
@CrossOrigin("*")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LeaveRepository leaveRepository;
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 @Autowired
 private InternRepository internRepository;
	 
	//add leave
	@PostMapping("/{id}")
	public ResponseEntity<Leaves> addleaveToEmployee(@PathVariable("id") int id,@RequestBody Leaves leave)
	{
	
		try {
			leave = this.leaveService.addLeaveToEmployee(id, leave);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.ok(leave);
	}
	
	//get leave
	@GetMapping("/{id}")
	public Leaves getleave(@PathVariable("id") int leaveId)
	{
		return this.leaveService.getLeave(leaveId);
	}
	
	//getallleave
	@GetMapping("/getall")
	public ResponseEntity<?> getAllLeaves()
	{
		return ResponseEntity.ok(this.leaveService.getLeaves());
	}
	
    //update 
     
	    @PutMapping("/{id}/leave/{employeeId}")
	    public ResponseEntity<Leaves> update(@RequestBody Leaves leave , @PathVariable("id") int id,@PathVariable("employeeId") int empId) {
//	        Optional<Employee> optionalEmployee = employeeRepository.findById(leave.getEmployee().getId());
	    	Optional<Employee> employee = employeeRepository.findById(empId);
	    	Optional<Intern> intern = this.internRepository.findById(empId);
	    	Optional<Leaves> optionalLeaves = leaveRepository.findById(id);
	    
	        if (employee.isPresent()) {
	        	   leave.setEmployee(employee.get());
	        }
	        else if(intern.isPresent()) {
	        	   leave.setIntern(intern.get());
	        }

	        else  {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	     
	        leave.setId(optionalLeaves.get().getId());
	        leaveRepository.save(leave);

	        return ResponseEntity.noContent().build();
	 }
	
	//delete leave
	@DeleteMapping("/{id}")
	public void deleteLeave(@PathVariable("id") int leaveId)
	{
		this.leaveService.deleteLeaves(leaveId);
	}

//	
//	@PutMapping("/update/{id}")
//	 public Leaves upateLeaves(@PathVariable("id") int id, @RequestBody Leaves leave) {
//		 List<Leaves> leaves = new ArrayList<>();
//	        Employee employee1 = new Employee();
//
//	        Optional<Employee> byId = this.employeeRepository.findById(id);
//	        try {
//				if (!byId.isPresent()) {
//				    throw new Exception("Employee with id " + id + " does not exist");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        Employee employee = byId.get();
//
//	        //tie Employee to leave
//	        leave.setEmployee(employee);
//
//	        Leaves leave1 = this.leaveRepository.save(leave);
//	        //tie leave to employee
//	        leaves.add(leave1);
//	        employee1.setLeavelist(leaves);;
//
//	        return leave1;
//
//		
//	}
	
	//add leaves in Intern
	
	@PostMapping("/intern/{id}")
	public ResponseEntity<Leaves> addleaveToIntern(@PathVariable("id") int id,@RequestBody Leaves leave)
	{
	
		try {
			leave = this.leaveService.addLeaveToIntern(id, leave);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.ok(leave);
	}
}
