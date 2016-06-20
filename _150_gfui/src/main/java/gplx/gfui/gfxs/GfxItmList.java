package gplx.gfui.gfxs; import gplx.*; import gplx.gfui.*;
public class GfxItmList extends List_adp_base {
	@gplx.New public GfxItm Get_at(int i) {return (GfxItm)Get_at_base(i);}
	public void Add(GfxItm gfxItm) {Add_base(gfxItm);}
}
class GfxItmListFxt {
	public void tst_SubItm_count(GfxAdpMok gfx, int expd) {Tfds.Eq(expd, gfx.SubItms().Count());}
	public void tst_SubItm(GfxAdpMok gfx, int i, GfxItm expd) {
		GfxItm actl = gfx.SubItms().Get_at(i);
		Tfds.Eq(expd, actl);
	}
	public static GfxItmListFxt new_() {return new GfxItmListFxt();} GfxItmListFxt() {}
}
