package gplx.xowa.specials.search.quicks; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.search.*;
public class Xoa_search_itm {
	public Xoa_search_itm(byte[] url, byte[] name, byte[] descrip, byte[] img) {
		this.url = url; this.name = name; this.descrip = descrip; this.img = img;
	}
	public byte[] Url() {return url;} private final byte[] url;				// EX: en.wikipedia.org/wiki/Earth
	public byte[] Name() {return name;} private final byte[] name;			// EX: Earth
	public byte[] Descrip() {return descrip;} private final byte[] descrip;	// EX: Third planet from the Sun
	public byte[] Img() {return img;} private final byte[] img;				// EX: Earth.png
}
