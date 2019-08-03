import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataParser {

	public static void main(String[] args) {
		Set<Group> groups = makeGroups("group_sizes.tsv");
		System.out.println(groups.size());
		Set<Person> people = makePeople("preferences.tsv", groups);
		System.out.println(people.size());
	}

	public static Set<Group> makeGroups(String filename) {
		Set<Group> groups = new HashSet<Group>();
		// Open the file
		Scanner sc;
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find group size file.");
			return groups;
		}
		// Read the groups from the file
		while (sc.hasNext()) {
			String[] line = sc.nextLine().split("\t");
			Group g = new Group(line[0], Integer.parseInt(line[1]));
			groups.add(g);
		}
		// Close up shop
		sc.close();
		return groups;
	}

	public static Set<Person> makePeople(String filename, Set<Group> groups) {
		Set<Person> people = new HashSet<Person>();
		Map<String, Group> groupMap = toGroupMap(groups);
		// Open the file
		Scanner sc;
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find preference file.");
			return people;
		}
		// Read the header row
		List<String> groupNameList = new ArrayList<String>();
		String[] header = sc.nextLine().split("\t");
		for (int i = 2; i < header.length; i++) {
			groupNameList.add(header[i]);
		}
		// Read the people
		while (sc.hasNext()) {
			String[] row = sc.nextLine().split("\t");
			Map<Group, Integer> prefMap = new HashMap<Group, Integer>();
			for (int i = 2; i < row.length; i++) {
				if (row[i].length() == 0)
					continue;
				int prefNum = Integer.parseInt(row[i]);
				if (prefNum <= 5) {
					Group g = groupMap.get(groupNameList.get(i - 2));
					prefMap.put(g, prefNum - 1);
				}
			}
			people.add(new Person(row[0], prefMap));
		}
		sc.close();
		return people;
	}

	private static Map<String, Group> toGroupMap(Set<Group> groups) {
		Map<String, Group> out = new HashMap<String, Group>();
		for (Group gr : groups) {
			out.put(gr.getName(), gr);
		}
		return out;
	}

}
