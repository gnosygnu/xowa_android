package gplx.xowa.apps.cfgs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_data_itm {
	public Xocfg_data_itm(String key, String usr, String ctx, String val) {this.key = key; this.usr = usr; this.ctx = ctx; this.val = val;}
	public String Key() {return key;} private final    String key;
	public String Usr() {return usr;} private final    String usr;
	public String Ctx() {return ctx;} private String ctx;
	public String Val() {return val;} private String val;
}
