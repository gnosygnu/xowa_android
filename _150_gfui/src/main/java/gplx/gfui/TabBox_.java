package gplx.gfui; import gplx.*;
public class TabBox_ {
	public static TabBox as_(Object obj) {return obj instanceof TabBox ? (TabBox)obj : null;}
	public static TabBox cast(Object obj) {try {return (TabBox)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, TabBox.class, obj);}}
	public static TabBox new_() {
		TabBox rv = new TabBox();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_false_());
		return rv;
	}
	public static int Cycle(boolean fwd, int val, int max) {
		if (fwd)
			return val == max - 1 ? 0 : val + 1;
		else
			return val == 0 ? max - 1 : val - 1;
	}
}
