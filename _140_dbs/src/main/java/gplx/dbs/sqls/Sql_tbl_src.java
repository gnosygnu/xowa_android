package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.strings.*;
public class Sql_tbl_src {
	public Sql_join_itmType JoinType() {return type;} public Sql_tbl_src JoinType_(Sql_join_itmType v) {this.type = v; return this;} Sql_join_itmType type = Sql_join_itmType.Inner;
	public List_adp JoinLinks() {return joinLinks;} List_adp joinLinks = List_adp_.new_();
	public String TblName() {return tblName;} public Sql_tbl_src TblName_(String s) {tblName = s; return this;} private String tblName;
	public String Alias() {return alias;} public Sql_tbl_src Alias_(String s) {alias = s; return this;} private String alias;
	public void XtoSql(String_bldr sb) {
		sb.Add_many(tblName, alias == null ? "" : " " + alias);
	}
	public static Sql_tbl_src new_() {return new Sql_tbl_src();} Sql_tbl_src() {}
}
