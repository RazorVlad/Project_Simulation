package mathModel;

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

import mathModel.fieldsInteraction.FieldsInteraction;
import mathModel.manufacturing.Manufacturing;

import org.apache.log4j.Logger;

import exceptions.DataRequiredEx;

public class ModelObjectsContainer implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(ModelObjectsContainer.class);

	private Population population;

	private Map<Integer, Education> education = new HashMap<Integer, Education>();

	private Map<Integer, RnD> rnd_r = new HashMap<Integer, RnD>();
	private Map<Integer, RnD> rnd_d = new HashMap<Integer, RnD>();

	private Map<Integer, Manufacturing> vedOrd = new HashMap<Integer, Manufacturing>();
	private Map<Integer, Manufacturing> vedInn = new HashMap<Integer, Manufacturing>();

	private Map<Integer, Taxes> taxesOrd = new HashMap<Integer, Taxes>();
	private Map<Integer, Taxes> taxesInn = new HashMap<Integer, Taxes>();

	private Map<Integer, InvestmentsRnD> investmentsRnD = new HashMap<Integer, InvestmentsRnD>();
	private Map<Integer, InvestmentsManuf> investmentsManuf = new HashMap<Integer, InvestmentsManuf>();

	private FieldsInteraction fi;

	private static boolean ready = false;

	private static ModelObjectsContainer instance = new ModelObjectsContainer();

	private ModelObjectsContainer() {

	}

	public static synchronized ModelObjectsContainer getInstance() {
		return instance;
	}

	public Taxes getTaxesOrd(Integer ved) {
		return taxesOrd.get(ved);
	}

	public Taxes getTaxesInn(Integer ved) {
		return taxesInn.get(ved);
	}

	public void setTaxesOrd(Integer ved, Taxes taxes) {
		this.taxesOrd.put(ved, taxes);
	}

	public void setTaxesInn(Integer ved, Taxes taxes) {
		this.taxesInn.put(ved, taxes);
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population p) {
		this.population = p;
		ready = false;
	}

	public Education getEducation(Integer field) {
		return education.get(field);
	}

	public void setEducation(Integer field, Education edu) {
		education.put(field, edu);
		ready = false;
	}

	public RnD getRndR(Integer field) {
		return rnd_r.get(field);
	}

	public void setRnd_R(Integer field, RnD rnd) {
		this.rnd_r.put(field, rnd);
		ready = false;
	}

	public RnD getRndD(Integer field) {
		return rnd_d.get(field);
	}

	public void setRnd_D(Integer field, RnD rnd) {
		this.rnd_d.put(field, rnd);
		ready = false;
	}

	public InvestmentsRnD getInvestmentsRnD(Integer field) {
		return investmentsRnD.get(field);
	}

	public void setInvestmentsRnD(Integer field, InvestmentsRnD inv) {
		investmentsRnD.put(field, inv);
		ready = false;
	}

	public InvestmentsManuf getInvestmentsManuf(int ved) {
		return investmentsManuf.get(ved);
	}

	public void setInvestmentsManuf(int ved, InvestmentsManuf inv) {
		investmentsManuf.put(ved, inv);
		ready = false;
	}

	public void setVedOrd(Integer vedName, Manufacturing ved) {
		this.vedOrd.put(vedName, ved);
		ready = false;
	}

	public void setVedInn(Integer vedName, Manufacturing ved) {
		this.vedInn.put(vedName, ved);
		ready = false;
	}

	public Manufacturing getVedOrd(Integer vedName) {
		return vedOrd.get(vedName);
	}

	public Manufacturing getVedInn(Integer vedName) {
		return vedInn.get(vedName);
	}

	public synchronized FieldsInteraction getFieldsInteraction() {
		if (fi == null) {
			fi = new FieldsInteraction();
			ready = false;
		}
		return fi;
	}

	public List<Manufacturing> getAllVedOrd() {
		return new ArrayList<Manufacturing>(vedOrd.values());
	}

	public List<Manufacturing> getAllVedInn() {
		return new ArrayList<Manufacturing>(vedInn.values());
	}

	public void proceed() {
		if (modelIsReady()) {
			LOG.debug("Model is already ready");
			return;
		}
		try {
			population.proceed();
			for (Education edu : education.values()) {
				LOG.debug("Education proceed");
				edu.proceed();
			}

			for (RnD r : rnd_r.values()) {
				LOG.debug("RnD (Researchment) proceed");
				r.proceed();
			}
			for (RnD d : rnd_d.values()) {
				LOG.debug("RnD (Development) proceed");
				d.proceed();
			}
			for (Manufacturing ved : vedOrd.values()) {
				LOG.debug("Ved (ordinary) proceed before FI");
				ved.proceedBeforeFI();
			}
			for (Manufacturing ved : vedInn.values()) {
				LOG.debug("Ved (innovational) proceed before FI");
				ved.proceedBeforeFI();
			}
			fi.proceed();
			for (Manufacturing ved : vedOrd.values()) {
				LOG.debug("Ved (ordinary) proceed after FI");
				ved.proceedAfterFI();
			}
			for (Manufacturing ved : vedInn.values()) {
				LOG.debug("Ved (innovational) proceed after FI");
				ved.proceedAfterFI();
			}
			LOG.debug("Model is ready now");
			ready = true;
		} catch (DataRequiredEx e) {
			throw new RuntimeException(e);
		}
	}

	public boolean modelIsReady() {
		return ready;
	}

	public static void saveToFile(String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(instance);
	}

	public static ModelObjectsContainer readFromFile(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		instance = (ModelObjectsContainer) in.readObject();
		return instance;
	}

}
