package gplx.core.stores; import gplx.*; import gplx.core.*;
public class DbMaprArg {
	public String ObjProp() {return objProp;} private String objProp;
	public String DbFld() {return dbFld;} private String dbFld;
	public static DbMaprArg new_(String objProp, String dbFld) {
		DbMaprArg rv = new DbMaprArg();
		rv.objProp = objProp; rv.dbFld = dbFld;
		return rv;
	}
}
