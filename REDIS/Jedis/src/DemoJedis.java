import redis.clients.jedis.Jedis;

public class DemoJedis {

	public static void main(String[] args) throws Exception {
		try {
			Jedis jedis = new Jedis("localhost");
		    System.out.println("Connection Succesful");
		    System.out.println("Server Ping"+jedis.ping());
		}catch(Exception e){
		   System.out.println(e);	
		}
	}

}
