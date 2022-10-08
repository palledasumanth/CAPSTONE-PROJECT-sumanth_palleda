package com.homecommerce.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.utils.AwsS3Upload;
import com.homecommerce.utils.StorageService;

@CrossOrigin
@RestController
@Controller
public class FileController {
	@Autowired
	private StorageService storageService;

	@RequestMapping(value="/{fileName}", produces = "image/*")
	public void download(@PathVariable("fileName") String fileName, HttpServletResponse resp) {
		System.out.println("Loading file: " + fileName);
		Resource resource = storageService.load(fileName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//	@PostMapping(path = "/uploadFileToAws", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
//	public ResponseEntity<String> uploadFileToAws(@RequestBody FilesRequestVo filesRequestVo) throws Exception {
////		FilesResponseVo filesResponseVo = new FilesResponseVo();
////		filesResponseVo.setStatus(Constants.SUCCESS);
////		filesResponseVo.setMessage("");
//		InputStream inputStream= decodeBase64ToInputStream(filesRequestVo.getEncodedString());
//		AwsS3Upload a1 = new AwsS3Upload();
//    	String fileUrl= a1.putObjectWithExtension(filesRequestVo.getFileName(), inputStream, filesRequestVo.getFileSize(),getExtension(filesRequestVo.getFileName()));
//    	System.out.println("Hello "+getExtension(filesRequestVo.getFileName()));
////    	filesResponseVo.setFileURL(fileUrl);
//		return new ResponseEntity<>(fileUrl, HttpStatus.OK);
//	}
//	
//	public String getExtension(String name) {
//    	System.out.println("name="+name);
//    String ext="";
//    String ar[]=StringUtils.split(name, "."); //name.split(".");
//    System.out.println("ar="+ar[0]);
//    ext=ar[1];
//    
//    return ext;
//    }
//	
//	public InputStream decodeBase64ToInputStream(String encodedString) {
//		byte[] decodedString = Base64.getDecoder().decode(encodedString);
//		InputStream is = new ByteArrayInputStream(decodedString);
//		return is;
//	}
	
	@RequestMapping(value = "/singleFileupload", method = RequestMethod.POST)
	public ResponseEntity<String> singleFileupload(@RequestParam("file") MultipartFile file) throws IOException {
		
		AwsS3Upload a1 = new AwsS3Upload();
    	String fileUrl= a1.putObjectWithExtension(file.getOriginalFilename(), file.getInputStream(), file.getSize());
		
		return new ResponseEntity<String>(fileUrl,org.springframework.http.HttpStatus.OK);
	}
}
