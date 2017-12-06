package com.ureactor.jeesite.common.config;
import java.io.IOException;

import com.obs.services.ObsConfiguration;  
  
public class ObsConfig {  
  
    private String endpoint;  
    private String accessKeyId;  
    private String accessKeySecret;  
    private String bucketName;  
    private String accessUrl; 
    private ObsConfiguration config;
  
    /** 
     * 通过配置文件.properties文件获取，这几项内容。 
     * @param storageConfName 
     * @throws IOException 
     */  
    public ObsConfig() throws IOException {  
  
        endpoint = Global.getConfig("obs.endpoint");  
        accessKeyId = Global.getConfig("obs.accessKeyId"); 
        accessKeySecret = Global.getConfig("obs.accessKeySecret"); 
        bucketName =  Global.getConfig("obs.bucketName");
        accessUrl =  Global.getConfig("obs.accessUrl");
        config =new ObsConfiguration();
        config.setSocketTimeout(30000);
        config.setConnectionTimeout(10000);
        config.setEndPoint(endpoint);
        config.setHttpsOnly(true);
        config.setDisableDnsBucket(true);
        config.setSignatString("v4");
        config.setDefaultBucketLocation("CHINA");
    }  
  
    public ObsConfig(String endpoint, String accessKeyId,  
            String accessKeySecret, String bucketName, String accessUrl) {  
  
        this.endpoint = endpoint;  
        this.accessKeyId = accessKeyId;  
        this.accessKeySecret = accessKeySecret;  
        this.bucketName = bucketName;  
        this.accessUrl = accessUrl;  
    }  
  
    public String getEndpoint() {  
        return endpoint;  
    }  
  
    public void setEndpoint(String endpoint) {  
        this.endpoint = endpoint;  
    }  
  
    public String getAccessKeyId() {  
        return accessKeyId;  
    }  
  
    public void setAccessKeyId(String accessKeyId) {  
        this.accessKeyId = accessKeyId;  
    }  
  
    public String getAccessKeySecret() {  
        return accessKeySecret;  
    }  
  
    public void setAccessKeySecret(String accessKeySecret) {  
        this.accessKeySecret = accessKeySecret;  
    }  
  
    public String getBucketName() {  
        return bucketName;  
    }  
  
    public void setBucketName(String bucketName) {  
        this.bucketName = bucketName;  
    }  
  
    public String getAccessUrl() {  
        return accessUrl;  
    }  
  
    public void setAccessUrl(String accessUrl) {  
        this.accessUrl = accessUrl;  
    }

	public ObsConfiguration getConfig() {
		return config;
	}

	public void setConfig(ObsConfiguration config) {
		this.config = config;
	}  
  
}  