package gplx.gfui; import gplx.*;
//#{import
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
//#}
import gplx.core.gfo_regys.*;
public class IconAdp {
	public Icon UnderIcon() {return icon;} private final Icon icon;
	public Image XtoImage() {return ((ImageIcon)icon).getImage();}		//#<>icon.ToBitmap()~((ImageIcon)icon).getImage()
	public Io_url Url() {return url;} private Io_url url = Io_url_.Empty;
	IconAdp(Icon icon) {this.icon = icon;}
	public static IconAdp new_(Icon icon) {return new IconAdp(icon);}
	public static IconAdp file_or_blank(Io_url url) {return file_(url);}
	public static IconAdp file_(Io_url url) {
		//#{file_
		Icon icon = new ImageIcon(url.Xto_api());
		IconAdp rv = new IconAdp(icon);
		rv.url = url;
		return rv;
		//#}
	}
	public static void regy_loadDir_(Io_url imgDir) {GfoRegy.Instance.RegDir(imgDir, "*.png", true, "_", ".");} //#<>.ico~.png
	public static void regy_loadDir_shallow(Io_url imgDir) {GfoRegy.Instance.RegDir(imgDir, "*.png", false, "_", ".");} //#<>.ico~.png
	public static IconAdp regy_(String key) {
		GfoRegyItm itm = GfoRegy.Instance.FetchOrNull(key);
		if (itm == null) {UsrDlg_.Instance.Warn("missing icon; key={0}", key); return null;}
		if	(itm.ValType() != GfoRegyItm.ValType_Url) throw Err_.new_wo_type("regyItm should be of type url", "key", key);
		return IconAdp.file_(itm.Url());
	}
	public static IconAdp as_(Object obj) {return obj instanceof IconAdp ? (IconAdp)obj : null;}
}
