package gplx.gfui; import gplx.*;
public class GfuiBtn_ {
	public static GfuiBtn as_(Object obj) {return obj instanceof GfuiBtn ? (GfuiBtn)obj : null;}
	public static GfuiBtn cast(Object obj) {try {return (GfuiBtn)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfuiBtn.class, obj);}}

	public static GfuiBtn msg_(String key, GfuiElem owner, GfoMsg msg) {
		GfuiBtn rv = new_(key); rv.Owner_(owner);
		rv.Click_msg_(msg);
		return rv;
	}
	public static GfuiBtn invk_(String key, GfuiElem owner, GfoInvkAble invk, String m) {
		GfuiBtn rv = new_(key); rv.Owner_(owner);
		rv.Click_invk(GfoInvkAbleCmd.new_(invk, m));
		return rv;
	}
	public static GfuiBtn kit_(Gfui_kit kit, String key, GxwElem elm, KeyValHash ctorArgs) {
		GfuiBtn rv = new GfuiBtn();
		rv.ctor_kit_GfuiElemBase(kit, key, elm, ctorArgs);
		return rv;
	}
	@gplx.Internal protected static GfuiBtn new_(String key) {
		GfuiBtn rv = new GfuiBtn();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_true_());
		rv.Key_of_GfuiElem_(key);
		return rv;
	}
	@gplx.Internal protected static void FocusBorderRect_set(GfuiBorderMgr borderMgr, GfuiElem elem) {
		borderMgr.Bounds_sync(RectAdp_.new_(3, 3, elem.Width() - 6, elem.Height() - 6));
	}
}
