package Acessoprogramático;

public class TestarLista {

	public static void main(String[] args) {
		Lista list = new Lista();

		list.add();
		list.print();
		System.out.println("Delete the first item");
		list.delete();
		list.print();
		System.out.println("Length of list:");
		System.out.println(list.qtd());
	}

}
