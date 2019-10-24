package AutoComplete;


import java.io.*;
import java.util.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;


public class autoA {

	public static void main(String[] args) {
		Jedis jedis = new Jedis();
		Scanner read = new Scanner(System.in);
		try {
			File f = new File("female-names.txt");
			Scanner sc = new Scanner(f);
			ScanParams parametro = new ScanParams();
			ScanResult <String> scanresult;
			List<String> s = new ArrayList<>();

			while(sc.hasNext()) {
				while(sc.hasNext()) {
					jedis.sadd("auto-complete",sc.next());
				}
			}
			int cursor = 0;
			System.out.println("Search for ('Enter' for quit):");
			String prefix = read.next();
			parametro.match(prefix+"*");

			do {	
				scanresult = jedis.sscan("auto-complete",String.valueOf(cursor),parametro);
				if (!scanresult.getResult().isEmpty()) {
					s=scanresult.getResult();
					Collections.sort(s);
					for (int i = 0; i < s.size(); i++) {
						System.out.println(s.get(i));
					}
				}
				cursor = Integer.parseInt(scanresult.getCursor());
			} while (cursor > 0);
			sc.close();



		}catch (FileNotFoundException e ) {
			System.out.println(e);
		}

	}
}
