package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_crt_visitor__last implements Srch_crt_visitor { // inorder
	private int last_uid;
	public int node_count;
	public Srch_crt_node last_node;
	public int Find_last_uid(Srch_crt_node root) {
		last_uid = node_count = 0;
		Visit(root);
		return last_uid;
	}
	public void Visit(Srch_crt_node node) {
		switch (node.tid) {
			case Srch_crt_node_.Tid__word:
			case Srch_crt_node_.Tid__word_quote:		last_uid = node.uid; ++node_count; last_node = node; break;
			case Srch_crt_node_.Tid__or:	
			case Srch_crt_node_.Tid__and:
			case Srch_crt_node_.Tid__not:
				Srch_crt_node[] subs = node.subs;					
				int subs_len = subs.length;
				for (int i = 0; i < subs_len; ++i)
					subs[i].Accept_visitor(this);
				break;
			case Srch_crt_node_.Tid__invalid:		break;			// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
        public static final Srch_crt_visitor__last Instance = new Srch_crt_visitor__last(); Srch_crt_visitor__last() {}
}
class Srch_crt_visitor__to_str implements Srch_crt_visitor {
	private final Bry_bfr bfr = Bry_bfr.new_();
	public String To_str(Srch_crt_node root) {
		Visit(root);
		return bfr.To_str_and_clear();
	}
	public void Visit(Srch_crt_node node) {
		switch (node.tid) {
			case Srch_crt_node_.Tid__word:			bfr.Add(node.raw); break;
			case Srch_crt_node_.Tid__word_quote:	bfr.Add_byte_quote().Add(node.raw).Add_byte_quote(); break;
			case Srch_crt_node_.Tid__and:
			case Srch_crt_node_.Tid__or:
				bfr.Add_byte(Byte_ascii.Paren_bgn);
				Srch_crt_node[] subs = node.subs;					
				int subs_len = subs.length;
				for (int i = 0; i < subs_len; ++i) {
					if (i != 0)
						bfr.Add_str_a7(node.tid == Srch_crt_node_.Tid__and ? " AND " : " OR ");
					subs[i].Accept_visitor(this);
				}
				bfr.Add_byte(Byte_ascii.Paren_end);
				break;
			case Srch_crt_node_.Tid__not:
				bfr.Add_str_a7("NOT ");
				node.subs[0].Accept_visitor(this);
				break;
			case Srch_crt_node_.Tid__invalid:		break;			// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
}
class Srch_crt_visitor__flatten implements Srch_crt_visitor {
	private final List_adp list = List_adp_.new_();
	private boolean flatten_pass;
	private int flatten_tid;
	public Srch_crt_node[] Flatten(Srch_crt_node root) {
		flatten_tid = 0;
		flatten_pass = true;
		Visit(root);
		Srch_crt_node[] rv = flatten_pass && flatten_tid != 0 ? (Srch_crt_node[])list.To_ary(Srch_crt_node.class) : null;
		list.Clear();
		return rv;
	}
	public void Visit(Srch_crt_node node) {
		if (!flatten_pass) return;
		int node_tid = node.tid;
		switch (node_tid) {
			case Srch_crt_node_.Tid__and:
			case Srch_crt_node_.Tid__or:
				if		(flatten_tid == 0)			flatten_tid = node_tid;
				else if	(flatten_tid == node_tid)	{}
				else								flatten_pass = false;
				if (flatten_pass) {
					Srch_crt_node[] subs = node.subs;
					int subs_len = subs.length;
					for (int i = 0; i < subs_len; ++i)
						Visit(subs[i]);
				}
				break;
			case Srch_crt_node_.Tid__word:
			case Srch_crt_node_.Tid__word_quote:
			case Srch_crt_node_.Tid__not:			list.Add(node); break;
			case Srch_crt_node_.Tid__invalid:		break;			// should not happen
			default:								throw Err_.new_unhandled_default(node.tid);
		}
	}
}
