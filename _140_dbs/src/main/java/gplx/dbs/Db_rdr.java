package gplx.dbs; import gplx.*;
public interface Db_rdr {
	boolean		Move_next();
	byte[]		Read_bry(String k);
	void		Save_bry_in_parts(Io_url url, String tbl, String fld, String crt_key, Object crt_val);
	byte[]		Read_bry_by_str(String k);
	String 		Read_str(String k);
	byte 		Read_byte(String k);
	int 		Read_int(String k);
	long 		Read_long(String k);
	float		Read_float(String k);
	double		Read_double(String k);
	DateAdp		Read_date_by_str(String k);
	boolean		Read_bool_by_byte(String k);
	int			Fld_len();
	Object 		Read_obj(String k);
	Object 		Read_at(int i);
	void		Rls();
}
