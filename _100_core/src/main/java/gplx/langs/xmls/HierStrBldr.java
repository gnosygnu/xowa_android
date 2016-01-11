package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
import gplx.core.strings.*; import gplx.core.envs.*;
public class HierStrBldr {
	public String Root() {return root;} public HierStrBldr Root_(String v) {root = v; return this;} private String root;
	public Io_url RootAsIoUrl() {return Io_url_.new_dir_(root);}
	public String DirFmt() {return dirFmt;} private String dirFmt;
	public String DirSpr() {return dirSpr;} private String dirSpr = Op_sys.Cur().Fsys_dir_spr_str();
	public String FilFmt() {return filFmt;} private String filFmt;
	public String NumFmt() {return numFmt;} private String numFmt;
	public int[] FilCountMaxs() {return filCountMaxs;} int[] filCountMaxs;
	public Io_url GenStrIdxOnlyAsoUrl(int idx) {return Io_url_.new_fil_(GenStrIdxOnly(idx));}
	public String GenStrIdxOnly(int idx) {return GenStr(String_.Ary_empty, idx);}
	public Io_url GenStrAsIoUrl(String[] subDirs, int idx) {
		return Io_url_.new_fil_(GenStr(subDirs, idx));
	}
	String GenStr(String[] subDirs, int idx) {
		String_bldr sb = String_bldr_.new_(); 
		sb.Add(root);
		for (String subDir : subDirs)
			sb.Add(subDir).Add(dirSpr);
		int multiple = 1;
		int[] multipleAry = new int[filCountMaxs.length];
		for (int i = filCountMaxs.length - 1; i >= 0; i--) {
			multiple *= filCountMaxs[i];
			multipleAry[i] = (idx / multiple) * multiple;	// NOTE: rounds down to multiple; EX: 11 -> 10
		}
		for (int i = 0; i < multipleAry.length; i++)
			sb.Add_fmt(dirFmt, Int_.To_str_fmt(multipleAry[i], numFmt));
		sb.Add_fmt(filFmt, Int_.To_str_fmt(idx, numFmt));
		return sb.To_str();
	}
	public HierStrBldr Ctor_io(Io_url root, String dirFmt, String filFmt, String numFmt, int... filCountMaxs) {
		this.Ctor(root.Raw(), dirFmt + dirSpr, filFmt, numFmt, filCountMaxs);
		return this;
	}
	public void Ctor(String root, String dirFmt, String filFmt, String numFmt, int... filCountMaxs) {
		this.root = root; this.dirFmt = dirFmt; this.filFmt = filFmt; this.numFmt = numFmt; this.filCountMaxs = filCountMaxs;
	}
}
