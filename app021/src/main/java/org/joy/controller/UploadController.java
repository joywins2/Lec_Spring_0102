package org.joy.controller;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	//...527p.servlet-context.xml에 설정한 파일경로.
	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {

		logger.info("uploadForm :: GET called...");
		
	}	

	//...524p.	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {

		/*
		 * ...523p.jsp 파일 :
		      <%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 필요함.
		   ...525p.web.xml : UTF-8 필터 설정.
		 */
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());//...파일의 MIME타입등의 정보.
	
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
	
		model.addAttribute("savedName", savedName);
	
		return "uploadResult";
	}	
	
	//...528p.
	private String uploadFile(String originalFilename, byte[] fileData) throws Exception {
		
		//...UUID는 중복되지 않는 고유한 키값을 설정할때 사용함.
		//   파일업로드에서 주의할 점은, 동일한 경로에 동일한 이름의 파일을 업로드하는 것.
		//   UUID와 같이 (거의)고유한 값을 생성해서 처리하면 해결할 수 있음.
		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalFilename;

		File target = new File(uploadPath, savedName);

		//...실제 파일처리는 스프링에서 제공하는 FileCopyUtils를 이용함.
		//   FileCopyUtils는 import org.springframework.util.FileCopyUtils패키지에 설정된 클래스임.
		//   파일 데이터를 파일로 처리하거나, 복사하는 등의 작업에 유용하게 사용될 수 있음.
		//   org.springframework.util.FileCopyUtils 패키지는 개발자가 개발하면서 필요한 여러 종류의 
		//   클래스를 제공함. 
		FileCopyUtils.copy(fileData, target);

		return savedName;

	}

}
