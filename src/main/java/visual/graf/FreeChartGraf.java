package visual.graf;


import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FreeChartGraf extends JPanel {

	private static final long serialVersionUID = -8570133157107564152L;
	ChartPanel chp;
	XYSeriesCollection collection;
	JFreeChart chart;
	XYPlot plot;
	NumberAxis rangeAxis;
	NumberAxis domainAxis;

	@SuppressWarnings("deprecation")
	public FreeChartGraf(boolean legend, String decimalFormat) {
		setLayout(new BorderLayout(0, 0));
		XYSeries series = new XYSeries("��� ��������");
		collection = new XYSeriesCollection();
		collection.addSeries(series);
		chart = ChartFactory.createXYLineChart(" ",// chart title
				" ",// x axis label
				" ",// y axis label
				collection, // data
				PlotOrientation.VERTICAL, legend,// include legend
				true,// tooltips
				true// urls
				);

		Color color = new Color(214, 217, 223);
		chart.setBackgroundPaint(color);
		plot = chart.getXYPlot();

		plot.setBackgroundPaint(Color.white);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesLinesVisible(0, false);
		// renderer.setSeriesShapesVisible(1, false);
		// ������� �����
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator("{2}", new DecimalFormat(
				decimalFormat), new DecimalFormat(decimalFormat));

		renderer.setItemLabelGenerator(generator);
		renderer.setItemLabelsVisible(true);
		plot.setRenderer(renderer);

		rangeAxis = (NumberAxis) plot.getRangeAxis();
		// rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setPositiveArrowVisible(true);
		domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
		domainAxis.setPositiveArrowVisible(true);

		chp = new ChartPanel(chart);
		chp.setMouseWheelEnabled(true);

		add(chp, BorderLayout.CENTER);
	}

	public void refresh(double[][] table, String[] seriesNames) {
		XYSeries series = new XYSeries("��� ��������");
		collection.removeAllSeries();

		int k = 0;
		if (!seriesNames[0].equals("")) {
			for (int i = 0; i < seriesNames.length; i++) {
				k += newSeries(collection, table[0], table[i + 1], seriesNames[i]);
			}
		}
		if (k == 0) {
			collection.addSeries(series);
		}
	}

	Integer newSeries(XYSeriesCollection collection, double[] x, double[] y, String seriesName) {
		if (y[0] != -1) {
			XYSeries series = new XYSeries(seriesName);
			for (int i = 0; i < x.length; i++) {
				series.add((int) x[i], y[i]);
			}
			collection.addSeries(series);
			return 1;
		} else {
			return 0;
		}
	}
}
