package gplx.dbs.diffs; import gplx.*; import gplx.dbs.*;
public class Gdif_db_ {
	public static final String
	  Fld__dif_txn			= "dif_txn"		// 0+ where 0+ is defined in a tbl
	, Fld__dif_uid			= "dif_uid"		// 0+
	, Fld__dif_type			= "dif_type"	// I,U,D,M
	, Fld__dif_db_trg		= "dif_db_trg"	// -1 for single-db tables; 0+ for multiple-db tables
	, Fld__dif_db_src		= "dif_db_src"	// -1 for I,U,D; 0+ for M
	;
	public static final byte
	  Tid__insert = 0
	, Tid__update = 1
	, Tid__delete = 2
	, Tid__move   = 3
	;
}
