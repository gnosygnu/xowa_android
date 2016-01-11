package gplx.xowa; import gplx.*;
public class Xoa_page_ {
	public static final byte Edit_mode_create = 1, Edit_mode_update = 2;
	public static final String Main_page_str = "Main_Page";
	public static final byte[] Main_page_bry = Bry_.new_a7(Main_page_str);	// NOTE; may not work for non-english wikis
	public static final byte[] Main_page_bry_empty = Bry_.Empty;
	public static final int Page_len_max = 2048 * Io_mgr.Len_kb;	// REF.MW: DefaultSettings.php; $wgMaxArticleSize = 2048;
}
