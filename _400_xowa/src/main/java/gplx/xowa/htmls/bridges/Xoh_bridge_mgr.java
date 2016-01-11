package gplx.xowa.htmls.bridges; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.langs.jsons.*;
import gplx.xowa.htmls.bridges.dbuis.tbls.*;
public class Xoh_bridge_mgr {
	public Xoh_bridge_mgr(Json_parser parser) {this.cmd_mgr = new Bridge_cmd_mgr(parser);}
	public Bridge_cmd_mgr	Cmd_mgr()	{return cmd_mgr;} private final Bridge_cmd_mgr cmd_mgr;
	public Bridge_msg_bldr	Msg_bldr()	{return msg_bldr;} private final Bridge_msg_bldr msg_bldr = new Bridge_msg_bldr();
}
