package gplx.xowa.files.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.gfui.*;
public class Xof_img_wkr_query_img_size_test implements Xof_img_wkr_query_img_size {
	public SizeAdp Exec(Io_url url) {
		ImageAdp image = ImageAdp_.txt_fil_(url);
		return image.Size();
	}
}
