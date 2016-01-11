package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.flds.*;
public class Xoctg_idx_itm {
	public int Pos() {return pos;} public Xoctg_idx_itm Pos_(int v) {pos = v; return this;} private int pos = -1;
	public int Id() {return id;} private int id;
	public int Timestamp() {return timestamp;} private int timestamp;
	public byte[] Sortkey() {return sortkey;} private byte[] sortkey;
	public int compareTo(Object obj) {Xoctg_idx_itm comp = (Xoctg_idx_itm)obj; return Int_.Compare(pos, comp.Pos());}
	public Xoctg_idx_itm Parse(Gfo_fld_rdr fld_rdr, int pos) {
		this.pos = pos;
		id				= fld_rdr.Read_int_base85_len5();
		timestamp		= fld_rdr.Read_int_base85_len5();
		sortkey			= fld_rdr.Read_bry_escape();
		return this;
	}
	public void Load(int id, byte[] sortkey, int timestamp) {
		this.id = id; this.sortkey = sortkey; this.timestamp = timestamp;
	}
	public void Copy(Xoctg_idx_itm orig) {
		this.pos = orig.pos;
		this.id = orig.id;
		this.timestamp = orig.timestamp;
		this.sortkey = orig.sortkey;
	}
}
class Xoctg_idx_itm_sorter_sortkey implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Xoctg_idx_itm lhs = (Xoctg_idx_itm)lhsObj;
		Xoctg_idx_itm rhs = (Xoctg_idx_itm)rhsObj;
		return Bry_.Compare(lhs.Sortkey(), rhs.Sortkey());
	}
	public static final Xoctg_idx_itm_sorter_sortkey Instance = new Xoctg_idx_itm_sorter_sortkey();
}
