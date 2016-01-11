package gplx.gfml; import gplx.*;
public class GfmlObjList extends List_adp_base {//_20101204
	@gplx.New public GfmlObj Get_at(int idx) {return (GfmlObj)Get_at_base(idx);}
	public void Add(GfmlObj tkn) {Add_base(tkn);}
	public void Add_at(GfmlObj tkn, int idx) {super.AddAt_base(idx, tkn);}
	public void Del(GfmlObj tkn) {Del_base(tkn);}
	public static GfmlObjList new_() {return new GfmlObjList();} GfmlObjList() {}
}
