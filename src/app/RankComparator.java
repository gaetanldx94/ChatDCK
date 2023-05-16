package src.app;

import java.util.Comparator;

class RankComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		double d1 = Double.parseDouble(s1.replaceAll("[^\\d\\.]", ""));
		double d2 = Double.parseDouble(s2.replaceAll("[^\\d\\.]", ""));
		return Double.compare(d1, d2);
	}
}