package gplx.xowa.apps.apis.xowa.wikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.wikis.langs.*;
public class Xoapi_wiki_lang implements GfoInvkAble {
	public Xoap_lang_variants	Variants()		{return variants;} private final Xoap_lang_variants variants = new Xoap_lang_variants();
	public void Subscribe(GfoEvObj sub) {variants.Subscribe(sub);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_variants)) 							return variants;
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_variants = "variants";
}
