package gplx.xowa.bldrs.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.ios.*;
class Sql_fld_mgr {
	public int Count() {return hash.Count();}
	public Sql_fld_itm Get_by_key(String fld) {return Get_by_key(Bry_.new_u8(fld));}
	public Sql_fld_itm Get_by_key(byte[] fld) {
		return (Sql_fld_itm)hash.Get_by(fld);
	}	private Ordered_hash hash = Ordered_hash_.New_bry();
	public Sql_fld_mgr Parse(byte[] raw) {
		hash.Clear();
		int bgn = Bry_find_.Find_fwd(raw, Tkn_create_table); if (bgn == Bry_find_.Not_found) throw Err_.new_wo_type("could not find 'CREATE TABLE'");
		bgn = Bry_find_.Find_fwd(raw, Byte_ascii.Nl, bgn); if (bgn == Bry_find_.Not_found) throw Err_.new_wo_type("could not find new line after 'CREATE TABLE'");
		bgn += Int_.Const_position_after_char;
		int end = Bry_find_.Find_fwd(raw, Tkn_unique_index);
		if (end == Bry_find_.Not_found) {	// as of 2016-07, en.w:categorylinks no longer has UNIQUE KEY; try PRIMARY KEY; DATE:2016-07-08
			end = Bry_find_.Find_fwd(raw, Tkn_primary_key);
			if (end == Bry_find_.Not_found)
				throw Err_.new_wo_type("could not find 'UNIQUE KEY' or 'PRIMARY KEY'");
		}
		end = Bry_find_.Find_bwd(raw, Byte_ascii.Nl, end); if (bgn == Bry_find_.Not_found) throw Err_.new_wo_type("could not find new line before 'UNIQUE KEY'");
		Parse_lines(Bry_.Mid(raw, bgn, end));
		return this;
	}
	private void Parse_lines(byte[] raw) {
		byte[][] lines = Bry_split_.Split(raw, Byte_ascii.Nl);
		int lines_len = lines.length;
		int fld_idx = 0;
		for (int i = 0; i < lines_len; i++) {
			byte[] line = lines[i];
			int bgn = Bry_find_.Find_fwd(line, Byte_ascii.Tick); if (bgn == Bry_find_.Not_found) continue;	// skip blank lines
			bgn += Int_.Const_position_after_char;
			int end = Bry_find_.Find_fwd(line, Byte_ascii.Tick, bgn); if (end == Bry_find_.Not_found) continue;	// skip blank lines
			byte[] key = Bry_.Mid(line, bgn, end);
			Sql_fld_itm fld = new Sql_fld_itm(fld_idx++, key);
			hash.Add(fld.Key(), fld);
		}
	}
	private static final    byte[] 
		Tkn_create_table	= Bry_.new_a7("CREATE TABLE")
	,	Tkn_unique_index	= Bry_.new_a7("UNIQUE KEY")
	,	Tkn_primary_key		= Bry_.new_a7("PRIMARY KEY")
	;
	public static final int Not_found = -1;
}
class Sql_fld_itm {
	public Sql_fld_itm(int idx, byte[] key) {this.idx = idx; this.key = key;}
	public int Idx() {return idx;} private int idx;
	public byte[] Key() {return key;} private byte[] key;
}
