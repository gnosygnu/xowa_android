package gplx.xowa.xtns.wbases; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.langs.*;
import gplx.xowa.xtns.wbases.core.*; import gplx.xowa.xtns.wbases.claims.*; import gplx.xowa.xtns.wbases.claims.itms.*; import gplx.xowa.xtns.wbases.hwtrs.*;
class Wdata_prop_val_visitor implements Wbase_claim_visitor {
	private Wdata_wiki_mgr wdata_mgr; private Xoae_app app; private Bry_bfr bfr; private byte[] lang_key;
	private final    Bry_bfr tmp_time_bfr = Bry_bfr_.Reset(255); private final    Bry_fmtr tmp_time_fmtr = Bry_fmtr.new_();
	private Wdata_hwtr_msgs msgs;
	public Wdata_prop_val_visitor(Xoae_app app, Wdata_wiki_mgr wdata_mgr) {this.app = app; this.wdata_mgr = wdata_mgr;}
	public void Init(Bry_bfr bfr, Wdata_hwtr_msgs msgs, byte[] lang_key) {
		this.bfr = bfr; this.msgs = msgs; this.lang_key = lang_key;
	}
	public void Visit_str(Wbase_claim_string itm) {
		bfr.Add(itm.Val_str());
	}
	public void Visit_time(Wbase_claim_time itm) {
		itm.Write_to_bfr(bfr, tmp_time_bfr, tmp_time_fmtr, msgs, Bry_.Empty);	// for now, don't bother passing ttl; only used for error msg; DATE:2015-08-03
	}
	public void Visit_monolingualtext(Wbase_claim_monolingualtext itm)	{bfr.Add(itm.Text());}			// phrase only; PAGE:en.w:Alberta; EX: {{#property:motto}} -> "Fortis et libre"; DATE:2014-08-28
	public void Visit_entity(Wbase_claim_entity itm) {
		Wdata_doc entity_doc = wdata_mgr.Doc_mgr.Get_by_xid_or_null(itm.Page_ttl_db());
		if (entity_doc == null) return;	// NOTE: wiki may refer to entity that no longer exists; EX: {{#property:p1}} which links to Q1, but p1 links to Q2 and Q2 was deleted; DATE:2014-02-01
		byte[] label = entity_doc.Label_list__get(lang_key);
		if (label == null && !Bry_.Eq(lang_key, Xol_lang_itm_.Key_en))	// NOTE: some properties may not exist in language of wiki; default to english; DATE:2013-12-19
			label = entity_doc.Label_list__get(Xol_lang_itm_.Key_en);
		if (label != null)	// if label is still not found, don't add null reference
			bfr.Add(label);
	}
	public void Visit_quantity(Wbase_claim_quantity itm) {
		byte[] amount_bry = itm.Amount();
		long val = Bry_.To_long_or(amount_bry, Byte_ascii.Comma_bry, 0, amount_bry.length, 0);	// NOTE: must cast to long for large numbers; EX:{{#property:P1082}} PAGE:en.w:Earth; DATE:2015-08-02
		Xol_lang_itm lang = app.Lang_mgr().Get_by(lang_key);
		bfr.Add(lang.Num_mgr().Format_num_by_long(val));	// amount; EX: 1,234
		if (itm.Lbound_as_num().To_long() != val && itm.Ubound_as_num().To_long() != val) {	// NOTE: do not output ± if lbound == val == ubound; PAGE:en.w:Tintinan DATE:2015-08-02
			bfr.Add(Bry__quantity_margin_of_error);			// symbol: EX: ±
			bfr.Add(itm.Unit());							// unit;   EX: 1
		}
	}
	public void Visit_globecoordinate(Wbase_claim_globecoordinate itm) {
		Wdata_prop_val_visitor_.Render__geo(bfr, itm.Lat(), itm.Lng());
	}
	public void Visit_system(Wbase_claim_value itm) {}
	public static final    byte[] Bry__quantity_margin_of_error = Bry_.new_u8("±");
}
