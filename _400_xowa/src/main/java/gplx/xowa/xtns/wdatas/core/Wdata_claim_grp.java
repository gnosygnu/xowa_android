package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.primitives.*;
public class Wdata_claim_grp {
	public Wdata_claim_grp(Int_obj_ref id_ref, Wdata_claim_itm_core[] itms) {this.id_ref = id_ref; this.itms = itms;}
	public Int_obj_ref Id_ref() {return id_ref;} private final    Int_obj_ref id_ref;
	public int Id() {return id_ref.Val();}
	public String Id_str() {if (id_str == null) id_str = "P" + Int_.To_str(id_ref.Val()); return id_str;} private String id_str;
	public int Len() {return itms.length;} private Wdata_claim_itm_core[] itms;
	public Wdata_claim_itm_core Get_at(int i) {return itms[i];}
	public static List_adp Xto_list(Ordered_hash hash) {
		int len = hash.Count();
		List_adp rv = List_adp_.New();
		for (int i = 0; i < len; ++i) {
			Wdata_claim_grp grp = (Wdata_claim_grp)hash.Get_at(i);
			int grp_len = grp.Len();
			for (int j = 0; j < grp_len; ++j)
				rv.Add(grp.Get_at(j));
		}
		return rv;
	}
}
