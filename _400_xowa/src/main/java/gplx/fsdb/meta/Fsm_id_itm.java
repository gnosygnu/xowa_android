package gplx.fsdb.meta; import gplx.*; import gplx.fsdb.*;
public class Fsm_id_itm {
	public Fsm_id_itm(String key, int id, int version) {this.key = key; this.id = id; this.version = version;}
	public String Key() {return key;} private final String key;
	public int Id() {return id;} private final int id;
	public int Version() {return version;} private final int version;
}
