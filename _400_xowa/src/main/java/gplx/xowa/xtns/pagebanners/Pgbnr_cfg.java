package gplx.xowa.xtns.pagebanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.primitives.*;
public class Pgbnr_cfg {
	private final    Hash_adp ns_hash = Hash_adp_.New(); private final    Int_obj_ref tmp_ns_key = Int_obj_ref.New_neg1();
	public Pgbnr_cfg(boolean enabled, boolean enable_heading_override, boolean enable_default_banner, int[] ns_ary, int dflt_img_wdata_prop, byte[] dflt_img_title, int[] standard_sizes) {
		this.enabled = enabled; this.enable_heading_override = enable_heading_override; this.enable_default_banner = enable_default_banner;
		this.standard_sizes = standard_sizes;
		this.dflt_img_wdata_prop = dflt_img_wdata_prop; this.dflt_img_title = dflt_img_title;
		for (int ns : ns_ary)
			this.ns_hash.Add_as_key_and_val(Int_obj_ref.New(ns));
	}
	public final    boolean enabled;
	public final    boolean enable_heading_override;
	public final    boolean enable_default_banner;
	public final    int dflt_img_wdata_prop;
	public final    byte[] dflt_img_title;
	public final    int[] standard_sizes;
	public boolean Chk_pgbnr_allowed(Xoa_ttl ttl, Xowe_wiki wiki) {
		boolean enabled_in_ns = ns_hash.Has(tmp_ns_key.Val_(ttl.Ns().Id()));
		return	enabled_in_ns										// chk if ns allows banner
			&&	!Bry_.Eq(ttl.Page_db(), wiki.Props().Main_page()); 	// never show on main page
	}
}
