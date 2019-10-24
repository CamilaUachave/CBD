package Acessoprogramático;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Hash {
	Jedis jedis = new Jedis();

	public Hash() {
		this.jedis = new Jedis("localhost");
	}

	public void add(String equipa, String cidade) {
		jedis.hset("portugal",cidade ,equipa);
	}

	public void getCity( String team) {
		Map <String, String> values = jedis.hgetAll("portugal");
		System.out.println(values.get(team));

	}
	public void delete(String team) {
		jedis.hdel("portugal",team); 
	}

	public void print() {
		Map <String, String> values = jedis.hgetAll("portugal");
		System.out.println(values);
	}
	public static void main(String[] args) {
		Hash map = new Hash();

		map.add("Lisboa","SLB");
		map.add("Porto","FCP");
		map.add("Lisboa","Sporting");
		map.add("Braga","SCBraga");


		System.out.println("1)print"); 
		map.print();
		System.out.println("2)Get SLB city:");
		map.getCity("SLB");
		System.out.println("3)Delete Team Sporting:");
		map.delete("Sporting");
		map.print();



	}
}
