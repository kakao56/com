package com;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHashApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			System.out.println(" ======================== RedisHashApp START ======================== \n");
			
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);			
			Jedis jedis = pool.getResource();
			jedis.auth("redis6379");
			
			Map<String, String> smithMap = new HashMap<String, String>();
			smithMap.put("empno","7369"); 							
			smithMap.put("ename","SMITH");
			smithMap.put("job","CLERK");
			smithMap.put("mgr","7902");
			smithMap.put("hiredate","1980-12-17  00:00:00");
			smithMap.put("sal","800");
			//smithMap.put("comm", null);//value sent to redis cannot be null
			smithMap.put("deptno","20");
			
			Map<String, String> allenMap = new HashMap<String, String>();
			allenMap.put("empno","7499"); 							  							
			allenMap.put("ename","SALESMAN");
			allenMap.put("job","ALLEN");
			allenMap.put("mgr","7698");
			allenMap.put("hiredate","1981-02-20 00:00:00");
			allenMap.put("sal","1600");
			allenMap.put("comm", "300");
			allenMap.put("deptno","30");
			
			Map<String, String> wardMap = new HashMap<String, String>();
			wardMap.put("empno","7521"); 														
			wardMap.put("ename","WARD");
			wardMap.put("job","SALESMAN");
			wardMap.put("mgr","7698");
			wardMap.put("hiredate","1981-02-22 00:00:00");
			wardMap.put("sal","1250");
			wardMap.put("comm", "500");
			wardMap.put("deptno","30");
			
			Map<String, String> kingMap = new HashMap<String, String>();
			kingMap.put("empno","7839"); 																				
			kingMap.put("ename","KING");
			kingMap.put("job","PRESIDENT");
			//kingMap.put("mgr","");//redis 빈칸이 들어감
			kingMap.put("hiredate","1981-02-22 00:00:00");
			kingMap.put("sal","5000");
			//smithMap.put("comm", "");//redis 빈칸이 들어감
			kingMap.put("deptno","10");
			
			jedis.hmset("smithMap",smithMap);//하나의 key에 여러개의 field와 value를 동시에 저장
			jedis.hmset("allenMap",allenMap);
			jedis.hmset("wardMap",wardMap);
			jedis.hmset("kingMap",kingMap);
			
			System.out.println("jedis.hgetAll(\"smithMap\") :	"+jedis.hgetAll("smithMap"));//key에 대한 모든 field와 value를 조회
			System.out.println("jedis.hgetAll(\"allenMap\") :	"+jedis.hgetAll("allenMap"));
			System.out.println("jedis.hgetAll(\"wardMap\") :	"+jedis.hgetAll("wardMap"));
			System.out.println("jedis.hgetAll(\"kingMap\") :	"+jedis.hgetAll("kingMap"));
			
			System.out.println("\njedis.hkeys(\"smithMap\") :	"+jedis.hkeys("smithMap"));//key에 대한 모든 field만 조회
			System.out.println("jedis.hkeys(\"allenMap\") :	"+jedis.hkeys("allenMap"));
			System.out.println("jedis.hkeys(\"wardMap\") :	"+jedis.hkeys("wardMap"));
			System.out.println("jedis.hkeys(\"kingMap\") :	"+jedis.hkeys("kingMap"));
			
			System.out.println("\njedis.hlen(\"smithMap\") :	"+jedis.hlen("smithMap"));//key에 대한 모든 field 개수 조회
			System.out.println("jedis.hlen(\"allenMap\") :	"+jedis.hlen("allenMap"));
			System.out.println("jedis.hlen(\"wardMap\") :	"+jedis.hlen("wardMap"));
			System.out.println("jedis.hlen(\"kingMap\") :	"+jedis.hlen("kingMap"));
			
			System.out.println("\njedis.hvals(\"smithMap\") :	"+jedis.hvals("smithMap"));//key에 대한 모든 value만 조회
			System.out.println("jedis.hvals(\"allenMap\") :	"+jedis.hvals("allenMap"));
			System.out.println("jedis.hvals(\"wardMap\") :	"+jedis.hvals("wardMap"));
			System.out.println("jedis.hvals(\"kingMap\") :	"+jedis.hvals("kingMap"));

			System.out.println("\njedis.hmget(\"smithMap\",\"empno\",\"ename\") :	"+jedis.hmget("smithMap","empno","ename"));//해당 key의 특정 field의 value만 조회
			System.out.println("jedis.hmget(\"allenMap\",\"job\",\"mgr\") :	"+jedis.hmget("allenMap","job","mgr"));
			System.out.println("jedis.hmget(\"wardMap\",\"hiredate\",\"sal\") :	"+jedis.hmget("wardMap","hiredate","sal"));
			System.out.println("jedis.hmget(\"kingMap\",\"empno\",\"ename\",\"job\",\"sal\") :	"+jedis.hmget("kingMap","empno","ename","job","sal"));
			
			System.out.println("\njedis.hexists(\"smithMap\", \"comm\") :	"+jedis.hexists("smithMap", "comm"));//해당 key의 특정 field 여부 조회
			System.out.println("jedis.hexists(\"allenMap\", \"comm\") :	"+jedis.hexists("allenMap", "comm"));
			
			jedis.hdel("allenMap", "comm");//해당 key의 특정 field 삭제
			System.out.println("\njedis.hdel(\"allenMap\", \"comm\")");
			
			System.out.println("\njedis.hgetAll(\"allenMap\") :	"+jedis.hgetAll("allenMap"));
			System.out.println("jedis.hexists(\"allenMap\", \"comm\") :	"+jedis.hexists("allenMap", "comm"));
			
			jedis.hset("allenMap", "comm", "123");//해당 key의 특정 field 저장
			System.out.println("\njedis.hset(\"allenMap\", \"comm\", \"123\");");
			
			System.out.println("jedis.hgetAll(\"allenMap\") :	"+jedis.hgetAll("allenMap"));
			System.out.println("\njedis.hexists(\"allenMap\", \"comm\") :	"+jedis.hexists("allenMap", "comm"));
						
			jedis.flushAll();
			
			jedis.close();

			pool.destroy();
			
			System.out.println("\n ======================== RedisHashApp END ======================== ");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
