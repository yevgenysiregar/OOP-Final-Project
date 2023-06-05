import java.util.Map;

public class Person {

	private String name;
	private Double nameScore;
	private Map<String, Integer> composition;
	private Perfume perfume;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getNameScore() {
		return nameScore;
	}

	public void setNameScore(Double nameScore) {
		this.nameScore = nameScore;
	}

	public Map<String, Integer> getComposition() {
		return composition;
	}

	public void setComposition(Map<String, Integer> composition) {
		this.composition = composition;
	}

	public Perfume getPerfume() {
		return perfume;
	}

	public void setPerfume(Perfume perfume) {
		this.perfume = perfume;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", nameScore=" + nameScore + ", composition=" + composition + ", perfume="
				+ perfume + "]";
	}

}
