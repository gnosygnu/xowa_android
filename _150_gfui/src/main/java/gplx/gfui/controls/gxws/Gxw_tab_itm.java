package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.controls.elems.*; import gplx.gfui.controls.standards.*;
public interface Gxw_tab_itm extends GxwElem {
	void Subs_add(GfuiElem sub);
	Gfui_tab_itm_data Tab_data();
	String Tab_name(); void Tab_name_(String v);
	String Tab_tip_text(); void Tab_tip_text_(String v);
}
