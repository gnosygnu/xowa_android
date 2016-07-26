package gplx.xowa.users.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xocfg_meta_itm {
	public Xocfg_meta_itm(String key, String type, String dflt, String version) {
		this.key = key; this.type = type; this.dflt = dflt; this.version = version;
	}
	public String Key() {return key;} private final    String key;
	public String Type() {return type;} private String type;
	public String Dflt() {return dflt;} private String dflt;
	public String Version() {return version;} private String version;
}
