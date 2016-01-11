package gplx.core.consoles; import gplx.*; import gplx.core.*;
public interface Console_adp {
	boolean Enabled();	// optimization; allows Write to be skipped (since Write may Concat strings or generate arrays)
	boolean Canceled_chk();
	int Chars_per_line_max(); void Chars_per_line_max_(int v);
	void Write_str(String s);
	void Write_fmt_w_nl(String fmt, Object... args);
	void Write_tmp(String s);
	char Read_key(String msg);
	String Read_line(String msg);
}
