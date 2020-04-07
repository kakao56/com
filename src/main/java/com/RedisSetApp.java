package com;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSetApp {

	public static void main(String[] args) {

		/**
		 * type : Set
		 * 
		 * Set 설명 : 정열이 안된 유니크 스트링의 집합
		 * 			List와 비슷하지만 중복이 안되는 유니크 스트링만 취급함
		 * 
		 * 명령어 : SADD, SMEMBERS, SISMEMBER, SREM
		 */
		
		try {

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");
			
			jedis.sadd("alphabetSet", "a");
			jedis.sadd("alphabetSet", "b");
			jedis.sadd("alphabetSet", "c");
			jedis.sadd("alphabetSet", "d");
			jedis.sadd("alphabetSet", "e");
			
			System.out.println("jedis.smembers('alphabetSet') " + jedis.smembers("alphabetSet"));
			System.out.println("jedis.sismember('alphabetSet', 'd')) " + jedis.sismember("alphabetSet", "d"));
			System.out.println("jedis.srem('alphabetSet', 'd')) " + jedis.srem("alphabetSet", "d"));
			System.out.println("jedis.sismember('alphabetSet', 'd')) " + jedis.sismember("alphabetSet", "d"));
			
			Set<String> alphabetSet = jedis.smembers("alphabetSet");

			for (String alphabet : alphabetSet) {
				System.out.println(alphabet);
			}

			jedis.flushAll();
			
			jedis.close();

			pool.destroy();
			
			System.out.println("RedisSetApp END!!!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
