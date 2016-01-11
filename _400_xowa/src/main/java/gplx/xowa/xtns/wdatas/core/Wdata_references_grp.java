package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_references_grp {
	public Wdata_references_grp(Wdata_claim_grp_list references, int[] references_order) {this.references = references; this.references_order = references_order;}
	public Wdata_claim_grp_list References() {return references;} private Wdata_claim_grp_list references;
	public int[] References_order() {return references_order;} private int[] references_order;
}
