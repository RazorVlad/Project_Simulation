package visual.topMenu;

import java.io.File;

public class ProjectFileFilter extends javax.swing.filechooser.FileFilter {
	String description;
	String filter;

	public String getDescription() {
		return description;
	}

	public ProjectFileFilter(String description, String filter) {
		this.description = description;
		this.filter = filter;
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			} else {
				return f.toString().endsWith(filter);
			}
		}
		return false;
	}
}