package visual.project_modeling.projectBaseTab;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mathModel.project.Project;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXLabel;

import visual.Format;
import visual.project_modeling.ProjectGroups;

public class GeneralInfo extends JPanel {
	JComboBox projectPriority;
	JComboBox projectGroup;
	JComboBox manufacturerVED;
	JComboBox manufacturerBranch;
	JComboBox customerBranch;
	JComboBox customerVED;
	private JFormattedTextField projectName;
	private JFormattedTextField projectSphere;
	private JFormattedTextField projectPrpose;
	private String font = "Calibri";

	public String getfield() {
		return projectSphere.getText();
	}

	public String getProjectGroup() {
		return (projectGroup.getSelectedItem().toString());
	}

	public int getProjectGroupIndex() {
		return Integer.parseInt(projectGroup.getSelectedItem().toString());
	}

	public String getProjectName() {
		return projectName.getText();
	}

	public int getProjectSphere() {
		return projectPriority.getSelectedIndex();
	}

	public String getProjectAim() {
		return projectPrpose.getText();
	}

	public GeneralInfo() {
		setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][]"));

		JXLabel projectInformationLabel = new JXLabel();
		projectInformationLabel.setFont(new Font(font, Font.BOLD, 14));
		projectInformationLabel.setBorder(null);
		projectInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectInformationLabel.setText("<html><b>Общая информация о проекте:");
		add(projectInformationLabel, "cell 0 0 3 1,growx");

		JLabel projectNameLabel = new JLabel("Название проекта:");
		projectNameLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(projectNameLabel, "cell 0 1");

		projectName = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(projectName, 14, 286, 28);
		add(projectName, "cell 1 1 2 1,growx");

		JLabel projectSphereLabel = new JLabel("Сфера деятельности:");
		projectSphereLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(projectSphereLabel, "cell 0 2");

		projectSphere = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(projectSphere, 14, 286, 28);
		add(projectSphere, "cell 1 2 2 1,growx");

		JLabel projectPrposeLabel = new JLabel("Назначение проекта:");
		projectPrposeLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(projectPrposeLabel, "cell 0 3");

		projectPrpose = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(projectPrpose, 14, 286, 28);
		add(projectPrpose, "cell 1 3 2 1,growx");

		JLabel projectPriorityLabel = new JLabel("Приоритетное направление:");
		projectPriorityLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(projectPriorityLabel, "cell 0 4");

		projectPriority = new JComboBox();
		setComboBoxDefaults(projectPriority);
		projectPriority.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int k = projectPriority.getSelectedIndex();
				projectGroup.setModel(new DefaultComboBoxModel(ProjectGroups.projectsGroup[k]));
			}
		});
		add(projectPriority, "cell 1 4,alignx left");
		projectPriority.setModel(new DefaultComboBoxModel(ProjectGroups.projectPriority));

		JLabel projectGroupLabel = new JLabel("Группа технологий");
		projectGroupLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(projectGroupLabel, "cell 0 5");

		projectGroup = new JComboBox();
		setComboBoxDefaults(projectGroup);
		add(projectGroup, "cell 1 5,alignx left");
		projectGroup.setModel(new DefaultComboBoxModel(ProjectGroups.projectsGroup[0]));
		JLabel customerLabel = new JLabel("ВЭД заказчик:");
		customerLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(customerLabel, "cell 0 6");

		customerVED = new JComboBox();
		setComboBoxDefaults(customerVED);
		add(customerVED, "cell 2 6,alignx left");
		customerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(0)));

		customerBranch = new JComboBox();
		setComboBoxDefaults(customerBranch);
		customerBranch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int k = customerBranch.getSelectedIndex();
				customerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(k)));
			}

		});
		add(customerBranch, "cell 1 6,alignx left");
		customerBranch.setModel(new DefaultComboBoxModel(mathModel.VedNames.FIELDNAMES.toArray()));

		JLabel manufacturerLabel = new JLabel("ВЭД производитель:");
		manufacturerLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(manufacturerLabel, "cell 0 7");

		manufacturerVED = new JComboBox();
		setComboBoxDefaults(manufacturerVED);
		manufacturerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(0)));
		add(manufacturerVED, "cell 2 7,alignx left");

		manufacturerBranch = new JComboBox();
		setComboBoxDefaults(manufacturerBranch);
		manufacturerBranch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int k = manufacturerBranch.getSelectedIndex();
				manufacturerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(k)));
			}
		});
		manufacturerBranch.setModel(new DefaultComboBoxModel(mathModel.VedNames.FIELDNAMES.toArray()));
		add(manufacturerBranch, "cell 1 7,alignx left");

	}

	private static final long serialVersionUID = -3112866466015612603L;

	void refresh(String name, Project pr) {
		projectName.setText(name);
		projectSphere.setText("");// TODO
		projectPrpose.setText(pr.getAim());
		projectPriority.setSelectedItem(pr.getField());
		projectGroup.setSelectedIndex(0);// TODO
		customerBranch.setSelectedIndex(mathModel.VedNames.getOtraslByVed(pr.getVedOwner()));
		customerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(customerBranch
				.getSelectedIndex())));
		customerVED.setSelectedIndex(pr.getVedOwner());
		manufacturerBranch.setSelectedIndex(mathModel.VedNames.getOtraslByVed(pr.getVedProducer()));
		manufacturerVED.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(manufacturerBranch
				.getSelectedIndex())));
		manufacturerVED.setSelectedIndex(pr.getVedProducer());
	}

	void setComboBoxDefaults(JComboBox box) {
		box.setFont(new Font(font, Font.PLAIN, 14));
		box.setMinimumSize(new Dimension(286, 26));
		box.setPreferredSize(new Dimension(373, 28));
	}

	public int getManufacturerVed() {
		return mathModel.VedNames.getID(manufacturerBranch.getSelectedItem().toString(), manufacturerVED
				.getSelectedItem().toString());
	}

	public int getVedOwner() {
		return mathModel.VedNames.getID(customerBranch.getSelectedItem().toString(), customerVED
				.getSelectedItem().toString());
	}
}
