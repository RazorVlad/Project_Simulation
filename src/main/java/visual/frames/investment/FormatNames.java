package visual.frames.investment;

import visual.Format;

public class FormatNames {
	public static String[] FormatVedNames(int type,String[] names){
		switch (type) {
		case 2:
			names[0] = Format.addBR(names[0], 35);
			System.out.println(names[0]);
			break;
		case 3:
			names[0] = Format.addBR(names[0], 20);
			break;
		case 4:
			names[0] = Format.addBR(names[0], 13);
			names[1] = Format.addBR(names[1], 13);
			break;
		case 5:
			names[0] = Format.addBR(names[0], 32);
			names[2] = Format.addBR(names[2], 25);
			break;
		case 6:
			names[0] = Format.addBR(names[0], 36);
			break;
		case 7:
			names[1] = Format.addBR(names[1], 21);
			break;
		case 8:
			names[0] = Format.addBR(names[0], 26);
			names[1] = Format.addBR(names[1], 13);
			names[2] = Format.addBR(names[2], 13);
			names[3] = Format.addBR(names[3], 39);
			names[4] = Format.addBR(names[4], 20);
			break;
		case 9:
			names[0] = Format.addBR(names[0], 25);
			names[2] = Format.addBR(names[2], 30);
			break;
		}
		return names;
	}
}
