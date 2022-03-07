package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.ComDocument;
import com.project.repo.ComDocumentRepository;
import com.project.service.ComDocumentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/docs")
public class ComDocumentController {


	@Autowired
	private ComDocumentRepository docsRepository;
	@Autowired
	private ComDocumentService documentService;


	   @PostMapping( value = "/upload/{id}")
	   public ResponseEntity<String> uploadFile( @RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
	        ComDocument comDoc = new ComDocument();
		   if(!file.getContentType().equals("application/pdf") ) {
			   System.out.println("pdf file required!!");
			   throw new IllegalArgumentException("Incorrect file type, PDF required.");

		   }

	          this.documentService.UploadDocument(file, comDoc, id);

			return ResponseEntity.ok("file uploaded");
		  }

	   @PostMapping( value = "/upload/intern/{id}")
	   public ResponseEntity<String> uploadFileToIntern( @RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
	        ComDocument comDoc = new ComDocument();
		   if(!file.getContentType().equals("application/pdf") ) {
			   System.out.println("pdf file required!!");
			   throw new IllegalArgumentException("Incorrect file type, PDF required.");

		   }

	          this.documentService.UploadDocumenToIntern(file, comDoc, id);

			return ResponseEntity.ok("file uploaded");
		  }



	   @GetMapping("/download/{fileName:.+}")
	   public ResponseEntity<Resource> downloadFromDB(@PathVariable String fileName) throws Exception {
	   	ComDocument document = this.docsRepository.findByFilename(fileName);
	   	System.out.println(document.getFileUri());

	    return ResponseEntity.ok().
//	    		contentType(new MediaType("text")).
	            header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getFilename() + "\"").
	            body(new ByteArrayResource(document.getFile()));

	}

}
