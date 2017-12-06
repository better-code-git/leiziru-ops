package com.ureactor.jeesite.common.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.aliyun.oss.OSSClient;

@Configuration
public class JedisConnectionFactoryConfig {

  @Resource
  private OSSConfig ossConfig;
  
  @Bean
  public JedisConnectionFactory getJedisConnectionFactory(@Value("${redis.host}") String hostName, @Value("${redis.port}") int port,
      @Value("${redis.password}") String password) {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(hostName);
    factory.setPort(port);
    factory.setPassword(password);
    return factory;
  }
  
  /**
   * 存储-上传
   * 
   * @return
   */
  @Bean(name = "oSSClient")
  public OSSClient uploadOSSClient() {
    return new OSSClient(ossConfig.getUploadEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }

}
