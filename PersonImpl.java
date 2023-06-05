public class PersonImpl extends Action {

	public Person create(String name) {
		Person person = new Person(name);
		return person;
	}

}