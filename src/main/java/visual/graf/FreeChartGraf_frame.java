package visual.graf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class FreeChartGraf_frame extends JFrame {

	private static final long serialVersionUID = 396260910666363268L;
	private JPanel contentPane;
	ChartPanel chp;
	XYSeriesCollection collection;
	JFreeChart chart;
	XYPlot plot;
	NumberAxis rangeAxis;
	NumberAxis domainAxis;

	@SuppressWarnings("deprecation")
	public FreeChartGraf_frame(final String title, double[][] table, String[] seriesNames) {
		setBounds(new Rectangle(100, 50, 1000, 630));
		setTitle(title);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		XYSeries series = new XYSeries("��� ��������");
		collection = new XYSeriesCollection();
		int k = 0;
		for (int i = 0; i < seriesNames.length; i++) {
			k += newSeries(collection, table[0], table[i + 1], seriesNames[i]);
		}
		if (k == 0) {
			collection.addSeries(series);
		}

		chart = ChartFactory.createXYLineChart(title,// chart title
				" ",// x axis label
				" ",// y axis label
				collection, // data
				PlotOrientation.VERTICAL, true,// include legend
				true,// tooltips
				true// urls
				);
		Color color = new Color(214, 217, 223);
		chart.setBackgroundPaint(color);
		plot = chart.getXYPlot();

		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesLinesVisible(0, false);
		// renderer.setSeriesShapesVisible(1, false);
		// ������� �����
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator("{2}", new DecimalFormat("0.00"),
				new DecimalFormat("0.00"));

		renderer.setItemLabelGenerator(generator);
		renderer.setItemLabelsVisible(true);
		// renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
		// ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER
		// ));
		plot.setRenderer(renderer);

		rangeAxis = (NumberAxis) plot.getRangeAxis();
		// rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setPositiveArrowVisible(true);
		domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		domainAxis.setPositiveArrowVisible(true);
		contentPane.setLayout(new BorderLayout(0, 0));

		chp = new ChartPanel(chart);
		chp.setMouseWheelEnabled(true);

		getContentPane().add(chp);
	}

	public void setRangeAxis(String label) {
		rangeAxis.setLabel(label);
	}

	public void setDomainAxis(String label) {
		domainAxis.setLabel(label);
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