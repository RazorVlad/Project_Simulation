package visual.frames.investment.investmentFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import mathModel.InvestmentsManuf;
import mathModel.InvestmentsRnD;
import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.VedNames;
import net.miginfocom.swing.MigLayout;
import visual.frames.investment.InvestmentResultFrame;
import visual.frames.main.Main;

public class InvestmentFrame extends JFrame {

	private static final long serialVersionUID = -3539132141627106042L;
	private static Logger LOG = Logger.getLogger(InvestmentFrame.class);
	final InvestTypePanel gosInvest;
	final InvestTypePanel chastnInvest;
	private JPanel contentPane;
	private String font="Calibri";
	private Map<Integer, Statistics> privInvDMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> govInvDMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> privInvRMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> govInvRMap = new HashMap<Integer, Statistics>();

	private Map<Integer, Statistics> innPrivInvMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> innGovInvMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> ordPrivInvMap = new HashMap<Integer, Statistics>();
	private Map<Integer, Statistics> ordGovInvMap = new HashMap<Integer, Statistics>();

	public InvestmentFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		setBounds(new Rectangle(50, 50, 900, 800));
		setTitle("\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		gosInvest = new InvestTypePanel(true);
		tabbedPane
				.addTab("\u0420\u0430\u0441\u0445\u043E\u0434\u044B \u0413\u043E\u0441\u0431\u044E\u0434\u0436\u0435\u0442\u0430",
						null, gosInvest, null);

		chastnInvest = new InvestTypePanel(false);
		tabbedPane
				.addTab("\u0427\u0430\u0441\u0442\u043D\u044B\u0435 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0438",
						null, chastnInvest, null);

		JPanel panel_16 = new JPanel();
		panel_16.setMinimumSize(new Dimension(10000, 26));
		contentPane.add(panel_16, BorderLayout.SOUTH);
		panel_16.setLayout(new MigLayout("", "[][grow][][]", "[]"));

		JButton btnRezultShow = new JButton(
				"\u041F\u0440\u043E\u0441\u043C\u043E\u0442\u0440 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u043E\u0432");
		btnRezultShow.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				try {
					InvestmentResultFrame inv = new InvestmentResultFrame(gosInvest.getPanel4()
							.getInvestInBranch(), chastnInvest.getPanel4().getInvestInBranch());
					inv.show();
				} catch (Exception e1) {
					LOG.error(e1.getMessage(), e1);
				}

			}
		});
		btnRezultShow.setMinimumSize(new Dimension(180, 26));
		btnRezultShow.setMaximumSize(new Dimension(180, 26));
		btnRezultShow.setPreferredSize(new Dimension(149, 26));
		btnRezultShow.setFont(new Font(font, Font.PLAIN, 13));
		panel_16.add(btnRezultShow, "cell 0 0");

		JButton btnOK = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setData();
				chastnInvest.savePanelData();
				gosInvest.savePanelData();
				ModelObjectsContainer model = ModelObjectsContainer.getInstance();
				for (Integer field : VedNames.FIELDS) {
					InvestmentsRnD inv = new InvestmentsRnD(getPrivInvDMap(field), getGovInvDMap(field),
							getPrivInvRMap(field), getGovInvRMap(field));
					model.setInvestmentsRnD(field, inv);
				}

				for (Integer ved : VedNames.VED) {
					InvestmentsManuf inv = new InvestmentsManuf(getInnPrivInvMap(ved), getInnGovInvMap(ved),
							getOrdPrivInvMap(ved), getOrdGovInvMap(ved));
					model.setInvestmentsManuf(ved, inv);
				}
			}
		});
		btnOK.setMinimumSize(new Dimension(105, 26));
		btnOK.setMaximumSize(new Dimension(105, 26));
		btnOK.setPreferredSize(new Dimension(105, 26));
		btnOK.setFont(new Font(font, Font.PLAIN, 13));
		panel_16.add(btnOK, "cell 2 0");

		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setMinimumSize(new Dimension(105, 26));
		btnCancel.setMaximumSize(new Dimension(105, 26));
		btnCancel.setPreferredSize(new Dimension(105, 26));
		btnCancel.setFont(new Font(font, Font.PLAIN, 13));
		panel_16.add(btnCancel, "cell 3 0");
		gosInvest.refreshPanel();
		chastnInvest.refreshPanel();
	}

	void setData() {
		double[][][] gos = gosInvest.getPanel4().getInvestInBranch();
		double[][][] priv = chastnInvest.getPanel4().getInvestInBranch();

		for (int j = 0; j < 10; j++) {
			Statistics govInvD = new Statistics();
			Statistics govInvR = new Statistics();
			Statistics privInvD = new Statistics();
			Statistics privInvR = new Statistics();
			try {

				for (int i = 0; i < Main.ModelLenght - 1; i++) {
					govInvD.add(Main.Year + i, gos[1][i][j]);
					govInvR.add(Main.Year + i, gos[2][i][j]);
					privInvD.add(Main.Year + i, priv[1][i][j]);
					privInvR.add(Main.Year + i, priv[2][i][j]);
					LOG.debug("govInvD(" + (Main.Year + i) + ") = " + gos[1][i][j]);
					LOG.debug("govInvR(" + (Main.Year + i) + ") = " + gos[2][i][j]);
					LOG.debug("privInvD(" + (Main.Year + i) + ") = " + priv[1][i][j]);
					LOG.debug("privInvR(" + (Main.Year + i) + ") = " + priv[2][i][j]);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			privInvDMap.put(j, privInvD);
			govInvDMap.put(j, govInvD);
			privInvRMap.put(j, privInvR);
			govInvRMap.put(j, govInvR);
		}
		int[] index = new int[10];
		for (int i = 0; i < 10; i++) {
			index[i] = 0;
		}
		for (int i = 0; i < 25; i++) {
			Statistics innPrivInv = new Statistics();
			Statistics innGovInv = new Statistics();
			Statistics ordPrivInv = new Statistics();
			Statistics ordGovInv = new Statistics();
			try {
				for (int j = 0; j < Main.ModelLenght - 1; j++) {
					if (i < 3) {
						ordPrivInv.add(Main.Year + j, setData(priv, 3 + (index[0]), j, 0));
						ordGovInv.add(Main.Year + j, setData(gos, 3 + (index[0]), j, 0));
						innPrivInv.add(Main.Year + j, setData(priv, 4 + (index[0]), j, 0));
						innGovInv.add(Main.Year + j, setData(gos, 4 + (index[0]), j, 0));
						if (j == Main.ModelLenght - 2) {
							index[0] += 2;
						}
					}
					//
					int id = 0;
					int addId = 0;
					if (i > 2 && i < 6) {
						id = 1;
						addId = index[id];
					}
					if (i == 6) {
						id = 2;
					}
					if (i > 6 && i < 9) {
						id = 3;
						addId = index[id];
					}
					if (i > 8 && i < 11) {
						id = 4;
						addId = index[id];
					}
					if (i > 10 && i < 14) {
						id = 5;
						addId = index[id];
					}
					if (i == 14) {
						id = 6;
					}
					if (i > 14 && i < 17) {
						id = 7;
						addId = index[id];
					}
					if (i > 16 && i < 22) {
						id = 8;
						addId = index[id];
					}
					if (i > 21 && i < 25) {
						id = 9;
						addId = index[9];
					}
					if (id > 0) {
						ordPrivInv.add(Main.Year + j, setData(priv, 3 + addId, j, id));
						ordGovInv.add(Main.Year + j, setData(gos, 3 + addId, j, id));
						innPrivInv.add(Main.Year + j, setData(priv, 4 + addId, j, id));
						innGovInv.add(Main.Year + j, setData(gos, 4 + addId, j, id));
						if (j == Main.ModelLenght - 2 && addId > 0) {
							index[id] += 2;
						}
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			innPrivInvMap.put(i, innPrivInv);
			innGovInvMap.put(i, innGovInv);
			ordPrivInvMap.put(i, ordPrivInv);
			ordGovInvMap.put(i, ordGovInv);
		}
	}

	public double setData(double[][][] mass, int i, int j, int k) {
		double nullData = 0;
		try {
			nullData = mass[i][j][k];
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return nullData;
	}

	public Statistics getPrivInvDMap(int otraslID) {
		return privInvDMap.get(otraslID);
	}

	public Statistics getGovInvDMap(int otraslID) {
		return govInvDMap.get(otraslID);
	}

	public Statistics getPrivInvRMap(int otraslID) {
		return privInvRMap.get(otraslID);
	}

	public Statistics getGovInvRMap(int otraslID) {
		return govInvRMap.get(otraslID);
	}

	public Statistics getInnPrivInvMap(int vedID) {
		return innPrivInvMap.get(vedID);
	}

	public Statistics getInnGovInvMap(int vedID) {
		return innGovInvMap.get(vedID);
	}

	public Statistics getOrdPrivInvMap(int vedID) {
		return ordPrivInvMap.get(vedID);
	}

	public Statistics getOrdGovInvMap(int vedID) {
		return ordGovInvMap.get(vedID);
	}

}
