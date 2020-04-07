package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSortedSetApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * type : Sorted Set
		 * 
		 * Sorted Set 설명 : Member라 불리는 유니크 Key와 Score라 불리는 Value의 쌍으로 Score 기반 sorting
		 * 제공
		 * 
		 * 명령어 :
		 */

		try {

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");

			jedis.zadd("scoreSet", 2, "안녕하세요");
			jedis.zadd("scoreSet", 3, "아이디");
			jedis.zadd("scoreSet", 4, "님 저희 물건을 구매해주셔서 고객님 고맙습니다. 고객님께서 구매하신");
			jedis.zadd("scoreSet", 5, "물건");
			jedis.zadd("scoreSet", 6, "은");
			jedis.zadd("scoreSet", 6, "날짜에");
			jedis.zadd("scoreSet", 7, "배송될 예정입니다.");

			System.out.println("jedis.zrange('scoreSet')"+jedis.zrange("scoreSet", 0, -1));
			System.out.println("jedis.zcard('scoreSet')"+jedis.zcard("scoreSet"));
			System.out.println("jedis.zrank('scoreSet')"+jedis.zrank("scoreSet","물건"));
			System.out.println("jedis.zrem('scoreSet')"+jedis.zrem("scoreSet","안녕하세요"));
			System.out.println("jedis.zrange('scoreSet')"+jedis.zrange("scoreSet", 0, -1));
			
			jedis.flushAll();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
