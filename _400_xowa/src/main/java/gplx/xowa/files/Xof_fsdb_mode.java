package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
public class Xof_fsdb_mode {
	private int tid;
	Xof_fsdb_mode(int tid) {this.tid = tid;}
//		public boolean Tid_v0()			{return tid == Tid_int_v0;}
	public boolean Tid_v2_gui()		{return tid == Tid_int_v2_gui;}
	public boolean Tid_v2_bld()		{return tid == Tid_int_v2_bld;}
	public void Tid_v2_bld_y_()		{tid = Tid_int_v2_bld;}
	private static final int
	  Tid_int_v0		= 1
	, Tid_int_v2_gui	= 2
	, Tid_int_v2_bld	= 3
	;
	public static Xof_fsdb_mode new_v0()		{return new Xof_fsdb_mode(Tid_int_v0);}
	public static Xof_fsdb_mode new_v2_gui()	{return new Xof_fsdb_mode(Tid_int_v2_gui);}
}
