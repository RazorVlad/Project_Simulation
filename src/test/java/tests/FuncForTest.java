package tests;

import mathModel.Statistics;

public class FuncForTest {

	public static boolean equals(Statistics value1, Statistics value2) {
		boolean answer = true;
		if (value1.size() == value2.size()) {
			for (int i = 0; i < value1.size(); i++) {
				if (value1.getValue(i) != value2.getValue(i))
					answer = false;
			}
		} else {
			System.out.println("different size:");
			System.out.println("value1.size=" + value1.size());
			System.out.println("value2.size=" + value2.size());
			answer = false;
		}
		return answer;
	}

}
