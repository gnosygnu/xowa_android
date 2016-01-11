package gplx.xowa.apps.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
public class Xoa_cfg_itm implements GfoInvkAble {
	public Xoa_cfg_itm(Xoa_cfg_grp grp, byte[] key) {this.grp = grp; this.key = key;}
	public Xoa_cfg_grp Grp() {return grp;} private final Xoa_cfg_grp grp;
	public byte[] Key() {return key;} private final byte[] key;
	public String Val() {return val;} private String val;
	public boolean Val_is_dirty() {return val_is_dirty;} private boolean val_is_dirty;
	public boolean Val_is_customized() {return val_is_customized;} private boolean val_is_customized;	// false if value is system default; true if changed by user
	public void Val_(String v) {
		this.val = v;
		if (grp.Notify(this)) {
			val_is_customized = true;
			val_is_dirty = true;
		}
	} 
	public void Val_load_done() {
		val_is_dirty = false;
		val_is_customized = true;
	} 
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_val)) 		return val;
		else if	(ctx.Match(k, Invk_val_)) 		Val_(m.ReadStr("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_val = "val", Invk_val_ = "val_";
}
