package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Gallery_html_wtr_utl {
	public static int Calc_itm_div_len(int v) {return v + 30;}		// 30=Thumb padding; REF.MW:ImageGallery.php|THUMB_PADDING
	public static int Calc_itm_box_w(int v) {return v + 5;}			// 5=Gallery Box padding; REF.MW:ImageGallery.php|GB_PADDING
	public static int Calc_itm_pad_w(int v) {return v + 8;}			// 8=Gallery Box borders; REF.MW:ImageGallery.php|GB_BORDERS
	public static int Calc_vpad(int mgr_itm_height, int html_h) {
		int	min_thumb_height = html_h > 17 ? html_h : 17;						// $minThumbHeight =  $thumb->height > 17 ? $thumb->height : 17;
		return (int)Math_.Floor((30 + mgr_itm_height - min_thumb_height) / 2);	// $vpad = floor(( self::THUMB_PADDING + $this->mHeights - $minThumbHeight ) /2);
	}
}
