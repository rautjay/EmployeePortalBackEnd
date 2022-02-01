package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.entity.Bills;
import com.project.repo.BillsRepository;
import com.project.service.impl.BillsServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/bill")
public class BillsController {
	
    private static final Logger logger = LoggerFactory.getLogger(BillsController.class);
	
   @Autowired
   private BillsServiceImpl billsService;
   
   @Autowired
   private BillsRepository billsRepository;
   
   @PostMapping("/")
   public ResponseEntity<String> addBill(@RequestBody Bills bill){
	   
//        
//       System.out.println(attachement.getContentType());
//       System.out.println(attachement.getOriginalFilename());
//       System.out.println(attachement.getSize());
//       System.out.println(attachement.isEmpty());
       
    	
	
	
	  this.billsService.addBills(bill);
	   
	   return ResponseEntity.ok("file uploaded");
	   
	    }
   
   @GetMapping("/getall")
   public ResponseEntity<?> getAllBills(){
	    return ResponseEntity.ok(this.billsService.allBills());
   }
   
   @GetMapping("/{billsId}")
   public Bills getBill(@PathVariable("billsId") int id) {
	   
	   return this.billsService.getBill(id);
	   
   }
   
   
   @PutMapping("/")
   public Bills updateBills(@RequestBody Bills bill) {
	   return this.billsService.updateBills(bill);
   }
   
   
   @DeleteMapping("/{billsId}")
   public void deleteBill(@PathVariable("billsId") int id) {
	  
	   this.billsService.deleteBills(id);
	   
   }
   
   @PostMapping( value = "/upload")
   public ResponseEntity<String> uploadFile(@RequestPart("obj") Bills bill, @RequestPart("file") MultipartFile file) {
	   
	   System.out.println("file....." + file + "amount.....");
	   if(!file.getContentType().equals("application/pdf") ) {
		   System.out.println("pdf file required!!");
		   throw new IllegalArgumentException("Incorrect file type, PDF required.");
		 
	   }
	   
          this.billsService.storeBills(file,bill);
		
		return ResponseEntity.ok("file uploaded");
	  }
   
   
   @GetMapping("/download/{fileName:.+}")
   public ResponseEntity<Resource> downloadFromDB(@PathVariable String fileName) throws Exception {
   	Bills document = this.billsRepository.findByFilename(fileName);
   	System.out.println(document.getFileUri());
 
    return ResponseEntity.ok().
//    		contentType(new MediaType("text")).
            header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getFilename() + "\"").
            body(new ByteArrayResource(document.getAttachement()));
 
}
	
	
}
