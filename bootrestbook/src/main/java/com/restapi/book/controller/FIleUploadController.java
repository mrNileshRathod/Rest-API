package com.restapi.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.book.helper.FileUploader;

@RestController
public class FIleUploadController {
	
	@Autowired
	private FileUploader fileUploader;

	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		if(file.isEmpty()) {
			return ResponseEntity.internalServerError().body("Request must contains image");
		}
		
		if(!file.getContentType().equals("image/png")) {
			return ResponseEntity.internalServerError().body("Selected image must contains .png type");
		}
		
		try {
			boolean isUploaded = fileUploader.uploadFile(file);
			
			if(isUploaded) {
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").
						path(file.getOriginalFilename()).toUriString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.internalServerError().body("Failed to upload file. Try again...");
	}

}
