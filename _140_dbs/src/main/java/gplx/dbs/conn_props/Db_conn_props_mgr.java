package gplx.dbs.conn_props; import gplx.*; import gplx.dbs.*;
public class Db_conn_props_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New();
	public boolean Has(String key) {return hash.Has(key);}
	public boolean Match(String key, String expd_val) {
		String actl_val = (String)hash.Get_by(key);
		return actl_val == null ? false : String_.Eq(expd_val,actl_val);
	}
	public void Add(String key, String val) {hash.Add(key, val);}
	public void Del(String key)				{hash.Del(key);}
}
