/**
 * Copyright &copy; 2012-2016
 * <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.common.config;

import com.google.common.collect.Maps;
import com.ureactor.jeesite.common.utils.PropertiesLoader;
import com.ureactor.jeesite.common.utils.StringUtils;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局配置类
 *
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {


  static RelaxedPropertyResolver resolver;
  /**
   * 当前对象实例
   */
  private static Global global = new Global();

  /**
   * 保存全局属性值
   */
  private static Map<String, String> map = Maps.newHashMap();

  /**
   * 属性文件加载对象
   */
  private static PropertiesLoader loader = new PropertiesLoader("bootstrap.yml");

  /**
   * 是/否
   */
  public static final String YES = "1";
  public static final String NO = "0";

  /**
   * 对/错
   */
  public static final String TRUE = "true";
  public static final String FALSE = "false";

  /**
   * 上传文件基础虚拟路径
   */
  public static final String USERFILES_BASE_URL = "/userfiles/";

  /**
   * 配置文件记录日志URI分隔符
   */
  private static final String REGEX = ",";

  /**
   * 获取requestURI分隔符
   */
  private static final String URI_REGEX = "/";

  /**
   * 配置文件记录日志URI key
   */
  private static final String LOG_PATH = "logPath";
  /**
   * 储存日志的配置信息
   */
  private static final Map<String, Boolean> DATA_MAP = Maps.newHashMap();

  /**
   * 获取当前对象实例
   */
  public static Global getInstance() {
    return global;
  }

  /**
   * 从配置文件中取出需要记录日志信息，存入DATA_MAP
   */
  static {
    String config = Global.getConfig(LOG_PATH);
    if (StringUtils.isNotBlank(config)) {
      String[] split = config.split(REGEX);
      for (int i = 0; i < split.length; i++) {
        DATA_MAP.put(split[i], true);
      }
    }
  }

  /**
   * 获取配置 ${fns:getConfig('adminPath')}
   */
  public static String getConfig(String key) {
    String value = map.get(key);
    if (value == null) {
      try {
        value = resolver.getProperty(key);
        if (StringUtils.isBlank(value))
          throw new RuntimeException("value null");
        map.put(key, value);
      } catch (Exception e) {
        value = loader.getProperty(key);
        map.put(key, value != null ? value : StringUtils.EMPTY);
      }
    }
    return value;
  }

  /**
   * 获取管理端根路径
   */
  public static String getAdminPath() {
    return getConfig("adminPath");
  }

  /**
   * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
   */
  public static Boolean isDemoMode() {
    String dm = getConfig("demoMode");
    return "true".equals(dm) || "1".equals(dm);
  }

  public static String getJdbcType() {
//    if (map.containsKey("spring.datasource.url"))
//      return map.get("spring.datasource.url");
//    try {
//      String url = resolver.getProperty("spring.datasource.url");
//      String type = getDbType(url);
//      map.put("spring.datasource.url", type);
//      return type;
//    } catch (Exception e) {
//      logger.error("get jdbcType error", e);
//    }
//    logger.error("return the defaut jdbc type is mysql");
    return "mysql";
  }

