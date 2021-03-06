package gplx.xowa.htmls.core.wkrs.bfr_args; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
public class Bfr_arg__hatr_fmtr implements Bfr_arg {
	private final    byte[] atr_bgn;
	private final    Bry_fmtr fmtr = Bry_fmtr.new_();
	private Object[] args;
	public Bfr_arg__hatr_fmtr(byte[] key, String fmt, String... keys) {
		this.atr_bgn = Bfr_arg__hatr_.Bld_atr_bgn(key);
		this.fmtr.Fmt_(fmt).Keys_(keys);
		this.Clear();
	}
	public void Set_args(Object... args) {this.args = args;}
	public void Clear() {args = null;}
	public void Bfr_arg__clear() {this.Clear();}
	public boolean Bfr_arg__missing() {return args == null;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		bfr.Add(atr_bgn);
		fmtr.Bld_bfr_many(bfr, args);
		bfr.Add_byte_quote();			
	}
}
