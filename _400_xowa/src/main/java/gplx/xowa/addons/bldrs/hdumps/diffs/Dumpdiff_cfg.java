package gplx.xowa.addons.bldrs.hdumps.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.hdumps.*;
class Dumpdiff_cfg implements Gfo_invk {
	public Io_url Prv_dir() {return prv_dir;} private Io_url prv_dir;
	public Io_url Cur_dir() {return cur_dir;} private Io_url cur_dir;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__cur_dir_))			this.cur_dir = m.ReadIoUrl("v");
		else if	(ctx.Match(k, Invk__prv_dir_))			this.prv_dir = m.ReadIoUrl("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}		
	public static final String Invk__cur_dir_ = "cur_dir_", Invk__prv_dir_ = "prv_dir_";
}
