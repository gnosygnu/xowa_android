package gplx.xowa.xtns.wbases.claims; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
public class Wbase_claim_grp_list {
	private Ordered_hash hash = Ordered_hash_.New();
	public void Add(Wbase_claim_grp itm) {hash.Add(itm.Id_ref(), itm);}
	public int Len() {return hash.Count();}
	public Wbase_claim_grp Get_at(int i) {return (Wbase_claim_grp)hash.Get_at(i);}
}
