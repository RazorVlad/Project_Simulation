package visual.frames.technology.bMatrix;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;


public class BMatrixTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4564942775982675036L;
	private static Logger LOG = Logger.getLogger(BMatrixTab.class);
	private final ButtonGroup buttonGroupResult = new ButtonGroup();
	private final JSlider slider;
	private BMatrix bMatrix;
	private double[][] bx;
	private double[][] bi;
	private double[][] bsum;
	private int defaultRowSize;
	private double[][] bxColumnsSum;
	private double[][] biColumnsSum;
	private double[][] bsumColumnsSum;

	public double[][] getBx() {
		return bx;
	}

	public double[][] getBi() {
		return bi;
	}

	public double[][] getBsum() {
		return bsum;
	}

	public BMatrixTab(String[][] data, String[] columnNames, int defaultRowSize) {
		this.defaultRowSize = defaultRowSize;
		setLayout(new BorderLayout(0, 0));
		JPanel panelWithBTables = new JPanel();
		panelWithBTables.setLayout(new BorderLayout());
		add(panelWithBTables, BorderLayout.CENTER);

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (bMatrix != null) {
					bMatrix.refreshRowSize(slider.getValue());
				}
			}
		});
		setSlider(slider);

		bMatrix = new BMatrix(data.clone(), columnNames);
		panelWithBTables.add(bMatrix, BorderLayout.CENTER);

		JPanel topPanelResult = new JPanel();
		add(topPanelResult, BorderLayout.NORTH);
		topPanelResult.setLayout(new BorderLayout(0, 0));

		JPanel radioPanelResult = new JPanel();
		topPanelResult.add(radioPanelResult, BorderLayout.CENTER);

		final JRadioButton radioBx = new JRadioButton("Матрица выпуска");
		buttonGroupResult.add(radioBx);
		radioBx.setSelected(true);
		radioPanelResult.add(radioBx);

		final JRadioButton radioBi = new JRadioButton("Матрица импорта");
		buttonGroupResult.add(radioBi);
		radioPanelResult.add(radioBi);

		final JRadioButton radioBsum = new JRadioButton("Суммарная матрица");
		buttonGroupResult.add(radioBsum);
		radioPanelResult.add(radioBsum);

		topPanelResult.add(slider, BorderLayout.WEST);

		radioBx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowSize = slider.getValue();
				bMatrix.setBMatrix(bx, bxColumnsSum, rowSize);
			}
		});
		radioBi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowSize = slider.getValue();
				bMatrix.setBMatrix(bi, biColumnsSum, rowSize);
			}
		});
		radioBsum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowSize = slider.getValue();
				bMatrix.setBMatrix(bsum, bsumColumnsSum, rowSize);
			}
		});
	}

	public void setBMatrix(double[][] bx, double[][] bi, double[][] bsum) {
		this.bx = bx.clone();
		this.bi = bi.clone();
		this.bsum = bsum.clone();
		int rowSize = slider.getValue();
		this.bxColumnsSum = new double[2][this.bx[0].length];
		this.biColumnsSum = new double[2][this.bi[0].length];
		this.bsumColumnsSum = new double[1][this.bsum.length];
		for (byte i = 0; i < bxColumnsSum[0].length; i++) {
			bxColumnsSum[0][i] = 0;
			biColumnsSum[0][i] = 0;
			bsumColumnsSum[0][i] = 0;
			for (byte j = 0; j < this.bx.length; j++) {
				bxColumnsSum[0][i] += this.bx[j][i];
				biColumnsSum[0][i] += this.bi[j][i];
				bsumColumnsSum[0][i] += this.bsum[j][i];
			}
			bxColumnsSum[1][i] = bxColumnsSum[0][i] / bsumColumnsSum[0][i] * 100;
			biColumnsSum[1][i] = biColumnsSum[0][i] / bsumColumnsSum[0][i] * 100;
		}
		bMatrix.setBMatrix(this.bx, this.bxColumnsSum, rowSize);
	}

	public void setSlider(JSlider slider) {
		if (defaultRowSize > 150) {
			defaultRowSize = 150;
		}
		if (defaultRowSize < 40) {
			defaultRowSize = 40;
		}
		slider.setBorder(new EmptyBorder(0, 90, 0, 0));
		slider.setMinimum(40);
		slider.setMaximum(150);
		slider.setValue(defaultRowSize);
	}
}
