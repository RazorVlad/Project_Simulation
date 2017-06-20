package visual.planning;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mathModel.project.Project;
import mathModel.variantsResolver.Variant;

import org.apache.log4j.Logger;

public class Before_notAfter_table extends JScrollPane {

	private static final long serialVersionUID = 8484606971808483281L;
	private static Logger LOG = Logger.getLogger(Before_notAfter_table.class);
	private JTable table;
	List<Variant> vars;

	public Before_notAfter_table(List<Variant> vars) {
		this.vars = vars;
	}

	void setTable(int m) {
		String[][] data = new String[m][m];
		String[] columnNames = new String[m];
		columnNames[0] = "";
		for (int i = 1; i < m; i++) {
			columnNames[i] = "вариант " + i;
		}
		for (int i = 0; i < m; i++) {
			data[i][0] = ("вариант " + (i + 1));
		}
		table = new JTable(m, m);
		table.setShowVerticalLines(true);
		table.setModel(new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 6012009170185315958L;

			public Class getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Object.class;
				} else {
					return Boolean.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt("вариант " + (i + 1), i, 0);
			for (int j = 1; j < table.getColumnCount(); j++) {
				if (i == 0) {
					table.getColumnModel().getColumn(j).setHeaderValue("вариант " + j);
				}
				table.setValueAt(false, i, j);
			}
		}

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedColumn() == 0) {
					showProjectsInVariant(table);
				}
			}
		});
		setViewportView(table);

	}

	void showProjectsInVariant(JTable table) {
		StringBuilder projects = new StringBuilder("<html>");
		String selected = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
		try {
			Variant selectedVariant = vars.get(table.getSelectedRow());
			List<Project> projectsInVariant = selectedVariant.getProjects();
			for (Project i : projectsInVariant) {
				projects.append(i.getName()).append("<br>");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.debug("selected = " + selected);
		JOptionPane.showMessageDialog(null, projects.toString(), selected, JOptionPane.PLAIN_MESSAGE);
	}

	List<Integer> getVariants(int index) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (Boolean.parseBoolean(table.getValueAt(i, index).toString())) {
				data.add(i);
			}
		}
		return data;
	}
}
