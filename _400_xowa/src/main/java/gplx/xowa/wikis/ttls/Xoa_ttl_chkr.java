package gplx.xowa.wikis.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.tests.*;
public class Xoa_ttl_chkr implements Tst_chkr {
	public Class<?> TypeOf() {return Xoa_ttl.class;}
	public int Chk(Tst_mgr mgr, String path, Object o) {
		Xoa_ttl actl = (Xoa_ttl)o;
		int rv = 0;
		rv += mgr.Tst_val(expd_str == null, path, "raw", expd_str, String_.new_u8(actl.Raw()));
		return rv;
	}
	public String Expd_str() {return expd_str;} public Xoa_ttl_chkr Expd_str_(String v) {expd_str = v; return this;} private String expd_str;
	public static Xoa_ttl_chkr new_(String v) {return new Xoa_ttl_chkr().Expd_str_(v);} private Xoa_ttl_chkr() {}
}
