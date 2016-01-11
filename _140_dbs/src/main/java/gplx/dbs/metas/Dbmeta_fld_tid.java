package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
import gplx.dbs.engines.sqlite.*;
public class Dbmeta_fld_tid {
	public Dbmeta_fld_tid(int tid_ansi, int tid_sqlite, byte[] name, int len_1, int len_2) {
		this.tid_ansi = tid_ansi; this.tid_sqlite = tid_sqlite; this.name = name; this.len_1 = len_1; this.len_2 = len_2;
	}
	public int Tid_ansi()	{return tid_ansi;} private final int tid_ansi;
	public int Tid_sqlite() {return tid_sqlite;} private final int tid_sqlite;
	public byte[] Name()	{return name;} private final byte[] name;
	public int Len_1()		{return len_1;} private final int len_1;
	public int Len_2()		{return len_2;} private final int len_2;
	public boolean Eq(Dbmeta_fld_tid comp) {
		return	tid_ansi == comp.tid_ansi
			&&	tid_sqlite == comp.tid_sqlite
			&&	Bry_.Eq(name, comp.name)
			&&	len_1 == comp.len_1
			&&	len_2 == comp.len_2;
	}

	public static final int Tid__bool = 0, Tid__byte = 1, Tid__short = 2, Tid__int = 3, Tid__long = 4, Tid__float = 5, Tid__double = 6, Tid__str = 7, Tid__text = 8, Tid__bry = 9, Tid__decimal = 10, Tid__date = 11;
	public static final Dbmeta_fld_tid 
	  Itm__byte			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__byte		, Sqlite_tid.Tid_int		, Bry_.new_a7("tinyint")	, -1, -1)
	, Itm__short		= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__short		, Sqlite_tid.Tid_int		, Bry_.new_a7("smallint")	, -1, -1)
	, Itm__int			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__int		, Sqlite_tid.Tid_int		, Bry_.new_a7("integer")	, -1, -1)
	, Itm__long			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__long		, Sqlite_tid.Tid_int		, Bry_.new_a7("bigint")		, -1, -1)
	, Itm__text			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__text		, Sqlite_tid.Tid_text		, Bry_.new_a7("text")		, -1, -1)
	, Itm__bry			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__bry		, Sqlite_tid.Tid_none		, Bry_.new_a7("blob")		, -1, -1)
	, Itm__float		= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__float		, Sqlite_tid.Tid_real		, Bry_.new_a7("float")		, -1, -1)
	, Itm__double		= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__double		, Sqlite_tid.Tid_real		, Bry_.new_a7("double")		, -1, -1)
	, Itm__numeric		= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__decimal	, Sqlite_tid.Tid_numeric	, Bry_.new_a7("numeric")	, -1, -1)
	, Itm__bool			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__bool		, Sqlite_tid.Tid_numeric	, Bry_.new_a7("bit")		, -1, -1)	// "bit" is not SQLITE
	, Itm__date			= new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__date		, Sqlite_tid.Tid_numeric	, Bry_.new_a7("date")		, -1, -1)
	;
	public static Dbmeta_fld_tid Itm__str		(int len)				{return new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__str		, Sqlite_tid.Tid_text		, Bry_.new_a7("varchar")	, len, -1);}
	public static Dbmeta_fld_tid Itm__decimal	(int len_1, int len_2)	{return new Dbmeta_fld_tid(Dbmeta_fld_tid.Tid__decimal	, Sqlite_tid.Tid_numeric	, Bry_.new_a7("decimal")	, len_1, len_2);}
	public static Dbmeta_fld_tid New(int tid, int len1) {
		switch (tid) {
			case Dbmeta_fld_tid.Tid__bool:		return Itm__bool;
			case Dbmeta_fld_tid.Tid__byte:		return Itm__byte;
			case Dbmeta_fld_tid.Tid__short:		return Itm__short;
			case Dbmeta_fld_tid.Tid__int:		return Itm__int;
			case Dbmeta_fld_tid.Tid__long:		return Itm__long;
			case Dbmeta_fld_tid.Tid__float:		return Itm__float;
			case Dbmeta_fld_tid.Tid__double:	return Itm__double;
			case Dbmeta_fld_tid.Tid__str:		return Itm__str(len1);
			case Dbmeta_fld_tid.Tid__text:		return Itm__text;
			case Dbmeta_fld_tid.Tid__bry:		return Itm__bry;
			case Dbmeta_fld_tid.Tid__date:		return Itm__date;
			case Dbmeta_fld_tid.Tid__decimal:	//	return Itm__decimal(len1);
			default:							throw Err_.new_unhandled(tid);
		}
	}
}
