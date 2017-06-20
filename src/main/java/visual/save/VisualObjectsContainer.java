package visual.save;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class VisualObjectsContainer implements Serializable {

	private static VisualObjectsContainer instance = new VisualObjectsContainer();

	private static Logger LOG = Logger.getLogger(VisualObjectsContainer.class);
	Invest gov = new Invest();
	Invest chastn = new Invest();
	Technology_save technology = new Technology_save();
	Map<Integer, Spros_save> sprosMap = new HashMap<Integer, Spros_save>();
	List<TableAndGrafSave> tables = new ArrayList<TableAndGrafSave>();
	int startYear;

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	int duration;

	public Spros_save getSprosMap(int vedIndex) {
		return sprosMap.get(vedIndex);
	}

	public TableAndGrafSave getTableAndGraf(int vedIndex) {
		return tables.get(vedIndex);
	}

	public Technology_save getTechnology() {
		return technology;
	}

	public Invest getInvest(boolean isgov) {
		if (isgov) {
			return gov;
		} else {
			return chastn;
		}
	}

	public static synchronized VisualObjectsContainer getInstance() {
		return instance;
	}

	private static final long serialVersionUID = 1L;

	private static ObjectInputStream in;

	private static ObjectOutputStream out;

	private VisualObjectsContainer() {
		for (int i = 0; i < 25; i++) {
			sprosMap.put(i, new Spros_save());
			tables.add(new TableAndGrafSave());
		}
	}

	public static void saveToFile(String fileName) throws FileNotFoundException, IOException {
		out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(instance);
	}

	public static VisualObjectsContainer readFromFile(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		in = new ObjectInputStream(new FileInputStream(fileName));
		instance = (VisualObjectsContainer) in.readObject();
		return instance;
	}

}
