package visual.prom_modeling.stud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.NumberOrButton;
import visual.frames.main.Main;
import visual.tableBuilders.TableWithRowHeaders;

public class Stud_panel extends JPanel {

	private static final long serialVersionUID = -7502277548652291258L;
	private JTable statFieldCollegeAppl;
	private JTable statFieldUniverAppl;
	JScrollPane scrollPane;
	JScrollPane scrollPane1;

	private static Logger LOG = Logger.getLogger(Stud_panel.class);
	private JFormattedTextField univerStudT0;
	private JFormattedTextField collegeStudT0;
	final NumberOrButton fieldCollegeApple9;
	final NumberOrButton fieldCollegeAppl12;
	final NumberOrButton succCollegeGrad;
	final NumberOrButton univerApplAmongCollegeGrad;
	final NumberOrButton fieldUniverAppl12;
	final NumberOrButton succUniverGrad;
	final NumberOrButton collegeGradWork;
	final NumberOrButton univerGradWork;
	final NumberOrButton univerGradEdu;
	final NumberOrButton univerGradRnD;

	public Statistics getStatFieldCollegeAppl() {
		Statistics res = new Statistics();
		for (int j = 0, k = 2; j < 3; j++, k--) {
			res.add(Main.Year - j, Format.getDouble(statFieldCollegeAppl.getValueAt(0, k + 1)));
		}
		return res;
	}

	public Statistics getStatFieldUniverAppl() {
		Statistics res = new Statistics();
		if (statFieldUniverAppl != null) {
			for (int j = 0, k = 4; j < 5; j++, k--) {
				res.add(Main.Year - j, Format.getDouble(statFieldUniverAppl.getValueAt(0, k + 1)));
			}
		}
		return res;
	}

	public double getUniverStudT0() {
		return Format.getDouble(univerStudT0.getText());
	}

	public double getCollegeStudT0() {
		return Format.getDouble(collegeStudT0.getText());
	}

	public Statistics getFieldCollegeApple9() {
		return fieldCollegeApple9.getStatistics();
	}

	public Statistics getFieldCollegeAppl12() {
		return fieldCollegeAppl12.getStatistics();
	}

	public Statistics getSuccCollegeGrad() {
		return succCollegeGrad.getStatistics();
	}

	public Statistics getUniverApplAmongCollegeGrad() {
		return univerApplAmongCollegeGrad.getStatistics();
	}

	public Statistics getFieldUniverAppl12() {
		return fieldUniverAppl12.getStatistics();
	}

	public Statistics getSuccUniverGrad() {
		return succUniverGrad.getStatistics();
	}

	public Statistics getCollegeGradWork() {
		return collegeGradWork.getStatistics();
	}

	public Statistics getUniverGradWork() {
		return univerGradWork.getStatistics();
	}

	public Statistics getUniverGradEdu() {
		return univerGradEdu.getStatistics();
	}

	public Statistics getUniverGradRnD() {
		return univerGradRnD.getStatistics();
	}

