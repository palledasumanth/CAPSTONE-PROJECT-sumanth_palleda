package com.homecommerce.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.entity.Category;
import com.homecommerce.entity.Product;
import com.homecommerce.repos.ProductRepository;
import com.homecommerce.utils.CSVHelper;

@Service
public class CSVService {
  @Autowired
  ProductRepository repository;
  
//  @Autowired
  CategoryService categoryRepository;

  public void save(MultipartFile file, CategoryService categoryRepository) {
	  this.categoryRepository =   categoryRepository;
    try {
    	System.out.println("categoryRepository :: "+categoryRepository);
    	CSVHelper CSVHelper = new CSVHelper();
      List<Product> tutorials = CSVHelper.csvToTutorials(file.getInputStream(), categoryRepository);
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

//  public ByteArrayInputStream load() {
//    List<Tutorial> tutorials = repository.findAll();
//
//    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
//    return in;
//  }
//
//  public List<Tutorial> getAllTutorials() {
//    return repository.findAll();
//  }
  
  
  	public Category getCategotryById(String id) {
  		Category category = null;
  		System.out.println("categoryRepository :: "+categoryRepository);
//  		category = categoryRepository.findById(Integer.parseInt(id));
		  return category;
  	}
}
