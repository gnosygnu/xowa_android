package gplx.core.criterias; import gplx.*; import gplx.core.*;
public interface Criteria extends To_str_able {
	byte Tid();
	boolean Matches(Object obj);
	void Val_from_args(Hash_adp args);
	void Val_as_obj_(Object obj);
}
