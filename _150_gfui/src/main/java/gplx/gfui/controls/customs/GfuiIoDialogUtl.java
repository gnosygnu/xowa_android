package gplx.gfui.controls.customs; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
//#{import
import java.io.File;
import java.awt.FileDialog;

import javax.swing.JFrame;
//#}
import gplx.gfui.controls.elems.*; import gplx.gfui.controls.windows.*;
public class GfuiIoDialogUtl {
	//#{lang
	public static Io_url SelectDir() {return SelectDir(Io_url_.Empty);}
	public static Io_url SelectDir(Io_url startingDir) {		
		FileDialog openFileDialog = NewOpenFileDialog(startingDir);
//		openFileDialog.FileName = @"press enter to select this folder";
		openFileDialog.setVisible(true);
		String selectedDir = openFileDialog.getDirectory();
		if (selectedDir == null) return Io_url_.Empty; // nothing selected
		Io_url selected = Io_url_.new_any_(selectedDir);
		Io_url selectedFil = selected.GenSubFil(openFileDialog.getFile());
		return selectedFil.OwnerDir();
	}
	public static Io_url SelectFile() {return SelectFile(Io_url_.Empty);}
	public static Io_url SelectFile(Io_url startingDir) {
		FileDialog openFileDialog = NewOpenFileDialog(startingDir);
		openFileDialog.setVisible(true);
		String selectedDir = openFileDialog.getDirectory();
		if (selectedDir == null) return Io_url_.Empty; // nothing selected
		Io_url selected = Io_url_.new_any_(selectedDir);
		Io_url selectedFil = selected.GenSubFil(openFileDialog.getFile());
		return selectedFil;
	}
	static FileDialog NewOpenFileDialog(Io_url startingDir) {
		//WORKAROUND (Windows.Forms): InitialDirectory only works for new instances; http://groups.google.com/groups?hl=en&lr=&threadm=ubk6dEUYDHA.536%40TK2MSFTNGP10.phx.gbl&rnum=30&prev=/groups%3Fq%3Ddotnet%2BInitialDirectory%26hl%3Den%26btnG%3DGoogle%2BSearch
		FileDialog openFileDialog = new FileDialog(new JFrame());
		if (!startingDir.EqNull())
			openFileDialog.setDirectory(startingDir.Xto_api());
		return openFileDialog;
	}
	//#}
}
