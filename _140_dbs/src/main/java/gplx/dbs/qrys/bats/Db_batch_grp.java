package gplx.dbs.qrys.bats; import gplx.*; import gplx.dbs.*; import gplx.dbs.qrys.*;
import gplx.dbs.engines.*;
public class Db_batch_grp {
	public final    Ordered_hash hash = Ordered_hash_.New();
	public Db_batch_grp(byte tid) {this.tid = tid;}
	public byte Tid() {return tid;} private final    byte tid;
	public int Len()								{return hash.Len();}
	public Db_batch_itm Get_at(int idx)				{return (Db_batch_itm)hash.Get_at(idx); }
	public void Add(Db_batch_itm itm)				{hash.Add(itm.Key(), itm);}
	public void Del(String key)						{hash.Del(key);}
	public void Run(Db_engine engine) {
		int len = hash.Len();
		for (int i = 0; i < len; ++i) {
			Db_batch_itm itm = (Db_batch_itm)hash.Get_at(i);
			itm.Qry_bat__run(engine);
		}
	}
	public void Copy(Db_batch_grp src) {
		int len = src.Len();
		for (int i = 0; i < len; ++i) {
			Db_batch_itm itm = src.Get_at(i);
			this.Add(itm);
		}
	}
	public static final byte Tid__conn_bgn = 0, Tid__conn_end = 1, Tid__txn_bgn = 2, Tid__txn_end = 3; 
}
