package gplx.xowa.htmls.core.wkrs.bfr_args; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*;
public class Bfr_arg__hatr_cls implements Bfr_arg {
	private final byte[] atr_bgn;
	private byte[][] ary;
	public Bfr_arg__hatr_cls()							{this.atr_bgn = Bfr_arg__hatr_.Bld_atr_bgn(gplx.langs.htmls.Gfh_atr_.Bry__class);}
	public Bfr_arg__hatr_cls Set_by_ary(byte[][] v)		{ary = v; return this;}
	public void Bfr_arg__clear()						{ary = null;}
	public boolean Bfr_arg__missing()						{return ary == null;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		bfr.Add(atr_bgn);
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			if (i != 0) bfr.Add_byte_space();
			bfr.Add(ary[i]);
		}
		bfr.Add_byte_quote();
	}
}
