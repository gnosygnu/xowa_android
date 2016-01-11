package gplx;
import gplx.core.strings.*;
public class KeyValList {//20101217
	public int Count() {return list.Count();} List_adp list = List_adp_.new_();
	public void Clear() {list.Clear();}
	public KeyVal GetAt(int i) {return (KeyVal)list.Get_at(i);}
	public KeyValList Add(String key, Object val) {list.Add(KeyVal_.new_(key, val)); return this;}
	public KeyVal[] Xto_bry() {return (KeyVal[])list.To_ary(KeyVal.class);}
	public String To_str() {
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < list.Count(); i++) {
			KeyVal kv = (KeyVal)list.Get_at(i);
			sb.Add_spr_unless_first(kv.Key(), " ", i);
			sb.Add("=").Add(kv.Val_to_str_or_empty());
		}
		return sb.To_str();
	}
	public static KeyValList args_(String key, Object val) {return new KeyValList().Add(key, val);}
}
