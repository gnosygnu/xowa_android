package gplx.core.gfo_regys; import gplx.*; import gplx.core.*;
public class GfoRegyItm {
	public String Key() {return key;} private String key;
	public Object Val() {return val;} Object val;
	public Io_url Url() {return url;} Io_url url;
	public int ValType() {return valType;} int valType;
	public GfoRegyItm(String key, Object val, int valType, Io_url url) {this.key = key; this.val = val; this.valType = valType; this.url = url;}

	public static final int 
		  ValType_Obj = 1
		, ValType_Url = 2
		, ValType_B64 = 3
		;
}
