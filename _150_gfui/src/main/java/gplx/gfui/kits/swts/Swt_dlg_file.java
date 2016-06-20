package gplx.gfui.kits.swts; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
//#{swt
import gplx.gfui.kits.*;
import gplx.gfui.kits.core.Gfui_dlg_file;
import gplx.gfui.kits.core.Gfui_kit_;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
public class Swt_dlg_file implements Gfui_dlg_file {
	private FileDialog under;
	public Swt_dlg_file(byte type, Shell shell)	{
		int file_dialog_type
			= type == Gfui_kit_.File_dlg_type_save
			? SWT.SAVE
			: SWT.OPEN
			;			
		under = new FileDialog(shell, file_dialog_type);
	}
	public Gfui_dlg_file Init_msg_(String v) 			{under.setText(v); return this;}
	public Gfui_dlg_file Init_file_(String v) 			{under.setFileName(v); return this;}
	public Gfui_dlg_file Init_dir_(Io_url v) 			{under.setFilterPath(v.Xto_api()); return this;}
	public Gfui_dlg_file Init_exts_(String... v) 		{under.setFilterExtensions(v); return this;}
	public String Ask() 								{return under.open();}
}
//#}
