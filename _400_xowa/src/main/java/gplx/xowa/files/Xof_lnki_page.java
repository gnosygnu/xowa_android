package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.stores.*;
import gplx.dbs.*;
public class Xof_lnki_page {
	public static final int		Null = -1;
	public static boolean		Null_y(int v) {return v == Null;}
	public static boolean		Null_n(int v) {return v != Null;}
	public static int		Db_load_int(DataRdr rdr, String fld)	{return rdr.ReadInt(fld);}
	public static int		Db_load_int(Db_rdr rdr, String fld)		{return rdr.Read_int(fld);}
	public static int		Db_save_int(int v) {return v;}
}
