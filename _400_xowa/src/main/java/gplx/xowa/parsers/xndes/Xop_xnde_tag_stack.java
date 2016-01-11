package gplx.xowa.parsers.xndes; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_xnde_tag_stack {
	public void Push()		{xmlTagsStack.Add(xmlTags); xmlTags = new int[Xop_xnde_tag_.Tid__len];}
	public void Pop()		{xmlTags = (int[])List_adp_.Pop(xmlTagsStack);}
	public boolean Has(int id) {return xmlTags[id] != 0;}
	public void Add(int id) {++xmlTags[id];}
	public void Del(int id) {
		int val = --xmlTags[id];
		if (val == -1) xmlTags[id] = 0;
	}
	public void Clear() {
		for (int i = 0; i < Xop_xnde_tag_.Tid__len; i++)
			xmlTags[i] = 0;
		xmlTagsStack.Clear();
	}
	List_adp xmlTagsStack = List_adp_.new_();
	int[] xmlTags = new int[Xop_xnde_tag_.Tid__len];
}
