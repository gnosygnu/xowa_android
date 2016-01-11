package gplx.gfui; import gplx.*;
public interface Gfui_mnu_grp extends Gfui_mnu_itm {
	String Root_key();
	void Itms_clear();
	boolean Disposed();
	Gfui_mnu_itm Itms_add_btn_cmd	(String txt, ImageAdp img, GfoInvkAble invk, String invk_cmd);
	Gfui_mnu_itm Itms_add_btn_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg msg);
	Gfui_mnu_itm Itms_add_chk_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg msg_n, GfoMsg msg_y);
	Gfui_mnu_itm Itms_add_rdo_msg	(String txt, ImageAdp img, GfoInvkAble invk, GfoInvkRootWkr root_wkr, GfoMsg msg);
	Gfui_mnu_grp Itms_add_grp		(String txt, ImageAdp img);
	Gfui_mnu_itm Itms_add_separator();
}
