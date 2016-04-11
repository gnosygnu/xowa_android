package gplx.dbs.qrys.bats; import gplx.*; import gplx.dbs.*; import gplx.dbs.qrys.*;
public class Db_batch_mgr {
	public Db_batch_grp		Conn_bgn() {return conn_bgn;} private final    Db_batch_grp conn_bgn = new Db_batch_grp(Db_batch_grp.Tid__conn_bgn);
	public Db_batch_grp		Conn_end() {return conn_end;} private final    Db_batch_grp conn_end = new Db_batch_grp(Db_batch_grp.Tid__conn_end);
	public Db_batch_grp		Txn_end () {return txn_end;}  private final    Db_batch_grp txn_end  = new Db_batch_grp(Db_batch_grp.Tid__txn_end);
	public void Copy(String src_tid, Db_batch_mgr src) {
		conn_bgn.Copy(src.conn_bgn);
		conn_end.Copy(src.conn_end);
		txn_end.Copy(src.txn_end);
	}
}
