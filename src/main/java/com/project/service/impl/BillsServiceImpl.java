package com.project.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.entity.Bills;
import com.project.repo.BillsRepository;
import com.project.service.BillsService;

@Service
public class BillsServiceImpl implements BillsService {
	
	@Autowired
	private BillsRepository billsrepository;
	 


	@Override
	public Bills addBills(Bills bill) {
	
	 return this.billsrepository.save(bill);
	}

	@Override
	public List<Bills> allBills() {
		
		return this.billsrepository.findAll();
	}

	@Override
	public Bills updateBills(Bills bill) {
		
		return this.billsrepository.save(bill);
	}

	@Override
	public Bills getBill(int id) {
		
		return this.billsrepository.findById(id).get();
	}

	@Override
	public void deleteBills(int id) {
		
		Bills bill = new Bills();
		bill.setBillsId(id);
		
		 this.billsrepository.delete(bill);
		
	}

	@Override
	public Bills storeBills(MultipartFile file, Bills bill){
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	   	     try {
			bill.setAttachement(file.getBytes());
			  bill.setFilename(fileName);
			  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/files/download/")
						.path(fileName)
						.toUriString();
			  bill.setFileUri(fileDownloadUri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
              return this.billsrepository.save(bill);	   
//
//	    return this.billsrepository.save(bill);
	}
	
	
	    
	 
	 

	

}
