package gplx.xowa.xtns.wbases.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.core.primitives.*;
import gplx.langs.jsons.*; import gplx.xowa.xtns.wbases.core.*; import gplx.xowa.xtns.wbases.claims.*; import gplx.xowa.xtns.wbases.claims.enums.*; import gplx.xowa.xtns.wbases.claims.itms.*;
class Wdata_claims_parser_v2 {
	private final    Wbase_claim_factory factory = new Wbase_claim_factory();
	public void Make_claim_itms(byte[] qid, List_adp claim_itms_list, byte[] src, Json_kv claim_grp) {
		Json_ary claim_itms_ary = Json_ary.cast_or_null(claim_grp.Val());
		int claim_itms_len = claim_itms_ary.Len();
		int pid = Parse_pid(claim_grp.Key().Data_bry());
		for (int i = 0; i < claim_itms_len; ++i) {
			Json_nde claim_itm_nde = Json_nde.cast(claim_itms_ary.Get_at(i));
			Wbase_claim_base itm = Parse_claim_itm(qid, claim_itm_nde, pid);
			if (itm != null)	// HACK: itm can be null if value is "somevalue"; DATE:2014-09-20
				claim_itms_list.Add(itm);
		}
	}
	private Wbase_claim_base Parse_claim_itm(byte[] qid, Json_nde nde, int pid) {
		int len = nde.Len();
		Hash_adp_bry dict = Wdata_dict_claim.Dict;
		byte rank_tid = Wbase_claim_rank_.Tid__unknown;
		Wbase_claim_base claim_itm = null; Wbase_claim_grp_list qualifiers = null; int[] qualifiers_order = null; Wbase_references_grp[] snaks_grp = null;
		for (int i = 0; i < len; ++i) {
			Json_kv sub = Json_kv.cast(nde.Get_at(i));
			byte tid = Wdata_dict_utl.Get_tid_or_invalid(qid, dict, sub.Key().Data_bry()); if (tid == Wbase_claim_enum_.Tid__invalid) continue;
			switch (tid) {
				case Wdata_dict_claim.Tid_mainsnak:			claim_itm = Parse_mainsnak(qid, Json_nde.cast(sub.Val()), pid); break;
				case Wdata_dict_claim.Tid_rank:				rank_tid = Wbase_claim_rank_.To_tid_or_unknown(sub.Val().Data_bry()); break;
				case Wdata_dict_claim.Tid_references:		snaks_grp = Parse_references(qid, Json_ary.cast_or_null(sub.Val())); break;
				case Wdata_dict_claim.Tid_qualifiers:		qualifiers = Parse_qualifiers(qid, Json_nde.cast(sub.Val())); break;
				case Wdata_dict_claim.Tid_qualifiers_order:	qualifiers_order = Parse_pid_order(Json_ary.cast_or_null(sub.Val())); break;
				case Wdata_dict_claim.Tid_type:				break;		// ignore: "statement"
				case Wdata_dict_claim.Tid_id:				break;		// ignore: "Q2$F909BD1C-D34D-423F-9ED2-3493663321AF"
			}
		}
		if (claim_itm != null) {
			claim_itm.Rank_tid_(rank_tid);
			if (qualifiers != null)			claim_itm.Qualifiers_(qualifiers);
			if (qualifiers_order != null)	claim_itm.Qualifiers_order_(qualifiers_order);
			if (snaks_grp != null)			claim_itm.References_(snaks_grp);
		}
		return claim_itm;
	}
	public Wbase_references_grp[] Parse_references(byte[] qid, Json_ary owner) {
		int len = owner.Len();
		Wbase_references_grp[] rv = new Wbase_references_grp[len];
		for (int i = 0; i < len; ++i) {
			Json_nde grp_nde = Json_nde.cast(owner.Get_at(i));
			rv[i] = Parse_references_grp(qid, grp_nde);
		}
		return rv;
	}
	private Wbase_references_grp Parse_references_grp(byte[] qid, Json_nde owner) {
		int len = owner.Len();
		Hash_adp_bry dict = Wdata_dict_reference.Dict;
		Wbase_claim_grp_list snaks = null; int[] snaks_order = null;
		for (int i = 0; i < len; ++i) {
			Json_kv sub = Json_kv.cast(owner.Get_at(i));
			byte tid = Wdata_dict_utl.Get_tid_or_invalid(qid, dict, sub.Key().Data_bry()); if (tid == Wbase_claim_enum_.Tid__invalid) continue;
			switch (tid) {
				case Wdata_dict_reference.Tid_hash:	break;	// ignore: "b923b0d68beb300866b87ead39f61e63ec30d8af"
				case Wdata_dict_reference.Tid_snaks:			snaks = Parse_qualifiers(qid, Json_nde.cast(sub.Val())); break;
				case Wdata_dict_reference.Tid_snaks_order:		snaks_order = Parse_pid_order(Json_ary.cast_or_null(sub.Val())); break;
			}
		}
		return new Wbase_references_grp(snaks, snaks_order);
	}
	public Wbase_claim_grp_list Parse_qualifiers(byte[] qid, Json_nde qualifiers_nde) {
		Wbase_claim_grp_list rv = new Wbase_claim_grp_list();
		if (qualifiers_nde == null) return rv;	// NOTE:sometimes references can have 0 snaks; return back an empty Wbase_claim_grp_list, not null; PAGE:Птичкин,_Евгений_Николаевич; DATE:2015-02-16
		int len = qualifiers_nde.Len();
		for (int i = 0; i < len; ++i) {
			Json_kv qualifier_kv = Json_kv.cast(qualifiers_nde.Get_at(i));
			int pid = Parse_pid(qualifier_kv.Key().Data_bry());
			Wbase_claim_grp claims_grp = Parse_props_grp(qid, pid, Json_ary.cast_or_null(qualifier_kv.Val()));
			rv.Add(claims_grp);
		}
		return rv;
	}
	public int[] Parse_pid_order(Json_ary ary) {
		int len = ary.Len();
		int[] rv = new int[len];
		for (int i = 0; i < len; ++i) {
			Json_itm pid_itm = ary.Get_at(i);
			rv[i] = Parse_pid(pid_itm.Data_bry());				
		}
		return rv;
	}
	private Wbase_claim_grp Parse_props_grp(byte[] qid, int pid, Json_ary props_ary) {
		List_adp list = List_adp_.New();
		int len = props_ary.Len();
		for (int i = 0; i < len; ++i) {
			Json_nde qualifier_nde = Json_nde.cast(props_ary.Get_at(i));
			Wbase_claim_base qualifier_itm = Parse_mainsnak(qid, qualifier_nde, pid);
			list.Add(qualifier_itm);
		}
		return new Wbase_claim_grp(Int_obj_ref.New(pid), (Wbase_claim_base[])list.To_ary_and_clear(Wbase_claim_base.class));
	}
	public Wbase_claim_base Parse_mainsnak(byte[] qid, Json_nde nde, int pid) {
		int len = nde.Len();
		Hash_adp_bry dict = Wdata_dict_mainsnak.Dict;
		byte snak_tid = Byte_.Max_value_127;
		for (int i = 0; i < len; ++i) {
			Json_kv sub = Json_kv.cast(nde.Get_at(i));
			byte tid = Wdata_dict_utl.Get_tid_or_invalid(qid, dict, sub.Key().Data_bry()); if (tid == Wbase_claim_enum_.Tid__invalid) continue;
			switch (tid) {
				case Wdata_dict_mainsnak.Tid_snaktype:		snak_tid = Wbase_claim_value_type_.To_tid_or_fail(sub.Val().Data_bry()); break;
				case Wdata_dict_mainsnak.Tid_datavalue:		return Parse_datavalue(qid, pid, snak_tid, Json_nde.cast(sub.Val()));
				case Wdata_dict_mainsnak.Tid_datatype:		break;		// ignore: has values like "wikibase-property"; EX: www.wikidata.org/wiki/Property:P397; DATE:2015-06-12
				case Wdata_dict_mainsnak.Tid_property:		break;		// ignore: pid already available above
				case Wdata_dict_mainsnak.Tid_hash:			break;		// ignore: "84487fc3f93b4f74ab1cc5a47d78f596f0b49390"
			}
		}
		return new Wbase_claim_value(pid, Wbase_claim_type_.Tid__unknown, snak_tid); // NOTE: mainsnak can be null, especially for qualifiers; PAGE:Q2!P576; DATE:2014-09-20
	}
	public Wbase_claim_base Parse_datavalue(byte[] qid, int pid, byte snak_tid, Json_nde nde) {
		int len = nde.Len();
		Hash_adp_bry dict = Wdata_dict_datavalue.Dict;
		Json_itm value_itm = null; byte value_tid = Wbase_claim_type_.Tid__unknown;
		for (int i = 0; i < len; ++i) {
			Json_kv sub = Json_kv.cast(nde.Get_at(i));
			byte tid = Wdata_dict_utl.Get_tid_or_invalid(qid, dict, sub.Key().Data_bry()); if (tid == Wbase_claim_enum_.Tid__invalid) continue;
			switch (tid) {
				case Wdata_dict_datavalue.Tid_type:			value_tid = Wbase_claim_type_.To_tid_or_unknown(sub.Val().Data_bry()); break;
				case Wdata_dict_datavalue.Tid_value:		value_itm = sub.Val(); break;
				case Wdata_dict_datavalue.Tid_error:		break;	// ignore: "Can only construct GlobeCoordinateValue with a String globe parameter"
			}
		}
		return factory.Parse(qid, pid, snak_tid, nde, value_tid, value_itm);
	}
	private static int Parse_pid(byte[] pid_bry) {
		int rv = Bry_.To_int_or(pid_bry, 1, pid_bry.length, -1); if (rv == -1) throw Err_.new_wo_type("invalid pid", "pid", String_.new_u8(pid_bry));
		return rv;
	}
}
