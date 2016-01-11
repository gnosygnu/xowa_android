package gplx.dbs;
import gplx.*; import gplx.dbs.*;
import gplx.dbs.engines.*; import gplx.dbs.engines.sqlite.*; import gplx.dbs.metas.*; import gplx.dbs.qrys.*; import gplx.dbs.sqls.*;
import gplx.core.strings.*; import gplx.core.brys.fmtrs.*; import gplx.core.stores.*;
import android.content.*; import android.database.*; import android.database.sqlite.*;

public class Drd_db_mgr extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public Drd_db_mgr(Context context, String conn_info) {super(context, conn_info, null, DATABASE_VERSION);}
    @Override public void onCreate(SQLiteDatabase db) {}
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public static void Setup(Context context) {
        Db_conn_bldr.Instance.Reg_default_sqlite();
        Drd_db_engine prototype = new Drd_db_engine(null, context);
        Db_conn_pool.Instance.Engines__add(prototype);
    }
}
class Drd_db_rdr implements Db_rdr {
    private final Drd_db_engine engine;
    private final Cursor cursor;
    private final Db_stmt stmt;
    public Drd_db_rdr(Drd_db_engine engine, Db_stmt stmt, Cursor cursor) {
        this.engine = engine; this.stmt = stmt; this.cursor = cursor;
    }
    private boolean move_first_needed = true;
    public boolean Move_next() {
        boolean rv = false;
        try {
            if (move_first_needed) {
                move_first_needed = false;
                rv = cursor.moveToFirst();
            }
            else {
                rv = cursor.moveToNext();
            }
        } catch (Exception e) {
            Gfo_usr_dlg_.Instance.Warn_many("", "", "failed; e=~{0}", Err_.Message_gplx_full(e));
        }
        return rv;
    }
    public byte[]		Read_bry(String k) {return cursor.getBlob(cursor.getColumnIndex(k));}
    public void			Save_bry_in_parts(Io_url url, String tbl, String fld, String crt_key, Object crt_val) {
        SQLiteDatabase db = engine.Db();
        String[] crt_args = String_.Ary(Object_.Xto_str_strict_or_null(crt_val));
        Cursor len_cursor = Open_cursor(db, String_.Format("SELECT LENGTH({1}) AS len FROM {0} WHERE {2} = ?", tbl, fld, crt_key), crt_args);
        int len_val = len_cursor.getInt(0);
        len_cursor.close();

        Io_mgr.Instance.DeleteFil_args(url).MissingFails_off().Exec();
        int increment = 1000000;
        for (int rng_bgn = 1; rng_bgn <= len_val; rng_bgn += increment) {
            int substr_len = increment;
            if (rng_bgn + substr_len > len_val) substr_len = len_val - rng_bgn;
            Cursor bin_cursor = Open_cursor(db, String_.Format("SELECT SUBSTR({1}, {3}, {4}) AS partial FROM {0} WHERE {2} = ?", tbl, fld, crt_key, rng_bgn, substr_len), crt_args);
            byte[] partial = bin_cursor.getBlob(0);
            Io_mgr.Instance.AppendFilByt(url, partial);
            bin_cursor.close();
        }
    }
    private Cursor Open_cursor(SQLiteDatabase db, String sql, String[] args) {
        Cursor rv = db.rawQuery(sql, args);
        if (rv == null || !rv.moveToFirst()) throw Err_.new_wo_type("failed to open cursor", "sql", sql, "crt", String_.AryXtoStr(args));
        return rv;
    }
    public byte[]		Read_bry_by_str(String k) 		{return Bry_.new_u8(cursor.getString(cursor.getColumnIndex(k)));}
    public String 		Read_str(String k) 				{return cursor.getString(cursor.getColumnIndex(k));}
    public byte 		Read_byte(String k) 			{return (byte)cursor.getInt(cursor.getColumnIndex(k));}
    public int 			Read_int(String k) 				{return cursor.getInt(cursor.getColumnIndex(k));}
    public long 		Read_long(String k) 			{return cursor.getLong(cursor.getColumnIndex(k));}
    public float		Read_float(String k)			{return cursor.getFloat(cursor.getColumnIndex(k));}
    public double		Read_double(String k)			{return cursor.getDouble(cursor.getColumnIndex(k));}
    public DateAdp		Read_date_by_str(String k) 		{return DateAdp_.parse_iso8561(Read_str(k));}
    public boolean 		Read_bool_by_byte(String k) 	{return ((byte)cursor.getInt(cursor.getColumnIndex(k))) == 1;}
    public Object 		Read_obj(String k)				{throw Err_.new_unimplemented();}
    public void			Rls() 							{
        cursor.close();
        if (stmt != null) stmt.Rls();
    }
}
class Drd_db_stmt implements Db_stmt {
    private static final String Key_na = ""; // key is not_available; only called by procs with signature of Val(<type> v);
    private List_adp list = List_adp_.new_();
    private Db_qry qry_obj; private SQLiteDatabase db; private Drd_db_engine engine;
    public Drd_db_stmt(Db_engine engine, Db_qry qry, SQLiteDatabase db) {Ctor_stmt(engine, qry);}
    public void Ctor_stmt(Db_engine new_engine, Db_qry qry) {
        this.engine = (Drd_db_engine)new_engine; this.qry_obj = qry;
        this.db = engine.Db();
    }
    public Db_stmt Crt_bool_as_byte(String k, boolean v)		{return Add_bool_as_byte(Bool_.Y, k, v);}
    public Db_stmt Val_bool_as_byte(String k, boolean v)		{return Add_bool_as_byte(Bool_.N, k, v);}
    public Db_stmt Val_bool_as_byte(boolean v)					{return Add_bool_as_byte(Bool_.N, Key_na, v);}
    private Db_stmt Add_bool_as_byte(boolean where, String k, Boolean v) {return Add_byte(where, k, v ? Bool_.Y_byte : Bool_.N_byte);}
    public Db_stmt Crt_byte(String k, byte v)					{return Add_byte(Bool_.Y, k, v);}
    public Db_stmt Val_byte(String k, byte v)					{return Add_byte(Bool_.N, k, v);}
    public Db_stmt Val_byte(byte v)								{return Add_byte(Bool_.N, Key_na, v);}
    private Db_stmt Add_byte(boolean where, String k, byte v) 	{Add(k, Byte_.To_str(v)); return this;}
    public Db_stmt Crt_int(String k, int v)						{return Add_int(Bool_.Y, k, v);}
    public Db_stmt Val_int_by_bool(String k, boolean v)			{return Add_int(Bool_.N, k, v ? 1 : 0);}
    public Db_stmt Val_int(String k, int v)						{return Add_int(Bool_.N, k, v);}
    public Db_stmt Val_int(int v)								{return Add_int(Bool_.N, Key_na, v);}
    private Db_stmt Add_int(boolean where, String k, int v) 	{Add(k, Int_.To_str(v)); return this;}
    public Db_stmt Crt_long(String k, long v)					{return Add_long(Bool_.Y, k, v);}
    public Db_stmt Val_long(String k, long v)					{return Add_long(Bool_.N, k, v);}
    public Db_stmt Val_long(long v)								{return Add_long(Bool_.N, Key_na, v);}
    private Db_stmt Add_long(boolean where, String k, long v) 	{Add(k, Long_.To_str(v)); return this;}
    public Db_stmt Crt_float(String k, float v)					{return Add_float(Bool_.Y, k, v);}
    public Db_stmt Val_float(String k, float v)					{return Add_float(Bool_.N, k, v);}
    public Db_stmt Val_float(float v)							{return Add_float(Bool_.N, Key_na, v);}
    private Db_stmt Add_float(boolean where, String k, float v) {Add(k, Float_.To_str(v)); return this;}
    public Db_stmt Crt_double(String k, double v)				{return Add_double(Bool_.Y, k, v);}
    public Db_stmt Val_double(String k, double v)				{return Add_double(Bool_.N, k, v);}
    public Db_stmt Val_double(double v)							{return Add_double(Bool_.N, Key_na, v);}
    private Db_stmt Add_double(boolean where, String k, double v) {Add(k, Double_.To_str(v)); return this;}
    public Db_stmt Crt_decimal(String k, Decimal_adp v)			{return Add_decimal(Bool_.Y, k, v);}
    public Db_stmt Val_decimal(String k, Decimal_adp v)			{return Add_decimal(Bool_.N, k, v);}
    public Db_stmt Val_decimal(Decimal_adp v)					{return Add_decimal(Bool_.N, Key_na, v);}
    private Db_stmt Add_decimal(boolean where, String k, Decimal_adp v) {Add(k, v.To_str()); return this;}
    public Db_stmt Crt_bry(String k, byte[] v)					{return Add_bry(Bool_.Y, k, v);}
    public Db_stmt Val_bry(String k, byte[] v)					{return Add_bry(Bool_.N, k, v);}
    public Db_stmt Val_bry(byte[] v)							{return Add_bry(Bool_.N, Key_na, v);}
    private Db_stmt Add_bry(boolean where, String k, byte[] v) 	{Add(k, String_.new_u8(v)); return this;}
    public Db_stmt Crt_str(String k, String v)					{return Add_str(Bool_.Y, k, v);}
    public Db_stmt Val_str(String k, String v)					{return Add_str(Bool_.N, k, v);}
    public Db_stmt Val_str(String v)							{return Add_str(Bool_.N, Key_na, v);}
    private Db_stmt Add_str(boolean where, String k, String v) 	{Add(k, v); return this;}
    public Db_stmt Crt_bry_as_str(String k, byte[] v)			{return Add_bry_as_str(Bool_.Y, k, v);}
    public Db_stmt Val_bry_as_str(String k, byte[] v)			{return Add_bry_as_str(Bool_.N, k, v);}
    public Db_stmt Val_bry_as_str(byte[] v)						{return Add_bry_as_str(Bool_.N, Key_na, v);}
    private Db_stmt Add_bry_as_str(boolean where, String k, byte[] v) {Add(k, String_.new_u8(v)); return this;}
    public Db_stmt Val_rdr_(gplx.core.ios.Io_stream_rdr rdr, long rdr_len) {return this;}
    private void Add(String k, String v) {
        if (k == Dbmeta_fld_itm.Key_null) return;	// key is explicitly null; ignore; allows version_2+ type definitions
        list.Add(v);
    }
    private void Db_assert() {
        if (db != null) return;
        Drd_db_engine drd_engine = (Drd_db_engine)engine;
        drd_engine.Conn_open();
        db = drd_engine.Db();
        if (db == null) {
            drd_engine.Conn_open();
            db = drd_engine.Db();
        }
    }
    private final Bry_bfr tmp_bfr = Bry_bfr.new_(255); private final Bry_fmtr tmp_fmtr = new Bry_fmtr();
    public boolean Exec_insert() {
//		Db_qry_insert qry = (Db_qry_insert)qry_obj;
//		int len = list.Count();
//		for (int i = 0; i < len; ++i) {
//			KeyVal kv = (KeyVal)qry.Args().FetchAt(i);
//			Db_arg arg = (Db_arg)kv.Val(); 
//			arg.Val_((String)list.FetchAt(i));
//		}
//		Db_assert();
//		db.execSQL(qry.Xto_sql());
//		list.Clear();
        Exec_qry(qry_obj);
        return true;
    }
    private void Exec_qry(Db_qry qry) {
        String sql = Sql_qry_wtr_.Instance.Xto_str(qry, true);
        sql = Db_stmt_sql.Xto_str(tmp_bfr, tmp_fmtr, sql, list);
        list.Clear();
        Db_assert();
//		Tfds.Write(sql);
        db.execSQL(sql);
    }
    public int Exec_update() {
//		Db_qry_update qry = (Db_qry_update)qry_obj;
//		String sql = Sql_qry_wtr_.new_ansi().Xto_str(qry, true);
//		int len = list.Count();
//		String[] args = new String[len];
//		for (int i = 0; i < len; ++i) {
//			args[i] = ((String)list.FetchAt(i));
//		}
//		String stmt_sql = Parse(sql, args);
//		Db_assert();
//		db.execSQL(stmt_sql);
//		list.Clear();
        Exec_qry(qry_obj);
        return 0;
    }
    public int Exec_delete() {throw Err_.new_unimplemented();}
    public DataRdr Exec_select() {return null;}
    public Object Exec_select_val() {return null;}
    public Db_rdr Exec_select__rls_manual() 	{return Exec_select(Bool_.Y);}
    public Db_rdr Exec_select__rls_auto() 		{return Exec_select(Bool_.N);}
    public Db_rdr Exec_select(boolean rls_manual) {
        Db_assert();
        String[] selection_args = Selection_args_make();
        Db_qry qry_base = (Db_qry)qry_obj;
        if (qry_base.Tid() == Db_qry_.Tid_select_in_tbl) {
            String_bldr sb = String_bldr_.new_();
            Db_qry__select_in_tbl qry = (Db_qry__select_in_tbl)qry_base;
            qry.Where_sql(sb);
            String where_sql = sb.To_str_and_clear();
            Cursor cursor = db.query(qry.Base_table(), qry.Select_flds(), where_sql, selection_args, qry.Group_by_sql(), qry.Having_sql(), qry.Order_by_sql());
            return rls_manual ? engine.New_rdr__rls_manual(cursor, "") : engine.New_rdr__rls_auto(this, cursor, "");
        }
        else {
            if (qry_obj.getClass().equals(Db_qry_sql.class)) {
                Db_qry_sql qry = (Db_qry_sql) qry_obj;
                Cursor cursor = db.rawQuery(qry.Xto_sql(), String_.Ary_empty);
                return rls_manual ? engine.New_rdr__rls_manual(cursor, "") : engine.New_rdr__rls_auto(this, cursor, "");
            }
            else {
                Db_qry__select_cmd qry = (Db_qry__select_cmd) qry_obj;
                Cursor cursor = db.rawQuery(qry.Xto_sql_prepare(), selection_args);
                return rls_manual ? engine.New_rdr__rls_manual(cursor, "") : engine.New_rdr__rls_auto(this, cursor, "");
            }
        }
    }
    private String[] Selection_args_make() {
        int len = list.Count(); if (len == 0) return null;
        String[] rv = new String[len];
        for (int i = 0; i < len; ++i) {
            rv[i] = (String)list.Get_at(i);
        }
        list.Clear();
        return rv;
    }
    public Db_stmt Clear() {list.Clear(); return this;}
    public Db_stmt Reset_stmt() {return this;}
    public void Rls() {}
}
