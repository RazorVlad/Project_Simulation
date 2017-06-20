package exceptions;

import java.util.List;

import javax.swing.JOptionPane;

public class DataRequiredEx extends Exception {

	private static final long serialVersionUID = -7651937787778252987L;
	public List<String> data;

	public DataRequiredEx(List<String> data) {
		this.data = data;
		print();
	}

	public void print() {
		String list = "";

		if (this.data == null) {
			JOptionPane.showMessageDialog(null, "�� ������� ��� ����������� ������", "������!",
					JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i = 0; i < data.size(); i++) {
				list += data.get(i) + "<br>";
			}
			JOptionPane.showMessageDialog(null,
					"<html><b>���������� ������ ��� ��������� ��������� ������: </b><br>" + list, "������!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
