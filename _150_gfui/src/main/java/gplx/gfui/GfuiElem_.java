package gplx.gfui; import gplx.*;
public class GfuiElem_ {
	public static final String 
		  InitKey_focusAble = "focusAble"
		, InitKey_ownerWin = "ownerForm";
	public static GfuiElem as_(Object obj) {return obj instanceof GfuiElem ? (GfuiElem)obj : null;}
	public static GfuiElem cast(Object obj) {try {return (GfuiElem)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfuiElem.class, obj);}}
	public static GfuiElemBase sub_(String key, GfuiElem owner) {
		GfuiElemBase rv = new_();
		rv.Owner_(owner, key);
		return rv;
	}
	public static GfuiElemBase new_() {
		GfuiElemBase rv = new GfuiElemBase();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_true_());
		return rv;
	}
	public static KeyValHash init_focusAble_true_()			{return KeyValHash.new_().Add(GfuiElem_.InitKey_focusAble, true);}
	public static KeyValHash init_focusAble_false_()		{return KeyValHash.new_().Add(GfuiElem_.InitKey_focusAble, false);}
	public static void Y_adj(int adj, GfuiElem... ary) {
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			GfuiElem itm = ary[i];
			itm.Y_(itm.Y() + adj);
		}
	}
}
