package com.homecommerce.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.entity.Product;
import com.homecommerce.services.CSVService;
import com.homecommerce.services.CategoryService;

//@Service
public class CSVHelper {
	
	
	
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public  List<Product> csvToTutorials(InputStream is, CategoryService categoryRepository) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Product> tutorials = new ArrayList<Product>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Product tutorial = new Product();
//    	  tutorial.setId(Integer.parseInt(csvRecord.get("id")));
    	  tutorial.setPname(csvRecord.get("pname")); 
    	  System.out.println("csvRecord.get(\"pname\") ::"+csvRecord.get("pname"));
    	  tutorial.setDescr(csvRecord.get("descr"));
    	  System.out.println(" csvRecord.get(\"descr\") ::"+csvRecord.get("descr"));
    	  System.out.println(" csvRecord.get(\"cat_id\") ::"+csvRecord.get("cat_id"));
    	  if(csvRecord.get("cat_id") != null) {
    			  tutorial.setCategory(categoryRepository.findById(Integer.parseInt(csvRecord.get("cat_id"))));
    	  }
//    	  tutorial.setCat_id(Integer.parseInt(csvRecord.get("cat_id")));
    	  tutorial.setStocks(Integer.parseInt(csvRecord.get("stocks")));
    	  tutorial.setPrice(Integer.parseInt(csvRecord.get("price")));
    	  tutorial.setPhoto(csvRecord.get("photo"));
    			  
//    	  Product tutorial = new Tutorial(
//              Long.parseLong(csvRecord.get("Id")),
//              csvRecord.get("Title"),
//              csvRecord.get("Description"),
//              Boolean.parseBoolean(csvRecord.get("Published"))
//            );

        tutorials.add(tutorial);
      }

      return tutorials;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

//  public static ByteArrayInputStream tutorialsToCSV(List<Tutorial> tutorials) {
//    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//      for (Tutorial tutorial : tutorials) {
//        List<String> data = Arrays.asList(
//              String.valueOf(tutorial.getId()),
//              tutorial.getTitle(),
//              tutorial.getDescription(),
//              String.valueOf(tutorial.isPublished())
//            );
//
//        csvPrinter.printRecord(data);
//      }
//
//      csvPrinter.flush();
//      return new ByteArrayInputStream(out.toByteArray());
//    } catch (IOException e) {
//      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//    }
//  }

}
