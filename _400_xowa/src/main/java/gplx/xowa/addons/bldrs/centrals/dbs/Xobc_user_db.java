package gplx.xowa.addons.bldrs.centrals.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.dbs.*;
import gplx.xowa.addons.bldrs.centrals.dbs.users.*;
public class Xobc_user_db {
	public Xobc_user_db(Db_conn conn) {
		this.work_task_tbl = new Xobc_work_task_tbl(conn);
		this.done_task_tbl = new Xobc_done_task_tbl(conn);
		this.done_step_tbl = new Xobc_done_step_tbl(conn);
		conn.Meta_tbl_assert(work_task_tbl, done_task_tbl, done_step_tbl);
	}
	public Xobc_work_task_tbl Work_task_tbl() {return work_task_tbl;} private final    Xobc_work_task_tbl work_task_tbl;
	public Xobc_done_task_tbl Done_task_tbl() {return done_task_tbl;} private final    Xobc_done_task_tbl done_task_tbl;
	public Xobc_done_step_tbl Done_step_tbl() {return done_step_tbl;} private final    Xobc_done_step_tbl done_step_tbl;
}
