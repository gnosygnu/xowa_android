package gplx.dbs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gplx.Err_;
import gplx.Gfo_usr_dlg;
import gplx.Gfo_usr_dlg_;
import gplx.Io_url;
import gplx.String_;
import gplx.core.stores.DataRdr;
import gplx.dbs.engines.Db_engine;
import gplx.dbs.engines.sqlite.Sqlite_conn_info;
import gplx.dbs.engines.sqlite.Sqlite_schema_mgr;
import gplx.dbs.metas.Dbmeta_tbl_mgr;
import gplx.dbs.sqls.Sql_qry_wtr;
import gplx.dbs.sqls.Sql_qry_wtr_;

public class Drd_db_engine implements Db_engine {
    private final Context context; private SQLiteOpenHelper db_helper; private SQLiteDatabase db;
    private final Sqlite_schema_mgr schema_mgr;
    public Drd_db_engine(Sqlite_conn_info conn_info, Context context) {
        this.conn_info = conn_info;
        this.context = context;
        this.schema_mgr = new Sqlite_schema_mgr(this);
    }
    public String Tid() {return Sqlite_conn_info.Key_const;}
    public Db_conn_info Conn_info() {return conn_info;} private final Sqlite_conn_info conn_info;
    @Override public Sql_qry_wtr Sql_wtr() {return Sql_qry_wtr_.Sqlite;}
    public SQLiteDatabase Db() {
        if (db == null) Conn_open();
        return db;
    }
    public void Conn_open() {
        if (db != null) return;
        String url = conn_info.Url().Xto_api();
        db_helper = new Drd_db_mgr(context, url);
        String err_msg = null;
        // db_helper.getWritableDatabase();
        try 				{db = SQLiteDatabase.openDatabase(url, null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);}
        catch (Exception e) {err_msg = Err_.Message_gplx_full(e);}
        if (err_msg == null) return;
        Gfo_usr_dlg_.Instance.Log_many("", "", "db:open writable failed; url=~{0}", url);	// DRD: Kit_kat fails to open files in R/W. try to open in readable only
        try 				{db = SQLiteDatabase.openDatabase(url, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);}
        catch (Exception e) {err_msg = Err_.Message_gplx_full(e);}
        if (db != null) return;	// NOTE: opening in readonly still throws error; just check that if not null; android.database.sqlite.SQLiteException: not an error (code 0): Could not open the database in read/write mode.
        Gfo_usr_dlg_.Instance.Log_many("", "", "db:open readable failed; url=~{0}", url);
    }
    public void Conn_term() {
        db.close();
        db_helper.close();
        db = null;
    }
    public Db_engine 	New_clone(Db_conn_info conn_info) {return new Drd_db_engine((Sqlite_conn_info)conn_info, context);}
    public void 	Ddl_create_tbl(Dbmeta_tbl_itm meta) 			{Exec_sql(meta.To_sql_create(this.Sql_wtr()));}
    public void 	Ddl_append_fld(String tbl, Dbmeta_fld_itm fld) {Exec_sql(this.Sql_wtr().Schema_wtr().Bld_alter_tbl_add(tbl, fld));}
    public void 	Ddl_delete_tbl(String tbl) 					{Exec_sql(this.Sql_wtr().Schema_wtr().Bld_drop_tbl(tbl));}
    public void Ddl_create_idx(Gfo_usr_dlg usr_dlg, Dbmeta_idx_itm... ary) {
        if (db == null) Conn_open();
        int len = ary.length;
        for (int i = 0; i < len; ++i) {
            Dbmeta_idx_itm idx = ary[i];
            usr_dlg.Plog_many("", "", "db.index.create; server=~{0} sql=~{1}", conn_info.Database(), idx.To_sql_create(this.Sql_wtr()));
            db.execSQL(idx.To_sql_create(this.Sql_wtr()));
        }
    }
    @Override public void		Env_db_attach(String alias, Db_conn conn)	{
        Db_conn_info cs_obj = conn.Conn_info(); if (!String_.Eq(cs_obj.Key(), Sqlite_conn_info.Key_const)) throw Err_.new_("dbs", "must attach to sqlite databases", "conn", cs_obj.Raw());
        Sqlite_conn_info cs = (Sqlite_conn_info)cs_obj;
        Env_db_attach(alias, cs.Url());
    }
    @Override public void		Env_db_attach(String alias, Io_url db_url)	{
        db.execSQL("ATTACH DATABASE '" + db_url.Raw() + "' AS " + alias);
    }
    public void		Env_db_detach(String alias)					{db.execSQL("DETACH DATABASE " + alias);}
    public DataRdr	New_rdr(java.sql.ResultSet rdr, String sql) {return null;}
    public Db_stmt 	New_stmt_prep(Db_qry qry) {return new Drd_db_stmt(this, qry, db);}
    public Object 	New_stmt_prep_as_obj(String sql) {throw Err_.new_unimplemented();}
    public Db_rdr	New_rdr__rls_manual(Object rdr_obj, String sql) 				{return New_rdr(null, rdr_obj, sql);}
    public Db_rdr	New_rdr__rls_auto(Db_stmt stmt, Object rdr_obj, String sql) 	{return New_rdr(stmt, rdr_obj, sql);}
    private Db_rdr  New_rdr(Db_stmt stmt, Object rdr_obj, String sql) {return new Drd_db_rdr(this, stmt, (Cursor)rdr_obj);}
    public void 	Txn_bgn(String name)	{if (db == null) Conn_open(); db.beginTransaction();}
    public String 	Txn_end()				{if (db == null) Conn_open(); db.setTransactionSuccessful(); db.endTransaction(); return "";}
    public void 	Txn_cxl()				{if (db == null) Conn_open(); db.endTransaction();}
    public void 	Txn_sav() 				{Txn_end(); Txn_bgn("");}
    public boolean	Meta_tbl_exists(String name) {
        if (db == null) Conn_open();
        return schema_mgr.Tbl_exists(name);
    }
    public boolean	Meta_fld_exists(String tbl, String fld) {
        if (db == null) Conn_open();
        return schema_mgr.Fld_exists(tbl, fld);
    }
    public Dbmeta_tbl_mgr Meta_tbl_load_all() {if (db == null) Conn_open(); return schema_mgr.Tbl_load_all();}
    public void		Meta_reload() {schema_mgr.Tbl_load_all();}
    public Object	Exec_as_obj(Db_qry cmd) {
        if (db == null) Conn_open();
        if (db == null) return 0;
        db.execSQL(cmd.To_sql__exec(Sql_wtr()));
        return 0;
    }
    private void Exec_sql(String sql) {
        if (db == null) Conn_open();
        db.execSQL(sql);
    }
}
