package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
import gplx.core.lists.*;
public class ClassXtnPool extends Hash_adp_base {
	public void Add(ClassXtn typx) {Add_base(typx.Key(), typx);}
	public ClassXtn Get_by_or_fail(String key) {return (ClassXtn)Get_by_or_fail_base(key);}

	public static final ClassXtnPool Instance =  new ClassXtnPool();
	public static final String Format_null = "";
	public static ClassXtnPool new_() {return new ClassXtnPool();}
	ClassXtnPool() {
		Add(ObjectClassXtn.Instance);
		Add(StringClassXtn.Instance);
		Add(IntClassXtn.Instance);
		Add(BoolClassXtn.Instance);
		Add(ByteClassXtn.Instance);
		Add(DateAdpClassXtn.Instance);
		Add(TimeSpanAdpClassXtn.Instance);
		Add(IoUrlClassXtn.Instance);
		Add(DecimalAdpClassXtn.Instance);
		Add(FloatClassXtn.Instance);
	}
}
