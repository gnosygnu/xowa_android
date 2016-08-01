package gplx.xowa.xtns.wbases.claims; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
public class Wbase_references_grp {
	public Wbase_references_grp(Wbase_claim_grp_list references, int[] references_order) {this.references = references; this.references_order = references_order;}
	public Wbase_claim_grp_list References() {return references;} private Wbase_claim_grp_list references;
	public int[] References_order() {return references_order;} private int[] references_order;
}
