package com.ureactor.jeesite.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.ureactor.jeesite.common.security.shiro.cache.RedisCacheManager;
import com.ureactor.jeesite.common.security.shiro.session.RedisSessionDAO;
import com.ureactor.jeesite.common.security.shiro.session.SessionManager;
import com.ureactor.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.ureactor.jeesite.modules.sys.security.SystemAuthorizingRealm;

/**
 * 对应spring-context-shiro.xml
 * 
 * @author fangpengli@uworks.cc
 *
 */
@Configuration
public class ShiroRedisConfig {

  @Bean("shiroFilterChainDefinitions")
  public String getShiroFilterChainDefinitions(Environment environment, @Value("${adminPath}") String adminPath) {
    Global.resolver = new RelaxedPropertyResolver(environment);
    String string = "/static/** = anon\n";
    string += "/userfiles/** = anon\n";
    string += adminPath + "/basic = basic\n";
    string += adminPath + "/login = authc\n";
    string += adminPath + "/logout = logout\n";
    string += adminPath + "/** = user\n";
    string += "/ReportServer/** = user";
    return string;
  }

  @Bean(name = "basicHttpAuthenticationFilter")
  public BasicHttpAuthenticationFilter casFilter(@Value("${adminPath:/a}") String adminPath) {
    BasicHttpAuthenticationFilter basicHttpAuthenticationFilter = new BasicHttpAuthenticationFilter();
    basicHttpAuthenticationFilter.setLoginUrl(adminPath + "/login");
    return basicHttpAuthenticationFilter;
  }

  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilterFactoryBean(@Value("${adminPath:/a}") String adminPath,
      BasicHttpAuthenticationFilter basicHttpAuthenticationFilter, FormAuthenticationFilter formAuthenticationFilter,
      DefaultWebSecurityManager securityManager, @Qualifier("shiroFilterChainDefinitions") String shiroFilterChainDefinitions) {
    Map<String, Filter> filters = new HashMap<>();
    filters.put("basic", basicHttpAuthenticationFilter);
    filters.put("authc", formAuthenticationFilter);
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setFilters(filters);
    bean.setSecurityManager(securityManager);
    bean.setLoginUrl(adminPath + "/login");
    bean.setSuccessUrl(adminPath + "?login");
    bean.setFilterChainDefinitions(shiroFilterChainDefinitions);
    return bean;
  }

  @Bean(name = "shiroCacheManager")
  public RedisCacheManager shiroCacheManager() {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    return redisCacheManager;
  }

  @Bean(name = "sessionManager")
  public SessionManager sessionManager(RedisSessionDAO dao) {
    SessionManager sessionManager = new SessionManager();
    sessionManager.setSessionDAO(dao);
    sessionManager.setGlobalSessionTimeout(86400000);
    sessionManager.setSessionValidationInterval(1800000);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionIdCookie(new SimpleCookie("ureactor.jeesite.session.id"));
    sessionManager.setSessionIdCookieEnabled(true);
    return sessionManager;
  }

  @Bean(name = "securityManager")
  public DefaultWebSecurityManager defaultWebSecurityManager(SystemAuthorizingRealm systemAuthorizingRealm, SessionManager sessionManager,
      RedisCacheManager redisCacheManager) {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    defaultWebSecurityManager.setSessionManager(sessionManager);
    defaultWebSecurityManager.setCacheManager(redisCacheManager);
    defaultWebSecurityManager.setRealm(systemAuthorizingRealm);
    return defaultWebSecurityManager;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
    return authorizationAttributeSourceAdvisor;
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
    filterRegistration.addInitParameter("targetFilterLifecycle", "true");
    filterRegistration.setEnabled(true);
    filterRegistration.addUrlPatterns("/*");
    return filterRegistration;
  }
  
  @Bean(name = "lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
      return new LifecycleBeanPostProcessor();
  }

  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
      DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
      defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
      return defaultAdvisorAutoProxyCreator;
  }
}
