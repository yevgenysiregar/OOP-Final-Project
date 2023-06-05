import java.io.File; // Import the File class
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileNotFoundException; // Import the IOException class to handle errors
import java.util.List;

public class DataMasterImpl implements DataMasterInterface {

	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	char[] charAlpha = alphabet.toCharArray();
	String fileName = "dataset.txt";
	File myObj = new File(fileName);
	File perfumeObj = new File("perfume-theme.txt");

	@Override
	public boolean checkDataSet() {
		System.out.println("Check Dataset");
		try {
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				return true;
			} else {
				System.out.println("File already exists.");
				return false;

			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void createDataset() {

		try {

			Scanner scanner = new Scanner(System.in);

			Map<Character, Integer> value = new HashMap<>();

			for (char c : charAlpha) {
				System.out.print("Enter value for letter " + c + " : ");
				int number = scanner.nextInt();
				value.put(Character.toUpperCase(c), number);
			}

			FileWriter myWriter = new FileWriter(fileName);

			for (Map.Entry<Character, Integer> entry : value.entrySet()) {
				myWriter.write(entry.toString() + "\n");
			}

			myWriter.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	@Override
	public void checkFile() {
		System.out.println("Checking File");
		Map<Character, Integer> value = new HashMap<>();

		try {
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				// String[] list = data.toCharArray();
				value.put(data.charAt(0), Character.getNumericValue(data.charAt(2)));
			}
			myReader.close();

			if (value.size() < 1) {
				System.out.println("Data Set is Empty");
			}

			int notFound = 0;
			Set<Character> charNotFound = new HashSet<>();

			for (char c : charAlpha) {
				Integer val = value.get(Character.toUpperCase(c));
				if (val == null) {
					notFound++;
					charNotFound.add(Character.toUpperCase(c));
				}
			}

			if (notFound > 0) {
				System.out.println("Formula is not set for Char : " + charNotFound.toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	@Override
	public Map<Character, Integer> loadData() {

		System.out.println("Load data");

		try {

			Map<Character, Integer> data = new HashMap<>();

			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String datas = myReader.nextLine();
				data.put(datas.charAt(0), Character.getNumericValue(datas.charAt(2)));
			}
			myReader.close();
			return data;

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;

		}
	}

	// load data perfume
	@Override
	public List<Perfume> loadPerfume() {
		System.out.println("Load perfume data");
		List<Perfume> perfumes = new ArrayList<>();

		try {

			Scanner myReader = new Scanner(perfumeObj);

			while (myReader.hasNextLine()) {
				String datas = myReader.nextLine();
				String[] arrStrings = datas.split("\\|");
				Perfume perfume = new Perfume();
				perfume.setCategory(arrStrings[0]);
				perfume.setDescription(arrStrings[1]);
				perfume.setTheme(arrStrings[2]);
				perfume.setComposition(arrStrings[3]);
				perfumes.add(perfume);
			}

			Double score = 0.0;
			Double add = 10.0 / perfumes.size();

			for (Perfume perfume : perfumes) {
				score += add;
				perfume.setScore(score);
			}

			return perfumes;
		} catch (

		FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;

		}
	}

}
