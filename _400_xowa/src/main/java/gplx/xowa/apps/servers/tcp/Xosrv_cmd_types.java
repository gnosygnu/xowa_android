package gplx.xowa.apps.servers.tcp; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
public class Xosrv_cmd_types {
	public static final byte[]
		Cmd_exec 		= Bry_.new_a7("xowa.cmd.exec")	, Cmd_pass	 	= Bry_.new_a7("xowa.cmd.result")	, Cmd_fail		= Bry_.new_a7("xowa.cmd.error")
	,	Js_exec 		= Bry_.new_a7("xowa.js.exec")	, Js_pass		= Bry_.new_a7("xowa.js.result")		, Js_fail		= Bry_.new_a7("xowa.js.error")
	,	Browser_exec 	= Bry_.new_a7("browser.js.exec"), Browser_pass 	= Bry_.new_a7("browser.js.result")	, Browser_fail 	= Bry_.new_a7("browser.js.error")
	;
}
