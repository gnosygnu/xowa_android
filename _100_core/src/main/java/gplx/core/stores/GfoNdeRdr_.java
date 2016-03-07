package gplx.core.stores; import gplx.*; import gplx.core.*;
import gplx.core.gfo_ndes.*; import gplx.core.type_xtns.*;
public class GfoNdeRdr_ {
	public static GfoNdeRdr kvs_(Keyval_list kvList) {
		GfoFldList flds = GfoFldList_.new_();
		int pairsLen = kvList.Count();
		Object[] vals = new Object[pairsLen];
		for (int i = 0; i < pairsLen; i++) {
			Keyval pair = kvList.Get_at(i);
			flds.Add(pair.Key(), StringClassXtn.Instance);
			vals[i] = pair.Val_to_str_or_empty();
		}
		GfoNde nde = GfoNde_.vals_(flds, vals);
		return root_(nde, true);
	}
	public static GfoNdeRdr root_parseNot_(GfoNde root) {return root_(root, true);}
	public static GfoNdeRdr root_(GfoNde root, boolean parse) {
		DataRdr_mem rv = DataRdr_mem.new_(root, root.Flds(), root.Subs()); rv.Parse_set(parse);
		return rv;
	}
	public static GfoNdeRdr leaf_(GfoNde cur, boolean parse) {
		DataRdr_mem rv = DataRdr_mem.new_(cur, cur.Flds(), GfoNdeList_.Null); rv.Parse_set(parse);
		return rv;
	}
	public static GfoNdeRdr peers_(GfoNdeList peers, boolean parse) {
		GfoFldList flds = peers.Count() == 0 ? GfoFldList_.Null : peers.FetchAt_asGfoNde(0).Flds();
		DataRdr_mem rv = DataRdr_mem.new_(null, flds, peers); rv.Parse_set(parse);
		return rv;
	}
	public static GfoNdeRdr as_(Object obj) {return obj instanceof GfoNdeRdr ? (GfoNdeRdr)obj : null;}
	public static GfoNdeRdr cast(Object obj) {try {return (GfoNdeRdr)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfoNdeRdr.class, obj);}}
}
