package gplx.core.stores; import gplx.*; import gplx.core.*;
import gplx.core.gfo_ndes.*;
public class DataWtr_ {
	public static final    DataWtr Null = new DataWtr_null();
}
class DataWtr_null implements DataWtr {
	public boolean Type_rdr() {return false;}
	public Hash_adp EnvVars() {return envVars;} Hash_adp envVars = Hash_adp_.New();
	public void InitWtr(String key, Object val) {}
	public void WriteTableBgn(String name, GfoFldList fields) {}
	public void WriteNodeBgn(String nodeName) {}
	public void WriteLeafBgn(String leafName) {}
	public void WriteData(String name, Object val) {}
	public void WriteNodeEnd() {}
	public void WriteLeafEnd() {}
	public void Clear() {}
	public String To_str() {return "";}
	public Object StoreRoot(SrlObj root, String key) {return null;}
	public boolean SrlBoolOr(String key, boolean v) {return v;}
	public byte SrlByteOr(String key, byte v) {return v;}
	public int SrlIntOr(String key, int or) {return or;}
	public long SrlLongOr(String key, long or) {return or;}
	public String SrlStrOr(String key, String or) {return or;}
	public DateAdp SrlDateOr(String key, DateAdp or) {return or;}
	public Decimal_adp SrlDecimalOr(String key, Decimal_adp or) {return or;}
	public double SrlDoubleOr(String key, double or) {return or;}
	public Object SrlObjOr(String key, Object or) {return or;}
	public void SrlList(String key, List_adp list, SrlObj proto, String itmKey) {}
	public void TypeKey_(String v) {}
	public SrlMgr SrlMgr_new(Object o) {return this;}
}
