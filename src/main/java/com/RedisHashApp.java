package com;

import java.util.Collection;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHashApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * type : Hash
		 * 
		 * 설명 : String 필드와 String 값 사이의 Map
		 * 
		 * 명령어 : HSET, HGET, HGETALL, HDEL
		 */
		
		try {

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");
			
			jedis.hset("numberHash","a", "1");
			jedis.hset("numberHash","b", "2");
			jedis.hset("numberHash","c", "3");
			jedis.hset("numberHash","d", "4");
			jedis.hset("numberHash","e", "5");
			
			System.out.println("jedis.hget('numberHash', 'e') : "+jedis.hget("numberHash", "e"));
			
			Map<String, String> numberHash = jedis.hgetAll("numberHash");
	
			System.out.println("numberHash : " + numberHash);
			
			jedis.hdel("numberHash", "c");
			
			Collection<String>  numberHashValues =  numberHash.values();			
			
			for (String number : numberHashValues) {
				System.out.println(number);
			}
			
			jedis.flushAll();
			
			jedis.close();

			pool.destroy();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
