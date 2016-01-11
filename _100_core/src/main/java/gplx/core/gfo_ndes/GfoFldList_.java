package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.strings.*; import gplx.core.type_xtns.*;
public class GfoFldList_ {//_20110416
	public static final GfoFldList Null = new GfoFldList_null();
	public static GfoFldList new_() {return new GfoFldList_base();}
	public static GfoFldList str_(String... names) {
		GfoFldList rv = new GfoFldList_base();
		for (String name : names)
			rv.Add(name, StringClassXtn.Instance);
		return rv;
	}
}
class GfoFldList_base implements GfoFldList {//_20110416
	public int Count() {return hash.Count();}
	public boolean Has(String key) {return hash.Has(key);}
	public int Idx_of(String key) {			
		Object rv = idxs.Get_by(key);
		return rv == null ? List_adp_.NotFound : Int_.cast(rv);
	}
	public GfoFld Get_at(int i) {return (GfoFld)hash.Get_at(i);}
	public GfoFld FetchOrNull(String key) {return (GfoFld)hash.Get_by(key);}
	public GfoFldList Add(String key, ClassXtn c) {
		GfoFld fld = GfoFld.new_(key, c);
		hash.Add(key, fld);
		idxs.Add(key, idxs.Count());
		return this;
	}
	public String To_str() {
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < hash.Count(); i++) {
			GfoFld fld = this.Get_at(i);
			sb.Add(fld.Key()).Add("|");
		}
		return sb.To_str();
	}
	Ordered_hash hash = Ordered_hash_.New(); Hash_adp idxs = Hash_adp_.new_(); // PERF: idxs used for Idx_of; need to recalc if Del ever added 
}
class GfoFldList_null implements GfoFldList {//_20110416
	public int Count() {return 0;}
	public boolean Has(String key) {return false;}
	public int Idx_of(String key) {return List_adp_.NotFound;}
	public GfoFld Get_at(int i) {return GfoFld.Null;}
	public GfoFld FetchOrNull(String key) {return null;}
	public GfoFldList Add(String key, ClassXtn typx) {return this;}
	public String To_str() {return "<<GfoFldList_.Null>>";}
}