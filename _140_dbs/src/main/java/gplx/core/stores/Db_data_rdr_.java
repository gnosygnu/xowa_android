package gplx.core.stores; import gplx.*; import gplx.core.*;
//#{import
import java.sql.ResultSet;
//#}
public class Db_data_rdr_ {
	//#{Db_data_rdr_
	public static Db_data_rdr new_(ResultSet rdr, String commandText) {return new Db_data_rdr().ctor_db_data_rdr(rdr, commandText);}
	//#}
}
