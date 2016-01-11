package gplx.xowa.htmls.core.wkrs.bfr_args; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*;
public class Bfr_arg__wrapper implements Bfr_arg {
	private Bfr_arg inner;
	public void Clear() {
		inner = Bfr_arg_.Noop;
	}
	public Bfr_arg__wrapper Set(Bfr_arg v)		{inner = v; return this;}
	public void Bfr_arg__clear()				{this.Clear();}
	public boolean Bfr_arg__missing()				{return inner == Bfr_arg_.Noop;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		inner.Bfr_arg__add(bfr);
	}
}
