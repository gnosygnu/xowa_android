package gplx.core.stores; import gplx.*; import gplx.core.*;
public abstract class DataWtr_base implements SrlMgr {
	@gplx.Virtual public Hash_adp EnvVars() {return envVars;} Hash_adp envVars = Hash_adp_.new_();
	public boolean Type_rdr() {return false;}
	public abstract void WriteData(String key, Object o);
	public abstract void WriteNodeBgn(String nodeName);
	public abstract void WriteNodeEnd();
	@gplx.Virtual public void SrlList(String key, List_adp list, SrlObj proto, String itmKey) {
		this.WriteNodeBgn(key);
		for (Object itmObj : list) {
			SrlObj itm = (SrlObj)itmObj;
			this.WriteNodeBgn(itmKey);
			itm.SrlObj_Srl(this);
			this.WriteNodeEnd();
		}
		this.WriteNodeEnd();
	}
	@gplx.Virtual public Object StoreRoot(SrlObj root, String key) {
		this.WriteNodeBgn(key);
		root.SrlObj_Srl(this);
		this.WriteNodeEnd();
		return root;
	}
	public boolean SrlBoolOr(String key, boolean v) {WriteData(key, v); return v;}
	public byte SrlByteOr(String key, byte v) {WriteData(key, v); return v;}
	public int SrlIntOr(String key, int or) {WriteData(key, or); return or;}
	public long SrlLongOr(String key, long or) {WriteData(key, or); return or;}
	public String SrlStrOr(String key, String or) {WriteData(key, or); return or;}
	public DateAdp SrlDateOr(String key, DateAdp or) {WriteData(key, or.XtoStr_gplx()); return or;}
	public Decimal_adp SrlDecimalOr(String key, Decimal_adp or) {WriteData(key, or.Under()); return or;}
	public double SrlDoubleOr(String key, double or) {WriteData(key, or); return or;}
	public Object SrlObjOr(String key, Object or) {throw Err_.new_unimplemented();}
	public void TypeKey_(String v) {}
	public abstract SrlMgr SrlMgr_new(Object o);
}
