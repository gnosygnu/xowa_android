package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*; import gplx.xowa.xtns.wbases.claims.itms.times.*;
public class Wbase_claim_time_ {
	public static final byte
	  Tid__time									= 0
	, Tid__timezone								= 1
	, Tid__before								= 2
	, Tid__after								= 3
	, Tid__precision							= 4
	, Tid__calendarmodel						= 5
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("claim.val.time", 6);
	public static final    Wbase_enum_itm
	  Itm__time						= Reg.Add(Tid__time				, "time")
	, Itm__timezone					= Reg.Add(Tid__timezone			, "timezone")
	, Itm__before					= Reg.Add(Tid__before			, "before")
	, Itm__after					= Reg.Add(Tid__after			, "after")
	, Itm__precision				= Reg.Add(Tid__precision		, "precision")
	, Itm__calendarmodel			= Reg.Add(Tid__calendarmodel	, "calendarmodel")
	;
	public static final    Wbase_data_itm 
	  Dflt__precision		= Wbase_data_itm.New_int(11)
	, Dflt__before			= Wbase_data_itm.New_int(0)
	, Dflt__after			= Wbase_data_itm.New_int(0)
	, Dflt__timezone		= Wbase_data_itm.New_int(0)
	, Dflt__calendarmodel	= Wbase_data_itm.New_str("http://www.wikidata.org/entity/Q1985727")
	;

	private static final    byte[] Bry_year_prefix = Bry_.new_a7("+0000000");
	public static byte[] To_bry(Bry_bfr bfr,  String date) {return To_bry(bfr, DateAdp_.parse_fmt(date, "yyyy-MM-dd HH:mm:ss"));}
	public static byte[] To_bry(Bry_bfr bfr, DateAdp date) {// +0000000yyyy-MM-ddTHH:mm:ssZ
		bfr
		.Add(Bry_year_prefix)
		.Add_int_fixed(date.Year(), 4)
		.Add_byte(Byte_ascii.Dash)
		.Add_int_fixed(date.Month(), 2)
		.Add_byte(Byte_ascii.Dash)
		.Add_int_fixed(date.Day(), 2)
		.Add_byte(Byte_ascii.Ltr_T)
		.Add_int_fixed(date.Hour(), 2)
		.Add_byte(Byte_ascii.Colon)
		.Add_int_fixed(date.Minute(), 2)
		.Add_byte(Byte_ascii.Colon)
		.Add_int_fixed(date.Second(), 2)
		.Add_byte(Byte_ascii.Ltr_Z)
		;
		return bfr.To_bry_and_clear();
	}
}
