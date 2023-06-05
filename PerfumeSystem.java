import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.io.File; // Import the File class
import java.util.List;

public class PerfumeSystem extends Action {

	PersonImpl personImpl = new PersonImpl();
	Scanner scanner = new Scanner(System.in);
	DataMasterImpl dataMasterImpl = new DataMasterImpl();
	File myObj = new File("dataset.txt");

	Map<Character, Integer> value = new HashMap<>();
	List<Perfume> perfumes = new ArrayList<>();

	// start
	public void start() {
		preparation();
		value = dataMasterImpl.loadData();
		createOrder();

	}

	// applications setup
	void preparation() {
		boolean isCreated = dataMasterImpl.checkDataSet();

		if (isCreated) {
			dataMasterImpl.createDataset();
		} else {
			dataMasterImpl.checkFile();
		}

		perfumes = dataMasterImpl.loadPerfume();

	}

	// order step
	void createOrder() {
		System.out.println("=======PREPARATION COMPLETE========");
		System.out.print("Enter Name : ");
		Person person = create(scanner.nextLine());
		System.out.println("==============");
		person.setNameScore(countName(person.getName()));
		person.setPerfume(searchPerfume(person.getNameScore()));
		printOrder(person);

	}

	// create person
	public Person create(String name) {
		Person person = personImpl.create(name);
		return person;
	}

	// count total value based on letter
	private Double countName(String name) {
		Map<Character, Integer> charVal = new HashMap<>();
		char[] nameChar = name.replaceAll(" ", "").toCharArray();
		List<Character> charList = new ArrayList<>();

		int nameScore = 0;

		for (char c : nameChar) {
			charList.add(c);
			charVal.put(Character.toUpperCase(c), value.get(Character.toUpperCase(c)));
		}

		for (Character chars : charList) {
			nameScore += charVal.get(Character.toUpperCase(chars));
		}

		int totalScore = digSum(nameScore);

		System.out.println("Total Score : " + totalScore);

		return Double.valueOf(totalScore);

	}

	// search perfume theme based on score
	private Perfume searchPerfume(Double score) {

		double difference = Double.MAX_VALUE;
		Perfume perfume = new Perfume();
		for (Perfume item : perfumes) {
			if (difference > Math.abs(score - item.getScore())) {
				difference = Math.abs(score - item.getScore());
				perfume = item;
			}
		}

		return perfume;
	}

	void printOrder(Person person) {
		System.out.println("======= DETAIL PERFUME PROFILE ========");
		System.out.println("NAME : " + person.getName());
		System.out.println("PERFUME CATEGORY : " + person.getPerfume().getCategory());
		System.out.println("PERFUME THEME : " + person.getPerfume().getTheme());
		System.out.println("DESCRIPTION : " + person.getPerfume().getDescription());
		System.out.println("COMPOSITION : " + person.getPerfume().getComposition());

	}

	static int digSum(int n) {
		int sum = 0;

		// Loop to do sum while
		// sum is not less than
		// or equal to 9
		while (n > 0 || sum > 9) {
			if (n == 0) {
				n = sum;
				sum = 0;
			}
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}

}
