package gplx.xowa.users.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
import gplx.dbs.*; import gplx.xowa.users.data.*;
public class Xoud_site_mgr {
	private Xoud_site_tbl tbl;
	private final    Xoud_id_mgr id_mgr;
	public Xoud_site_mgr(Xoud_id_mgr id_mgr) {this.id_mgr = id_mgr;}
	public void Conn_(Db_conn conn, boolean created) {
		tbl = new Xoud_site_tbl(conn);
		if (created) tbl.Create_tbl();
	}
	public Xoud_site_row[] Get_all() {return tbl.Select_all();}
	public void Import(String domain, String name, String path, String xtn) {	// insert or update wiki
		Xoud_site_row[] ary = tbl.Select_by_domain(domain);
		int len = ary.length, update_id = -1, priority = 0;
		for (int i = 0; i < len; ++i) {
			Xoud_site_row itm = ary[i];
			if (priority <= itm.Priority()) priority = itm.Priority() + 1;
			if (String_.Eq(path, itm.Path())) {	// same domain and same path; change insert to update;
				update_id = itm.Id();
				break;
			}
		}
		if (update_id == -1)
			tbl.Insert(id_mgr.Get_next_and_save("xowa.user.site"), priority, domain, name, path, xtn);
		else
			tbl.Update(update_id, priority, domain, name, path, xtn);			
	}
}
