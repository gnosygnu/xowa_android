package gplx.core.intls.ucas; import gplx.*; import gplx.core.*; import gplx.core.intls.*;
public interface Uca_collator {
	void	Init(String locale, boolean numeric_ordering);
	byte[]	Get_sortkey(String s);
}
