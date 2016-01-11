package gplx.gfui; import gplx.*;
public class Gfui_mnu_itm_ {
	public static String Gen_uid() {return "mnu_" + Int_.To_str(++uid_next);} private static int uid_next = 0;
	public static final int Tid_nil = 0, Tid_grp = 1, Tid_spr = 2, Tid_btn = 3, Tid_chk = 4, Tid_rdo = 5;
}
