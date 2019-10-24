package insercaoMassiva;

import java.io.*;
import java.util.*;


public class Inserir {

	public static void main(String[] args) throws IOException {
		Map <String,Integer> mapa = new HashMap<>();
		
		try {
			
			File f = new File("female-names.txt");
			Scanner sc = new Scanner(f);
			List<String> list = new ArrayList<>();
			Map<String,Integer> map = new HashMap<>();
			String letters = "abcdefghijklmnopqrstuvwxyz";
			
			
			while(sc.hasNext()) {
				list.add(sc.next().toLowerCase());
			}
			
			FileWriter file = new FileWriter ("initials4redis.txt");
			PrintWriter save = new PrintWriter(file);
			
			int count=0;
			for (int j = 0; j < 26 ;j++){
				String letra = ""+letters.charAt(j);
				for (int i = 0 ; i < list.size() ; i++) {
					if (list.get(i).charAt(0)== letters.charAt(j)) {
		                count++;
					}
				    map.put(""+letters.charAt(j),count);		  
				}
				save.println(" SET "+letra.toUpperCase()+" "+count );
				count = 0;
			}
			save.close();
				
		}catch (FileNotFoundException e ) {
			System.out.println(e);
		}

	}

}
