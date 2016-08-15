package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
public class Xof_html_elem {
	public static final byte Tid_none = 0, Tid_img = 1, Tid_vid = 2, Tid_gallery = 3, Tid_gallery_v2 = 4, Tid_imap = 5, Tid_aud = 6;
	public static boolean Tid_is_file(byte tid) {
		switch (tid) {
			case Tid_img: case Tid_vid:		return true;
			default:						return false;
		}
	}
}
