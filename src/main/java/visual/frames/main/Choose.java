package visual.frames.main;

import org.apache.log4j.Logger;

public class Choose {

	private static Logger LOG = Logger.getLogger(TaskPaneMouseListener.class);

	public static void choose(String Name, String otrasl, int type) {
		// name - �������� ���������� ���������;
		// otrasl - ��������� ��������������;
		// type- ���:
		// 0-�����������;
		// 1-�����;
		// 2-���;

		LOG.info("Choose: " + otrasl + "." + Name);
		Main.otrasl_name = otrasl;
		Main.ved_name = Name;

		Main.name = Name + " " + otrasl;
		Main.lblName.setText(Name + " [" + otrasl + "]");

		byte otrid = mathModel.VedNames.getOtraslID(Main.otrasl_name);
		Main.STUDpanel.setVisible(type == 0);
		Main.NIOKRpanel.setVisible(type == 1);
		Main.VEDpanel.setVisible(type == 2);
		switch (type) {
		case 0:// �����������
			Main.StudMap.get(otrid).setVisible(true);
			Main.StudMap.get(Main.otraslIndex).setVisible(false);
			break;
		case 1:// �����
			Main.NiokrMap.get(otrid).setVisible(true);
			Main.NiokrMap.get(Main.otraslIndex).setVisible(false);
			break;
		case 2:// ���
			byte vedid = mathModel.VedNames.getID(Main.otrasl_name, Main.ved_name);
			System.out.println(Main.otrasl_name + " " + Main.ved_name + " vedID=" + vedid);
			Main.VedMap.get(vedid).setVisible(true);
			Main.VedMap.get(Main.vedIndex).setVisible(false);
			Main.vedIndex = vedid;
			break;
		}
		Main.otraslIndex = mathModel.VedNames.getOtraslID(Main.otrasl_name);
	}
}
