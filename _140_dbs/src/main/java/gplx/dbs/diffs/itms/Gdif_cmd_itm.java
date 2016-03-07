package gplx.dbs.diffs.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
public class Gdif_cmd_itm {
	public Gdif_cmd_itm(int grp_id, int cmd_id, int tid) {
		this.Grp_id = grp_id; this.Cmd_id = cmd_id; this.Tid = tid;
	}
	public final int Grp_id;
	public final int Cmd_id;
	public final int Tid;
	public String Data;

	public static final int Tid__data = 1;
}
