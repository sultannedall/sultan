
public class TestQuadratic {

	public static void main(String[] args) {

		QuadraticHashing<Integer> quadraticHashing = new QuadraticHashing<>(5);

		quadraticHashing.insert(66);
		quadraticHashing.insert(89);
		quadraticHashing.insert(97);
		quadraticHashing.insert(62);
		quadraticHashing.insert(85);
		quadraticHashing.insert(34);
		quadraticHashing.insert(18);
		quadraticHashing.insert(99);

		System.out.println(quadraticHashing);

		System.out.println("-------------------------------------");
		System.out.println("Delete 66");
		quadraticHashing.delete(66);

		System.out.println("Delete 66");
		quadraticHashing.delete(66);
		
		System.out.println("Delete 38");
		quadraticHashing.delete(38);

		System.out.println("-------------------------------------");

		System.out.println(quadraticHashing);

		System.out.println("-------------------------------------");

		System.out.println("Search 34");
		quadraticHashing.search(34);

		System.out.println("Search 80");
		quadraticHashing.search(80);

		System.out.println("-------------------------------------");

	}

}
