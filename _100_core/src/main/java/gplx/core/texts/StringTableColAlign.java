package gplx.core.texts; import gplx.*; import gplx.core.*;
public class StringTableColAlign {
	public int Val() {return val;} int val = 0;
	public static StringTableColAlign new_(int v) {
		StringTableColAlign rv = new StringTableColAlign();
		rv.val = v;
		return rv;
	}	StringTableColAlign() {}
	public static final StringTableColAlign Left = new_(0);
	public static final StringTableColAlign Mid = new_(1);
	public static final StringTableColAlign Right = new_(2);
}
