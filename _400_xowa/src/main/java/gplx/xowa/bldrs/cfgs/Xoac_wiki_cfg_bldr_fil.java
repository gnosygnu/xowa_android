package gplx.xowa.bldrs.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public class Xoac_wiki_cfg_bldr_fil implements GfoInvkAble {
	public Xoac_wiki_cfg_bldr_fil(String wiki) {this.wiki = wiki;}
	public String Wiki() {return wiki;} private String wiki;
	public int Itms_count() {return list.Count();}
	public Xoac_wiki_cfg_bldr_cmd Itms_get_at(int i) {return (Xoac_wiki_cfg_bldr_cmd)list.Get_at(i);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_new_cmd_)) 		{Itms_add(m.ReadStr("id"), m.ReadStr("text"));}
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_new_cmd_ = "new_cmd_";
	public Xoac_wiki_cfg_bldr_cmd Itms_add(String key, String text) {
		Xoac_wiki_cfg_bldr_cmd rv = new Xoac_wiki_cfg_bldr_cmd(key, text);
		list.Add(rv);
		return rv;
	}
	List_adp list = List_adp_.new_();
}
