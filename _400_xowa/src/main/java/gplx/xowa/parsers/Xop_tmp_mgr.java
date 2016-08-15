package gplx.xowa.parsers; import gplx.*; import gplx.xowa.*;
import gplx.core.primitives.*; import gplx.core.btries.*;
import gplx.xowa.files.*;
import gplx.xowa.xtns.pfuncs.exprs.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.pfuncs.ttls.*;
public class Xop_tmp_mgr {
	public Xof_math_itm				Math_itm()				{return math_itm;}		private final    Xof_math_itm math_itm = new Xof_math_itm();
	public Xof_xfer_itm				Xfer_itm()				{return xfer_itm;}		private final    Xof_xfer_itm xfer_itm = new Xof_xfer_itm();
	public Gfo_number_parser		Pfunc_num_parser_0()	{return num_parser_0;}	private final    Gfo_number_parser num_parser_0 = new Gfo_number_parser().Hex_enabled_(true);
	public Gfo_number_parser		Pfunc_num_parser_1()	{return num_parser_1;}	private final    Gfo_number_parser num_parser_1 = new Gfo_number_parser().Hex_enabled_(true);
	public Pfunc_expr_shunter		Expr_shunter()			{return expr_shunter;}	private final    Pfunc_expr_shunter expr_shunter = new Pfunc_expr_shunter();
	public Btrie_slim_mgr			Xnde__xtn_end()			{return xnde__xtn_end;} private final    Btrie_slim_mgr xnde__xtn_end = Btrie_slim_mgr.ci_a7();	// NOTE:ci.ascii:MW_const.en; listed XML node names are en
	public Btrie_rv					Xnde__trv()				{return xnde__trv;}		private final    Btrie_rv xnde__trv = new Btrie_rv();
	public Int_obj_ref				Pfunc_rel2abs()			{return pfunc_rel2abs;}	private final    Int_obj_ref pfunc_rel2abs = Int_obj_ref.New_zero();
	public Pfunc_anchorencode_mgr	Pfunc_anchor_encoder() {
//			if (pf
		return null;
	}	//private pfunc_anchor_encoder
}
