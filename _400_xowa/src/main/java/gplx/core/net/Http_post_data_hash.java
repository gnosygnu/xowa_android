package gplx.core.net; import gplx.*; import gplx.core.*;
public class Http_post_data_hash {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public int Len() {return hash.Count();}
	public Http_post_data_itm Get_at(int i)		{return (Http_post_data_itm)hash.Get_at(i);}
	public Http_post_data_itm Get_by(byte[] k)	{return (Http_post_data_itm)hash.Get_by(k);}
	public void Add(byte[] key, byte[] val) {
		hash.Add(key, new Http_post_data_itm(key, val));
	}
}
