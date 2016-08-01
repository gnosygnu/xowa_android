package gplx.xowa.xtns.wbases.claims; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.xtns.wbases.claims.itms.*;
public interface Wbase_claim_visitor {
	void Visit_str				(Wbase_claim_string itm);
	void Visit_entity			(Wbase_claim_entity itm);
	void Visit_monolingualtext	(Wbase_claim_monolingualtext itm);
	void Visit_quantity			(Wbase_claim_quantity itm);
	void Visit_time				(Wbase_claim_time itm);
	void Visit_globecoordinate	(Wbase_claim_globecoordinate itm);
	void Visit_system			(Wbase_claim_value itm);
}
