package visual.planning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

public class ResultPanel extends JPanel {

	private static final long serialVersionUID = 331983169729263872L;

	private JLabel label;
	private JLabel lblNewLabel1;
	private JLabel criteriaIncomeLabel;
	private JLabel criteriaOutcomeLabel;
	private JLabel criteriaInnovLabel;
	private JLabel criteriaIncomeResult;
	private JLabel criteriaOutcomeResult;
	private JLabel criteriaInnovResult;
	private JScrollPane scrollPaneResult;
	private JTable tableResult;
	private String font = "Calibri";
	private static Logger LOG = Logger.getLogger(ResultPanel.class);

	public JLabel getCriteriaOutcomeResult() {
		return criteriaOutcomeResult;
	}

	public void setCriteriaOutcomeResult(JLabel criteriaOutcomeResult) {
		this.criteriaOutcomeResult = criteriaOutcomeResult;
	}

	public JLabel getCriteriaIncomeResult() {
		return criteriaIncomeResult;
	}

	public void setCriteriaIncomeResult(JLabel criteriaIncomeResult) {
		this.criteriaIncomeResult = criteriaIncomeResult;
	}

	public ResultPanel() {
		setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0)));
		setPreferredSize(new Dimension(10, 200));
		setLayout(new MigLayout("", "[][][grow]", "[][][][][][70]"));

		label = new JLabel("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B");
		label.setFont(new Font(font, Font.BOLD, 14));
		add(label, "cell 0 0");

		lblNewLabel1 = new JLabel(
				"\u041E\u043F\u0442\u0438\u043C\u0430\u043B\u044C\u043D\u044B\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044F \u043A\u0440\u0438\u0442\u0435\u0440\u0438\u0435\u0432:");
		lblNewLabel1.setFont(new Font(font, Font.PLAIN, 14));
		add(lblNewLabel1, "cell 0 1");

		criteriaIncomeLabel = new JLabel(
				"\u0421\u043E\u0432\u043E\u043A\u0443\u043F\u043D\u0430\u044F \u043F\u0440\u0438\u0431\u044B\u043B\u044C \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u0438 \u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 \u0438 \u0440\u0435\u0430\u043B\u0438\u0437\u0430\u0446\u0438\u0438  \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438");
		criteriaIncomeLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaIncomeLabel, "flowx,cell 0 2");

		criteriaIncomeResult = new JLabel("\u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435");
		criteriaIncomeResult.setVisible(false);
		criteriaIncomeResult.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaIncomeResult, "cell 2 2");

		criteriaOutcomeLabel = new JLabel(
				"\u041E\u0431\u0449\u0438\u0435 \u0437\u0430\u0442\u0440\u0430\u0442\u044B \u043D\u0430 \u0444\u0446\u043D\u043A\u0446\u0438\u043E\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u0438 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u0435 \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		criteriaOutcomeLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaOutcomeLabel, "flowx,cell 0 3");

		criteriaOutcomeResult = new JLabel("\u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435");
		criteriaOutcomeResult.setVisible(false);
		criteriaOutcomeResult.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaOutcomeResult, "cell 2 3");

		criteriaInnovLabel = new JLabel(
				"\u0421\u043E\u0432\u043E\u043A\u0443\u0430\u043D\u044B\u0439 \u043E\u0431\u044A\u0435\u043C \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 \u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u043E\u0439 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438 \u043E\u0442\u0440\u0430\u0441\u043B\u044C\u044E \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		criteriaInnovLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaInnovLabel, "flowx,cell 0 4");

		criteriaInnovResult = new JLabel("\u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435");
		criteriaInnovResult.setVisible(false);
		criteriaInnovResult.setFont(new Font(font, Font.PLAIN, 14));
		add(criteriaInnovResult, "cell 2 4");

		scrollPaneResult = new JScrollPane();
		scrollPaneResult.setVisible(false);
		scrollPaneResult.setMaximumSize(new Dimension(32767, 70));
		add(scrollPaneResult, "cell 0 5 3 1,grow");
		scrollPaneResult.setViewportView(tableResult);

		setTableResult(null, 0, 1);
	}

	void setTableResult(double[] coef, int modelYear, int columnCount) {
		tableResult = new JTable();
		String[] columnNames = new String[columnCount];
		String[][] data = new String[1][columnCount];
		columnNames[0] = "";
		if (coef != null) {
			try {
				for (int i = 0; i < columnCount - 1; i++) {
					columnNames[i + 1] = String.valueOf(modelYear + i);
					data[0][i + 1] = String.valueOf(coef[i]);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			for (int i = 0; i < columnCount - 1; i++) {
				columnNames[i + 1] = "";
			}
		}
		data[0][0] = "\u0420\u0435\u0430\u043B\u0438\u0437\u0443\u0435\u043C\u044B\u0439 \u0432\u0430\u0440\u0438\u0430\u043D\u0442 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F";

		tableResult.setModel(new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 4501582043234238064L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableResult.setRowHeight(42);
		tableResult.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableResult.getColumnModel().getColumn(0).setMinWidth(150);
		tableResult.setFillsViewportHeight(true);
		tableResult.revalidate();
		tableResult.repaint();
		scrollPaneResult.setViewportView(tableResult);
		scrollPaneResult.setVisible(true);
	}

	void SetResult(double[] result, int modelYear, int columnCount, double criteriaIncome,
			double criteriaOutcome, double criteriaInnov) {
		criteriaIncomeResult.setText(String.valueOf(criteriaIncome));
		criteriaIncomeResult.setVisible(true);
		criteriaOutcomeResult.setText(String.valueOf(criteriaOutcome));
		criteriaOutcomeResult.setVisible(true);
		criteriaInnovResult.setText(String.valueOf(criteriaInnov));
		criteriaInnovResult.setVisible(true);
		setTableResult(result, modelYear, columnCount);
	}
}
