package visual.topMenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Icon extends JPanel {
	private static final long serialVersionUID = 1L;
	private int size;

	public int getIconWidth() {
		return size;
	}

	// "D:\\eclipse\\workspace\\project_Simulation\\buttons\\";
//	public static String iconPath = "buttons\\";
	public static String iconPath = "/buttons/";

	public Icon(int size, String icon, String text1, String text2) {
		this.size = size;
		setBorder(null);
		addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent arg0) {
				MouseEntered();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				MouseExited();
			}
		});
		setPreferredSize(new Dimension(105, 93));
		setMinimumSize(new Dimension(size, 10));
		setMaximumSize(new Dimension(size, 100));
		setOpaque(false);
		setLayout(null);

		JLabel lblInput = new JLabel("");
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput.setFocusable(false);
		lblInput.setBounds(0, -1, size, 35);
//		lblInput.setIcon(new ImageIcon(iconPath + icon));
		lblInput.setIcon(new ImageIcon(getClass().getResource(iconPath+icon)));
		add(lblInput);

		JLabel inputText = new JLabel(text1);
		inputText.setHorizontalAlignment(SwingConstants.CENTER);
		inputText.setFont(new Font("Calibri", Font.PLAIN, 13));
		inputText.setFocusable(false);
		inputText.setBounds(0, 30, size, 14);
		add(inputText);

		JLabel inputText2 = new JLabel(text2);
		inputText2.setHorizontalAlignment(SwingConstants.CENTER);
		inputText2.setFont(new Font("Calibri", Font.PLAIN, 13));
		inputText2.setFocusable(false);
		inputText2.setBounds(0, 37, size, 14);
		add(inputText2);
	}

	public void MouseEntered() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	}

	public void MouseExited() {
		setBorder(null);
	}
}
