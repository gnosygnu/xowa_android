package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public interface GfuiElem extends GfoInvkAble, GxwCbkHost, IptBndsOwner, GftItem, GfoEvObj {
	//% Layout
	int X(); GfuiElem X_(int val);
	int Y(); GfuiElem Y_(int val);
	int X_max();
	int Y_max();
	GfuiElem Pos_(PointAdp val); GfuiElem Pos_(int x, int y);
	int Width(); GfuiElem Width_(int val);
	int Height(); GfuiElem Height_(int val);
	GfuiElem Size_(SizeAdp val); GfuiElem Size_(int w, int h); 
	RectAdp Rect(); void Rect_set(RectAdp rect);
	void Zorder_front(); void Zorder_back();
	PointAdp Pos();
	SizeAdp Size();
	
	//% Visual
	boolean Visible(); void Visible_set(boolean v); GfuiElem Visible_on_(); GfuiElem Visible_off_();
	ColorAdp BackColor(); GfuiElem BackColor_(ColorAdp v);
	GfuiBorderMgr Border(); GfuiElem Border_on_(); GfuiElem Border_off_();
	void Redraw();

	//% Text
	GfxStringData TextMgr();
	String Text(); GfuiElem Text_(String v); GfuiElem Text_any_(Object v);
	GfuiElem ForeColor_(ColorAdp v);
	void TextAlignH_(GfuiAlign v);
	GfuiElem TextAlignH_left_(); GfuiElem TextAlignH_right_(); GfuiElem TextAlignH_center_();
	String TipText(); GfuiElem TipText_(String v);

	//% Focus
	boolean Focus_has();
	boolean Focus_able(); GfuiElem Focus_able_(boolean v);
	int Focus_idx(); GfuiElem Focus_idx_(int val);
	String Focus_default(); GfuiElem Focus_default_(String v);
	void Focus();

	//% Action
	void Click();

	//% Elem Tree Hierarchy (Owners And Subs)
	//String Key_of_GfuiElem(); 
	GfuiElem Key_of_GfuiElem_(String val);	
	GfuiElem OwnerElem(); GfuiElem OwnerElem_(GfuiElem val); GfuiElem Owner_(GfuiElem owner); GfuiElem Owner_(GfuiElem owner, String key);
	GfuiElemList SubElems();
	GfuiElem Inject_(InjectAble sub);

	//% Form
	GfuiWin OwnerWin(); GfuiElem OwnerWin_(GfuiWin val);		
	void Opened_cbk(); boolean Opened_done();
	void Dispose();

	//% Infrastructure
	GxwElem UnderElem();
	GxwElem UnderElem_make(Keyval_hash ctorArgs);
	void ctor_GfuiBox_base(Keyval_hash ctorArgs);
	void Invoke(GfoInvkAbleCmd cmd);
}
