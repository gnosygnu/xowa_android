package gplx.xowa.addons.bldrs.centrals.tasks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
public class Xobc_task_regy__done extends Xobc_task_regy__base {
	public Xobc_task_regy__done(Xobc_task_mgr task_mgr) {super(task_mgr, "done");}
	public void Del_done(int task_id) {
		task_mgr.User_db().Done_task_tbl().Delete(task_id);
		task_mgr.Transfer(this, task_mgr.Todo_mgr(), this.Get_by(task_id));
	}
}