//  private static String getDbType(String rawUrl) {
//    return rawUrl == null ? null : (!rawUrl.startsWith("jdbc:derby:") && !rawUrl.startsWith("jdbc:log4jdbc:derby:") ? (!rawUrl.startsWith("jdbc:mysql:") && !rawUrl.startsWith("jdbc:cobar:") && !rawUrl.startsWith("jdbc:log4jdbc:mysql:") ? (rawUrl.startsWith("jdbc:mariadb:") ? "mariadb" : (!rawUrl.startsWith("jdbc:oracle:") && !rawUrl.startsWith("jdbc:log4jdbc:oracle:") ? (rawUrl.startsWith("jdbc:alibaba:oracle:") ? "AliOracle" : (!rawUrl.startsWith("jdbc:microsoft:") && !rawUrl.startsWith("jdbc:log4jdbc:microsoft:") ? (!rawUrl.startsWith("jdbc:sqlserver:") && !rawUrl.startsWith("jdbc:log4jdbc:sqlserver:") ? (!rawUrl.startsWith("jdbc:sybase:Tds:") && !rawUrl.startsWith("jdbc:log4jdbc:sybase:") ? (!rawUrl.startsWith("jdbc:jtds:") && !rawUrl.startsWith("jdbc:log4jdbc:jtds:") ? (!rawUrl.startsWith("jdbc:fake:") && !rawUrl.startsWith("jdbc:mock:") ? (!rawUrl.startsWith("jdbc:postgresql:") && !rawUrl.startsWith("jdbc:log4jdbc:postgresql:") ? (rawUrl.startsWith("jdbc:edb:") ? "edb" : (!rawUrl.startsWith("jdbc:hsqldb:") && !rawUrl.startsWith("jdbc:log4jdbc:hsqldb:") ? (rawUrl.startsWith("jdbc:odps:") ? "odps" : (rawUrl.startsWith("jdbc:db2:") ? "db2" : (rawUrl.startsWith("jdbc:sqlite:") ? "sqlite" : (rawUrl.startsWith("jdbc:ingres:") ? "ingres" : (!rawUrl.startsWith("jdbc:h2:") && !rawUrl.startsWith("jdbc:log4jdbc:h2:") ? (rawUrl.startsWith("jdbc:mckoi:") ? "mckoi" : (rawUrl.startsWith("jdbc:cloudscape:") ? "cloudscape" : (!rawUrl.startsWith("jdbc:informix-sqli:") && !rawUrl.startsWith("jdbc:log4jdbc:informix-sqli:") ? (rawUrl.startsWith("jdbc:timesten:") ? "timesten" : (rawUrl.startsWith("jdbc:as400:") ? "as400" : (rawUrl.startsWith("jdbc:sapdb:") ? "sapdb" : (rawUrl.startsWith("jdbc:JSQLConnect:") ? "JSQLConnect" : (rawUrl.startsWith("jdbc:JTurbo:") ? "JTurbo" : (rawUrl.startsWith("jdbc:firebirdsql:") ? "firebirdsql" : (rawUrl.startsWith("jdbc:interbase:") ? "interbase" : (rawUrl.startsWith("jdbc:pointbase:") ? "pointbase" : (rawUrl.startsWith("jdbc:edbc:") ? "edbc" : (rawUrl.startsWith("jdbc:mimer:multi1:") ? "mimer" : (rawUrl.startsWith("jdbc:dm:") ? "dm" : (rawUrl.startsWith("jdbc:kingbase:") ? "kingbase" : (rawUrl.startsWith("jdbc:log4jdbc:") ? "log4jdbc" : (rawUrl.startsWith("jdbc:hive:") ? "hive" : (rawUrl.startsWith("jdbc:hive2:") ? "hive" : (rawUrl.startsWith("jdbc:phoenix:") ? "phoenix" : null)))))))))))))))) : "informix"))) : "h2"))))) : "hsql")) : "postgresql") : "mock") : "jtds") : "sybase") : "sqlserver") : "sqlserver")) : "oracle")) : "mysql") : "derby");
//  }
  
  /**
   * 获取工程路径
   * @return
   */
  public static String getProjectPath(){
    // 如果配置了工程路径，则直接返回，否则自动获取。
  String projectPath = Global.getConfig("projectPath");
  if (StringUtils.isNotBlank(projectPath)){
    return projectPath;
  }
  try {
    File file = new DefaultResourceLoader().getResource("").getFile();
    if (file != null){
      while(true){
        File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
        if (f == null || f.exists()){
          break;
        }
        if (file.getParentFile() != null){
          file = file.getParentFile();
        }else{
          break;
        }
      }
      projectPath = file.toString();
    }
  } catch (IOException e) {
    e.printStackTrace();
  }
  return projectPath;
  }
  
  /**
   * 读取配置，根据请求URI结尾判断是否记录日志
   * 
   * @param request
   * @return true:不记录;false:记录
   */
  public static boolean isRecord(HttpServletRequest request) {
    // 未读到拦截配置默认全部拦截
    if (null == DATA_MAP || DATA_MAP.isEmpty() || null == request) {
      return false;
    }
    // URI为空默认拦截
    String requestURI = request.getRequestURI();
    if (StringUtils.isBlank(requestURI)) {
      return false;
    }
    Boolean isURI = DATA_MAP.get(requestURI.substring(requestURI.lastIndexOf(URI_REGEX)));
    return null == isURI || !isURI.equals(true) ? false : true;
  }

}
