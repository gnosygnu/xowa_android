package gplx.xowa.apps.apis.xowa.bldrs.runners; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.bldrs.*;
public class Xoapi_runner implements Gfo_invk {
	// private Xoa_app app;
	public void Ctor_by_app(Xoa_app app) {}//this.app = app;}
	private void Exec(GfoMsg msg) {
//			int len = msg.Args_count();
//			String cmd = (String)msg.Args_getAt(0).Val();
//			Keyval[] args = new Keyval[len - 1];
//			for (int i = 1; i < len; ++i) {
//				String arg = (String)msg.Args_getAt(i).Val();
//				int eq_pos = String_.FindFwd(arg, "=");
//				String key = String_.Mid(arg, 0, eq_pos);
//				String val = String_.Mid(arg, eq_pos + 1);
//				args[i - 1] = Keyval_.new_(key, val);
//			}
//			gplx.core.ios.zips.Io_zip_decompress_task task = new gplx.core.ios.zips.Io_zip_decompress_task();
		// task.Init(true, Gfo
		// app.Gui__cbk_mgr().Send_prog("test", "key_0", "val_0");
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_exec)) 						Exec(m);
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_exec = "exec";
}
//	class Xodl_prog_ui : gplx.core.progs.Gfo_prog_ui {
//		public void Prog__update_val(long cur, long max) {}
//		public void Prog__end() {}
//	}
