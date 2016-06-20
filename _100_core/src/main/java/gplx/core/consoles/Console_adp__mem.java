package gplx.core.consoles; import gplx.*; import gplx.core.*;
public class Console_adp__mem implements Console_adp {
	private final    List_adp written = List_adp_.New();
	private final    Hash_adp ignored = Hash_adp_.New();
	public boolean Enabled() {return true;}
	public boolean Canceled_chk() {return false;}
	public int Chars_per_line_max() {return 80;} public void Chars_per_line_max_(int v) {}
	public Console_adp__mem Ignore_add(String s) {ignored.Add_as_key_and_val(s); return this;}
	public void Write_str(String s) {WriteString(s);}
	public void Write_fmt_w_nl(String s, Object... args) {WriteString(String_.Format(s, args) + String_.CrLf);}
	public void Write_tmp(String s) {WriteString(s);}
	public String Read_line(String msg) {return "";}
	public char Read_key(String msg) {return '\0';}
	public Console_adp__mem CancelWhenTextWritten(String val) {
		cancelVal = val;
		return this;
	}
	void WriteString(String s) {
		if (ignored.Has(s)) return;
		written.Add(s);
		if (cancelVal != null && String_.Has(s, cancelVal)) throw Err_.new_wo_type("canceled", "cancel_val", s);
	}
	String cancelVal;

	public List_adp Written() {return written;}
	public void tst_WrittenStr(String... expd) {
		String[] actl = new String[written.Count()];
		int actlLength = Array_.Len(actl);
		for (int i = 0; i < actlLength; i++)
			actl[i] = written.Get_at(i).toString();
		Tfds.Eq_ary(actl, expd);
	}
}
