package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface GxwTextFld extends GxwElem {
	boolean Border_on(); void Border_on_(boolean v);
	int SelBgn(); void SelBgn_set(int v);
	int SelLen(); void SelLen_set(int v);
	void CreateControlIfNeeded();
	boolean OverrideTabKey(); void OverrideTabKey_(boolean v);
	void Margins_set(int left, int top, int right, int bot);
}
