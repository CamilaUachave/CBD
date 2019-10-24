package SistemaDeMensagens;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Jedis jedis = new Jedis("localhost");
		Socket socket = new Socket("localhost",4999);
		ScanParams parametro = new ScanParams();
		ScanResult <Entry<String, String>> scanresult;
		int cursor = 0;

		System.out.println("-------------Welcome-------------");
		System.out.print("username: ");
		Scanner sc = new Scanner (System.in);
		String user = sc.next();
		jedis.sadd("users",user);

		PrintWriter print = new PrintWriter(socket.getOutputStream());
		print.println(user);
		print.flush();

		int escolha ;
		String show;
		int count = 0;

		do {
			count++;
			show= "------Choose an option:----- 1.Follow someone(write the user) 2.Post a quote 3.Look feed of (write the user) 4.print followers 5.End  ";
			System.out.println(show);
			escolha = sc.nextInt();	

			String list;

			switch(escolha){

			case 1: System.out.println("Username:");
			String userF = sc.next(); 
			jedis.sadd(user,userF);
			break;
			case 2: 
				System.out.print("Quote:");
				String message = sc.next();
				//esse count poderia ser a hora que a mensagem entrou
				jedis.hset("post",user+count,message);

				break;  

			case 3: System.out.println("Username:");
			String userM= sc.next(); 
			parametro.match(userM+"*");
			do {
				scanresult = jedis.hscan("post",String.valueOf(cursor),parametro);
				if (jedis.sismember(user,userM) ) {
					if (!scanresult.getResult().isEmpty()) {
						Iterator<Entry<String, String>> s1 = scanresult.getResult().iterator();
						while(s1.hasNext()) {
							System.out.println(s1.next());
						}
					}
				}
				else {
					System.out.println("First follow this user!!!");
				}
				cursor = Integer.parseInt(scanresult.getCursor());
			}while(cursor > 0);

			break;

			case 4: 
				System.out.println("Following: "+jedis.smembers(user));

			}
		}while(escolha != 5) ;		

	}
}
