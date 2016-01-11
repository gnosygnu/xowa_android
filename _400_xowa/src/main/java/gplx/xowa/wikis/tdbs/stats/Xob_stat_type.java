package gplx.xowa.wikis.tdbs.stats; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.tdbs.*;
import gplx.core.strings.*; import gplx.xowa.wikis.tdbs.*;
public class Xob_stat_type {
	public byte Tid() {return tid;} private byte tid;
	public Xob_stat_type(byte tid) {this.tid = tid;}
	public Xob_stat_itm GetOrNew(String ns) {
		Xob_stat_itm rv = (Xob_stat_itm)regy.Get_by(ns);
		if (rv == null) {
			rv = new Xob_stat_itm(ns);
			regy.Add(ns, rv);
		}
		return rv;
	}
	public Xob_stat_itm GetAt(int i) {return (Xob_stat_itm)regy.Get_at(i);}
	public int Count() {return regy.Count();}
	public void To_str(String_bldr sb) {
		for (int i = 0; i < regy.Count(); i++) {
			Xob_stat_itm itm = (Xob_stat_itm)regy.Get_at(i);
			sb.Add(Xotdb_dir_info_.Tid_name(tid)).Add(Xob_stat_itm.Dlm);
			itm.To_str(sb);
			sb.Add(Byte_ascii.Nl);
		}
	}
	Ordered_hash regy = Ordered_hash_.New();
	public static final Xob_stat_type Instance = new Xob_stat_type(); Xob_stat_type() {}
}
