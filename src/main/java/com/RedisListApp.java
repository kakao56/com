package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ListPosition;

public class RedisListApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(" ======================== RedisListApp START ======================== \n");
		
		try {

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");

			 //jedis.rpush(key,value1,value2..); key에 마지막인덱스에 value를 저장한다. value1이 0번이 되고 value2가 1번
			jedis.rpush("strList", "2","3");
			
			System.out.println("\njedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1)); // 0번 부터 마지막 인덱스까지 조회 
			
			//jedis.lpush(key,value1,value2..); key에 0번 인덱스에 value를 저장한다. 이미 사용되고 있는 인덱스가 뒤로 밀린다. value1이 1번이 되고 value2가 0번
			jedis.lpush("strList","1","0"); 
			
			System.out.println("\njedis.lrange(\"strList\", 0, 2) :	" + jedis.lrange("strList", 0, 2));  // 0번 부터 2번 인덱스 까지 조회
			
			System.out.println("\njedis.rpop(\"strList\") :	"+jedis.rpop("strList"));// 마지막 인덱스에서 value 조회하고 제거
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1)); // 0번 부터 마지막 인덱스까지 조회 
			
			System.out.println("\njedis.lpop(\"strList\") :	"+jedis.lpop("strList"));// 0번 인덱스에서 value 조회하고 제거
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1));
			
			System.out.println("\njedis.lindex(\"strList\", 0) :	"+jedis.lindex("strList", 0)); //해당 인덱스 조회
			System.out.println("jedis.lindex(\"strList\", 1) :	"+jedis.lindex("strList", 1));

			jedis.lset("strList", 0, "1.5");//0번째 인덱스에 value 저장 
			System.out.println("\njedis.lset(\"strList\", 0, \"1.5\")");
			System.out.println("jedis.lindex(\"strList\", 0) :	"+jedis.lindex("strList", 0));
			
			jedis.lpushx("strList", "1");//0번째 인덱스에 1 저장 나머지 인덱스 뒤로밀림
			System.out.println("\njedis.lpushx(\"strList\", \"1\")");
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1));
			
			jedis.rpushx("strList", "2.5");//마지막 인덱스에 2.5 저장
			System.out.println("\njedis.rpushx(\"strList\", \"2.5\")");
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1));
			
			
			jedis.linsert("strList", ListPosition.BEFORE , "2.5", "2.2");// value 2.5 전에 2.2 삽입
			System.out.println("\njedis.linsert(\"strList\", ListPosition.BEFORE , \"2.5\", \"2.2\")");
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1));
			
			jedis.linsert("strList", ListPosition.AFTER , "0.9", "1.3");// value 1 후에 1.3 삽입
			System.out.println("\njedis.linsert(\"strList\", ListPosition.BEFORE , \"1\", \"1.3\")");
			System.out.println("jedis.lrange(\"strList\", 0, -1) :	" + jedis.lrange("strList", 0, -1));
			
			jedis.flushAll();
			
			jedis.close();

			pool.destroy();
			
			System.out.println("\n ======================== RedisListApp END ======================== ");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
