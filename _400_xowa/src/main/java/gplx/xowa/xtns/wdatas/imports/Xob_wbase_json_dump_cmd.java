package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xob_wbase_json_dump_cmd implements Xob_cmd {
	private final    Xob_wbase_json_dump_parser json_dump_parser;
	private Io_url src_fil;
	public Xob_wbase_json_dump_cmd(Xob_bldr bldr, Xowe_wiki wiki) {
		this.json_dump_parser = new Xob_wbase_json_dump_parser(bldr, wiki);
	}
	public String Cmd_key() {return Xob_cmd_keys.Key_wbase_json_dump;}
	public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	public void Cmd_run() {json_dump_parser.Parse(src_fil);}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_end() {}
	public void Cmd_term() {}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_src_fil_))		this.src_fil = m.ReadIoUrl("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_src_fil_ = "src_fil_";
}
