package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public interface Wdata_claim_visitor {
	void Visit_str(Wdata_claim_itm_str itm);
	void Visit_entity(Wdata_claim_itm_entity itm);
	void Visit_monolingualtext(Wdata_claim_itm_monolingualtext itm);
	void Visit_quantity(Wdata_claim_itm_quantity itm);
	void Visit_time(Wdata_claim_itm_time itm);
	void Visit_globecoordinate(Wdata_claim_itm_globecoordinate itm);
	void Visit_system(Wdata_claim_itm_system itm);
}
