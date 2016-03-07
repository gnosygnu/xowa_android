package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public interface Srch2_crt_node_visitor {
	void Visit(Srch2_crt_node node);
}
class Srch2_crt_node_visitor__to_str_ary implements Srch2_crt_node_visitor { // inorder
	private final List_adp list = List_adp_.new_();
	public String[] To_str_ary() {return list.To_str_ary_and_clear();}
	public void Visit(Srch2_crt_node node) {
		switch (node.tid) {
			case Srch2_crt_node.Tid_word:
			case Srch2_crt_node.Tid_word_quote:	list.Add(String_.new_u8(node.raw)); break;
			case Srch2_crt_node.Tid_or:	
			case Srch2_crt_node.Tid_and:
				node.lhs.Accept_visitor(this);
				node.rhs.Accept_visitor(this);
				break;
			case Srch2_crt_node.Tid_not:
				node.rhs.Accept_visitor(this);
				break;
			case Srch2_crt_node.Tid_invalid:		break;			// should not happen
			default:							throw Err_.new_unhandled_default(node.tid);
		}
	}
}
