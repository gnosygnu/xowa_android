package gplx.xowa.addons.bldrs.exports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
public class Xow_db_file_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New();
	public int Len() {return hash.Len();}
	public Xow_db_file Get_at(int i) {return (Xow_db_file)hash.Get_at(i);}		
	public Xow_db_file_mgr Load_by_type(Xow_db_mgr db_mgr, int type) {
		int len = db_mgr.Dbs__len();
		for (int i = 0; i < len; ++i) {
			Xow_db_file db_file = db_mgr.Dbs__get_at(i);
			if (db_file.Tid() != type) continue;
			hash.Add(db_file.Url().Raw(), db_file);
		}
		return this;
	}
}
