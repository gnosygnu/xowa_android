package gplx.xowa.htmls.core.htmls.tidy; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.htmls.*;
public interface Xoh_tidy_wkr {
	byte Tid();
	void Indent_(boolean v);
	void Init_by_app(Xoae_app app);
	void Exec_tidy(Xoae_page page, Bry_bfr bfr);
}
