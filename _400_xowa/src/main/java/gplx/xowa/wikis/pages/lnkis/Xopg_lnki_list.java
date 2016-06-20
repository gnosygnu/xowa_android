package gplx.xowa.wikis.pages.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.parsers.lnkis.*;
public class Xopg_lnki_list {
	private final    List_adp list = List_adp_.New();
	private int lnki_idx;
	public Xopg_lnki_list(boolean ttl_is_module) {			// never redlink in Module ns; particularly since Lua has multi-line comments for [[ ]]
		this.disabled = ttl_is_module;
		this.Clear();
	}
	public boolean			Disabled() {return disabled;} private final    boolean disabled;
	public int				Len() {return list.Len();}
	public Xopg_lnki_itm	Get_at(int i) {return (Xop_lnki_tkn)list.Get_at(i);}
	public void	Add(Xopg_lnki_itm lnki) {
		if (disabled) return;
		Xoa_ttl ttl = lnki.Ttl(); if (ttl == null) return;		// ttl is null for invalid links
		Xow_ns ns = ttl.Ns();
		lnki.Html_uid_(++lnki_idx);								// NOTE: set html_id in order html to print out "id='xowa_lnki_1'; want to print out id for consistency's sake, even if these links won't be check for redlinks; DATE:2015-05-07
		if (	ns.Id_is_file_or_media()						// ignore files which will usually not be in local wiki (most are in commons), and whose html is built up separately
			||	(ns.Id_is_ctg() && !ttl.ForceLiteralLink())		// ignore ctgs which have their own html builder, unless it is literal; EX: [[:Category:A]]; DATE:2014-02-24
			||	ns.Id_is_special()								// ignore special, especially Search; EX: Special:Search/Earth
			||	ttl.Anch_bgn() == Xoa_ttl.Anch_bgn_anchor_only	// anchor only link; EX: [[#anchor]]
			||	ttl.Wik_itm() != null							// xwiki lnki; EX: simplewiki links in homewiki; [[simplewiki:Earth]]
			)
			return;				
		list.Add(lnki);
	}
	public void	Clear() {
		lnki_idx = gplx.xowa.htmls.core.wkrs.lnkis.htmls.Xoh_lnki_wtr.Lnki_id_min;			// NOTE: must start at 0, so that ++lnki_idx is > 0; html_wtr checks for > 0; DATE:2014-10-09
		list.Clear();
	}

	public static final String Lnki_id_prefix = "xolnki_";
}
