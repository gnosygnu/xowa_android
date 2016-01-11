package gplx.xowa.xtns.math; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.gfui.*;
import gplx.xowa.apps.*;
public class Xof_math_itm {
	public Xof_math_itm Ctor(byte[] math, String hash, Io_url png_url) {this.math = math; this.hash = hash; this.png_url = png_url; return this;}
	public int Id() {return id;} public Xof_math_itm Id_(int v) {id = v; return this;} private int id;
	public String Hash() {return hash;} private String hash;
	public byte[] Math() {return math;} private byte[] math;
	public Io_url Png_url() {return png_url;} Io_url png_url;
	public Xof_math_itm Clone() {return new Xof_math_itm().Ctor(math, hash, png_url);}
}
