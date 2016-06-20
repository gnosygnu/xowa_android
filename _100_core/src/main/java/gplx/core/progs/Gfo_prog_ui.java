package gplx.core.progs; import gplx.*; import gplx.core.*;
public interface Gfo_prog_ui extends Cancelable {
	byte			Prog_status();
	void			Prog_status_(byte v);
	long			Prog_data_cur();
	long			Prog_data_end();
	boolean			Prog_notify_and_chk_if_suspended(long cur, long max);
}
