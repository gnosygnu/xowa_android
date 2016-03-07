package gplx.dbs; import gplx.*;
public class Db_rdr_ {
	public static final Db_rdr Empty = new Db_rdr__empty();
}
class Db_rdr__empty implements Db_rdr {
	public boolean			Move_next()						{return false;}
	public byte[]		Read_bry(String k)				{return Bry_.Empty;}
	public byte[]		Read_bry_by_str(String k)		{return Bry_.Empty;}
	public void			Save_bry_in_parts(Io_url url, String tbl, String fld, String crt_key, Object crt_val) {}
	public byte			Read_byte(String k)				{return Byte_.Max_value_127;}
	public String 		Read_str(String k)				{return String_.Empty;}
	public DateAdp		Read_date_by_str(String k)		{return DateAdp_.MinValue;}
	public int 			Read_int(String k)				{return Int_.Min_value;}
	public long 		Read_long(String k)				{return Long_.Min_value;}
	public float		Read_float(String k)			{return Float_.NaN;}
	public double		Read_double(String k)			{return Double_.NaN;}
	public boolean			Read_bool_by_byte(String k)		{return false;}
	public int			Fld_len()						{return 0;}
	public Object 		Read_obj(String k)				{return null;}
	public Object 		Read_at(int i)					{return null;}
	public void			Rls() {}
}
