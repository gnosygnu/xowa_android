package gplx.xowa.xtns.wbases.claims.itms.times; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*; import gplx.xowa.xtns.wbases.claims.itms.*;
public class Wbase_data_itm {
	public Wbase_data_itm(int val_int, String val_str, byte[] val_bry) {
		this.val_int = val_int;
		this.val_str = val_str;
		this.val_bry = val_bry;
	}
	public int Val_int() {return val_int;} private final    int val_int;
	public String Val_str() {return val_str;} private final    String val_str;
	public byte[] Val_bry() {return val_bry;} private final    byte[] val_bry;

	public static Wbase_data_itm New_int(int val)		{return new Wbase_data_itm(val, Int_.To_str(val), Int_.To_bry(val));}
	public static Wbase_data_itm New_str(String val)	{return new Wbase_data_itm( -1, val, Bry_.new_u8(val));}
}
