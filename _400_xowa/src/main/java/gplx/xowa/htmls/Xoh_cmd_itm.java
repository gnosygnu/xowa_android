package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
public interface Xoh_cmd_itm {
	String Hcmd_id();
	void Hcmd_exec(Xoae_app app, Gfo_usr_dlg usr_dlg, Xoae_page page);
	void Hcmd_write(Xoae_app app, Gfo_usr_dlg usr_dlg, Xoae_page page);
}
