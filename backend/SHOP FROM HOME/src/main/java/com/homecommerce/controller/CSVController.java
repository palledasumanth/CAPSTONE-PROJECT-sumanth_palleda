package com.homecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.services.CSVService;
import com.homecommerce.services.CategoryService;
import com.homecommerce.utils.CSVHelper;

//@CrossOrigin("http://localhost:8082")
@CrossOrigin
@Controller
@RequestMapping("/csv")
public class CSVController {

  @Autowired
  CSVService fileService;
  
  @Autowired
  CategoryService categoryRepository;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    System.out.println("categoryRepository controller :: "+categoryRepository );
    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file, categoryRepository);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(message);
      } catch (Exception e) {
    	  e.printStackTrace();
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

//  @GetMapping("/tutorials")
//  public ResponseEntity<List<Tutorial>> getAllTutorials() {
//    try {
//      List<Tutorial> tutorials = fileService.getAllTutorials();
//
//      if (tutorials.isEmpty()) {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//      }
//
//      return new ResponseEntity<>(tutorials, HttpStatus.OK);
//    } catch (Exception e) {
//      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  @GetMapping("/download")
//  public ResponseEntity<Resource> getFile() {
//    String filename = "tutorials.csv";
//    InputStreamResource file = new InputStreamResource(fileService.load());
//
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//        .contentType(MediaType.parseMediaType("application/csv"))
//        .body(file);
//  }

}
