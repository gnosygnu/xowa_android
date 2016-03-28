package gplx.xowa.bldrs.cmds.utils; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.gfui.*;
public class Xob_alert_cmd extends Xob_cmd__base implements Xob_cmd {
	public Xob_alert_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	public Xob_alert_cmd Msg_(String v) {this.msg = v; return this;} private String msg = "developer forgot to include message";
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_util_alert;}
	@Override public void Cmd_run() {
		Gfui_kit kit = app.Gui_mgr().Kit();
		if (kit.Tid() != Gfui_kit_.Swt_tid) return;
		kit.Ask_ok("", "", msg);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return GfoInvkAble_.Rv_unhandled;}
}
