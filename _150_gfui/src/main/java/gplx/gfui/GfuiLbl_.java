package gplx.gfui; import gplx.*;
public class GfuiLbl_ {
	public static GfuiLbl sub_(String key, GfuiElem owner) {
		GfuiLbl rv = new_();
		rv.Owner_(owner, key); // must be after ctor, else "Error creating window handle"
		rv.TextMgr().AlignH_(GfuiAlign_.Mid);
		return rv;
	}
	public static GfuiLbl kit_(Gfui_kit kit, String key, GxwElem elm, Keyval_hash ctorArgs) {
		GfuiLbl rv = new GfuiLbl();
		rv.ctor_kit_GfuiElemBase(kit, key, elm, ctorArgs);
		return rv;
	}
	public static GfuiLbl prefix_(String key, GfuiElem owner, String text) {
		GfuiLbl rv = sub_(key, owner);
		rv.Text_(text);
		rv.TextMgr().AlignH_(GfuiAlign_.Left);
		return rv;
	}
	public static GfuiLbl menu_(String key, GfuiElem owner, String text, String tipText) {
		GfuiLbl rv = sub_(key, owner);
		rv.Text_(text);
		rv.TextMgr().AlignH_(GfuiAlign_.Mid);
		rv.TipText_(tipText);
		rv.Border().All_(PenAdp_.black_());
		return rv;
	}
	public static final String Text_Null = null;
	@gplx.Internal protected static GfuiLbl new_() {
		GfuiLbl rv = new GfuiLbl();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_false_());
		return rv;
	}
}
