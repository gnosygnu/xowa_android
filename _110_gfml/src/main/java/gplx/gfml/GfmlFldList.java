package gplx.gfml; import gplx.*;
public class GfmlFldList {//_20101027
	public int Count() {return hash.Count();}
	public GfmlFld Get_at(int index) {return (GfmlFld)hash.Get_at(index);}
	public GfmlFld Get_by(String id) {return (GfmlFld)hash.Get_by(id);}
	public void Add(GfmlFld fld) {
		if (String_.Len_eq_0(fld.Name())) throw Err_.new_wo_type("fld name cannot be null");
		if (hash.Has(fld.Name())) throw Err_.new_wo_type("key already exists", "key", fld.Name()); // FIXME: commented out to allow multiple types with same name; need "_type:invk"
		hash.Add_if_dupe_use_nth(fld.Name(), fld);
	}
	public void Del(GfmlFld fld) {
		hash.Del(fld);
		hash.Del(fld.Name());
	}
	Ordered_hash hash = Ordered_hash_.New();
	public static GfmlFldList new_() {return new GfmlFldList();} GfmlFldList() {}
}
