package gplx.xowa.specials.xowa.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.*;
import gplx.xowa.specials.*; import gplx.core.ios.*; import gplx.core.net.*;
public class Xosp_fbrow_special implements Xows_page {
	private static final Xoa_url_arg_mgr url_args = new Xoa_url_arg_mgr(null);
	public Xows_special_meta Special_meta() {return Xows_special_meta_.Itm__file_browser;}
	public void Special_gen(Xowe_wiki wiki, Xoae_page page, Xoa_url url, Xoa_ttl ttl) {
		Xosp_fbrow_rslt rslt = Gen(url.Qargs_ary(), GfoInvkAble_.Null);
		page.Html_data().Html_restricted_n_();
		page.Html_data().Custom_head_end_concat(rslt.Html_head());
		page.Data_raw_(rslt.Html_body());
	}
	public static Xosp_fbrow_rslt Gen(Gfo_qarg_itm[] args, GfoInvkAble select_invkable) {
		url_args.Init(args);
		byte[] cmd_bry = url_args.Read_bry_or_empty(Arg_cmd);
		Xosp_fbrow_cmd cmd = (Xosp_fbrow_cmd)cmd_regy.Get_by_bry(cmd_bry); if (cmd == null) cmd = Xosp_fbrow_cmd__err.Instance;
		return cmd.Make_new().Write_html(url_args, select_invkable);
	}
	private static final byte[] Arg_cmd = Bry_.new_a7("cmd");
	private static final Hash_adp_bry cmd_regy = Hash_adp_bry.cs()
	.Add_bry_obj(Xosp_fbrow_cmd__wiki_add.Regy_key, Xosp_fbrow_cmd__wiki_add.Instance)
	.Add_bry_obj(Xosp_fbrow_cmd__root_set.Regy_key, Xosp_fbrow_cmd__root_set.Instance)
	;
}
