package visual.frames.modelResult;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ModelResult extends JPanel {

	private static final long serialVersionUID = -4303841344328152743L;
	JComboBox chooseSphereComboBox;
	JComboBox manufacturingTypeComboBox;
	JComboBox chooseVEDComboBox;
	private JPanel tablePanel;
	private ResultTable resultTable;
	FileOutputStream out;
	private JFileChooser dlg = new JFileChooser(".");
	private static Logger LOG = Logger.getLogger(ModelResult.class);

	public ModelResult() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		setBounds(new Rectangle(50, 50, 1150, 630));

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new MigLayout("", "[][414.00][grow][]", "[][][]"));

		JLabel chooseSphereLabel = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u043E\u0442\u0440\u0430\u0441\u043B\u044C:");
		topPanel.add(chooseSphereLabel, "cell 0 0,alignx trailing");

		chooseSphereComboBox = new JComboBox();
		chooseSphereComboBox.setMinimumSize(new Dimension(300, 18));
		chooseSphereComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int k = chooseSphereComboBox.getSelectedIndex();

				int[] veds = mathModel.VedNames.getVeds(k);
				String[] model = new String[veds.length + 1];
				for (int l = 0; l < veds.length; l++) {
					model[l] = mathModel.VedNames.getVedName(veds[l]);
				}
				model[veds.length] = "Âñå ÂÝÄû";
				chooseVEDComboBox.setModel(new DefaultComboBoxModel(model));

				resultTable.setTable(chooseVEDComboBox.getSelectedItem().toString(), chooseSphereComboBox
						.getSelectedItem().toString(), manufacturingTypeComboBox.getSelectedIndex());
			}
		});
		chooseSphereComboBox.setModel(new DefaultComboBoxModel(mathModel.VedNames.FIELDNAMES.toArray()));
		topPanel.add(chooseSphereComboBox, "cell 1 0,growx");

		JLabel additionalIndicatorsLabel = new JLabel(
				"\u0412\u044B\u0432\u043E\u0434\u0438\u0442\u044C \u0434\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u043E \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044F \u043F\u043E\u043A\u0430\u0437\u0430\u0442\u0435\u043B\u0435\u0439 \u0432 % \u043E\u0442\u043D\u043E\u0448\u0435\u043D\u0438\u0438 \u043A:");
		topPanel.add(additionalIndicatorsLabel, "flowx,cell 2 0,alignx right");

		JCheckBox lastYearCheckBox = new JCheckBox(
				"\u043F\u0440\u0435\u0434\u044B\u0434\u0443\u0449\u0435\u043C\u0443 \u0433\u043E\u0434\u0443");
		topPanel.add(lastYearCheckBox, "flowx,cell 3 0");

		JLabel chooseVEDLabel = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0412\u042D\u0414:");
		topPanel.add(chooseVEDLabel, "cell 0 1,alignx left");

		chooseVEDComboBox = new JComboBox();
		chooseVEDComboBox
				.setModel(new DefaultComboBoxModel(
						new String[] {
								"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u043E \u043F\u0438\u0449\u0435\u0432\u044B\u0445 \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u043E\u0432",
								"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u043E \u043D\u0430\u043F\u0438\u0442\u043A\u043E\u0432",
								"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u043E \u0442\u0430\u0431\u0430\u0447\u043D\u044B\u0445 \u0438\u0437\u0434\u0435\u043B\u0438\u0439",
								"\u0412\u0441\u0435 \u0412\u042D\u0414\u044B" }));
		chooseVEDComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				resultTable.setTable(chooseVEDComboBox.getSelectedItem().toString(), chooseSphereComboBox
						.getSelectedItem().toString(), manufacturingTypeComboBox.getSelectedIndex());
			}
		});
		chooseVEDComboBox.setMinimumSize(new Dimension(300, 18));

		topPanel.add(chooseVEDComboBox, "cell 1 1,growx");

		JCheckBox baseYearCheckBox = new JCheckBox(
				"\u0431\u0430\u0437\u043E\u0432\u043E\u043C\u0443 \u0433\u043E\u0434\u0443");
		topPanel.add(baseYearCheckBox, "cell 3 1");

		JLabel manufacturingTypeLabel = new JLabel(
				"\u0422\u0438\u043F \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430");
		topPanel.add(manufacturingTypeLabel, "cell 0 2,alignx left");

		manufacturingTypeComboBox = new JComboBox();
		manufacturingTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				resultTable.setTable(chooseVEDComboBox.getSelectedItem().toString(), chooseSphereComboBox
						.getSelectedItem().toString(), manufacturingTypeComboBox.getSelectedIndex());
			}
		});
		manufacturingTypeComboBox.setMinimumSize(new Dimension(300, 18));
		manufacturingTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u0418\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u043E\u0435",
				"\u0420\u044F\u0434\u043E\u0432\u043E\u0435" }));
		topPanel.add(manufacturingTypeComboBox, "cell 1 2,growx");

		JButton exportDataBtn = new JButton(
				"\u042D\u043A\u0441\u043F\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435");
		exportDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (dlg.showSaveDialog(ModelResult.this) != JFileChooser.APPROVE_OPTION) {
					return;
				}

				String fileName = dlg.getSelectedFile().getAbsolutePath() + ".xlsx";

				try {
					Workbook workbook = new SXSSFWorkbook();
					Sheet sheet = workbook.createSheet();
					XLS.createXLSX(workbook, sheet, resultTable.getTable(), chooseSphereComboBox
							.getSelectedItem().toString(), chooseVEDComboBox.getSelectedItem().toString(),
							manufacturingTypeComboBox.getSelectedItem().toString(), resultTable
									.getMainHeaders(), resultTable.getHeaders());
					out = new FileOutputStream(fileName);
					workbook.write(out);
					out.close();
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		});
		topPanel.add(exportDataBtn, "cell 3 2,alignx right");

		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.CENTER);

		resultTable = new ResultTable();
		tablePanel.add(resultTable, BorderLayout.CENTER);
	}
}
