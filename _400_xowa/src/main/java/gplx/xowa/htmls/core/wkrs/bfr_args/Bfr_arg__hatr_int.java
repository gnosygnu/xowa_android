package gplx.xowa.htmls.core.wkrs.bfr_args; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*;
public class Bfr_arg__hatr_int implements Bfr_arg_clearable {
	private final byte[] atr_bgn;
	private int val;
	public Bfr_arg__hatr_int(byte[] key)		{this.atr_bgn = Bfr_arg__hatr_.Bld_atr_bgn(key);}
	public Bfr_arg__hatr_int Set_by_int(int v)	{val = v; return this;}
	public void Bfr_arg__clear()				{val = Int_.Min_value;}
	public boolean Bfr_arg__missing()				{return val == Int_.Min_value;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		bfr.Add(atr_bgn);
		bfr.Add_int_variable(val);
		bfr.Add_byte_quote();
	}
}
