package visual.treeBuilders.disabledNode;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class DisabledLeafIcon implements Icon {
	int width = 16;
	int height = 16;
	int additionalHeight = 4;

	public void paintIcon(Component c, Graphics g, int x, int y) {
		int right = width - 1;
		int bottom = height + 1;

		g.setColor(c.getBackground());
		g.fillRect(0, 0, width, getIconHeight());

		// Draw frame
		g.setColor(MetalLookAndFeel.getControlDisabled());
		g.drawLine(2, 2, 2, bottom); // left
		g.drawLine(2, 2, right - 4, 2); // top
		g.drawLine(2, bottom, right - 1, bottom); // bottom
		g.drawLine(right - 1, 8, right - 1, bottom); // right
		g.drawLine(right - 6, 4, right - 2, 8); // slant 1
		g.drawLine(right - 5, 3, right - 4, 3); // part of slant 2
		g.drawLine(right - 3, 4, right - 3, 5); // part of slant 2
		g.drawLine(right - 2, 6, right - 2, 7); // part of slant 2
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height + additionalHeight;
	}
}
