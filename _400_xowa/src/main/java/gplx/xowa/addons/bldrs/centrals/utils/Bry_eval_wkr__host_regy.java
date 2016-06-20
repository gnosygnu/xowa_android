package gplx.xowa.addons.bldrs.centrals.utils; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.brys.evals.*;
import gplx.xowa.wikis.domains.*;
public class Bry_eval_wkr__host_regy implements Bry_eval_wkr {
	private Xow_domain_itm domain_itm;
	public String Key() {return "host_regy";}
	public Bry_eval_wkr__host_regy Domain_itm_(Xow_domain_itm domain_itm) {this.domain_itm = domain_itm; return this;}
	public void Resolve(Bry_bfr rv, byte[] src, int args_bgn, int args_end) {
		// EX: "~{host_regy|wiki_abrv}" -> "enwiki"
		int type = hash.Get_as_byte_or(src, args_bgn, args_end, Byte_.Max_value_127);
		switch (type) {
			case Type__wiki_abrv:		rv.Add(domain_itm.Abrv_wm()); break;
			default:					throw Err_.new_unhandled_default(type);
		}
	}

	public static final byte Type__wiki_abrv = 0;
	private static final    Hash_adp_bry hash = Hash_adp_bry.cs()
	.Add_str_byte("wiki_abrv", Type__wiki_abrv)
	;
}
