package com.ureactor.jeesite.common.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.service.OssService;

/**
 * 文件上传
 * 
 * @author wangshichuan@uworks.cc
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/file")
public class FileUploadController extends BaseController {

	@Autowired
	private OssService ossService;

	/**
	 * 文件上传
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		String filePath = ossService.upload(file);
		return filePath;
	}

}
