package gplx.gfui; import gplx.*;
public class Gfui_tab_itm_data {
	public Gfui_tab_itm_data(String key, int idx) {this.key = key; this.idx = idx;}
	public String		Key() {return key;} private String key;
	public int			Idx() {return idx;} public Gfui_tab_itm_data Idx_(int v) {idx = v; return this;} private int idx;
	public String		Name() {return name;} public void Name_(String v) {name = v;} private String name;
	public String		Tip_text() {return tip_text;} public Gfui_tab_itm_data Tip_text_(String v) {tip_text = v; return this;} private String tip_text;
	public boolean			Pinned() {return pinned;} public Gfui_tab_itm_data Pinned_(boolean v) {pinned = v; return this;} private boolean pinned;
	public void Switch(Gfui_tab_itm_data comp) {	// NOTE: key is invariant; idx should not be switched, as tab_idx remains the same; only contents have been switched
		String tmp_str = name;
		this.name = comp.name; comp.name = tmp_str;
		tmp_str = tip_text;
		this.tip_text = comp.tip_text; comp.tip_text = tmp_str;
	}
	public static int Get_idx_after_closing(int cur, int len) {
		if		(len < 1)				return Idx_null;		// 1 or 0 tabs; return -1
		else if (cur == len - 1)		return len - 2;			// last tab; select_bwd
		else							return cur + 1;			// else, select_fwd
	}
	public static final int Idx_null = -1;
}
