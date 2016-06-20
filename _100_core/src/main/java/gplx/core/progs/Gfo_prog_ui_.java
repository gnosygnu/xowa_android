package gplx.core.progs; import gplx.*; import gplx.core.*;
public class Gfo_prog_ui_ {
	public static final    Gfo_prog_ui Noop = new Gfo_prog_ui__noop(), Always = new Gfo_prog_ui__always();
	public static final byte 
	  Status__init			=  1
	, Status__working		=  2
	, Status__done			=  4
	, Status__fail			=  8
	, Status__suspended		= 16
	, Status__runnable		= Status__init | Status__suspended
	;
}
class Gfo_prog_ui__noop implements Gfo_prog_ui {
	public boolean		Canceled() {return true;}
	public void		Cancel() {}
	public byte		Prog_status() {return Gfo_prog_ui_.Status__init;}
	public void		Prog_status_(byte v) {}
	public long		Prog_data_cur() {return 0;}
	public long		Prog_data_end() {return 0;}
	public boolean		Prog_notify_and_chk_if_suspended(long cur, long max) {return false;}
}
class Gfo_prog_ui__always implements Gfo_prog_ui {
	public boolean		Canceled() {return false;}
	public void		Cancel() {}
	public byte		Prog_status() {return Gfo_prog_ui_.Status__init;}
	public void		Prog_status_(byte v) {}
	public long		Prog_data_cur() {return 0;}
	public long		Prog_data_end() {return 0;}
	public boolean		Prog_notify_and_chk_if_suspended(long cur, long max) {return false;}
}