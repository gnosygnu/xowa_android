package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
import gplx.xowa.guis.views.*;
public class Xocfg_tab_btn_mgr implements GfoInvkAble, GfoEvMgrOwner {
	public Xocfg_tab_btn_mgr() {
		evMgr = GfoEvMgr.new_(this);
	}
	public GfoEvMgr EvMgr() {return evMgr;} private GfoEvMgr evMgr;
	public int Height() {return height;} private int height = 20;
	public boolean Place_on_top() {return place_on_top;} private boolean place_on_top = true;
	public boolean Curved() {return curved;} private boolean curved = false;
	public boolean Close_visible() {return close_visible;} private boolean close_visible = true;
	public boolean Unselected_close_visible() {return unselected_close_visible;} private boolean unselected_close_visible = true;
	public int Text_min_chars() {return text_min_chars;} public Xocfg_tab_btn_mgr Text_min_chars_(int v) {text_min_chars = v; return this;} private int text_min_chars = -1;
	public int Text_max_chars() {return text_max_chars;} public Xocfg_tab_btn_mgr Text_max_chars_(int v) {text_max_chars = v; return this;} private int text_max_chars = 40;
	public boolean Hide_if_one() {return hide_if_one;} public Xocfg_tab_btn_mgr Hide_if_one_(boolean v) {hide_if_one = v; return this;} private boolean hide_if_one;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_place_on_top))					return Yn.To_str(place_on_top);
		else if	(ctx.Match(k, Invk_place_on_top_))					{place_on_top = m.ReadYn("v"); GfoEvMgr_.PubVal(this, Evt_place_on_top_changed, place_on_top);}
		else if	(ctx.Match(k, Invk_curved))							return Yn.To_str(curved);
		else if	(ctx.Match(k, Invk_curved_))						{curved = m.ReadYn("v"); GfoEvMgr_.PubVal(this, Evt_curved_changed, curved);}
		else if	(ctx.Match(k, Invk_height))							return height;
		else if	(ctx.Match(k, Invk_height_))						{height = m.ReadInt("v"); GfoEvMgr_.PubVal(this, Evt_height_changed, height);}
		else if	(ctx.Match(k, Invk_close_visible))					return Yn.To_str(close_visible);
		else if	(ctx.Match(k, Invk_close_visible_))					{close_visible = m.ReadYn("v"); GfoEvMgr_.PubVal(this, Evt_close_visible_changed, close_visible);}
		else if	(ctx.Match(k, Invk_unselected_close_visible))		return Yn.To_str(unselected_close_visible);
		else if	(ctx.Match(k, Invk_unselected_close_visible_))		{unselected_close_visible = m.ReadYn("v"); GfoEvMgr_.PubVal(this, Evt_unselected_close_visible_changed, unselected_close_visible);}
		else if	(ctx.Match(k, Invk_text_min_chars))					return text_min_chars;
		else if	(ctx.Match(k, Invk_text_min_chars_))				{text_min_chars = m.ReadInt("v"); GfoEvMgr_.PubVal(this, Evt_text_min_chars_changed, text_min_chars);}
		else if	(ctx.Match(k, Invk_text_max_chars))					return text_max_chars;
		else if	(ctx.Match(k, Invk_text_max_chars_))				{text_max_chars = m.ReadInt("v"); GfoEvMgr_.PubVal(this, Evt_text_max_chars_changed, text_max_chars);}
		else if	(ctx.Match(k, Invk_hide_if_one))					return Yn.To_str(hide_if_one);
		else if	(ctx.Match(k, Invk_hide_if_one_))					{hide_if_one = m.ReadYn("v"); GfoEvMgr_.PubVal(this, Evt_hide_if_one_changed, hide_if_one);}
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk_place_on_top = "place_on_top", Invk_place_on_top_ = "place_on_top_"
	, Invk_curved = "curved", Invk_curved_ = "curved_"
	, Invk_height = "height", Invk_height_ = "height_"
	, Invk_close_visible = "close_visible", Invk_close_visible_ = "close_visible_"
	, Invk_unselected_close_visible = "unselected_close_visible", Invk_unselected_close_visible_ = "unselected_close_visible_"
	, Invk_text_min_chars = "text_min_chars", Invk_text_min_chars_ = "text_min_chars_"
	, Invk_text_max_chars = "text_max_chars", Invk_text_max_chars_ = "text_max_chars_"
	, Invk_hide_if_one = "hide_if_one", Invk_hide_if_one_ = "hide_if_one_"
	;
	public static final String 
	  Evt_place_on_top_changed = "place_on_top_changed", Evt_curved_changed = "curved_changed", Evt_height_changed = "height_changed"
	, Evt_close_visible_changed = "close_visible_changed", Evt_unselected_close_visible_changed = "unselected_close_visible_changed"
	, Evt_text_min_chars_changed = "text_min_chars_changed", Evt_text_max_chars_changed = "text_max_chars_changed"
	, Evt_hide_if_one_changed = "hide_if_one_changed"
	;
}
