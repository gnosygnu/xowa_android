package gplx.gfml; import gplx.*;
class GfmlTypeHash {//_20101027
	public GfmlType Get_by(String key) {return (GfmlType)hash.Get_by(key);}
	public void Add(GfmlType type) {
		if (type.IsTypeNull()) throw Err_.new_wo_type("cannot add null type to GfmlTypeHash");
		if (hash.Has(type.Key())) throw Err_.new_wo_type("type key already exists", "key", type.Key());
		hash.Add(type.Key(), type);
	}
	Hash_adp hash = Hash_adp_.new_();
	public static GfmlTypeHash new_() {return new GfmlTypeHash();} GfmlTypeHash() {}
}
class GfmlTypRegy {//_20101027
	public boolean Has(String typeKey) {return hash.Has(typeKey);}
	public GfmlType FetchOrNull(String typeKey) {return FetchOrNull(typeKey, GfmlDocPos_.Root);}
	public GfmlType FetchOrNull(String typeKey, GfmlDocPos pos) {
		if (typeKey == null) throw Err_.new_wo_type("typeKey cannot be null when added to typRegy");
		GfmlType rv = (GfmlType)hash.Get_by(typeKey, pos);
		return rv == null ? GfmlType_.Null : rv;
	}
	public GfmlTypRegy Add(GfmlType type) {
		hash.Del(type);	// always replace existing with most recent			
		hash.Add(type);
		return this;
	}
	public void Add_ary(GfmlType... ary) {
		for (GfmlType type : ary)
			this.Add(type);
	}
	public void Del(GfmlType type) {hash.Del(type);}
	GfmlScopeRegy hash = GfmlScopeRegy.new_();
	public static GfmlTypRegy new_() {return new GfmlTypRegy();}
}