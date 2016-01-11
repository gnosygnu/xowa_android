package gplx.gfml; import gplx.*;
class GfmlVarCtx {//_20101106
	public String Key() {return key;} private String key;
	public void Add_if_dupe_use_nth(GfmlVarItm itm) {hash.Add_if_dupe_use_nth(itm.Key(), itm);}
	public void Del(String key) {hash.Del(key);}
	public String Fetch_Val(String key) {
		GfmlVarItm itm = (GfmlVarItm)hash.Get_by(key); if (itm == null) return null;
		return itm.TknVal();
	}
	Hash_adp hash = Hash_adp_.new_();
	public static GfmlVarCtx new_(String key) {
		GfmlVarCtx rv = new GfmlVarCtx();
		rv.key = key;
		return rv;
	}
}
class GfmlVarCtx_ {//_20101106
	public static GfmlVarCtx FetchFromCacheOrNew(Hash_adp cache, String ctxKey) {
		Hash_adp ctxRegy = FetchRegyOrNew(cache);
		GfmlVarCtx rv = (GfmlVarCtx)ctxRegy.Get_by(ctxKey);
		if (rv == null) {
			rv = (String_.Eq(ctxKey, DefaultKey))
				? default_(ctxKey)
				: GfmlVarCtx.new_(ctxKey);
			ctxRegy.Add(rv.Key(), rv);
		}
		return rv;
	}
	static GfmlVarCtx default_(String ctxKey) {
		GfmlVarCtx rv = GfmlVarCtx.new_(ctxKey);
		rv.Add_if_dupe_use_nth(GfmlVarItm.new_("t", GfmlTkn_.raw_("\t"), GfmlVarCtx_.DefaultKey));
		rv.Add_if_dupe_use_nth(GfmlVarItm.new_("n", GfmlTkn_.raw_(String_.CrLf), GfmlVarCtx_.DefaultKey));
		return rv;
	}
	static Hash_adp FetchRegyOrNew(Hash_adp cache) {
		String key = "gfml.cacheKeys.ctxRegy";
		Hash_adp rv = (Hash_adp)cache.Get_by(key);
		if (rv == null) {
			rv = Hash_adp_.new_();
			cache.Add(key, rv);
		}
		return rv;
	}
	public static final String DefaultKey = "gfml.varKeys.default";
}
