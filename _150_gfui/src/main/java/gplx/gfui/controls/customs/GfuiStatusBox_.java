package gplx.gfui.controls.customs; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.kits.core.*; import gplx.gfui.controls.gxws.*; import gplx.gfui.controls.elems.*;
public class GfuiStatusBox_ {
	public static GfuiStatusBox new_(String key) {
		GfuiStatusBox rv = new GfuiStatusBox();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_false_());
		rv.Key_of_GfuiElem_(key);
		return rv;
	}
	public static GfuiStatusBox kit_(Gfui_kit kit, String key, GxwElem underElem) {
		GfuiStatusBox rv = new GfuiStatusBox();
		rv.ctor_kit_GfuiElemBase(kit, key, underElem, GfuiElem_.init_focusAble_false_());
		return rv;
	}
}
