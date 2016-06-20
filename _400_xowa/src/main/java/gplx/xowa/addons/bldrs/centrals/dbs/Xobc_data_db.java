package gplx.xowa.addons.bldrs.centrals.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.dbs.*;
import gplx.xowa.addons.bldrs.centrals.dbs.datas.*; import gplx.xowa.addons.bldrs.centrals.dbs.datas.imports.*;
public class Xobc_data_db {
	public Xobc_data_db(Io_url db_url) {
		this.url = db_url;
		this.conn = Db_conn_bldr.Instance.Get_or_autocreate(true, db_url);
		this.tbl__task_regy = new Xobc_task_regy_tbl(conn);
		this.tbl__step_regy = new Xobc_step_regy_tbl(conn);
		this.tbl__step_map = new Xobc_step_map_tbl(conn);
		this.tbl__import_step = new Xobc_import_step_tbl(conn);
		this.tbl__host_regy = new Xobc_host_regy_tbl(conn);
		this.tbl__version_regy = new Xobc_version_regy_tbl(conn);
		conn.Meta_tbl_assert(tbl__task_regy, tbl__step_regy, tbl__step_map, tbl__import_step, tbl__host_regy, tbl__version_regy);
	}
	public Db_conn					Conn() {return conn;} private final    Db_conn conn;
	public Io_url					Url() {return url;} private final    Io_url url;
	public Xobc_task_regy_tbl		Tbl__task_regy()	{return tbl__task_regy;}		private final    Xobc_task_regy_tbl tbl__task_regy;
	public Xobc_step_regy_tbl		Tbl__step_regy()	{return tbl__step_regy;}		private final    Xobc_step_regy_tbl tbl__step_regy;
	public Xobc_step_map_tbl		Tbl__step_map()		{return tbl__step_map;}			private final    Xobc_step_map_tbl tbl__step_map;
	public Xobc_import_step_tbl		Tbl__import_step()	{return tbl__import_step;}		private final    Xobc_import_step_tbl tbl__import_step;
	public Xobc_host_regy_tbl 		Tbl__host_regy()	{return tbl__host_regy;}		private final    Xobc_host_regy_tbl tbl__host_regy;
	public Xobc_version_regy_tbl 	Tbl__version_regy()	{return tbl__version_regy;}		private final    Xobc_version_regy_tbl tbl__version_regy;

	public void Delete_by_import(byte[] wiki_abrv, String wiki_date) {
		// get all step ids from import_regy
		Xobc_task_step_hash task_step_hash = new Xobc_task_step_hash();
		tbl__import_step.Select_tasks_steps(task_step_hash, tbl__step_map, wiki_abrv, wiki_date);
		for (int i = 0; i < task_step_hash.Tasks__len(); ++i) {
			int task_id = task_step_hash.Tasks__get_at(i);
			tbl__task_regy.Delete(task_id);
			tbl__step_map.Delete_by_task_id(task_id);
		}
		for (int i = 0; i < task_step_hash.Steps__len(); ++i) {
			int step_id = task_step_hash.Steps__get_at(i);
			tbl__step_regy.Delete(step_id);
			tbl__import_step.Delete(step_id);
		}
	}
	private final    gplx.core.lists.hashs.Hash_adp__int host_hash = new gplx.core.lists.hashs.Hash_adp__int();
	public String Bld_src_http_url(gplx.core.brys.evals.Bry_eval_mgr eval_mgr, int host_id, String file_name) {
		Xobc_host_regy_itm host_itm = (Xobc_host_regy_itm)host_hash.Get_by_or_null(host_id);
		if (host_itm == null) {
			host_itm = tbl__host_regy.Select(host_id);
			host_hash.Add(host_id, host_itm);
		}
		String host_dir = String_.new_u8(eval_mgr.Eval(Bry_.new_u8(host_itm.Host_data_dir())));
		return String_.Format("http://{0}/{1}/{2}", host_itm.Host_domain(), host_dir, file_name);
	}
}
