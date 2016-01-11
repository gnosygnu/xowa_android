package gplx.gfml; import gplx.*;
interface GfmlScopeItm {//_20101023
	String Key();
	GfmlDocPos DocPos();
}
class GfmlScopeRegy {
	public boolean Has(String key) {
		GfmlScopeList list = (GfmlScopeList)hash.Get_by(key); if (list == null) return false;
		return list.Count() > 0;
	}
	public void Add(GfmlScopeItm itm) {
		GfmlScopeList list = ItmOrNew(itm.Key());
		list.Add(itm);
	}
	public void Del(GfmlScopeItm itm) {
		GfmlScopeList list = (GfmlScopeList)hash.Get_by(itm.Key()); if (list == null) return;
		list.Del(itm);
		if (list.Count() == 0) hash.Del(itm.Key());
	}
	public GfmlScopeItm Get_by(String key, GfmlDocPos pos) {
		GfmlScopeList list = (GfmlScopeList)hash.Get_by(key); if (list == null) return null;
		return list.Get_by(pos);
	}
	GfmlScopeList ItmOrNew(String key) {
		GfmlScopeList rv = (GfmlScopeList)hash.Get_by(key);
		if (rv == null) {
			rv = GfmlScopeList.new_(key);
			hash.Add(key, rv);
		}
		return rv;
	}
	Hash_adp hash = Hash_adp_.new_();
	public static GfmlScopeRegy new_() {return new GfmlScopeRegy();}
}
class GfmlScopeList {
	public String Key() {return key;} private String key;
	public int Count() {return list.Count();}
	public void Add(GfmlScopeItm itm) {list.Add(itm);}
	public void Del(GfmlScopeItm itm) {list.Del(itm);}
	public GfmlScopeItm Get_by(GfmlDocPos pos) {
		if (list.Count() == 0) return null;
		GfmlScopeItm rv = null;
		for (Object itemObj : list) {
			GfmlScopeItm itm = (GfmlScopeItm)itemObj;
			if (CompareAble_.Is_moreOrSame(pos, itm.DocPos()))
				rv = itm;
			else
				break;	// ASSUME: insertion is done in order; first lessThan means rest will also be lessThan
		}
		return rv;
	}
	List_adp list = List_adp_.new_();
	public static GfmlScopeList new_(String key) {
		GfmlScopeList rv = new GfmlScopeList(); rv.key = key; return rv;
	}	GfmlScopeList() {}
}
