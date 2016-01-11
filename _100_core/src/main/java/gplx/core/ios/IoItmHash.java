package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoItmHash extends Ordered_hash_base {
	public Io_url Url() {return url;} Io_url url;
	public void Add(IoItm_base itm) {Add_base(MakeKey(itm.Url()), itm);}
	public void Del(Io_url url) {Del(MakeKey(url));}
	public IoItm_base Get_by(Io_url url) {return IoItm_base_.as_(Fetch_base(MakeKey(url)));}
	@gplx.New public IoItm_base Get_at(int i) {return IoItm_base_.as_(Get_at_base(i));}
	public Io_url[] XtoIoUrlAry() {
		int count = this.Count();
		Io_url[] rv = new Io_url[count];
		for (int i = 0; i < count; i++)
			rv[i] = this.Get_at(i).Url();
		return rv;
	}
	String MakeKey(Io_url url) {return url.XtoCaseNormalized();}
	public static IoItmHash new_() {
		IoItmHash rv = new IoItmHash();
		rv.url = null;//Io_url_.Empty;
		return rv;
	}	IoItmHash() {}
	public static IoItmHash list_(Io_url url) {
		IoItmHash rv = new IoItmHash();
		rv.url = url;
		return rv;
	}
}
