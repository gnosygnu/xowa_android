package gplx.dbs.diffs.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
public class Gdif_txn_itm {
	public Gdif_txn_itm(int job_id, int txn_id, int cmd_id, int owner_txn) {
		this.Job_id = job_id; this.Txn_id = txn_id; this.Cmd_id = cmd_id; this.Owner_txn = owner_txn;
	}
	public final int Job_id;
	public final int Txn_id;
	public final int Cmd_id;
	public final int Owner_txn;

	public static final int Owner_txn__null = 0;
}
