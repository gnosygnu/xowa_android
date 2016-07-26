package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.flds.*; import gplx.core.ios.*; import gplx.xowa.wikis.tdbs.*;
import gplx.xowa.bldrs.sqls.*; import gplx.xowa.wikis.tdbs.bldrs.*;
public abstract class Xob_sql_dump_base extends Xob_itm_dump_base implements Xob_cmd, Gfo_invk {
	private final    Sql_file_parser parser = new Sql_file_parser(); protected boolean fail = false;
	public abstract String Cmd_key();
	public Io_url Src_fil() {return src_fil;} private Io_url src_fil;
	public Io_url_gen Make_url_gen() {return make_url_gen;} private Io_url_gen make_url_gen;
	public abstract String Sql_file_name();
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {
		this.Init_dump(this.Cmd_key());
		make_url_gen = Io_url_gen_.dir_(temp_dir.GenSubDir("make"));
		if (src_fil == null) {
			src_fil = Xob_io_utl_.Find_nth_by_wildcard_or_null(wiki.Fsys_mgr().Root_dir(), Sql_file_name() + ".sql", ".gz", ".sql");
			if (src_fil == null) {
				String msg = String_.Format(".sql file not found in dir.\nPlease download the file for your wiki from dumps.wikimedia.org.\nfile={0} dir={1}", Sql_file_name(), wiki.Fsys_mgr().Root_dir());
				app.Usr_dlg().Warn_many("", "", msg);
				app.Gui_mgr().Kit().Ask_ok("", "", msg);
				fail = true;
				return;
			}
		}
		parser.Src_fil_(src_fil).Trg_fil_gen_(dump_url_gen);
		Cmd_bgn_hook(bldr, parser);
	}	protected Gfo_fld_wtr fld_wtr = Gfo_fld_wtr.xowa_();
	public abstract void Cmd_bgn_hook(Xob_bldr bldr, Sql_file_parser parser);
	public void Cmd_run() {
		if (fail) return;
		parser.Parse(bldr.Usr_dlg());
	}
	@gplx.Virtual public void Cmd_end() {
		if (fail) return;
		Xobdc_merger.Basic(bldr.Usr_dlg(), dump_url_gen, temp_dir.GenSubDir("sort"), sort_mem_len, Io_line_rdr_key_gen_all.Instance, new Io_sort_fil_basic(bldr.Usr_dlg(), make_url_gen, make_fil_len));
	}
	public void Cmd_term() {}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_src_fil_))			src_fil = m.ReadIoUrl("v");
		else	return super.Invk(ctx, ikey, k, m);
		return this;
	}
	public static final String Invk_src_fil_ = "src_fil_";
}
