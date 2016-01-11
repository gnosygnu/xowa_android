package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.type_xtns.*;
public interface GfoFldList {//_20110416
	int Count();
	boolean Has(String key);
	int Idx_of(String key);
	GfoFld Get_at(int i);
	GfoFld FetchOrNull(String key);
	GfoFldList Add(String key, ClassXtn c);
	String To_str();
}
