package Acessoprogramático;

import java.util.*;

import redis.clients.jedis.Jedis;

public class Lista {
	private Jedis jedis;

	public Lista() {
		this.jedis = new Jedis("localhost");
	}

	public void add() {
		jedis.lpush("dog","pug");
		jedis.lpush("dog","Poodle");
		jedis.lpush("dog","Husky");
		jedis.lpush("dog","Boxer");
	}
	public String delete() {
		return jedis.lpop("dog"); 
	}

	public Long qtd() {
		return jedis.llen("dog"); 
	}

	public void print() {
		List<String> list = jedis.lrange("dog", 0, 3);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}

	}


}
