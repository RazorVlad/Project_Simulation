package mathModel.variantsResolver;

import java.util.List;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.project.Project;

import org.apache.log4j.Logger;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class VariantsResolver {
	// input
	private int delta;
	private double costBefore;
	private double amountBefore;
	private double varCostBefore;

	private double f0;
	private Statistics f;

	private List<Project> projects;
	private List<Integer> vedIndexes;

	private Statistics leftovers = new Statistics();

	private double lambdaRecord = Double.MAX_VALUE;
	public double innov;

	ZeroBestAndWorst zeroBestAndWorst;
	VRObjectsContainer vrContainer;

	private ModelObjectsContainer model = ModelObjectsContainer.getInstance();
	private int m;

	private static Logger LOG = Logger.getLogger(VariantsResolver.class);

	public VariantsResolver(int delta, int maxDuration, int duration, int lastTime, int aimYear, double norm,
			List<Project> projects, double costBefore, double amountBefore, double varCostBefore, double f0,
			Statistics f, List<Integer> vedIndexes) {
		this.delta = delta;
		this.projects = projects;
		this.f0 = f0;
		this.f = f;
		this.vedIndexes = vedIndexes;
		this.costBefore = costBefore;
		this.amountBefore = amountBefore;
		this.varCostBefore = varCostBefore;

		zeroBestAndWorst = new ZeroBestAndWorst();
		vrContainer = new VRObjectsContainer();
		vrContainer.setVRObjectsContainer(duration, norm, aimYear, maxDuration, projects, lastTime);
		leftovers.add(vrContainer.getStartYear(), f0);
	}

	public List<Variant> getVariants() {
		return vrContainer.getVariants();
	}

	public void generateVariants() {
		ICombinatoricsVector<Project> initialVector = Factory.createVector(projects);
		for (int combinationLength = 1; combinationLength <= delta; combinationLength++) {
			Generator<Project> gen = Factory.createSimpleCombinationGenerator(initialVector,
					combinationLength);
			for (ICombinatoricsVector<Project> combination : gen) {
				Variant var = new Variant();
				var.setProjects(combination.getVector());
				vrContainer.addVariant(var);
			}
		}
		m();
	}

	public void proceed() {
		model.proceed();
		zeroBestAndWorst.zeroBestAndWorst(costBefore, amountBefore, varCostBefore, f0, vedIndexes, f);
		int t = vrContainer.getStartYear();
		LOG.debug("t = " + t);
		int startVariantIndex = 0;
		LOG.debug("startVariantIndex = 0");
		times: while (true) {
			for (int variant = startVariantIndex; variant < vrContainer.getVariants().size(); variant++) {
				if (VariantOrderCheck.isVariantOrderOK(variant, t)) {
					LOG.debug("variant order is ok ==> proceed variant");
					vrContainer.getVariants().get(variant).proceed();
					if (GovermentOrder.govermentOrderIsSatisfied(t, vedIndexes)
							&& Leftovers.leftovers(t, leftovers, f)) {
						LOG.debug("govermentOrderIsSatisfied && leftovers(t)");
						double maxLambda = Lambda.maxLambda(vedIndexes, t, variant, zeroBestAndWorst,
								amountBefore, costBefore, varCostBefore);
						LOG.debug("maxLambda = " + maxLambda);
						if (maxLambda < lambdaRecord) {
							LOG.debug("maxLabmda < lambdaRecord");
							vrContainer.putChosenVariantsIndex(t, variant);
							if (t < vrContainer.getLastTime()) {
								LOG.debug("");
								LOG.debug("t < lastTime ==> t++; startVariantindex = 0");
								t++;
								startVariantIndex = 0;
							} else {
								lambdaRecord = maxLambda;
								LOG.debug("lambdaRecord = maxLambda");
								startVariantIndex = vrContainer.getChosenVariantsIndexes().get(t);
								LOG.debug("startVariantindex = " + startVariantIndex);
							}
							continue times;
						}
					}
				}
			}
			if (t > vrContainer.getStartYear()) {
				LOG.debug("t > startYear ==> t--");
				t--;
				startVariantIndex = vrContainer.getChosenVariantsIndexes().get(t);
				LOG.debug("startVariantindex = " + startVariantIndex);
			} else {
				if (vrContainer.getChosenVariantsIndexes().isEmpty()) {
					LOG.info("THERE ARE NO ACCEPTABLE VARIANTS!!!");
				} else {
					LOG.info("GOT OPTIMAL VARIANTS!!!");
				}
				break;
			}
		}
	}

	public int m() {
		// for (int i = 1; i < delta; i++) {
		// m += factorial(theta) / (factorial(theta - i) * factorial(i));
		// }
		m = vrContainer.getVariants().size();
		return m;
	}

	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}

}
