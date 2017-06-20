package visual.frames.spros;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

public class Spros extends JFrame {

	private static final long serialVersionUID = -5349832473194105357L;
	private static Logger LOG = Logger.getLogger(Spros.class);
	public String otraslName = "Пищевая промышленность";
	public String vedName = "Производство пищевых продуктов";
	int vedindex = 0;
	private JPanel contentPane;
	private Map<Integer, SprosPanel> vedMap = new HashMap<Integer, SprosPanel>();

	public Spros() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {

			}
		});
		setBounds(new Rectangle(100, 50, 1000, 630));
		setTitle("\u041A\u043E\u043D\u0435\u0447\u043D\u044B\u0439 \u0441\u043F\u0440\u043E\u0441 \u043D\u0430 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u044E");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new MigLayout("", "[250px][grow][250px]", "[30px]"));

		JButton okButton = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// FieldsInteraction fi =
				// ModelObjectsContainer.getInstance().getFieldsInteraction();
				SprosPanel spros = vedMap.get(vedindex);
				spros.save();
				spros.graf();

				// fi.setYx(spros.getYx(), vedindex);
				// fi.setYi(spros.getYi(), vedindex); TODO Sasha

				spros.getExport();
				spros.getYiinn();
				spros.getYiord();
				spros.getYxInn();
				spros.getYxOrd();

				// dispose();
			}
		});
		okButton.setMinimumSize(new Dimension(120, 28));
		okButton.setPreferredSize(new Dimension(120, 28));
		okButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		okButton.setMaximumSize(new Dimension(120, 28));
		buttonsPanel.add(okButton, "flowx,cell 2 0,alignx right,growy");

		JButton cancelButton = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setMinimumSize(new Dimension(120, 28));
		cancelButton.setPreferredSize(new Dimension(120, 28));
		cancelButton.setMaximumSize(new Dimension(120, 28));
		cancelButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonsPanel.add(cancelButton, "cell 2 0,alignx right,growy");

		JPanel vedListPanel = new JPanel();
		vedListPanel.setPreferredSize(new Dimension(350, 1500));
		vedListPanel.setMaximumSize(new Dimension(32767, 1500));
		vedListPanel.setMinimumSize(new Dimension(10, 1500));
		contentPane.add(vedListPanel, BorderLayout.WEST);
		vedListPanel.setLayout(new BorderLayout(0, 0));

		JLabel chooseVedLabel = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0412\u042D\u0414");
		chooseVedLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		chooseVedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chooseVedLabel.setPreferredSize(new Dimension(74, 25));
		vedListPanel.add(chooseVedLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		vedListPanel.add(scrollPane, BorderLayout.CENTER);

		JTreeCreator jtc = new JTreeCreator();
		DefaultMutableTreeNode level = jtc.createMainNode();

		JTree jt = new JTree(level);
		jt.setRootVisible(false);
		jt.setSelectionPath(new TreePath(jtc.getSelectionPath()));

		jt.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				String s = arg0.getPath().getLastPathComponent().toString();
				if (arg0.getNewLeadSelectionPath().getPathCount() == 3) {
					otraslName = arg0.getPath().getParentPath().getLastPathComponent().toString();
				}
				vedName = s;
				vedMap.get(vedindex).setVisible(false);
				vedindex = mathModel.VedNames.getID(otraslName, vedName);
				vedMap.get(vedindex).setVisible(true);
			}
		});

		scrollPane.setViewportView(jt);

		JPanel dataPanel = new JPanel();
		contentPane.add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(new CardLayout(0, 0));

		for (int i = 0; i < 25; i++) {
			vedMap.put(i, new SprosPanel(i));
			dataPanel.add(vedMap.get(i), "qwec" + i);
			vedMap.get(i).setVisible(i == 0);
		}
	}
}
