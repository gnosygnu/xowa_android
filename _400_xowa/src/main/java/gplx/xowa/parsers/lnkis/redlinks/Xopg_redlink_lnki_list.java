package gplx.xowa.parsers.lnkis.redlinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.users.*;
public class Xopg_redlink_lnki_list {
	private int lnki_idx = gplx.xowa.htmls.core.wkrs.lnkis.htmls.Xoh_lnki_wtr.Lnki_id_min;	// NOTE: default to 1, not 0, b/c 0 is ignored by wtr; DATE:2014-10-09		
	public Xopg_redlink_lnki_list(boolean ttl_is_module) {			// never redlink in Module ns; particularly since Lua has multi-line comments for [[ ]]
		this.disabled = ttl_is_module;
	}
	public boolean		Disabled() {return disabled;} private final boolean disabled;
	public List_adp	Lnki_list() {return lnki_list;} private final List_adp lnki_list = List_adp_.new_();
	public int		Thread_id() {return thread_id;} private int thread_id = 1;
	public void		Clear() {
		if (disabled) return;
		lnki_idx = gplx.xowa.htmls.core.wkrs.lnkis.htmls.Xoh_lnki_wtr.Lnki_id_min;			// NOTE: must start at 0, so that ++lnki_idx is > 0; html_wtr checks for > 0
		lnki_list.Clear();
		thread_id++;
	}
	public void Lnki_add(Xop_lnki_tkn lnki) {
		if (disabled) return;
		Xoa_ttl ttl = lnki.Ttl(); if (ttl == null) return;		// occurs for invalid links
		Xow_ns ns = ttl.Ns();
		lnki.Html_uid_(++lnki_idx);								// NOTE: set html_id in order html to print out "id='xowa_lnki_1'; want to print out id for consistency's sake, even if these links won't be check for redlinks; DATE:2015-05-07
		if (	ns.Id_is_file_or_media()						// ignore files which will usually not be in local wiki (most are in commons), and whose html is built up separately
			||	(ns.Id_is_ctg() && !ttl.ForceLiteralLink())		// ignore ctgs which have their own html builder, unless it is literal; EX: [[:Category:A]]; DATE:2014-02-24
			||	ns.Id_is_special()								// ignore special, especially Search; EX: Special:Search/Earth
			||	ttl.Anch_bgn() == Xoa_ttl.Anch_bgn_anchor_only	// anchor only link; EX: [[#anchor]]
			||	ttl.Wik_itm() != null							// xwiki lnki; EX: simplewiki links in homewiki; [[simplewiki:Earth]]
			)
			return;				
		lnki_list.Add(lnki);
	}
	public static final String Lnki_id_prefix = "xolnki_";
	public static final int Lnki_id_prefix_len = String_.Len(Lnki_id_prefix);
}
