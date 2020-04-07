package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisListApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * type : List
		 * 
		 * 설명 : Linked list of String 배열
		 * 
		 * 명령어 : LPUSH, RPUSH, LPOP, RPOP, LINDEX, LRANGE
		 */
		
		try {

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");

			System.out.println("The server is running " + jedis.ping());

			jedis.rpush("alphabetList", "b");
			jedis.rpush("alphabetList", "c");
			jedis.rpush("alphabetList", "d");
			jedis.rpush("alphabetList", "e");
			jedis.rpush("alphabetList", "f");
			jedis.rpush("alphabetList", "g");
			jedis.lpush("alphabetList", "a");

			System.out.println("lindex 0 : " + jedis.lindex("alphabetList", 0));
			System.out.println("lindex 3 : " + jedis.lindex("alphabetList", 3));
			System.out.println("lindex 3 : " + jedis.lrange("alphabetList", 0, -1));
			System.out.println("lindex 3 : " + jedis.lrange("alphabetList", 0, -3));
			//jedis.get("alphabetList");
			for (int i = 0; i < 7; i++) {

				System.out.println(jedis.lpop("alphabetList"));

			}

			jedis.flushAll();
			
			jedis.close();

			pool.destroy();

			System.out.println("RedisListApp END!!!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
