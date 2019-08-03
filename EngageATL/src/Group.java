
public class Group {

	private String name;
	private int maxSize;

	public Group(String name, int maxSize) {
		super();
		this.name = name;
		this.maxSize = maxSize;
	}

	public String getName() {
		return name;
	}

	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", maxSize=" + maxSize + "]";
	}

}
