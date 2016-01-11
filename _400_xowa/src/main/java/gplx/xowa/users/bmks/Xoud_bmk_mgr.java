package gplx.xowa.users.bmks; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
import gplx.dbs.*;
public class Xoud_bmk_mgr {		
	public Xoud_bmk_itm_tbl Tbl__itm() {return tbl__itm;} private Xoud_bmk_itm_tbl tbl__itm;
	public Xoud_bmk_dir_tbl Tbl__dir() {return tbl__dir;} private Xoud_bmk_dir_tbl tbl__dir;
	public void Conn_(Db_conn conn, boolean created) {
		this.tbl__dir = new Xoud_bmk_dir_tbl(conn);
		this.tbl__itm = new Xoud_bmk_itm_tbl(conn);
		// if (!conn.Meta_tbl_exists(tbl__dir.Tbl_name())) tbl__dir.Create_tbl();	// bmk_v2
		if (!conn.Meta_tbl_exists(tbl__itm.Tbl_name())) tbl__itm.Create_tbl();
	}
	public void Itms__add(int owner, Xoa_url url) {
		tbl__itm.Insert(owner, tbl__itm.Select_sort_next(owner), Xoa_ttl.Replace_unders(url.Page_bry()), url.Wiki_bry(), url.To_bry(Bool_.Y, Bool_.Y), Bry_.Empty);
	}
	public static final int Owner_root = -1;
}
