package gplx.xowa.addons.bldrs.centrals; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.langs.jsons.*;
import gplx.xowa.addons.bldrs.centrals.cmds.*;
public class Xobc_task_bridge implements gplx.xowa.htmls.bridges.Bridge_cmd_itm {
	public void Init_by_app(Xoa_app app) {}
	public String Exec(Json_nde data) {
		Xobc_task_mgr task_mgr = Xobc_task_special.Task_mgr;
		byte proc_id = proc_hash.Get_as_byte_or(data.Get_as_bry_or(Msg__proc, null), Byte_ascii.Max_7_bit);
		Json_nde args = data.Get_kv(Msg__args).Val_as_nde();
		switch (proc_id) {
			case Proc__reload:					task_mgr.Reload(); break;
			case Proc__add_work:				task_mgr.Todo_mgr().Add_work(args.Get_as_int("task_id")); break;
			case Proc__del_work:				task_mgr.Work_mgr().Del_work(args.Get_as_int("task_id")); break;
			case Proc__del_done:				task_mgr.Done_mgr().Del_done(args.Get_as_int("task_id")); break;
			case Proc__run_next:				task_mgr.Work_mgr().Run_next(); break;
			case Proc__stop_cur:				task_mgr.Work_mgr().Stop_cur(); break;
			case Proc__redo_cur:				task_mgr.Work_mgr().Redo_cur(); break;
			case Proc__download_db:				gplx.xowa.addons.bldrs.centrals.dbs.Xobc_data_db_upgrader.Check_for_updates(task_mgr); break;
			default: throw Err_.new_unhandled_default(proc_id);
		}
		return "";
	}
	private static final    byte[] Msg__proc = Bry_.new_a7("proc"), Msg__args = Bry_.new_a7("args");
	private static final byte Proc__reload = 0, Proc__add_work = 1, Proc__del_work = 2, Proc__del_done = 3, Proc__run_next = 4, Proc__stop_cur = 5, Proc__redo_cur = 6, Proc__download_db = 7;
	private static final    Hash_adp_bry proc_hash = Hash_adp_bry.cs()
	.Add_str_byte("reload"						, Proc__reload)
	.Add_str_byte("add_work"					, Proc__add_work)
	.Add_str_byte("del_work"					, Proc__del_work)
	.Add_str_byte("del_done"					, Proc__del_done)
	.Add_str_byte("run_next"					, Proc__run_next)
	.Add_str_byte("stop_cur"					, Proc__stop_cur)
	.Add_str_byte("redo_cur"					, Proc__redo_cur)
	.Add_str_byte("download_db"					, Proc__download_db)
	;

	public byte[] Key() {return BRIDGE_KEY;} public static final    byte[] BRIDGE_KEY = Bry_.new_a7("builder_central.exec");
        public static final    Xobc_task_bridge Prototype = new Xobc_task_bridge(); Xobc_task_bridge() {}
}
