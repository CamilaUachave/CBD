package SistemaDeMensagens;

import java.net.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		Jedis jedis = new Jedis("localhost");

		ServerSocket server = new ServerSocket(4999);
		Socket s = server.accept();

		Scanner sc = new Scanner(System.in);
		ScanParams parametro = new ScanParams();
		ScanResult<Entry<String, String>> scanresult;
		int cursor = 0;
		while(true) {
			InputStreamReader i = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(i);
			String str = bf.readLine();
			System.out.println("Feed de "+str);

			parametro.match(str+"*");

			do {
				scanresult = jedis.hscan("post",String.valueOf(cursor),parametro);

				if (!scanresult.getResult().isEmpty()) {
					Iterator<Entry<String, String>> s1 = scanresult.getResult().iterator();
					while(s1.hasNext()) {
						System.out.println(s1.next());
					}
				}

				cursor = Integer.parseInt(scanresult.getCursor());
			}while(cursor > 0);
		}

	}



}
