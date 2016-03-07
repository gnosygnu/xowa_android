package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.gfui.*; import gplx.xowa.guis.bnds.*; import gplx.xowa.guis.cmds.*;
import gplx.xowa.guis.langs.*;
public class Xog_win_itm_ {
	public static void Show_win(Xog_win_itm win) {
		Xoae_app app = win.App(); GfuiWin win_box = win.Win_box();			

		win_box.Focus_able_(false);
		win_box.BackColor_(ColorAdp_.White);
		win.Tab_mgr().Tab_mgr().BackColor_(ColorAdp_.White);

		app.Usere().Cfg_mgr().Startup_mgr().Window_mgr().Init_window(win_box);
		win.Resizer().Exec_win_resize(app, win_box.Width(), win_box.Height());

		win_box.Icon_(IconAdp.file_or_blank(app.Fsys_mgr().Bin_xowa_dir().GenSubFil_nest("file", "app.window", "app_icon.png")));
	}
	public static GfuiBtn new_btn(Xoae_app app, Gfui_kit kit, GfuiWin win, Io_url img_dir, String id, String file) {
		GfuiBtn rv = kit.New_btn(id, win);
		rv.Btn_img_(kit.New_img_load(img_dir.GenSubFil(file)));
		return rv;
	}
	public static GfuiTextBox new_txt(Xoae_app app, Gfui_kit kit, GfuiWin win, FontAdp ui_font, String id, boolean border_on) {
		GfuiTextBox rv = kit.New_text_box(id, win, Keyval_.new_(GfuiTextBox.CFG_border_on_, border_on));
		rv.TextMgr().Font_(ui_font);
		return rv;
	}
	public static void Update_tiptext(Xoae_app app, GfuiElem elem, int tiptext_id) {
		elem.TipText_(Xog_win_itm_.new_tiptext(app, tiptext_id));
	}
	public static void Font_update(Xog_win_itm win, Xol_font_info itm_font) {
		FontAdp gui_font = win.Url_box().TextMgr().Font();
		if (!itm_font.Eq(gui_font)) {
			FontAdp new_font = itm_font.XtoFontAdp();
			win.Url_box().TextMgr().Font_(new_font);
			win.Find_box().TextMgr().Font_(new_font);
			win.Prog_box().TextMgr().Font_(new_font);
			win.Tab_mgr().Tab_mgr().TextMgr().Font_(new_font);
		}
	}
	public static String new_tiptext(Xoae_app app, int id) {return String_.new_u8(app.Usere().Lang().Msg_mgr().Val_by_id(app.Usere().Wiki(), id));}
}
