package Framework;

import java.util.logging.Logger;

/**
 * @author Sharmilkumar Patel
 */

public class CheckPoint {
	private java.lang.Object expect;
	private java.lang.Object actual;
	private static final Logger LOGGER=Logger.getLogger(Framework.CheckPoint.class.getName());
	private boolean result;

	public CheckPoint(java.lang.Object expect, java.lang.Object actual) {
		this.expect = expect;
		this.actual = actual;
	}

	public boolean getResult() {
		return this.result;
	}

	public boolean execute() {
		if (expect instanceof String) {
			this.result = compare((String) expect, (String) actual);
		} else if (expect instanceof Integer) {
			this.result = compare((Integer) expect, (Integer) actual);
		} else if (expect instanceof Boolean) {
			this.result = compare((Boolean) expect, (Boolean) actual);
		} else {
			LOGGER.warning("Don't know the type");
			this.result = false;
		}
		LOGGER.info(this.toString());
		return this.result;
	}

	public boolean compare(String o1, String o2) {
		String s1 = o1;
		String s2 = o2;
		int len1 = s1.length();
		int len2 = s2.length();
		int n = Math.min(len1, len2);
		char[] v1 = s1.toCharArray();
		char[] v2 = s2.toCharArray();
		int pos = 0;

		while (n-- != 0) {
			char c1 = v1[pos];
			char c2 = v2[pos];
			if (c1 != c2) {
				return false;
			}
			pos++;
		}
		return (len1 - len2) == 0 ? true : false;
	}

	public boolean compare(Integer o1, Integer o2) {
		int v1 = o1.intValue();
		int v2 = o2.intValue();
		return v1 == v2 ? true : false;
	}

	public boolean compare(Boolean o1, Boolean o2) {
		return o1.equals(o2);
	}
	@Override
	public String toString() {
		return "[CheckPoint: " + " Result:" + this.result + " Expected: " + this.expect.toString() + " Actual: "
				+ this.actual.toString() + "]";
	}
}
