package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {
	
	private String folder = "D:\\IdeaWorkspace\\security\\imooc-security\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";

	@PostMapping//上传是一个post请求
	public FileInfo upload(MultipartFile file) throws Exception, IOException {
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile = new File(folder,new Date().getTime()+".txt");
		/*
		 * 如果是要上传到什么阿里云什么的，可以用FileInputStream等类读取文件内容
		 */
		//写到本地文件
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		try	(InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
			OutputStream outputStream = response.getOutputStream();){
			
			
			
			response.setContentType("application/x-download");
			//指定下载名为test.txt
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		} 
		
		
		
		
	}
	
}
