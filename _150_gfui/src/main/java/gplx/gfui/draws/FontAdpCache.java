package gplx.gfui.draws; import gplx.*; import gplx.gfui.*;
//#{import
import java.awt.Font;
import java.awt.Toolkit;
//#}
public class FontAdpCache {//_20101206
	public Font GetNativeFont(FontAdp fontAdp) {
		String key = fontAdp.toString();
		Font rv = (Font)hash.Get_by(key); if (rv != null) return rv;
		//#{GetNativeFont
		if (screenResolutionInDpi == -1) ScreenResolution_set();
	    int fontSize = XtoJavaDpi(fontAdp.Size());
		rv = new Font(fontAdp.Name(), fontAdp.Style().Val(), fontSize);		
		//#}
		hash.Add(key, rv);
		return rv;
	}	Hash_adp hash = Hash_adp_.New();
	//#{FontAdpCache
	public static void ScreenResolution_set() {screenResolutionInDpi = Toolkit.getDefaultToolkit().getScreenResolution();}	// usually either 96 or 120
	public static int XtoOsDpi(float v) {return Math.round((v * 72) / screenResolutionInDpi);} // WORKAROUND/JAVA: Java needs 72 dpi screen resolution; wnt uses 96 or 120 dpi
	public static int XtoJavaDpi(float v) {return Math.round((v * screenResolutionInDpi) / 72);}
	static int screenResolutionInDpi = -1;
	//#}
	public static final    FontAdpCache Instance = new FontAdpCache(); FontAdpCache() {}
}
