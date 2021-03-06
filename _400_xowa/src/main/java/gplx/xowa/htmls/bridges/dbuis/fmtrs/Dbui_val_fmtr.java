package gplx.xowa.htmls.bridges.dbuis.fmtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.bridges.*; import gplx.xowa.htmls.bridges.dbuis.*;
import gplx.core.brys.fmtrs.*;
import gplx.xowa.htmls.bridges.dbuis.tbls.*;
public interface Dbui_val_fmtr {
	Dbui_val_fmtr Init(Dbui_col_itm col, byte[] row_id, Dbui_val_itm val);
}
class Dbui_val_fmtr__view implements gplx.core.brys.Bfr_arg, Dbui_val_fmtr {
	private Dbui_val_itm val;
	public Dbui_val_fmtr Init(Dbui_col_itm col, byte[] row_id, Dbui_val_itm val) {this.val = val; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(val.Html());
	}
}
class Dbui_val_fmtr__edit implements gplx.core.brys.Bfr_arg, Dbui_val_fmtr {
	private Dbui_col_itm col; private byte[] row_id; private Dbui_val_itm val;
	public Dbui_val_fmtr Init(Dbui_col_itm col, byte[] row_id, Dbui_val_itm val) {this.col = col; this.row_id = row_id; this.val = val; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		switch (col.Type()) {
			case Dbui_col_itm.Type_id_str:	input_fmtr_str.Bld_bfr_many(bfr, col.Key(), col.Width(), val.Data(), row_id); break;
			case Dbui_col_itm.Type_id_text: textarea_fmtr_str.Bld_bfr_many(bfr, col.Key(), col.Width(), val.Data(), row_id); break;
			default: throw Err_.new_unimplemented();
		}
	}
	private static final    Bry_fmtr input_fmtr_str = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "    <input cl"+"ass='dbui_cell xo_resizable_col' dbui_col='~{col_key}' style='border:1px solid black; width:~{width}px' value='~{value}' onkeyup='Dbui__edit__keyup(event, \"~{row_id}\");'/>"
	), "col_key", "width", "value", "row_id");
	private static final    Bry_fmtr textarea_fmtr_str = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "    <textarea cl"+"ass='dbui_cell' dbui_col='~{col_key}' style='border:1px solid black; width:~{width}px; height:18px;' onkeyup='Dbui__edit__keyup(event, \"~{row_id}\");'>~{value}</textarea>"
	), "col_key", "width", "value", "row_id");
}
