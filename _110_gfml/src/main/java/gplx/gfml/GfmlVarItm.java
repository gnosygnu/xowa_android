package gplx.gfml; import gplx.*;
class GfmlVarItm implements GfmlScopeItm {//_20101106
	public String Key() {return key;} private String key;
	public GfmlDocPos DocPos() {return docPos;} public GfmlVarItm DocPos_(GfmlDocPos v) {docPos = v; return this;} GfmlDocPos docPos = GfmlDocPos_.Null;		
	public GfmlTkn Tkn() {return tkn;} public void Tkn_set(GfmlTkn v) {tkn = v;} GfmlTkn tkn;
	public String TknVal() {return tkn.Val();} 
	public String CtxKey() {return ctxKey;} private String ctxKey;
	@gplx.Internal protected void Scope_bgn(GfmlVarCtx ctx) {ctx.Add_if_dupe_use_nth(this);}
	@gplx.Internal protected void Scope_end(GfmlVarCtx ctx) {ctx.Del(key);}
	public static GfmlVarItm new_(String key, GfmlTkn tkn, String ctxKey) {
		GfmlVarItm rv = new GfmlVarItm();
		rv.key = key; rv.tkn = tkn; rv.ctxKey = ctxKey;
		return rv;
	}	GfmlVarItm() {}
}
