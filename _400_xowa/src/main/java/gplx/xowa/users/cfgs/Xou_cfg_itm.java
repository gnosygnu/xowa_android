package gplx.xowa.users.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xou_cfg_itm {
	public Xou_cfg_itm(int usr, String ctx, String key, String val) {
		this.usr = usr; this.ctx = ctx; this.key = key; this.val = val;
		this.uid = Xou_cfg_mgr.Bld_uid(usr, ctx, key);
	}
	public String	Uid() {return uid;} private final    String uid;
	public int		Usr() {return usr;} private final    int usr;
	public String	Ctx() {return ctx;} private final    String ctx;
	public String	Key() {return key;} private final    String key;
	public String	Val() {return val;} private String val;
	public void Val_(String v) {this.val = v;}
}
