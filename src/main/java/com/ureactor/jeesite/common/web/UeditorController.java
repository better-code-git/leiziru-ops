package com.ureactor.jeesite.common.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;
import com.ureactor.jeesite.common.service.OssService;

@RestController
@RequestMapping("/ueditor")
public class UeditorController {

  @Autowired
  private OssService ossService;

  @RequestMapping(value = "/config")
  public void config(HttpServletRequest request, HttpServletResponse response) {
    String action = request.getParameter("action");
    if ("config".equals(action)) {
      response.setContentType("application/json");
      try {
        //config.json需要放在static/ueditor下
        String rootPath = request.getServletContext().getRealPath("/static");
        String exec = new ActionEnter( request, rootPath ).exec();
        PrintWriter writer = response.getWriter();
        writer.write(exec);
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }else if("ueditorUploadImage".equals(action)){
      response.setContentType("text/html;charset=UTF-8");
      ReturnUploadImage rui = null;// 这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
      // 转型为MultipartHttpRequest：
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      MultipartFile file = multipartRequest.getFile("upfile");
      String filePath = ossService.upload(file);
      response.setCharacterEncoding("utf-8");
      // 保存
      try {
        rui = new ReturnUploadImage();
        rui.setTitle(file.getName());// 这里需要设置文件名称如：xxx.jpg
        rui.setOriginal(file.getName());// 这里需要设置文件名称如：xxx.jpg
        rui.setState("SUCCESS");
        rui.setUrl(filePath);// 这里是设置返回给UEditor的图片地址
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        String result = JSON.toJSONString(rui);// 这边就是为了返回给UEditor做的格式转换
        response.getWriter().print(result);
      } catch (IOException e) {
        e.printStackTrace();
      }
    
    }
  }
}
