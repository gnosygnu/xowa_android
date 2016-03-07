package gplx.xowa.xtns.scribunto.libs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
import gplx.xowa.xtns.wdatas.core.*;
class Scrib_lib_wikibase_srl_visitor implements Wdata_claim_visitor {
	public Keyval[] Rv() {return rv;} Keyval[] rv;
	public void Visit_str(Wdata_claim_itm_str itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Xto_str(itm.Val_tid()));
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, String_.new_u8(itm.Val_str()));
	}
	public void Visit_entity(Wdata_claim_itm_entity itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Str_entity);
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, Entity_value(itm));
	}
	private static Keyval[] Entity_value(Wdata_claim_itm_core itm) {
		Wdata_claim_itm_entity claim_entity = (Wdata_claim_itm_entity)itm;
		Keyval[] rv = new Keyval[2];
		rv[0] = Keyval_.new_(Wdata_dict_value_entity.Str_entity_type, claim_entity.Entity_tid_str());
		rv[1] = Keyval_.new_(Wdata_dict_value_entity.Str_numeric_id, Int_.To_str(claim_entity.Entity_id()));
		return rv;
	}
	public void Visit_monolingualtext(Wdata_claim_itm_monolingualtext itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Str_monolingualtext);
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, Monolingualtext_value(itm));
	}
	private static Keyval[] Monolingualtext_value(Wdata_claim_itm_monolingualtext itm) {
		Keyval[] rv = new Keyval[2];
		rv[0] = Keyval_.new_(Wdata_dict_value_monolingualtext.Str_text			, String_.new_u8(itm.Text()));
		rv[1] = Keyval_.new_(Wdata_dict_value_monolingualtext.Str_language		, String_.new_u8(itm.Lang()));
		return rv;
	}
	public void Visit_quantity(Wdata_claim_itm_quantity itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Str_quantity);
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, Quantity_value(itm));
	}
	private static Keyval[] Quantity_value(Wdata_claim_itm_quantity itm) {
		Keyval[] rv = new Keyval[4];
		rv[0] = Keyval_.new_(Wdata_dict_value_quantity.Str_amount			, itm.Amount_as_num().To_str());	// NOTE: must be num b/c Module code will directly do math calc on it; EX: "99" not "+99"; PAGE:eo.w:Mud�; DATE:2015-11-08
		rv[1] = Keyval_.new_(Wdata_dict_value_quantity.Str_unit				, String_.new_u8(itm.Unit()));
		rv[2] = Keyval_.new_(Wdata_dict_value_quantity.Str_upperbound		, itm.Ubound_as_num().To_str());
		rv[3] = Keyval_.new_(Wdata_dict_value_quantity.Str_lowerbound		, itm.Lbound_as_num().To_str());
		return rv;
	}
	public void Visit_time(Wdata_claim_itm_time itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Str_time);
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, Time_value(itm));
	}
	private static Keyval[] Time_value(Wdata_claim_itm_time itm) {
		Keyval[] rv = new Keyval[6];
		rv[0] = Keyval_.new_(Wdata_dict_value_time.Str_time				, String_.new_a7(itm.Time()));
		rv[1] = Keyval_.new_(Wdata_dict_value_time.Str_precision		, itm.Precision_int());		// NOTE: must return int, not str; DATE:2014-02-18
		rv[2] = Keyval_.new_(Wdata_dict_value_time.Str_before			, itm.Before_int());
		rv[3] = Keyval_.new_(Wdata_dict_value_time.Str_after			, itm.After_int());
		rv[4] = Keyval_.new_(Wdata_dict_value_time.Str_timezone			, Wdata_dict_value_time.Val_timezone_str);	// ASSUME: always 0 b/c UTF?; DATE:2015-09-21
		rv[5] = Keyval_.new_(Wdata_dict_value_time.Str_calendarmodel	, Wdata_dict_value_time.Val_calendarmodel_str);
		return rv;
	}
	public void Visit_globecoordinate(Wdata_claim_itm_globecoordinate itm) {
		rv = new Keyval[2];
		rv[0] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_type, Wdata_dict_val_tid.Str_globecoordinate);
		rv[1] = Keyval_.new_(Scrib_lib_wikibase_srl.Key_value, Globecoordinate_value(itm));
	}
	private static Keyval[] Globecoordinate_value(Wdata_claim_itm_globecoordinate itm) {			
		Keyval[] rv = new Keyval[5];
		rv[0] = Keyval_.new_(Wdata_dict_value_globecoordinate.Str_latitude			, Double_.parse(String_.new_a7(itm.Lat())));
		rv[1] = Keyval_.new_(Wdata_dict_value_globecoordinate.Str_longitude			, Double_.parse(String_.new_a7(itm.Lng())));
		rv[2] = Keyval_.new_(Wdata_dict_value_globecoordinate.Str_altitude			, String_.new_u8(itm.Alt()));
		rv[3] = Keyval_.new_(Wdata_dict_value_globecoordinate.Str_globe				, String_.new_u8(itm.Glb()));
		rv[4] = Keyval_.new_(Wdata_dict_value_globecoordinate.Str_precision			, itm.Prc_as_num().To_double());
		return rv;
	}
	public void Visit_system(Wdata_claim_itm_system itm) {
		rv = Keyval_.Ary_empty;
	}
}