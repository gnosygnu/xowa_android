package gplx.core.stores; import gplx.*; import gplx.core.*;
public interface SrlMgr {
	boolean		Type_rdr();
	Object		StoreRoot(SrlObj root, String key);

	boolean		SrlBoolOr(String key, boolean v);
	byte		SrlByteOr(String key, byte v);
	int			SrlIntOr(String key, int v);
	long		SrlLongOr(String key, long v);
	String		SrlStrOr(String key, String v);
	double		SrlDoubleOr(String key, double v);
	Decimal_adp	SrlDecimalOr(String key, Decimal_adp v);
	DateAdp		SrlDateOr(String key, DateAdp v);
	Object		SrlObjOr(String key, Object v);
	void		SrlList(String key, List_adp list, SrlObj proto, String itmKey);
	void		TypeKey_(String v);	
	SrlMgr		SrlMgr_new(Object o);
}
