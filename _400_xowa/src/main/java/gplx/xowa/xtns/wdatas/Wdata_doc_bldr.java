package gplx.xowa.xtns.wdatas; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.primitives.*;
import gplx.langs.jsons.*; import gplx.xowa.xtns.wdatas.core.*; import gplx.xowa.xtns.wdatas.parsers.*;
public class Wdata_doc_bldr {
	private Ordered_hash descr_list, label_list, slink_list, alias_list, claim_list;
	public Wdata_doc_bldr() {this.Init();}
	public Wdata_doc_bldr Qid_(String v) {this.qid = Bry_.new_a7(v); return this;} private byte[] qid;
	public Wdata_doc_bldr Add_claims(Wdata_claim_itm_core... ary) {
		if (ary.length == 0) throw Err_.new_wo_type("claims must be greater than 0");
		Wdata_claim_itm_core itm = ary[0];
		Wdata_claim_grp grp = new Wdata_claim_grp(Int_obj_ref.New(itm.Pid()), ary);
		claim_list.Add(grp.Id_ref(), grp);
		return this;
	}		
	public Wdata_doc_bldr Add_description(String lang, String text) {byte[] key = Bry_.new_u8(lang); descr_list.Add(key, new Wdata_langtext_itm(key, Bry_.new_u8(text))); return this;}
	public Wdata_doc_bldr Add_label(String lang, String text)		{byte[] key = Bry_.new_u8(lang); label_list.Add(key, new Wdata_langtext_itm(key, Bry_.new_u8(text))); return this;}
	public Wdata_doc_bldr Add_sitelink(String site, String link, String... bdgs) {
		byte[] key = Bry_.new_u8(site); slink_list.Add(key, new Wdata_sitelink_itm(key, Bry_.new_u8(link), Bry_.Ary(bdgs)));
		return this;
	}
	public Wdata_doc_bldr Add_alias(String lang, String... ary){byte[] key = Bry_.new_u8(lang); alias_list.Add(key, new Wdata_alias_itm	  (key, Bry_.Ary(ary))); return this;}
	public Wdata_doc Xto_wdoc() {
		Wdata_doc rv = new Wdata_doc(qid, slink_list, label_list, descr_list, alias_list, claim_list);
		this.Init();
		return rv;
	}
	private void Init() {
		descr_list = Ordered_hash_.New_bry(); label_list = Ordered_hash_.New_bry(); slink_list = Ordered_hash_.New_bry(); alias_list = Ordered_hash_.New_bry(); claim_list = Ordered_hash_.New();
	}
}
