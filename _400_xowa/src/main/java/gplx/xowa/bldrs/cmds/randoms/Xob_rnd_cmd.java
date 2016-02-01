package gplx.xowa.bldrs.cmds.randoms; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
public class Xob_rnd_cmd implements Xob_cmd {
	// private final Xob_bldr bldr; private final Xowe_wiki wiki;
	public Xob_rnd_cmd(Xob_bldr bldr, Xowe_wiki wiki) {}//this.bldr = bldr; this.wiki = wiki;}
	public String Cmd_key()		{return Xob_cmd_keys.Key_util_random;}
	public void Cmd_run() {
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}

	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_end() {}
	public void Cmd_term() {}
}
