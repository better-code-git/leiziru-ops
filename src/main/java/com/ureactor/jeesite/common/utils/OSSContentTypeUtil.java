package com.ureactor.jeesite.common.utils;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OSSContentTypeUtil {

  private static Logger logger = LoggerFactory.getLogger(OSSContentTypeUtil.class);
  
  private static Map<String, String> map = null;
  static{
    try {
      OSSContentTypeUtil.map = FileUtils.loadFileProperties("OSSContentType.properties");
    } catch (IOException e) {
      logger.error("", e);
    }
  }

  /*public static void init(String propertieFilePath) {
    try {
      OSSContentTypeUtil.map = FileUtils.loadFileProperties(propertieFilePath);
    } catch (IOException e) {
      logger.error("", e);
    }
  }*/

  public static String getContentType(String key) {
    if (map != null) {
      return map.get(key);
    }
    return null;
  }

}
