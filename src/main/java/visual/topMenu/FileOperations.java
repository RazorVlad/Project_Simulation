package visual.topMenu;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import visual.frames.main.Main;
import visual.save.VisualObjectsContainer;

public class FileOperations {

	private static Logger LOG = Logger.getLogger(FileOperations.class);

	public static boolean open() {

		JFileChooser fileopen = new JFileChooser();
		fileopen.setFileFilter(new ProjectFileFilter("Файлы проектов", ".project"));
		fileopen.setCurrentDirectory(new java.io.File("."));
		int ret = fileopen.showDialog(null, "Открыть файл");
		if (ret == JFileChooser.APPROVE_OPTION) {
			String path = fileopen.getSelectedFile().getAbsolutePath();
			String fileName = path.substring(0, path.length() - 8);
			try {
				openFile(fileName);
				return true;
			} catch (Exception e) {
				LOG.error("Error on open file '" + fileName + "'", e);
				return false;
			}
		}
		return false;
	}

	public static void openFile(String path) throws FileNotFoundException, ClassNotFoundException,
			IOException {
		LOG.debug("Try to open file'" + path + "'");

		VisualObjectsContainer.readFromFile(path + ".vproject");
		ModelObjectsContainer.readFromFile(path + ".project");

		Main.Year = (VisualObjectsContainer.getInstance().getStartYear());
		LOG.debug("set start year =" + Main.Year);

		Main.ModelLenght = (VisualObjectsContainer.getInstance().getDuration());
		LOG.debug("set duration =" + Main.ModelLenght);

		Main.refresh();
		Main.path = path;
		LOG.info("File '" + path + "' successfully opened");
	}

	public static boolean save(String path) {
		if (path.endsWith(".project")) {
			path = path.substring(0, path.length() - 8);
		}
		if (path.endsWith(".vproject")) {
			path = path.substring(0, path.length() - 9);
		}
		try {
			VisualObjectsContainer.getInstance().setStartYear(Main.Year);
			VisualObjectsContainer.getInstance().setDuration(Main.ModelLenght);
			VisualObjectsContainer.saveToFile(path + ".vproject");
			ModelObjectsContainer.saveToFile(path + ".project");
			LOG.info("Save project in file '" + path + "'");
			return true;
		} catch (Exception e) {
			LOG.error("Error on save project in file '" + path + "'", e);
			return false;
		}
	}

	public static boolean saveAs() {
		try {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(new ProjectFileFilter("Файлы проектов", ".project"));
			jFileChooser.setCurrentDirectory(new java.io.File("."));
			jFileChooser.showSaveDialog(jFileChooser);
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			Main.path = path;
			save(path);
			return true;
		} catch (Exception e) {
			LOG.error("Error on save project", e);
			return false;
		}
	}
}
