package visual.topMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

import visual.frames.ModelChoose;
import visual.frames.Param;
import visual.frames.Population;
import visual.frames.Taxes;
import visual.frames.main.Main;
import visual.frames.spros.Spros;
import visual.frames.technology.Technology;

public class TopMenu extends JPanel {

	private static final long serialVersionUID = -6278221941619311589L;
	private static Logger LOG = Logger.getLogger(TopMenu.class);
	private int standartIconSize = 130;
	private JMenuItem open;

	public JMenuItem getOpen() {
		return open;
	}

	public JMenuItem getSave() {
		return save;
	}

	public JMenuItem getSave_as() {
		return save_as;
	}

	private JMenuItem save;
	private JMenuItem save_as;

	public TopMenu(int max) {

		setPreferredSize(new Dimension(max, 67));
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(new Rectangle(0, 0, max, 70));
		setLayout(new BorderLayout(0, 0));
		standartIconSize = (max - 180) / 10;
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		menuBar.setBackground(UIManager.getColor("menu"));
		menuBar.setPreferredSize(new Dimension(12, 53));
		menuBar.setSize(new Dimension(50, 0));

		Box fileOperationsBox = Box.createVerticalBox();
		fileOperationsBox.setMaximumSize(new Dimension(150, 80));
		fileOperationsBox.setBounds(new Rectangle(0, 0, 150, 0));
		fileOperationsBox.setPreferredSize(new Dimension(150, 50));
		menuBar.add(fileOperationsBox);

		open = new JMenuItem("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		open.setFont(new Font("Calibri", Font.PLAIN, 13));
		open.setIcon(new ImageIcon(Icon.iconPath + "open2.png"));
		fileOperationsBox.add(open);

		save = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Main.path.equals("")) {
					FileOperations.saveAs();
				} else {
					if (FileOperations.save(Main.path)) {
						JOptionPane.showMessageDialog(null, "Проект сохранен в " + Main.path,
								"Проект сохранен", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Произошла ошибка при попытке сохранить проект в файл " + Main.path,
								"Ошибка", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		save.setFont(new Font("Calibri", Font.PLAIN, 13));
		save.setIcon(new ImageIcon(Icon.iconPath + "save1.png"));
		fileOperationsBox.add(save);

		save_as = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043A\u0430\u043A");
		save_as.setFont(new Font("Calibri", Font.PLAIN, 13));
		save_as.setIcon(new ImageIcon(Icon.iconPath + "save_.png"));
		fileOperationsBox.add(save_as);
		// 60
		final Icon input = new Icon(standartIconSize,
				"input.png", "\u0412\u0432\u043E\u0434",
				"\u0434\u0430\u043D\u043D\u044B\u0445");

		input.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.setVisibleInputPanel();
			}
		});
		menuBar.add(input);
		// 80
		final Icon param = new Icon(standartIconSize,
				"settings.png",
				"\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B",
				"\u0440\u0430\u0441\u0441\u0447\u0435\u0442\u0430");

		param.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Param param;
				try {
					param = new Param();
					param.setVisible(true);
				} catch (Exception e) {
					LOG.error("There was an error opening Param window", e);
				}
			}
		});

		menuBar.add(param);
		// 100
		final Icon invest = new Icon(standartIconSize,
				"invest.png",
				"\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435",
				"\u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439");

		invest.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					visual.frames.investment.investmentFrame.InvestmentFrame investment = new visual.frames.investment.investmentFrame.InvestmentFrame();
					investment.show();
				} catch (Exception e) {
					LOG.error("There was an error opening Investment window", e);
				}
			}
		});

		menuBar.add(invest);
		// 80
		final Icon population = new Icon(standartIconSize,
				"population.png",
				"\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430",
				"\u043D\u0430\u0441\u0435\u043B\u0435\u043D\u0438\u044F");

		population.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Population params = new Population();
					params.refresh();
					params.show();
				} catch (Exception e1) {
					LOG.error("There was an error opening Population window", e1);
				}
			}
		});

		menuBar.add(population);
		// 100
		final Icon tech = new Icon(standartIconSize,
				"tech.png",
				"\u0422\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u044F",
				"\u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430");

		tech.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Technology technology = new Technology();
				technology.show();
			}
		});

		menuBar.add(tech);
		// 50
		final Icon demand = new Icon(standartIconSize,
				"demand.png", "\u0421\u043F\u0440\u043E\u0441",
				"");

		demand.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Spros spros = new Spros();
					spros.show();
				} catch (Exception e) {
					LOG.error("There was an error opening Demand window", e);
				}
			}
		});

		menuBar.add(demand);
		// 130
		final Icon nalPol = new Icon(
				standartIconSize,
				"taxes.png",
				"\u0411\u044E\u0434\u0436\u0435\u0442\u043D\u043E-\u043D\u0430\u043B\u043E\u0433\u043E\u0432\u0430\u044F",
				"\u043F\u043E\u043B\u0438\u0442\u0438\u043A\u0430");

		nalPol.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Taxes nalog = new Taxes();
					nalog.show();
				} catch (Exception e) {
					LOG.error("There was an error opening Taxes window", e);
				}
			}
		});

		menuBar.add(nalPol);

		JLabel label_12 = new JLabel("");
		label_12.setMinimumSize(new Dimension(0, 0));
		label_12.setMaximumSize(new Dimension(0, 0));
		label_12.setPreferredSize(new Dimension(0, 0));
		menuBar.add(label_12);

		JSeparator separator = new JSeparator();
		separator.setFont(new Font("SansSerif", Font.BOLD, 12));
		separator.setMaximumSize(new Dimension(10, 32767));
		separator.setPreferredSize(new Dimension(5, 2));
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		// 90
		final Icon modeling = new Icon(standartIconSize,
				"modeling.png",
				"\u041C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u0442\u044C", "");

		modeling.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ModelChoose model = new ModelChoose();
					model.show();
				} catch (Exception e) {
					LOG.error("There was an error opening Modeling tab", e);
				}
			}
		});

		menuBar.add(modeling);
		// 80
		final Icon rezShow = new Icon(standartIconSize,
				"result.png",
				"\u041F\u0440\u043E\u0441\u043C\u043E\u0442\u0440",
				"\u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u043E\u0432");

		rezShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.setVisibleResultPanel();
			}
		});
		menuBar.add(rezShow);
		// 45
		final Icon report = new Icon(standartIconSize,
				"report.png", "\u041E\u0442\u0447\u0435\u0442",
				"");

		report.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		menuBar.add(report);

		JLabel mainDataTitle = new JLabel(
				"\u0418\u0441\u0445\u043E\u0434\u043D\u044B\u0435 \u0434\u0430\u043D\u043D\u044B\u0435");
		mainDataTitle.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		mainDataTitle.setFont(new Font("Calibri", Font.BOLD, 13));
		mainDataTitle.setBackground(SystemColor.activeCaption);
		int mainDataSize = fileOperationsBox.getWidth() + input.getIconWidth() + param.getIconWidth()
				+ invest.getIconWidth() + population.getIconWidth() + tech.getIconWidth()
				+ demand.getIconWidth() + nalPol.getIconWidth() + 8;
		// 818
		mainDataTitle.setPreferredSize(new Dimension(mainDataSize, 18));
		mainDataTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(mainDataTitle, BorderLayout.WEST);

		JLabel modelingTitle = new JLabel(
				"\u041C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435                      ");
		modelingTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
		modelingTitle.setFont(new Font("Calibri", Font.BOLD, 13));
		modelingTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(modelingTitle, BorderLayout.CENTER);

	}

	public void MouseEntered(JPanel panel) {
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	}

	public void MouseExited(JPanel panel) {
		panel.setBorder(null);
	}

}
