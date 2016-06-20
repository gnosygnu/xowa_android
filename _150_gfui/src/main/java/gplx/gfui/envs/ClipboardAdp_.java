package gplx.gfui.envs; import gplx.*; import gplx.gfui.*;
//#{import
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
//#}
import gplx.core.strings.*; import gplx.core.envs.*;
public class ClipboardAdp_ {
	public static void SetText(String text) {
		//#{SetText
		StringSelection data = new StringSelection(text);		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(data, data);
		//#}
	}
	public static boolean IsText() {
		//#{IsText
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		return clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor);
		//#}
	}
	public static String GetText() {
		//#{GetText
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String rv = "";
		try {rv = clipboard.getData(DataFlavor.stringFlavor).toString();}
		catch (Exception e) {throw Err_.new_exc(e, "ui", "clipboard get_data failed");}
		if (Op_sys.Cur().Tid_is_wnt()) {	// WORKAROUND:JAVA: On Windows, Clipboard will have \r\n, but Java automatically converts to \n
			String_bldr remake = String_bldr_.new_();
			for (int i = 0; i < String_.Len(rv); i++) {
				char c = String_.CharAt(rv, i);
				if (c == '\n' && i > 0) {
					char prev = String_.CharAt(rv, i - 1);
					if (prev != '\r') {
						remake.Add(String_.CrLf);
						continue;
					}					
				}
				remake.Add(c);
			}				
			rv = remake.To_str();
//			rv = String_.Replace(rv, "\n", Env_.NewLine);
		}
		return rv;
		//#}
	}
}
