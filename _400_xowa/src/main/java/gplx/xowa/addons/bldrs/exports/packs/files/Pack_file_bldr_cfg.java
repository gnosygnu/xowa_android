package gplx.xowa.addons.bldrs.exports.packs.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Pack_file_bldr_cfg implements Gfo_invk {
	public Io_url Deploy_dir() {return deploy_dir;} private Io_url deploy_dir;
	public boolean Pack_html() {return pack_html;} private boolean pack_html = true;
	public boolean Pack_file() {return pack_file;} private boolean pack_file = true;
	public DateAdp Pack_file_cutoff() {return pack_file_cutoff;} private DateAdp pack_file_cutoff = null;

	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__deploy_dir_))			deploy_dir = m.ReadIoUrl("v");
		else if	(ctx.Match(k, Invk__pack_html_))			pack_html = m.ReadYn("v");
		else if	(ctx.Match(k, Invk__pack_file_))			pack_file = m.ReadYn("v");
		else if	(ctx.Match(k, Invk__pack_file_cutoff_))		pack_file_cutoff = m.ReadDate("v");
		else												return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk__deploy_dir_ = "deploy_dir_"
	, Invk__pack_html_ = "pack_html_", Invk__pack_file_ = "pack_file_", Invk__pack_file_cutoff_ = "pack_file_cutoff_"
	;
}
