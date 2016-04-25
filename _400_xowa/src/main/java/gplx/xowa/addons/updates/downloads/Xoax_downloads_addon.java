package gplx.xowa.addons.updates.downloads; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.updates.*;
import gplx.xowa.specials.*;
public class Xoax_downloads_addon implements Xoax_addon_itm, Xoax_addon_itm__special {
	public Xows_page[] Pages_ary() {
		return new Xows_page[]
		{ Xodl_special_page.Prototype
		};
	}

	public static final    byte[] ADDON_KEY = Bry_.new_a7("xowa.imports.downloads");
	public byte[] Addon__key()	{return ADDON_KEY;}
}
//	class Xodl_core_regy {
//		public void Update() {}		// update from http
//		public void Load() {}		// load bin/
//	}
//	class Xodl_user_regy {
//		public void Load() {}
//		public void Apply() {}		// mark already downloaded items
//	}
//	class Xodl_main_mgr {
//		public void Show_ui() {}	// show list for ui
//		public void Process() {}	// process choices
//	}
