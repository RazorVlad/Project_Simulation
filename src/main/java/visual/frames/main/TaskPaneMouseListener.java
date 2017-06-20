package visual.frames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

class TaskPaneMouseListener implements MouseListener {

	private String typeName;
	private String otrasl;
	private int type;
	private JLabel label;
	Color color = Color.blue;

	public TaskPaneMouseListener(String typeName, String otrasl, int type, JLabel label) {
		this.typeName = typeName;
		this.otrasl = otrasl;
		this.type = type;
		this.label = label;
	}

	public void mouseClicked(MouseEvent arg0) {
		Choose.choose(typeName, otrasl, type);
		Main.choosenLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		label.setFont(new Font("Calibri", Font.BOLD, 12));
		Main.choosenLabel = label;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		label.setForeground(color);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		label.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
