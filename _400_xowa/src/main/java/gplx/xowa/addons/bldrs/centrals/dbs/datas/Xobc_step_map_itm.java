package gplx.xowa.addons.bldrs.centrals.dbs.datas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*;
public class Xobc_step_map_itm {
	public Xobc_step_map_itm(int sm_id, int task_id, int step_id, int step_seqn) {
		this.Sm_id = sm_id;
		this.Task_id = task_id;
		this.Step_id = step_id;
		this.Step_seqn = step_seqn;
	}
	public final    int Sm_id;
	public final    int Task_id;
	public final    int Step_id;
	public final    int Step_seqn;
}
