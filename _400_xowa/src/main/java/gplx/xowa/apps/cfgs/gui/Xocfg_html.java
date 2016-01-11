package gplx.xowa.apps.cfgs.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
public class Xocfg_html implements GfoInvkAble {
	public Xocfg_html() {
		this.content_editable = false;		// CFG: default to false for general user
	}
	public boolean Content_editable() {return content_editable;} public Xocfg_html Content_editable_(boolean v) {content_editable = v; return this;} private boolean content_editable;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_content_editable))		return Yn.To_str(content_editable);
		else if	(ctx.Match(k, Invk_content_editable_))		content_editable = m.ReadYn_toggle("v", content_editable);
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_content_editable = "content_editable", Invk_content_editable_ = "content_editable_";
}
