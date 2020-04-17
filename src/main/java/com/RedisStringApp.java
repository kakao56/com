package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisStringApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			System.out.println(" ======================== RedisStringApp START ======================== \n");

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

			int a = 1;

			while (true) {

				Jedis jedis = pool.getResource();
				jedis.auth("redis6379");

				jedis.setex("rVal" + a, 5, (int) (Math.random() * 100000)+ "");	//jedis.setex(key,time,value) 일정시간 동안 저장

				for (int i = 0; i < a; i++) {

					if (jedis.get("rVal" + (a-i)) != null) {

						System.out.print("rVal" + (a-i) + " : " + jedis.get("rVal" + (a-i))+"	");
					}

				}
				
				System.out.println("");

				if (jedis.exists("rVal10")) { //jedis.exists(key) 존재 여부
					jedis.del("rVal10");//jedis.del(key) 삭제
					System.out.println("\njedis.del(\"rVal10\")\n");
				}

				jedis.close();

				a++;

				Thread.sleep(1000);

				if (a > 11) {
					break;
				}

			}
			

			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");
						
			System.out.println("\n-------------------------------------------------------------------------------------------------------");		
			
			System.out.println("\njedis.keys(\"*\") :	"+jedis.keys("*"));//jedis.keys("*") 모든 key 조회
			System.out.println("\njedis.keys(\"*8\") :	"+jedis.keys("*8"));//jedis.keys("*8") 규칙에 맞는 key 조회
			System.out.println("\njedis.randomKey() :	"+ jedis.randomKey());//jedis.randomKey() key 랜덤 조회
			System.out.println("\njedis.exists(\"rVal10\") :	"+jedis.exists("rVal10"));
			System.out.println("\njedis.exists(\"rVal11\") :	"+jedis.exists("rVal11"));
			System.out.println("\njedis.strlen(\"rVal11\") :	"+jedis.strlen("rVal11"));//jedis.strlen(key) value의 길이
			System.out.println("\njedis.ttl(\"rVal11\") :	"+jedis.ttl("rVal11"));//jedis.ttl(key) key의 남은 시간
			
			System.out.println("\n-------------------------------------------------------------------------------------------------------");	
			
			jedis.mset("rVal12", "2"
					,"rVal14", "4"
					,"rVal15", "5");//다중 String 저장
			
			jedis.set("rVal13", "3");//단건 String 저장

			System.out.println("\njedis.mset(\"rVal12\", \"2\"\r\n" + 	
					"	,\"rVal14\", \"4\"\r\n" + 
					"	,\"rVal15\", \"5\")");
			
			System.out.println("\njedis.set(\"rVal13\", \"3\")");
			
			System.out.println("\njedis.keys(\"*\") :	"+jedis.keys("*"));			
			
			System.out.println("\njedis.mget(\"rVal11\",\"rVal13\",\"rVal10\"):	"+jedis.mget("rVal11","rVal13","rVal10")); //다중 String 조회	
			
			System.out.println("\n-------------------------------------------------------------------------------------------------------");	
			
			jedis.incr("rVal13");//1증가			
			
			System.out.println("\njedis.incr(\"rVal13\") :	"+jedis.get("rVal13"));
		
			jedis.incrBy("rVal13",10);//10증가		
			
			System.out.println("\njedis.incrBy(\"rVal13\",10) :	"+jedis.get("rVal13"));
			
			jedis.decr("rVal13");//1감소			
			
			System.out.println("\njedis.decr(\"rVal13\") :	"+jedis.get("rVal13"));
			
			jedis.decrBy("rVal13",10);//10감소		
			
			System.out.println("\njedis.decrBy(\"rVal13\",10) :	"+jedis.get("rVal13"));
			
			jedis.append("rVal13", "    문자열 추가");// 문자열추가
			
			System.out.println("\njedis.append(\"rVal13\", \"    문자열 추가\") :	"+jedis.get("rVal13"));
			
			jedis.save(); //C:\redis dump.rdb 저장
			System.out.println("\njedis.save()");
			
			jedis.flushAll();// 모든 key/value 제거
			System.out.println("\njedis.flushAll()");
			
			System.out.println("\njedis.keys(\"*\") :	"+jedis.keys("*"));
			System.out.println("\njedis.time() :	"+jedis.time()); //unix time in seconds
			System.out.println("\njedis.info() \n\n"+jedis.info());	//Redis 서버 상태 조회 
			
			jedis.close();
			
			pool.destroy();

			
			System.out.println("\n ======================== RedisStringApp END ======================== ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
