package gplx.dbs.qrys.bats; import gplx.*; import gplx.dbs.*; import gplx.dbs.qrys.*;
import gplx.dbs.engines.*;
public interface Db_batch_itm {
	String Key();
	void Qry_bat__run(Db_engine engine);
}
