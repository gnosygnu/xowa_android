package gplx.xowa.xtns.wdatas.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*; import gplx.xowa.xtns.wdatas.core.*;
abstract class Wdata_doc_parser_fxt_base {
	protected Wdata_doc_parser wdoc_parser;
	private final    Json_parser json_parser = new Json_parser();
	public void Init() {
		if (wdoc_parser == null) wdoc_parser = Make_parser();
	}
	public abstract Wdata_doc_parser Make_parser();
	public Wdata_sitelink_itm Make_sitelink(String site, String name, String... badges) {return new Wdata_sitelink_itm(Bry_.new_u8(site), Bry_.new_u8(name), Bry_.Ary(badges));}
	public Wdata_langtext_itm Make_langval(String lang, String text) {return new Wdata_langtext_itm(Bry_.new_u8(lang), Bry_.new_u8(text));}
	public Wdata_alias_itm Make_alias(String lang, String... vals) {return new Wdata_alias_itm(Bry_.new_u8(lang), Bry_.Ary(vals));}
	public Wdata_claim_itm_core Make_claim_str(int pid, String val) {return new Wdata_claim_itm_str(pid, Wdata_dict_snak_tid.Tid_value, Bry_.new_u8(val));}
	public Wdata_claim_itm_core Make_claim_entity_qid(int pid, int entityId) {return new Wdata_claim_itm_entity(pid, Wdata_dict_snak_tid.Tid_value, Wdata_dict_value_entity_tid.Tid_item, Int_.To_bry(entityId));}
	public Wdata_claim_itm_core Make_claim_entity_pid(int pid, int entityId) {return new Wdata_claim_itm_entity(pid, Wdata_dict_snak_tid.Tid_value, Wdata_dict_value_entity_tid.Tid_property, Int_.To_bry(entityId));}
	public Wdata_claim_itm_core Make_claim_monolingualtext(int pid, String lang, String text) {return new Wdata_claim_itm_monolingualtext(pid, Wdata_dict_snak_tid.Tid_value, Bry_.new_u8(lang), Bry_.new_u8(text));}
	public Wdata_claim_itm_core Make_claim_globecoordinate(int pid, String lat, String lng, String prc) {return new Wdata_claim_itm_globecoordinate(pid, Wdata_dict_snak_tid.Tid_value, Bry_.new_u8(lat), Bry_.new_u8(lng), Object_.Bry__null, Bry_.new_u8(prc), Bry_.new_a7("http://www.wikidata.org/entity/Q2"));}
	public Wdata_claim_itm_core Make_claim_quantity(int pid, int val, int unit, int ubound, int lbound) {return new Wdata_claim_itm_quantity(pid, Wdata_dict_snak_tid.Tid_value, Bry_.new_u8(Int_.To_str(val)), Bry_.new_u8(Int_.To_str(unit)), Bry_.new_u8(Int_.To_str(ubound)), Bry_.new_u8(Int_.To_str(lbound)));}
	public Wdata_claim_itm_core Make_claim_time(int pid, String val) {return new Wdata_claim_itm_time(pid, Wdata_dict_snak_tid.Tid_value, Wdata_dict_value_time.Xto_time(val), Wdata_dict_value_time.Val_timezone_bry, Wdata_dict_value_time.Val_before_bry, Wdata_dict_value_time.Val_after_bry, Wdata_dict_value_time.Val_precision_bry, Wdata_dict_value_time.Val_calendarmodel_bry);}
	public Wdata_claim_itm_core Make_claim_novalue(int pid) {return new Wdata_claim_itm_system(pid, Wdata_dict_val_tid.Tid_unknown, Wdata_dict_snak_tid.Tid_novalue);}
	public void Test_entity(String raw, String expd)		{Tfds.Eq(expd, String_.new_u8(wdoc_parser.Parse_qid(json_parser.Parse_by_apos(raw))));}
	public void Test_sitelinks(String raw, Wdata_sitelink_itm... expd) {
		Ordered_hash actl_hash = wdoc_parser.Parse_sitelinks(Q1_bry, json_parser.Parse_by_apos(raw));
		Tfds.Eq_ary_str((Wdata_sitelink_itm[])actl_hash.To_ary(Wdata_sitelink_itm.class), expd);
	}
	public void Test_labels(String raw, Wdata_langtext_itm... expd)		{Test_langvals(raw, Bool_.Y, expd);}
	public void Test_descriptions(String raw, Wdata_langtext_itm... expd)	{Test_langvals(raw, Bool_.N, expd);}
	private void Test_langvals(String raw, boolean labels_or_descriptions, Wdata_langtext_itm... expd) {
		Ordered_hash actl_hash = wdoc_parser.Parse_langvals(Q1_bry, json_parser.Parse_by_apos(raw), labels_or_descriptions);
		Tfds.Eq_ary_str((Wdata_langtext_itm[])actl_hash.To_ary(Wdata_langtext_itm.class), expd);
	}
	public void Test_aliases(String raw, Wdata_alias_itm... expd) {
		Ordered_hash actl_hash = wdoc_parser.Parse_aliases(Q1_bry, json_parser.Parse_by_apos(raw));
		Tfds.Eq_ary_str((Wdata_alias_itm[])actl_hash.To_ary(Wdata_alias_itm.class), expd);
	}
	public void Test_claims(String raw, Wdata_claim_itm_core... expd) {
		Ordered_hash actl_hash = wdoc_parser.Parse_claims(Q1_bry, json_parser.Parse_by_apos(raw));
		List_adp actl_list = Wdata_claim_grp.Xto_list(actl_hash);
		Tfds.Eq_ary_str((Wdata_claim_itm_core[])actl_list.To_ary(Wdata_claim_itm_core.class), expd);
	}
	public void Test_claims_data(String raw, Wdata_claim_itm_core expd) {
		Json_doc jdoc = json_parser.Parse_by_apos(raw);
		Wdata_claim_itm_base actl = wdoc_parser.Parse_claims_data(Q1_bry, 1, Wdata_dict_snak_tid.Tid_value, jdoc.Root_nde());
		Tfds.Eq(expd.toString(), actl.toString());
	}
	public void Test_qualifiers(String raw, Wdata_claim_itm_base... expd_itms) {
		Json_doc jdoc = json_parser.Parse_by_apos(raw);
		Json_nde qualifiers_nde = Json_nde.cast(Json_kv.cast(jdoc.Root_nde().Get_at(0)).Val());
		Wdata_claim_grp_list actl = wdoc_parser.Parse_qualifiers(Q1_bry, qualifiers_nde);
		Tfds.Eq_ary_str(expd_itms, To_ary(actl));
	}
	public void Test_references(String raw, int[] expd_order, Wdata_claim_itm_base... expd_itms) {
		Json_doc jdoc = json_parser.Parse_by_apos(raw);
		Json_ary owner = Json_ary.cast_or_null(Json_kv.cast(jdoc.Root_nde().Get_at(0)).Val());
		Wdata_references_grp[] actl = wdoc_parser.Parse_references(Q1_bry, owner);
		Wdata_references_grp actl_grp = actl[0];
		Tfds.Eq_ary(expd_order, actl_grp.References_order());
		Tfds.Eq_ary_str(expd_itms, To_ary(actl_grp.References()));
	}
	public void Test_pid_order(String raw, int... expd) {
		Json_doc jdoc = json_parser.Parse_by_apos(raw);
		Json_ary nde = Json_ary.cast_or_null(Json_kv.cast(jdoc.Root_nde().Get_at(0)).Val());
		int[] actl = wdoc_parser.Parse_pid_order(Q1_bry, nde);
		Tfds.Eq_ary(expd, actl);
	}
	Wdata_claim_itm_base[] To_ary(Wdata_claim_grp_list list) {
		List_adp rv = List_adp_.New();
		int list_len = list.Len();
		for (int i = 0; i < list_len; ++i) {
			Wdata_claim_grp grp = list.Get_at(i);
			int grp_len = grp.Len();
			for (int j = 0; j < grp_len; ++j) {
				Wdata_claim_itm_core itm = grp.Get_at(j);
				rv.Add(itm);
			}
		}
		return (Wdata_claim_itm_base[])rv.To_ary_and_clear(Wdata_claim_itm_base.class);
	}
	private static final    byte[] Q1_bry = Bry_.new_a7("Q1");
}
class Wdata_doc_parser_v2_fxt extends Wdata_doc_parser_fxt_base {
	@Override public Wdata_doc_parser Make_parser() {return new Wdata_doc_parser_v2();}
}
