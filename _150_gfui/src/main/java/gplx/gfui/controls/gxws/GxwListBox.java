package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface GxwListBox extends GxwElem {
	void Items_Add(Object item);
	void Items_Clear();
	int Items_Count();
	int Items_SelIdx(); void Items_SelIdx_set(int v);
	Object Items_SelObj();
}
