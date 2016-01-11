package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Xoh_cfg_gallery implements GfoInvkAble {
	public int Imgs_per_row() {return imgs_per_row;} public Xoh_cfg_gallery Imgs_per_row_(int v) {imgs_per_row = v; return this;} private int imgs_per_row = 8;	// changed from 4 to 8; DATE:2014-02-04
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_imgs_per_row_))		imgs_per_row = m.ReadInt("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_imgs_per_row_ = "imgs_per_row_";
}
