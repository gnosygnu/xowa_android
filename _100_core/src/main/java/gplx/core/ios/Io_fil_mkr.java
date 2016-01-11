package gplx.core.ios; import gplx.*; import gplx.core.*;
public class Io_fil_mkr {
	private final List_adp list = List_adp_.new_();
	public Io_fil_mkr Add(String url, String data) {return Add(Io_url_.mem_fil_(url), data);}
	public Io_fil_mkr Add(Io_url url, String data) {list.Add(new Io_fil(url, data)); return this;}
	public Io_fil[] To_ary() {return (Io_fil[])list.To_ary(Io_fil.class);}
}
