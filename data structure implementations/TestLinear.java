
public class TestLinear {

	public static void main(String[] args) {

		LinearHashing<Integer> linearHashing = new LinearHashing<>(5);

		linearHashing.insert(5);
		linearHashing.insert(16);
		linearHashing.insert(27);
		System.out.println(linearHashing);

		System.out.println("-------------------------------------");
		System.out.println("Delete 27");
		linearHashing.delete(27);

		System.out.println("Delete 5");
		linearHashing.delete(5);

		System.out.println("Delete 5");
		linearHashing.delete(5);

		System.out.println("Delete 38");
		linearHashing.delete(38);

		linearHashing.insert(49);

		linearHashing.insert(60);

		System.out.println("-------------------------------------");

		System.out.println(linearHashing);

		System.out.println("-------------------------------------");

		System.out.println("Search 60");
		linearHashing.search(60);

		System.out.println("Search 80");
		linearHashing.search(80);

		System.out.println("-------------------------------------");

		
	}
}
