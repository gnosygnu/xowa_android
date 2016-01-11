package gplx.core.stores; import gplx.*; import gplx.core.*;
import gplx.core.gfo_ndes.*;
public interface DataWtr extends SrlMgr {
	Hash_adp EnvVars();

	void InitWtr(String key, Object val);
	void WriteTableBgn(String name, GfoFldList fields);
	void WriteNodeBgn(String nodeName);
	void WriteLeafBgn(String leafName);
	void WriteData(String name, Object val);
	void WriteNodeEnd();
	void WriteLeafEnd();

	void Clear();
	String To_str();
}
