package gplx.gfui; import gplx.*;
public class Gfui_mnu_grp_ {
	public static final Gfui_mnu_grp Noop = new Gfui_mnu_grp_noop();
}
class Gfui_mnu_grp_noop implements Gfui_mnu_grp {
	public String Uid() {return "";}
	public int Tid() {return Gfui_mnu_itm_.Tid_grp;}
	public boolean Enabled() {return true;} public void Enabled_(boolean v) {}
	public boolean Disposed() {return false;}
	public String Text() {return null;} public void Text_(String v) {}
	public ImageAdp Img() {return null;} public void Img_(ImageAdp v) {}
	public boolean Selected() {return true;} public void Selected_(boolean v) {}
	public String Root_key() {return "null";}
	public Object Under() {return null;}
	public void Itms_clear() {}
	public Gfui_mnu_itm Itms_add_btn_cmd	(String txt, ImageAdp img, GfoInvkAble invk, String invk_cmd) {return Gfui_mnu_itm_null.Null;}
	public Gfui_mnu_itm Itms_add_btn_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg invk_msg) {return Gfui_mnu_itm_null.Null;}
	public Gfui_mnu_itm Itms_add_chk_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg msg_n, GfoMsg msg_y) {return Gfui_mnu_itm_null.Null;}
	public Gfui_mnu_itm Itms_add_rdo_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg msg) {return Gfui_mnu_itm_null.Null;}
	public Gfui_mnu_grp Itms_add_grp(String txt, ImageAdp img) {return Gfui_mnu_grp_.Noop;}
	public Gfui_mnu_itm Itms_add_separator() {return Gfui_mnu_itm_null.Null;}
}