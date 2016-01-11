package gplx.xowa.parsers.amps; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.tests.*;
public class Xop_html_txt_tkn_chkr extends Xop_tkn_chkr_base {
	@Override public Class<?> TypeOf() {return Xop_amp_tkn_txt.class;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_html_ref;}
	public String Html_ref_key() {return html_ref_key;} public Xop_html_txt_tkn_chkr Html_ref_key_(String v) {html_ref_key = v; return this;} private String html_ref_key;
	@Override public int Chk_hook(Tst_mgr mgr, String path, Object actl_obj, int err) {
		Xop_amp_tkn_txt actl = (Xop_amp_tkn_txt)actl_obj;
		err += mgr.Tst_val(html_ref_key == null, path, "html_ref_key", html_ref_key, String_.new_u8(actl.Xml_name_bry()));
		return err;
	}
}
