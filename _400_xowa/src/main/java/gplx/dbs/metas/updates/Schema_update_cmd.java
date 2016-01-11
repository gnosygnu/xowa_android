package gplx.dbs.metas.updates; import gplx.*; import gplx.dbs.*; import gplx.dbs.metas.*;
public interface Schema_update_cmd {
	String Name();
	boolean Exec_is_done();
	void Exec(Schema_db_mgr mgr, Db_conn conn);
}
