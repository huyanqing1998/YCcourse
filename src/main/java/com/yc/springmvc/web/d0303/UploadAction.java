package com.yc.springmvc.web.d0303;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadAction {
	@PostMapping("upload.do")
    public String handleFormUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1) throws IllegalStateException, IOException {

        if (!file.isEmpty()) {
            /*
             * 返回上传流
             * 平时不用，有更好的
             */
        	//file.getInputStream();
        	
        	//获取表单内的name
        	file.getName();
        	//获取文件名
        	String filename = file.getOriginalFilename();
        	//获取文件大小
        	file.getSize();
        	
        	File diskFile = new File("E:\\ba2",filename);
        	file.transferTo(diskFile);
        	
        	return "success";
        }

        return "redirect:upload.html";
    }
	
	@GetMapping("upload.html")
	public String toupload() {
		return "upload";
	}

	
	@ResponseBody
	@PostMapping("upload_ajax.do")
	public List<String> handleFormUpload1(@RequestParam("name") String name, 
			@RequestParam("file") MultipartFile[] files	 )
			throws IllegalStateException, IOException {		
		List<String> ret = new ArrayList<String>();
		for(MultipartFile file : files) {
			if (!file.isEmpty()) {
				String filename = file.getOriginalFilename(); // ===> 提交的文件名
				File diskFile = new File("E:/ba2/", filename);  // ???????
				file.transferTo(diskFile);  // 保存文件
				ret.add("ba2/" + filename);
			}
		}
		System.out.println(ret.toString());
		return ret;
	}

}
