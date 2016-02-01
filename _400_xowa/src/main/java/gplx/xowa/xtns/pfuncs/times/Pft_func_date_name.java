package gplx.xowa.xtns.pfuncs.times; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pft_func_date_name extends Pf_func_base {
	public Pft_func_date_name(int id, int date_tid, int seg_idx, int base_idx) {this.id = id; this.date_tid = date_tid; this.seg_idx = seg_idx; this.base_idx = base_idx;} private int date_tid, seg_idx, base_idx;
	@Override public int Id() {return id;} private int id;
	@Override public Pf_func New(int id, byte[] name) {return new Pft_func_date_name(id, date_tid, seg_idx, base_idx).Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		DateAdp date = DateAdp_.MinValue;
	    switch (date_tid) {
	        case Pft_func_date_int.Date_tid_lcl: date = DateAdp_.Now(); break;
	        case Pft_func_date_int.Date_tid_utc: date = DateAdp_.Now().XtoUtc(); break;
	        case Pft_func_date_int.Date_tid_rev: date = ctx.Page().Revision_data().Modified_on(); break;
			default: throw Err_.new_unhandled(date_tid);
	    }
		byte[] val = ctx.Wiki().Msg_mgr().Val_by_id(base_idx + date.Segment(seg_idx));
		bfr.Add(val);
		// translator.Translate(base_idx, date.Segment(seg_idx), bfr);
	}
}
