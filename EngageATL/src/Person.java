import java.util.List;
import java.util.Map;

public class Person {

	private String name;
	private Map<Group, Integer> preferences;

	public Person(String name, Map<Group, Integer> preferences) {
		this.name = name;
		this.preferences = preferences;
	}

	public String getName() {
		return name;
	}

	public Map<Group, Integer> getPreferences() {
		return preferences;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", preferences=" + preferences + "]";
	}

}
