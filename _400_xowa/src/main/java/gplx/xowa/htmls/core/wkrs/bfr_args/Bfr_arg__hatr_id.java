package gplx.xowa.htmls.core.wkrs.bfr_args; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*;
public class Bfr_arg__hatr_id implements Bfr_arg_clearable {
	private final byte[] atr_bgn;
	private final byte[] bry; private int num;
	public Bfr_arg__hatr_id(byte[] bry)					{this.bry = bry; this.atr_bgn = Bfr_arg__hatr_.Bld_atr_bgn(gplx.langs.htmls.Gfh_atr_.Bry__id);}
	public Bfr_arg__hatr_id Set(int num)				{this.num = num; return this;}
	public void Bfr_arg__clear()						{num = -1;}
	public boolean Bfr_arg__missing()						{return num == -1;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		bfr.Add(atr_bgn);
		bfr.Add(bry);
		bfr.Add_int_variable(num);
		bfr.Add_byte_quote();
	}
	public static Bfr_arg__hatr_id New(String v) {return new Bfr_arg__hatr_id(Bry_.new_u8(v));}
	public static Bfr_arg__hatr_id New(byte[] v) {return new Bfr_arg__hatr_id(v);}
}
