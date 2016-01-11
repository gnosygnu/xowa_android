package gplx.core.ios; import gplx.*; import gplx.core.*;
public class Io_fil implements gplx.CompareAble {
	public Io_fil(Io_url url, String data) {this.url = url; this.data = data;}
	public Io_url Url() {return url;} public Io_fil Url_(Io_url v) {url = v; return this;} Io_url url;
	public String Data() {return data;} public Io_fil Data_(String v) {data = v; return this;} private String data;
	public int compareTo(Object obj) {
		return gplx.CompareAble_.Compare(url.Raw(), ((Io_fil)obj).Url().Raw());
	}
	public static Io_fil[] new_ary_(Io_url[] url_ary) {
		int url_ary_len = url_ary.length;
		Io_fil[] rv = new Io_fil[url_ary_len];
		for (int i = 0; i < url_ary_len; i++) {
			Io_url url = url_ary[i];
			String data = Io_mgr.Instance.LoadFilStr(url);
			Io_fil fil = new Io_fil(url, data);
			rv[i] = fil;
		}
		return rv;
	}
	public static final Io_fil[] Ary_empty = new Io_fil[0];
}
