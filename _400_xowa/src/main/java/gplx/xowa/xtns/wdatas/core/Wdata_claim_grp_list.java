package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_claim_grp_list {
	private Ordered_hash hash = Ordered_hash_.New();
	public void Add(Wdata_claim_grp itm) {hash.Add(itm.Id_ref(), itm);}
	public int Len() {return hash.Count();}
	public Wdata_claim_grp Get_at(int i) {return (Wdata_claim_grp)hash.Get_at(i);}
}
