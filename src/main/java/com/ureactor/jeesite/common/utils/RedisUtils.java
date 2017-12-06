package com.ureactor.jeesite.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.ureactor.jeesite.common.config.Global;

/**
 * redis缓存工具类
 * 
 * @author fangpengli@uworks.cc
 *
 */
@SuppressWarnings("unchecked")
public class RedisUtils {

  private static RedisTemplate<String, String> redisTemplate =
      (RedisTemplate<String, String>) SpringContextHolder.getBean("redisTemplate");

  /**
   * 获取缓存前缀
   */
  private static final String PREFIX = Global.getConfig("redis.keyPrefix") + "_";

  /**
   * 获取用于序列化字符串的序列化类
   * 
   * @return
   */
  private static RedisSerializer<String> getStringSerializer() {
    return redisTemplate.getStringSerializer();
  }

  /**
   * 获取object系列化的系列化类
   * 
   * @return
   */
  private static RedisSerializer<Object> getObjectSerializer() {
    return (RedisSerializer<Object>) redisTemplate.getDefaultSerializer();
  }

  /**
   * 单个添加
   * 
   * @param key
   * @param value
   * @return
   */
  public static Boolean put(String key, Object value) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        connection.set(getStringSerializer().serialize(PREFIX + key), getObjectSerializer().serialize(value));
        // setNX当key不存在的时候才会去设值
        return true;
      }
    });
    return result;
  }

  /**
   * 将field域中key的值设置成value
   * 
   * @param key
   * @param field
   * @param value
   * @return
   */
  public static Boolean hput(String key, Object field, Object value) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.hSet(getStringSerializer().serialize(PREFIX + key), getObjectSerializer().serialize(field),
            getObjectSerializer().serialize(value));
      }
    });
    return result;
  }

  /**
   * 添加数据并设置过期
   * 
   * @param key
   * @param value
   * @param expired //单位是秒
   * @return
   */
  public static Boolean set(String key, Object value, long expired) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        connection.setEx(getStringSerializer().serialize(PREFIX + key), expired, getObjectSerializer().serialize(value));
        return true;
      }
    });
    return result;
  }

  /**
   * 根据key获取value
   * 
   * @param key
   * @return
   */
  public static Object get(String key) {
    Object result = redisTemplate.execute(new RedisCallback<Object>() {

      @Override
      public Object doInRedis(RedisConnection connection) throws DataAccessException {
        byte[] value = connection.get(getStringSerializer().serialize(PREFIX + key));
        if (value == null) {
          return null;
        }
        return getObjectSerializer().deserialize(value);
      }
    });
    return result;
  }

  /**
   * 
   * @param key
   * @return
   */
  public static Object hget(String key, Object field) {
    Object result = redisTemplate.execute(new RedisCallback<Object>() {

      @Override
      public Object doInRedis(RedisConnection connection) throws DataAccessException {
        byte[] value = connection.hGet(getStringSerializer().serialize(PREFIX + key), getObjectSerializer().serialize(field));
        if (value == null) {
          return null;
        }
        return getObjectSerializer().deserialize(value);
      }
    });
    return result;
  }

  /**
   * 
   * @param key
   * @return
   */
  public static Map<String, String> hgetAll(String key) {
    Map<String, String> result = redisTemplate.execute(new RedisCallback<Map<String, String>>() {
      @Override
      public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
        Map<byte[], byte[]> value = connection.hGetAll(getStringSerializer().serialize(PREFIX + key));
        if (value == null) {
          return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        Set<byte[]> keys = value.keySet();
        for (byte[] preKey : keys) {
          map.put(getStringSerializer().deserialize(preKey), getStringSerializer().deserialize(value.get(preKey)));
        }
        return map;
      }
    });
    return result;
  }

  /**
   * 根据key删除缓存
   * 
   * @param key
   * @return
   */
  public static Boolean delete(String key) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        long count = connection.del(getStringSerializer().serialize(PREFIX + key));
        if (count > 0) {
          return new Boolean(true);
        } else {
          return new Boolean(false);
        }
      }
    });
    return result;
  }

  public static Boolean hdelete(String key) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        long count = connection.hDel(getStringSerializer().serialize(PREFIX + key));
        if (count > 0) {
          return new Boolean(true);
        } else {
          return new Boolean(false);
        }
      }
    });
    return result;
  }

  /**
   * 删除域fieldkey
   * 
   * @param key
   * @param field
   * @return
   */
  public static Boolean hdelete(String key, Object field) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        long count = connection.hDel(getStringSerializer().serialize(PREFIX + key), getObjectSerializer().serialize(field));
        if (count > 0) {
          return new Boolean(true);
        } else {
          return new Boolean(false);
        }
      }
    });
    return result;
  }

  /**
   * 给key设置过期时间
   * 
   * @param key
   * @param exprired
   * @return
   */
  public static Boolean setExpired(String key, long exprired) {
    Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.expire(getStringSerializer().serialize(PREFIX + key), exprired);
      }
    });
    return result;
  }

  public static Long hLen(String key) {
    Long result = redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.hLen(getStringSerializer().serialize(PREFIX + key));
      }
    });
    return result;
  }

  public static Set<byte[]> hKeys(String key) {
    Set<byte[]> result = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {

      @Override
      public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.hKeys(getStringSerializer().serialize(key));
      }
    });
    return result;
  }

  public static Collection<byte[]> hVals(String key) {
    Collection<byte[]> result = redisTemplate.execute(new RedisCallback<Collection<byte[]>>() {

      @Override
      public Collection<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.hVals(getStringSerializer().serialize(key));
      }
    });
    return result;
  }

  /**
   * 获取byte[]类型Key
   * 
   * @param key
   * @return
   */
  public static Object getObjectKey(byte[] key) {
    try {
      return StringUtils.toString(key);
    } catch (UnsupportedOperationException uoe) {
      try {
        return RedisUtils.toObject(key);
      } catch (UnsupportedOperationException uoe2) {
        uoe2.printStackTrace();
      }
    }
    return null;
  }

  /**
   * byte[]型转换Object
   * 
   * @param key
   * @return
   */
  public static Object toObject(byte[] bytes) {
    return ObjectUtils.unserialize(bytes);
  }

}
