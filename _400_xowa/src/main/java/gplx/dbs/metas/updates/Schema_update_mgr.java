package gplx.dbs.metas.updates; import gplx.*; import gplx.dbs.*; import gplx.dbs.metas.*;
public class Schema_update_mgr {		
	private List_adp cmds = List_adp_.New();
	public void Add(Schema_update_cmd cmd) {cmds.Add(cmd);}
	public void Update(Schema_db_mgr schema_mgr, Db_conn conn) {
		int cmds_len = cmds.Count();
		for (int i = 0; i < cmds_len; ++i) {
			Schema_update_cmd cmd = (Schema_update_cmd)cmds.Get_at(i);
			try {cmd.Exec(schema_mgr, conn);}
			catch (Exception e) {
				Gfo_usr_dlg_.Instance.Warn_many("", "", "failed to run update cmd; name=~{0} err=~{1}", cmd.Name(), Err_.Message_gplx_full(e));
			}
		}
	}
}
