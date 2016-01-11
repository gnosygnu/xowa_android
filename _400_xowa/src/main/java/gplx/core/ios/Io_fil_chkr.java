package gplx.core.ios; import gplx.*; import gplx.core.*;
import gplx.core.tests.*;
public class Io_fil_chkr implements Tst_chkr {
	public Io_fil_chkr(Io_url url, String data) {this.expd_url = url; this.expd_data = data;}
	public Io_url Expd_url() {return expd_url;} public Io_fil_chkr Expd_url_(Io_url v) {expd_url = v; return this;} Io_url expd_url;
	public String Expd_data() {return expd_data;} public Io_fil_chkr Expd_data_(String v) {expd_data = v; return this;} private String expd_data;
	public Class<?> TypeOf() {return gplx.core.ios.Io_fil.class;}
	public int Chk(Tst_mgr mgr, String path, Object actl) {
		gplx.core.ios.Io_fil fil = (gplx.core.ios.Io_fil)actl;
		int rv = 0;
		rv += mgr.Tst_val(expd_url == null, path, "url", expd_url, fil.Url());
		rv += mgr.Tst_val(expd_data == null, path, "data", expd_data, fil.Data());
		return rv;
	}
}
