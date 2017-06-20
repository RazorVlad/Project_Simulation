package mathModel;

import java.util.ArrayList;
import java.util.List;

public class VedNames {

	public static final List<Integer> FIELDS = new ArrayList<Integer>();
	public static final List<Integer> VED = new ArrayList<Integer>();
	public static final List<String> FIELDNAMES = new ArrayList<String>();
	public static final List<String> VEDNAMES = new ArrayList<String>();

	static {
		FIELDS.add(0);
		FIELDS.add(1);
		FIELDS.add(2);
		FIELDS.add(3);
		FIELDS.add(4);
		FIELDS.add(5);
		FIELDS.add(6);
		FIELDS.add(7);
		FIELDS.add(8);
		FIELDS.add(9);

		VED.add(0);
		VED.add(1);
		VED.add(2);
		VED.add(3);
		VED.add(4);
		VED.add(5);
		VED.add(6);
		VED.add(7);
		VED.add(8);
		VED.add(9);
		VED.add(10);
		VED.add(11);
		VED.add(12);
		VED.add(13);
		VED.add(14);
		VED.add(15);
		VED.add(16);
		VED.add(17);
		VED.add(18);
		VED.add(19);
		VED.add(20);
		VED.add(21);
		VED.add(22);
		VED.add(23);
		VED.add(24);

		FIELDNAMES.add("Пищевая промышленность");
		FIELDNAMES.add("Легкая промышленность");
		FIELDNAMES.add("Деревообрабатывающая промышленность");
		FIELDNAMES.add("Целлюлозно-бумажная промышленность");
		FIELDNAMES.add("Нефтеперерабатывающая промышленность");
		FIELDNAMES.add("Химическая промышленность");
		FIELDNAMES.add("Промышленность стройматериалов");
		FIELDNAMES.add("Металлургия");
		FIELDNAMES.add("Машиностроение");
		FIELDNAMES.add("Электроэнергетика");

		VEDNAMES.add("Производство пищевых продуктов");
		VEDNAMES.add("Производство напитков");
		VEDNAMES.add("Производство табачных изделий");
		VEDNAMES.add("Текстильное производство");
		VEDNAMES.add("Производство одежды");
		VEDNAMES.add("Изготовление изделий из кожи");
		VEDNAMES.add("Обработка древесины и изготовление изделий из древесины и корка, кроме мебели");
		VEDNAMES.add("Производство бумаги и бумажных изделий");
		VEDNAMES.add("Полиграфическая деятельность");
		VEDNAMES.add("Производство кокса и коксопродуктов");
		VEDNAMES.add("Производство продуктов нефтепереработки");
		VEDNAMES.add("Производство химических веществ и химической продукции");
		VEDNAMES.add("Фармацевтическое производство");
		VEDNAMES.add("Производство резиновых и пластмассовых изделий");
		VEDNAMES.add("Производство прочей неметаллической минеральной продукции");
		VEDNAMES.add("Металлургическое производство");
		VEDNAMES.add("Производство готовых металлических изделий");
		VEDNAMES.add("Производство компьютеров, электронной и оптичекой продукции");
		VEDNAMES.add("Производство электрического оборудования");
		VEDNAMES.add("Производство машин и оборудования");
		VEDNAMES.add("Производство автотранспортных средств, прицепов и полуприцепов");
		VEDNAMES.add("Производство прочих транспортных средств");
		VEDNAMES.add("Производство, передача и распределение электроэнергии");
		VEDNAMES.add("Производство газа");
		VEDNAMES.add("Поставка пара, гарячей воды и кондиционированного воздуха");
	}

	public static byte getOtraslID(String otrasl) {
		byte index = -1;
		for (byte i = 0; i < 10; i++) {
			if (otrasl.equals(FIELDNAMES.get(i))) {
				index = i;
				break;
			}
		}
		return index;

	}

	public static String getOtraslName(int index) {
		return FIELDNAMES.get(index);

	}

	public static int[] getVeds(int otrasl) {
		switch (otrasl) {
		case 0:
			return new int[] { 0, 1, 2 };
		case 1:
			return new int[] { 3, 4, 5 };
		case 2:
			return new int[] { 6 };
		case 3:
			return new int[] { 7, 8 };
		case 4:
			return new int[] { 9, 10 };
		case 5:
			return new int[] { 11, 12, 13 };
		case 6:
			return new int[] { 14 };
		case 7:
			return new int[] { 15, 16 };
		case 8:
			return new int[] { 17, 18, 19, 20, 21 };
		case 9:
			return new int[] { 22, 23, 24 };
		default:
			return null;
		}
	}

	public static int getOtraslByVed(int vedIndex) {
		int index = -1;
		if (vedIndex < 3) {
			index = 0;
		}
		if (vedIndex > 2 && vedIndex < 6) {
			index = 1;
		}
		if (vedIndex == 6) {
			index = 2;
		}
		if (vedIndex > 6 && vedIndex < 9) {
			index = 3;
		}
		if (vedIndex > 8 && vedIndex < 11) {
			index = 4;
		}
		if (vedIndex > 10 && vedIndex < 14) {
			index = 5;
		}
		if (vedIndex == 14) {
			index = 6;
		}
		if (vedIndex > 14 && vedIndex < 17) {
			index = 7;
		}
		if (vedIndex > 16 && vedIndex < 22) {
			index = 8;
		}
		if (vedIndex > 21) {
			index = 9;
		}
		return index;
	}

	public static byte getID(String otrasl, String ved) {
		byte index = -1;

		otrasl = stripTags(otrasl);
		ved = stripTags(ved);

		for (byte i = 0; i < 25; i++) {
			if (ved.equals(VEDNAMES.get(i))) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static String getVedName(int ved) {
		return VEDNAMES.get(ved);
	}

	public static String stripTags(String str) {
		str = str.replaceAll("<br>", " ");
		str = str.replaceAll("<(.)+?>", "");
		str = str.replaceAll("<(\n)+?>", "");
		return str;
	}

	public static String[] getVedNames(int k) {
		int[] veds = getVeds(k);
		String[] model = new String[veds.length];
		for (int l = 0; l < veds.length; l++) {
			model[l] = getVedName(veds[l]);
		}
		return model;
	}

}
