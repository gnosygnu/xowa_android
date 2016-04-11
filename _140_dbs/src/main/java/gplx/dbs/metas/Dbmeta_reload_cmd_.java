package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
public class Dbmeta_reload_cmd_ {
	public static final    Dbmeta_reload_cmd Noop = new Dbmeta_reload_cmd__noop();
}
class Dbmeta_reload_cmd__noop implements Dbmeta_reload_cmd {
	public void Load_all() {}
}
