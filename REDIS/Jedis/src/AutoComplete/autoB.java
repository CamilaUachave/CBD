package AutoComplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Spliterator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

public class autoB {
	private Jedis jedis;

	public autoB(){
		this.jedis = new Jedis("localhost");
	}

	public void openAndsave(File file) {
		try {
			Scanner csv =new Scanner(file);
			while(csv.hasNext()){
				String data=csv.next(); 
				String values[]=data.split(",");
				jedis.zadd("myzset",Double.parseDouble(values[2]),values[0]);
			}
			csv.close();
		}catch(FileNotFoundException e ) {
			System.out.println(e);
		}

	}
	public void print() {
		Scanner read = new Scanner(System.in);
		int cursor = 0;
		ScanParams parametro = new ScanParams();
		ScanResult<Tuple> scanresult;
		System.out.println("Search for ('Enter' for quit):");
		String prefix = read.next();
		Iterator<Tuple> s ;
		parametro.match(prefix+"*");

		do {	
			scanresult = jedis.zscan("myzset",String.valueOf(cursor),parametro);
            
			if (!scanresult.getResult().isEmpty()) {
				s=scanresult.getResult().iterator();
				while(s.hasNext()) {
					Tuple elem = s.next();
					jedis.zadd("elements",elem.getScore(),elem.getElement());
				}
			}
			cursor = Integer.parseInt(scanresult.getCursor());
		} while (cursor > 0);


		Iterator<Tuple> z = jedis.zrevrangeWithScores("elements", 0, -1).iterator();
		while(z.hasNext()) {
			Tuple elem1 = z.next();
			System.out.println(elem1.getElement() +"-->"+elem1.getScore());
		}
		jedis.flushDB();

	}

	public static void main(String[] args) {
		autoB auto = new autoB ();
		File f = new File ("nomes-registados-2018.csv");
		auto.openAndsave(f);
		auto.print();
	}
}
