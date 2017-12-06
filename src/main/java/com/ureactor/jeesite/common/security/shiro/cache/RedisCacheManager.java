package com.ureactor.jeesite.common.security.shiro.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.ureactor.jeesite.common.utils.RedisUtils;
import com.ureactor.jeesite.common.web.Servlets;

public class RedisCacheManager implements CacheManager {

  private String cacheKeyPrefix = "shiro_cache";

  public String getCacheKeyPrefix() {
    return cacheKeyPrefix;
  }

  public void setCacheKeyPrefix(String cacheKeyPrefix) {
    this.cacheKeyPrefix = cacheKeyPrefix;
  }



  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    return new RedisCache<K, V>(name);
  }

  /**
   * 自定义授权缓存管理类
   * 
   * @author ureactor
   * @version 2014-7-20
   */
  public class RedisCache<K, V> implements Cache<K, V> {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private String cacheKeyName = null;

    public RedisCache(String cacheKeyName) {
      this.cacheKeyName = cacheKeyName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) throws CacheException {
      if (key == null) {
        return null;
      }

      V v = null;
      HttpServletRequest request = Servlets.getRequest();
      if (request != null) {
        v = (V) request.getAttribute(cacheKeyName);
        if (v != null) {
          return v;
        }
      }

      V value = null;
      value = (V) RedisUtils.hget(cacheKeyName, key);
      logger.debug("get {} {} {}", cacheKeyName, key, request != null ? request.getRequestURI() : "");
      if (request != null && value != null) {
        request.setAttribute(cacheKeyName, value);
      }

      return value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
      if (key == null) {
        return null;
      }
      RedisUtils.hput(cacheKeyName, key, value);
      logger.debug("put {} {} = {}", cacheKeyName, key, value);
      return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) throws CacheException {
      V value = null;
      value = (V) RedisUtils.hget(cacheKeyName, key);
      RedisUtils.hdelete(cacheKeyName, key);
      logger.debug("remove {} {}", cacheKeyName, key);
      return value;
    }

    @Override
    public void clear() throws CacheException {
      RedisUtils.hdelete(cacheKeyName);
    }

    @Override
    public int size() {
      int size = RedisUtils.hLen(cacheKeyName).intValue();
      return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
      Set<K> keys = Sets.newHashSet();
      Set<byte[]> set = RedisUtils.hKeys(cacheKeyName);
      for (byte[] key : set) {
        Object obj = (K) RedisUtils.getObjectKey(key);
        if (obj != null) {
          keys.add((K) obj);
        }
      }
      logger.debug("keys {} {} ", cacheKeyName, keys);
      return keys;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
      Collection<V> vals = Collections.emptyList();;
      Collection<byte[]> col = RedisUtils.hVals(cacheKeyName);
      for (byte[] val : col) {
        Object obj = RedisUtils.toObject(val);
        if (obj != null) {
          vals.add((V) obj);
        }
      }
      logger.debug("values {} {} ", cacheKeyName, vals);
      return vals;
    }

  }

}
