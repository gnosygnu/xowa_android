package gplx.langs.dsvs; import gplx.*; import gplx.langs.*;
public class DsvHeaderList {
	@gplx.Internal protected int Count() {return list.Count();}
	@gplx.Internal protected DsvHeaderItm Get_at(int i) {return (DsvHeaderItm)list.Get_at(i);}
	public DsvHeaderList Add_LeafTypes() {this.Add(new DsvHeaderItm(DsvHeaderItm.Id_LeafTypes, null)); return this;}
	public DsvHeaderList Add_LeafNames() {this.Add(new DsvHeaderItm(DsvHeaderItm.Id_LeafNames, null)); return this;}
	public DsvHeaderList Add_TableName() {this.Add(new DsvHeaderItm(DsvHeaderItm.Id_TableName, null)); return this;}
	public DsvHeaderList Add_BlankLine() {this.Add(new DsvHeaderItm(DsvHeaderItm.Id_BlankLine, null)); return this;}
	public DsvHeaderList Add_Comment(String comment) {this.Add(new DsvHeaderItm(DsvHeaderItm.Id_Comment, comment)); return this;}
	void Add(DsvHeaderItm data) {list.Add(data);}

	List_adp list = List_adp_.new_();
	public static DsvHeaderList new_() {return new DsvHeaderList();} DsvHeaderList() {}
}
class DsvHeaderItm {
	public int Id() {return id;} int id;
	public Object Val() {return val;} Object val;
	@gplx.Internal protected DsvHeaderItm(int id, Object val) {this.id = id; this.val = val;}

	public static final int
		  Id_Comment	= 1
		, Id_TableName	= 2
		, Id_BlankLine	= 3
		, Id_LeafTypes	= 4
		, Id_LeafNames	= 5
		;
}