	public Stud_panel(int otrasl) {
		setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		setBounds(new Rectangle(0, 0, 1200, 700));

		setLayout(new MigLayout("", "[grow]", "[224px][302px][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0)),
				"\u041F\u0440\u043E\u0444\u0435\u0441\u0441\u0438\u043E\u043D\u0430\u043B\u044C\u043D\u043E-\u0442\u0435\u0445\u043D\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043E\u0431\u0440\u0430\u0437\u043E\u0432\u0430\u043D\u0438\u0435",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setMinimumSize(new Dimension(10, 220));
		add(panel, "cell 0 0,growx,aligny top");
		panel.setLayout(new MigLayout("", "[grow][102][120:120:120]", "[][grow][grow][grow][70]"));

		JLabel label_collegeStudT0 = new JLabel(
				"Количество студентов в вузах 1-2 уровня аккредетации в начальном году, чел.");
		label_collegeStudT0.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(label_collegeStudT0, "cell 0 0,alignx left");

		collegeStudT0 = new JFormattedTextField(Format.getNumberFormat());
		collegeStudT0.setFont(new Font("Calibri", Font.PLAIN, 12));
		collegeStudT0.setMinimumSize(new Dimension(105, 27));
		collegeStudT0.setMaximumSize(new Dimension(125, 27));
		panel.add(collegeStudT0, "cell 2 0,growx");
		collegeStudT0.setColumns(10);

		JLabel fieldCollegeApple9Label = new JLabel(
				"Доля выпускников 9 классов поступивших на специальности относящиеся к данной отрасли");
		fieldCollegeApple9Label.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(fieldCollegeApple9Label, "cell 0 1,alignx left");

		fieldCollegeApple9 = new NumberOrButton(
				"Доля выпускников 9 классов поступивших на специальности относящиеся к данной отрасли", true);
		fieldCollegeApple9.setMaxValue(1);
		panel.add(fieldCollegeApple9, "cell 1 1 2 1,growx");

		JLabel fieldCollegeAppl12Label = new JLabel(
				"Доля выпускников 11 классов поступивших на специальности относящиеся к данной области");
		fieldCollegeAppl12Label.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(fieldCollegeAppl12Label, "cell 0 2,alignx left");

		fieldCollegeAppl12 = new NumberOrButton(
				"Доля выпускников 11 классов поступивших на специальности относящиеся к данной области", true);
		fieldCollegeAppl12.setMaxValue(1);
		panel.add(fieldCollegeAppl12, "cell 1 2 2 1,growx");

		JLabel succCollegeGradLabel = new JLabel(
				"Доля студентов вузов 1-2 уровня аккредетации успешно закончивших обучение");
		succCollegeGradLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(succCollegeGradLabel, "cell 0 3,alignx left");

		succCollegeGrad = new NumberOrButton(
				"Доля студентов вузов 1-2 уровня аккредетации успешно закончивших обучение", true);
		succCollegeGrad.setMaxValue(1);
		panel.add(succCollegeGrad, "cell 1 3 2 1,growx");

		scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(23, 220));
		scrollPane.setMaximumSize(new Dimension(32767, 50));
		panel.add(scrollPane, "cell 0 4,grow");

		setStatFieldCollegeAppl(null);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true),
				"\u0412\u044B\u0441\u0448\u0435\u0435 \u043E\u0431\u0440\u0430\u0437\u043E\u0432\u0430\u043D\u0438\u0435",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel1.setPreferredSize(new Dimension(10, 50));
		panel1.setMaximumSize(new Dimension(32767, 220));
		add(panel1, "cell 0 1,grow");
		panel1.setLayout(new MigLayout("", "[grow][102][107]", "[][][][][70]"));

		JLabel univerStudT0Label = new JLabel(
				"Количество студентов в вузах 3-4 уровня аккредетации в начальном году, чел.");
		univerStudT0Label.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel1.add(univerStudT0Label, "cell 0 0,alignx left");

		univerStudT0 = new JFormattedTextField(Format.getNumberFormat());
		univerStudT0.setFont(new Font("Calibri", Font.PLAIN, 12));
		univerStudT0.setMinimumSize(new Dimension(105, 27));
		univerStudT0.setMaximumSize(new Dimension(125, 27));
		panel1.add(univerStudT0, "cell 2 0,growx");
		univerStudT0.setColumns(10);

		JLabel univerApplAmongCollegeGradLabel = new JLabel(
				"Доля выпускников вузов 1-2 уровня аккредетации, поступивших в вузы 3-4 уровня, %");
		univerApplAmongCollegeGradLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel1.add(univerApplAmongCollegeGradLabel, "cell 0 1,alignx left,aligny center");

		univerApplAmongCollegeGrad = new NumberOrButton(
				"Доля выпускников вузов 1-2 уровня аккредетации, поступивших в вузы 3-4 уровня, %", true);
		univerApplAmongCollegeGrad.setMaxValue(1);
		panel1.add(univerApplAmongCollegeGrad, "cell 1 1 2 1,growx");

		JLabel fieldUniverAppl12Label = new JLabel(
				"Доля выпускников 11 классов, поступивших на специальности, относящиеся к данной отрасли");
		fieldUniverAppl12Label.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel1.add(fieldUniverAppl12Label, "cell 0 2,alignx left,aligny center");

		fieldUniverAppl12 = new NumberOrButton(
				"Доля выпускников 11 классов, поступивших на специальности, относящиеся к данной отрасли",
				true);
		fieldUniverAppl12.setMaxValue(1);
		panel1.add(fieldUniverAppl12, "cell 1 2 2 1,growx");

		JLabel succUniverGradLabel = new JLabel(
				"Доля студентов вузов 3-4 уровня аккредетации, успешно закончивших обучение");
		succUniverGradLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel1.add(succUniverGradLabel, "cell 0 3,alignx left,aligny center");

		succUniverGrad = new NumberOrButton(
				"Доля студентов вузов 3-4 уровня аккредетации, успешно закончивших обучение", true);
		succUniverGrad.setMaxValue(1);
		panel1.add(succUniverGrad, "cell 1 3 2 1,growx");

		scrollPane1 = new JScrollPane();
		scrollPane1.setMaximumSize(new Dimension(32767, 50));
		panel1.add(scrollPane1, "cell 0 4,grow");

		setStatFieldUniverAppl(null);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0)),
				"\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u043F\u043E \u0441\u0444\u0435\u0440\u0430\u043C \u0434\u0435\u044F\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u0438",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel2, "cell 0 2,growx,aligny top");
		panel2.setLayout(new MigLayout("", "[grow][105][105]", "[grow][grow][grow][grow]"));

		JLabel collegeGradWorkLabel = new JLabel(
				"\u0414\u043E\u043B\u044F \u0432\u044B\u043F\u0443\u0441\u043A\u043D\u0438\u043A\u043E\u0432 \u0432\u0443\u0437\u043E\u0432 1-2 \u0443\u0440\u043E\u0432\u043D\u044F \u0430\u043A\u043A\u0440\u0435\u0434\u0435\u0442\u0430\u0446\u0438\u0438, \u043F\u043E\u0441\u0442\u0443\u043F\u0438\u0432\u0448\u0438\u0445 \u043D\u0430 \u0440\u0430\u0431\u043E\u0442\u0443 \u0432 \u043E\u0442\u0440\u0430\u0441\u043B\u044C  ");
		collegeGradWorkLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel2.add(collegeGradWorkLabel, "cell 0 0,alignx left");

		collegeGradWork = new NumberOrButton(
				"Процент выпускников вузов 1-2 уровня аккредетации, поступивших на работу в отрасль  ", true);
		collegeGradWork.setMaxValue(1);
		panel2.add(collegeGradWork, "cell 1 0 2 1,growx");

		JLabel univerGradWorkLabel = new JLabel(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших на работу в отрасль");
		univerGradWorkLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel2.add(univerGradWorkLabel, "cell 0 1,alignx left");

		univerGradWork = new NumberOrButton(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших на работу в отрасль", true);
		univerGradWork.setMaxValue(1);
		panel2.add(univerGradWork, "cell 1 1 2 1,growx");

		JLabel univerGradEduLabel = new JLabel(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших в сферу образования");
		univerGradEduLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel2.add(univerGradEduLabel, "cell 0 2,alignx left");

		univerGradEdu = new NumberOrButton(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших в сферу образования", true);
		univerGradEdu.setMaxValue(1);
		panel2.add(univerGradEdu, "cell 1 2 2 1,growx");

		JLabel univerGradRnDLabel = new JLabel(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших в сферу НИОКР");
		univerGradRnDLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel2.add(univerGradRnDLabel, "cell 0 3,alignx left");

		univerGradRnD = new NumberOrButton(
				"Доля студентов вузов 3-4 уровня аккредетации, поступивших в сферу НИОКР", true);
		univerGradRnD.setMaxValue(1);
		panel2.add(univerGradRnD, "cell 1 3 2 1,growx");
		refresh(otrasl);

	}

	void setStatFieldCollegeAppl(Statistics data) {
		statFieldCollegeAppl = TableWithRowHeaders
				.setTable(
						data,
						Main.Year - 2,
						4,
						new String[] { "Статистика поступивших в Вузы 1-2 уровня аккредетации по данному направлению" });
		scrollPane.setViewportView(statFieldCollegeAppl);
	}

	void setStatFieldUniverAppl(Statistics data) {
		statFieldUniverAppl = TableWithRowHeaders
				.setTable(
						data,
						Main.Year - 4,
						6,
						new String[] { "Статистика поступивших в Вузы 3-4 уровня аккредетации по данному направлению" });
		scrollPane1.setViewportView(statFieldUniverAppl);

	}

	public void refresh(int vedID) {
		try {
			Education edu = ModelObjectsContainer.getInstance().getEducation(vedID);
			if (edu != null) {

				collegeStudT0.setText(String.valueOf(edu.getcollegeStud(0)));
				fieldCollegeApple9.setValue(edu.getfieldCollegeAppl9());
				fieldCollegeAppl12.setValue(edu.getfieldCollegeAppl12());
				succCollegeGrad.setValue(edu.getsuccCollegeGrad());
				univerStudT0.setText(String.valueOf(edu.getuniverStud(0)));
				univerApplAmongCollegeGrad.setValue(edu.getuniverApplAmongCollegeGrad());
				fieldUniverAppl12.setValue(edu.getfieldUniverAppl12());
				succUniverGrad.setValue(edu.getsuccUniverGrad());
				collegeGradWork.setValue(edu.getcollegeGradWork());
				univerGradWork.setValue(edu.getuniverGradWork());
				univerGradEdu.setValue(edu.getuniverGradEdu());
				univerGradRnD.setValue(edu.getuniverGradRnD());
				setStatFieldCollegeAppl(edu.getstatFieldCollegeAppl());
				setStatFieldUniverAppl(edu.getstatFieldUniverAppl());
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
