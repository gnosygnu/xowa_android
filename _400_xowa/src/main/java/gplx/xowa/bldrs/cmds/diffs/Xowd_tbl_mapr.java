package gplx.xowa.bldrs.cmds.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.xowa.wikis.data.*;
class Xowd_tbl_mapr {
	public Xowd_tbl_mapr(String name, int[] db_ids) {
		this.Name = name;
		this.Db_ids = db_ids; 
	}
	public final    String Name;
	public final    int[] Db_ids;
	public boolean Db_ids__has(int id) {return true;}
//		private static List_adp Fill_tbl_names(List_adp rv, int db_tid) {
//			switch (db_tid) {
//				case Xow_db_file_.Tid__cat:
//					return 
//					break;
//		}
}
