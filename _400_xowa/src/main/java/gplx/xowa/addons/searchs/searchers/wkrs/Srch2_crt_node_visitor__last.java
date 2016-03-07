package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.addons.searchs.searchers.crts.*;
public class Srch2_crt_node_visitor__last implements Srch2_crt_node_visitor { // inorder
	private int last_uid;
	public int node_count;
	public Srch2_crt_node last_node;
	public int Find_last_uid(Srch2_crt_node root) {
		last_uid = node_count = 0;
		Visit(root);
		return last_uid;
	}
	public void Visit(Srch2_crt_node node) {
		switch (node.tid) {
			case Srch2_crt_node.Tid_word:
			case Srch2_crt_node.Tid_word_quote:		last_uid = node.uid; ++node_count; last_node = node; break;
			case Srch2_crt_node.Tid_or:	
			case Srch2_crt_node.Tid_and:
				node.lhs.Accept_visitor(this);
				node.rhs.Accept_visitor(this);
				break;
			case Srch2_crt_node.Tid_not:
				node.rhs.Accept_visitor(this);
				break;
			case Srch2_crt_node.Tid_invalid:		break;			// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
        public static final Srch2_crt_node_visitor__last Instance = new Srch2_crt_node_visitor__last(); Srch2_crt_node_visitor__last() {}
}
