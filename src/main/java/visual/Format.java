package visual;

import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

import org.jfree.util.Log;

public class Format {
	static NumberFormat format;

	public static NumberFormat getNumberFormat() {
		format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(10);
		return format;
	}

	public static String formatValue(double value) {
		return getNumberFormat().format(value);
	}

	public static String formatString(String value) {
		double val = getDouble(value);
		return formatValue(val);
	}

	public static double getDouble(String value) {

		double val = 0;
		if (!value.isEmpty()) {
			try {
				val = NumberFormat.getInstance().parse(value.replaceAll(" ", "").replaceAll("\\.", ","))
						.doubleValue();
			} catch (ParseException e) {
				Log.error("Typed incorrect number", e);
			}
		}
		return val;
	}

	public static double getDouble(Object value) {
		if (value != null) {
			return getDouble(value.toString());
		} else {
			return 0;
		}
	}

	public static String addBR(String name, int n) {
		name = "<html>" + name.substring(0, n) + "<br>" + name.substring(n);
		return name;
	}

	public static void setFieldDefaults(JFormattedTextField field, int fontSize, int width, int height) {
		field.setFont(new Font("Calibri", Font.PLAIN, fontSize));
		field.setMinimumSize(new Dimension(width, height));
		field.setMaximumSize(new Dimension(width, height));
		field.setPreferredSize(new Dimension(width, height));
		field.setColumns(10);
	}

}
