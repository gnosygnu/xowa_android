package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
import gplx.core.primitives.*;
import gplx.xowa.wikis.domains.*;
public class Xoh_imgs_mgr implements Gfo_invk {
	public Xoh_imgs_mgr(Xow_html_mgr html_mgr) {wiki_is_default = html_mgr.Wiki().Domain_tid() == Xow_domain_tid_.Tid__home;} private boolean wiki_is_default;
	public Bool_obj_ref Alt_in_caption() {return alt_in_caption;} Bool_obj_ref alt_in_caption = Bool_obj_ref.y_();
	public Bool_obj_ref Alt_defaults_to_caption() {return alt_defaults_to_caption;} Bool_obj_ref alt_defaults_to_caption = Bool_obj_ref.y_();
	public void Copy_cfg(Xoh_imgs_mgr copy) {this.alt_in_caption = copy.alt_in_caption;}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_alt_in_caption))					return Yn.To_str(alt_in_caption.Val());
		else if	(ctx.Match(k, Invk_alt_in_caption_))				alt_in_caption = Modify(wiki_is_default, alt_in_caption, m.ReadYn("v"));
		else if	(ctx.Match(k, Invk_alt_defaults_to_caption))		return Yn.To_str(alt_defaults_to_caption.Val());
		else if	(ctx.Match(k, Invk_alt_defaults_to_caption_))		alt_defaults_to_caption = Modify(wiki_is_default, alt_defaults_to_caption, m.ReadYn("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	public static final String Invk_alt_in_caption = "alt_in_caption", Invk_alt_in_caption_ = "alt_in_caption_", Invk_alt_defaults_to_caption = "alt_defaults_to_caption", Invk_alt_defaults_to_caption_ = "alt_defaults_to_caption_";
	private static Bool_obj_ref Modify(boolean orig, Bool_obj_ref cur, boolean v) {return orig ? cur.Val_(v) : Bool_obj_ref.new_(v);}
}
