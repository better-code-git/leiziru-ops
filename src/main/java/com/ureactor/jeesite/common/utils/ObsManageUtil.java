package com.ureactor.jeesite.common.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.obs.services.ObsClient;
import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.config.ObsConfig;
import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.utils.PicUrlObject;

/**
 * 华为云OBS文件上传
 * 
 * @author ForrestCao
 *
 */
public class ObsManageUtil {
	private static Logger logger = LoggerFactory.getLogger("ObsService.class");

	@Resource
	private static ObsConfig obsConfig;
	
	private static ObsClient obsClient;
	
	/**
	 * obs上传文件，图片处理包装类
	 * 
	 * @param file：文件
	 * @return
	 */
	public static AjaxJson uploadFileKit(MultipartFile imgFileData ,String remotePath,String remotePathOld){
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		String rtnUrl ="";
    	if (imgFileData!=null) {
			if (!imgFileData.isEmpty()) {
				try {
					PicUrlObject puObject = picUrlDecode(remotePath);
		    		obsConfig = new ObsConfig();
		    		obsClient = new ObsClient(obsConfig.getAccessKeyId(),
							obsConfig.getAccessKeySecret(),obsConfig.getConfig());
					InputStream input = imgFileData.getInputStream();
					obsClient.putObject(obsConfig.getBucketName(), puObject.getFileKey(), input);
					rtnUrl = obsConfig.getAccessUrl() + "/" + puObject.getFileKey();
					logger.info("上传图片成功,url:"+rtnUrl);
					if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
						deleteFile(remotePathOld);
					}
					message =rtnUrl;
				} catch (IOException e) {
					e.printStackTrace();
					j.setSuccess(false);
					message="图片操作异常";
				}
			}
		}
    	j.setMsg(message);
		return j;
	}
	

	/**
	 * obs上传文件，返回文件访问路径
	 * 
	 * @param file：文件
	 * @return
	 */
	public static String uploadFile(MultipartFile imgFileData ,String remotePath){
		String rtnUrl ="";
    	PicUrlObject puObject = picUrlDecode(remotePath);
    	try {
    		obsConfig = new ObsConfig();
    		obsClient = new ObsClient(obsConfig.getAccessKeyId(),
					obsConfig.getAccessKeySecret(),obsConfig.getConfig());
			InputStream input = imgFileData.getInputStream();
			obsClient.putObject(obsConfig.getBucketName(), puObject.getFileKey(), input);
			rtnUrl = obsConfig.getAccessUrl() + "/" + puObject.getFileKey();
			logger.info("上传图片成功,url:"+rtnUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rtnUrl;
	}
	
	
	/** 
     * 根据key删除OBS服务器上的文件 
    * @Title: deleteFile  
    * @Description:  
    * @param @param ossConfigure 
    * @param @param filePath    设定文件  
    * @return void    返回类型  
    * @throws 
     */  
    public static void deleteFile(String filePath){  
    	PicUrlObject puObject = picUrlDecode(filePath);
    	if(obsClient==null){
    		obsClient=new ObsClient(obsConfig.getEndpoint(),obsConfig.getAccessKeyId(), obsConfig.getAccessKeySecret());
    	}
    	obsClient.deleteObject(obsConfig.getBucketName(), puObject.getFileKey()); 
        logger.info("---删除老图成功----,url:"+obsConfig.getAccessUrl() + "/" + puObject.getFileKey());
          
    }  
	
	 /**
     * 上传url解析
     */
    private static PicUrlObject picUrlDecode(String remotePath){
   	 PicUrlObject puo =new PicUrlObject();
   	 if(remotePath!=null&&!"".equals(remotePath)){
   		 remotePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/");
   		 int fileNamePosition =remotePath.lastIndexOf("/");
   		 puo.setFileName(remotePath.substring(fileNamePosition+1)); 
   		 remotePath= remotePath.substring(0,fileNamePosition);
   		 int fileDirPosition =remotePath.lastIndexOf("/");
   		 puo.setFileDir(remotePath.substring(fileDirPosition+1)); 
   		 remotePath= remotePath.substring(0,fileDirPosition);
   		 puo.setFileKey(puo.getFileDir()+"/"+puo.getFileName());
   	 }
		return puo;
   	 
    }

}
