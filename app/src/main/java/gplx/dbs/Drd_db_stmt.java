package gplx.dbs;

import android.database.*;
import android.database.sqlite.*;

import gplx.*;
import gplx.core.brys.fmtrs.*;
import gplx.core.stores.*;
import gplx.core.strings.*;
import gplx.dbs.engines.Db_engine;
import gplx.dbs.qrys.*;
import gplx.dbs.sqls.*;

class Drd_db_stmt implements Db_stmt {
    private List_adp select_list;
    private Db_qry qry_obj; private SQLiteDatabase db; private Drd_db_engine engine;
    private String sql; private SQLiteStatement stmt; private int val_idx;
    private byte select_tid;
    private String_bldr sb;
    public Drd_db_stmt(Db_engine engine, Db_qry qry, SQLiteDatabase db) {Ctor_stmt(engine, qry);}
    public void Ctor_stmt(Db_engine new_engine, Db_qry qry) {
        this.engine = (Drd_db_engine)new_engine; this.qry_obj = qry;
        this.db = engine.Db();
        this.select_tid = Drd_db_select_tid.To_tid(qry);
        this.sql = select_tid == Drd_db_select_tid.Tid__tbl
            ? ((Db_qry__select_in_tbl)qry).To_sql__exec(engine.Sql_wtr())
            : engine.Sql_wtr().To_sql_str(qry, true);
        if (select_tid != Drd_db_select_tid.Tid__nil)
            select_list = List_adp_.New();
        Reset_stmt();
    }
    public Db_stmt Reset_stmt() {
        stmt = (SQLiteStatement)engine.Stmt_by_sql(sql);	//#<>IDbCommand~PreparedStatement
        return this;
    }
    public Db_stmt Clear() {
        stmt.clearBindings();
        val_idx = 0;
        if (select_list != null) select_list.Clear();
        return this;
    }
    public Db_stmt Crt_bool_as_byte(String k, boolean v)		{return Add_bool_as_byte(Bool_.Y, k, v);}
    public Db_stmt Val_bool_as_byte(String k, boolean v)		{return Add_bool_as_byte(Bool_.N, k, v);}
    public Db_stmt Val_bool_as_byte(boolean v)					{return Add_bool_as_byte(Bool_.N, Key_na, v);}
    private Db_stmt Add_bool_as_byte(boolean where, String k, Boolean v) {return Add_byte(where, k, v ? Bool_.Y_byte : Bool_.N_byte);}
    public Db_stmt Crt_byte(String k, byte v)					{return Add_byte(Bool_.Y, k, v);}
    public Db_stmt Val_byte(String k, byte v)					{return Add_byte(Bool_.N, k, v);}
    public Db_stmt Val_byte(byte v)								{return Add_byte(Bool_.N, Key_na, v);}
    private Db_stmt Add_byte(boolean where, String k, byte v) 	{
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindLong(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "byte", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(Byte_.To_str(v));
        return this;
    }
    public Db_stmt Crt_int(String k, int v)						{return Add_int(Bool_.Y, k, v);}
    public Db_stmt Val_int_by_bool(String k, boolean v)			{return Add_int(Bool_.N, k, v ? 1 : 0);}
    public Db_stmt Val_int(String k, int v)						{return Add_int(Bool_.N, k, v);}
    public Db_stmt Val_int(int v)								{return Add_int(Bool_.N, Key_na, v);}
    private Db_stmt Add_int(boolean where, String k, int v) 	{
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindLong(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "int", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(Int_.To_str(v));
        return this;
    }
    public Db_stmt Crt_long(String k, long v)					{return Add_long(Bool_.Y, k, v);}
    public Db_stmt Val_long(String k, long v)					{return Add_long(Bool_.N, k, v);}
    public Db_stmt Val_long(long v)								{return Add_long(Bool_.N, Key_na, v);}
    private Db_stmt Add_long(boolean where, String k, long v) 	{
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindLong(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "long", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(Long_.To_str(v));
        return this;
    }
    public Db_stmt Crt_float(String k, float v)					{return Add_float(Bool_.Y, k, v);}
    public Db_stmt Val_float(String k, float v)					{return Add_float(Bool_.N, k, v);}
    public Db_stmt Val_float(float v)							{return Add_float(Bool_.N, Key_na, v);}
    private Db_stmt Add_float(boolean where, String k, float v) {
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindDouble(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "float", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(Float_.To_str(v));
        return this;
    }
    public Db_stmt Crt_double(String k, double v)				{return Add_double(Bool_.Y, k, v);}
    public Db_stmt Val_double(String k, double v)				{return Add_double(Bool_.N, k, v);}
    public Db_stmt Val_double(double v)							{return Add_double(Bool_.N, Key_na, v);}
    private Db_stmt Add_double(boolean where, String k, double v) {
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindDouble(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "double", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(Double_.To_str(v));
        return this;
    }
    public Db_stmt Crt_decimal(String k, Decimal_adp v)			{return Add_decimal(Bool_.Y, k, v);}
    public Db_stmt Val_decimal(String k, Decimal_adp v)			{return Add_decimal(Bool_.N, k, v);}
    public Db_stmt Val_decimal(Decimal_adp v)					{return Add_decimal(Bool_.N, Key_na, v);}
    private Db_stmt Add_decimal(boolean where, String k, Decimal_adp v) {
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindDouble(++val_idx, v.To_double());} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "decimal", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(v.To_str());
        return this;
    }
    public Db_stmt Crt_bry(String k, byte[] v)					{return Add_bry(Bool_.Y, k, v);}
    public Db_stmt Val_bry(String k, byte[] v)					{return Add_bry(Bool_.N, k, v);}
    public Db_stmt Val_bry(byte[] v)							{return Add_bry(Bool_.N, Key_na, v);}
    private Db_stmt Add_bry(boolean where, String k, byte[] v) 	{
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindBlob(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "bry", "val", v, "sql", sql);}
        if (select_list != null) throw Err_.new_("android.db", "byte[] cannot be used as criteria for a select statement", "key", k, "sql", sql);
        return this;
    }
    public Db_stmt Crt_bry_as_str(String k, byte[] v)			{return Add_bry_as_str(Bool_.Y, k, v);}
    public Db_stmt Val_bry_as_str(String k, byte[] v)			{return Add_bry_as_str(Bool_.N, k, v);}
    public Db_stmt Val_bry_as_str(byte[] v)						{return Add_bry_as_str(Bool_.N, Key_na, v);}
    private Db_stmt Add_bry_as_str(boolean where, String k, byte[] v) {Add_str(where, k, String_.new_u8(v)); return this;}
    public Db_stmt Crt_str(String k, String v)					{return Add_str(Bool_.Y, k, v);}
    public Db_stmt Val_str(String k, String v)					{return Add_str(Bool_.N, k, v);}
    public Db_stmt Val_str(String v)							{return Add_str(Bool_.N, Key_na, v);}
    private Db_stmt Add_str(boolean where, String k, String v) 	{
        if (k == Dbmeta_fld_itm.Key_null) return this;	// key is explicitly null; ignore; allows version_2+ type definitions
        try {stmt.bindString(++val_idx, v);} catch (Exception e) {this.Rls(); throw Err_.new_exc(e, "db", "failed to add value", "type", "string", "val", v, "sql", sql);}
        if (select_list != null) select_list.Add(v);
        return this;
    }
    public Db_stmt Crt_date(String k, DateAdp v)				{return Add_date(Bool_.Y, k, v);}
    public Db_stmt Val_date(String k, DateAdp v)				{return Add_date(Bool_.N, k, v);}
    private Db_stmt Add_date(boolean where, String k, DateAdp v) {
        return Add_str(where, k, v.XtoStr_fmt_iso_8561());
    }
    public Db_stmt Crt_text(String k, String v)					{return Add_text(Bool_.Y, k, v);}
    public Db_stmt Val_text(String k, String v)					{return Add_text(Bool_.N, k, v);}
    private Db_stmt Add_text(boolean where, String k, String v) {Add_str(where, k, v); return this;}
    public Db_stmt Val_rdr_(gplx.core.ios.streams.Io_stream_rdr rdr, long rdr_len) {throw Err_.new_unimplemented();}
    public boolean Exec_insert() {
        try	    {stmt.execute(); return true;}
        catch   (Exception e) {
            this.Rls();
            Reset_stmt();
            throw Err_.new_exc(e, "db_stmt", "insert failed", "url", engine.Conn_info().Db_api(), "sql", sql);
        }
    }
    public int Exec_update() {
        try	    {stmt.execute(); return 0;}
        catch   (Exception e) {
            this.Rls();
            Reset_stmt();
            throw Err_.new_exc(e, "db_stmt", "update failed", "url", engine.Conn_info().Db_api(), "sql", sql);
        }
    }
    public int Exec_delete() {
        try	    {stmt.execute(); return 0;}
        catch   (Exception e) {
            this.Rls();
            Reset_stmt();
            throw Err_.new_exc(e, "db_stmt", "delete failed", "url", engine.Conn_info().Db_api(), "sql", sql);
        }
    }
    public DataRdr Exec_select()                {throw Err_.new_unimplemented();}
    public Object Exec_select_val()             {throw Err_.new_unimplemented();}
    public Db_rdr Exec_select__rls_manual() 	{return Exec_select(Bool_.Y);}
    public Db_rdr Exec_select__rls_auto() 		{return Exec_select(Bool_.N);}
    public Db_rdr Exec_select(boolean rls_manual) {
        Db_assert();
        String[] selection_args = Selection_args_make();
        Db_qry qry_base = (Db_qry)qry_obj;
        Cursor cursor = null;
        switch (select_tid) {
            case Drd_db_select_tid.Tid__nil: throw Err_.new_("android.db", "select tid is null", "sql", sql);
            case Drd_db_select_tid.Tid__tbl: {
                if (sb == null) sb = String_bldr_.new_();
                Db_qry__select_in_tbl qry = (Db_qry__select_in_tbl) qry_base;
                qry.Where_sql(sb);
                String where_sql = sb.To_str_and_clear();
                cursor = db.query(qry.Base_table(), qry.Select_flds(), where_sql, selection_args, qry.Group_by_sql(), qry.Having_sql(), qry.Order_by_sql());
                break;
            }
            case Drd_db_select_tid.Tid__sql: {
                Db_qry_sql qry = (Db_qry_sql) qry_obj;
                if (selection_args == null || selection_args.length == 0)
                    cursor = db.rawQuery(qry.To_sql__exec(engine.Sql_wtr()), String_.Ary_empty);
                else
                    cursor = db.rawQuery(engine.Sql_wtr().To_sql_str(qry, Bool_.Y), selection_args);
                break;
            }
            case Drd_db_select_tid.Tid__cmd: {
                Db_qry__select_cmd qry = (Db_qry__select_cmd) qry_obj;
                cursor = db.rawQuery(qry.To_sql__prep(engine.Sql_wtr()), selection_args);
            }
        }
        return rls_manual ? engine.Exec_as_rdr__rls_manual(cursor, sql) : engine.Exec_as_rdr__rls_auto(this, cursor, sql);
    }
    private String[] Selection_args_make() {
        int len = select_list.Count(); if (len == 0) return null;
        String[] rv = new String[len];
        for (int i = 0; i < len; ++i)
            rv[i] = (String)select_list.Get_at(i);
        select_list.Clear();
        return rv;
    }
    public void Rls() {
        if (stmt == null) return;
        try {
            stmt.clearBindings();
            stmt.close();
            stmt = null;
        }
        catch (Exception e) {throw Err_.new_exc(e, "db", "failed to close command", "sql", sql);}
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
    private static final String Key_na = ""; // key is not_available; only called by procs with signature of Val(<type> v);
}
class Drd_db_select_tid {
    public static final byte Tid__nil = 0, Tid__tbl = 1, Tid__sql = 2, Tid__cmd = 3;
    public static byte To_tid(Db_qry qry_obj) {
        if (qry_obj.Tid() == Db_qry_.Tid_select_in_tbl)         return Tid__tbl;
        else {
            Class<?> qry_cls = qry_obj.getClass();
            if      (qry_cls.equals(Db_qry_sql.class))          return Tid__sql;
            else if (qry_cls.equals(Db_qry__select_cmd.class))  return Tid__cmd;
            else                                                return Tid__nil;
        }
    }
}