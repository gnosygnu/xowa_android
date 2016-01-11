package gplx.xowa.bldrs.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public class Sql_file_parser_data {
	public boolean Cancel_row() {return cancel_row;}
	public Sql_file_parser_data Cancel_row_n_() {cancel_row = false; return this;} 
	public Sql_file_parser_data Cancel_row_y_() {cancel_row = true; return this;} private boolean cancel_row;
}