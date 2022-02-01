package com.project.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity

public class Employee {
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int id;
		private String name;
		private String location;
		
		private String designation;
		
		private String mobile;
		
		private String email;
		
		private String department;
		@DateTimeFormat( pattern = "dd/mm/yyyy")
		private java.util.Date DOJ;
		
		private Date DOL;
		
		@OneToOne(mappedBy = "employee",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@JsonBackReference
		private User user;
		
		
		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
			@JsonManagedReference(value = "employee-leaves")
		 @Fetch(value = FetchMode.SUBSELECT)
		 private List<Leaves> leaveList = new ArrayList<>();
		
		@OneToOne(cascade = CascadeType.ALL,mappedBy = "employee",orphanRemoval=true)
		@JsonManagedReference(value = "employee-docs")
		private Documentation documents;
		
		@JsonManagedReference(value = "employee-report")
		@OneToMany( mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval=true)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<Report> reportList = new ArrayList<>();
		
		
		
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Leaves> getLeaveList() {
			return leaveList;
		}

		public void setLeaveList(List<Leaves> leaveList) {
			this.leaveList = leaveList;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public java.util.Date getDOJ() {
			return DOJ;
		}

		public void setDOJ(java.util.Date doj) {
			DOJ = doj;
		}

		public Date getDOL() {
			return DOL;
		}

		public void setDOL(Date dOL) {
			DOL = dOL;
		}

		public Documentation getDocuments() {
			return documents;
		}

		public void setDocuments(Documentation documents) {
			this.documents = documents;
		}

		
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	

		

		public List<Report> getReportList() {
			return reportList;
		}

		public void setReportList(List<Report> reportList) {
			this.reportList = reportList;
		}

		public List<Leaves> getLeavelist() {
			return leaveList;
		}

		public void setLeavelist(List<Leaves> leavelist) {
			this.leaveList = leavelist;
		}

		public Employee() {
			super();
		}
		
		  @JsonIgnore
		    @JsonProperty("UserId")
		public Integer getUserId() {
			return this.user.getUserId();
		}
		
		


		

}
