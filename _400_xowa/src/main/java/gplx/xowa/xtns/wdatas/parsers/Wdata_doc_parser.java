package gplx.xowa.xtns.wdatas.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*; import gplx.xowa.xtns.wdatas.core.*;
public interface Wdata_doc_parser {
	byte[] Parse_qid(Json_doc doc);
	Ordered_hash Parse_sitelinks(byte[] qid, Json_doc doc);
	Ordered_hash Parse_langvals(byte[] qid, Json_doc doc, boolean label_or_description);
	Ordered_hash Parse_aliases(byte[] qid, Json_doc doc);
	Ordered_hash Parse_claims(byte[] qid, Json_doc doc);
	Wdata_claim_itm_base Parse_claims_data(byte[] qid, int pid, byte snak_tid, Json_nde nde);
	Wdata_claim_grp_list Parse_qualifiers(byte[] qid, Json_nde nde);
	int[] Parse_pid_order(byte[] qid, Json_ary ary);
	Wdata_references_grp[] Parse_references(byte[] qid, Json_ary owner);
}
