package gplx.core.lists; import gplx.*; import gplx.core.*;
public class StackAdp_ {//_20110320 
	public static StackAdp new_() {return new StackAdp_base();}
}
class StackAdp_base implements StackAdp {//_20110320 
	public Object Peek() {return Peek_base();}
	public Object Pop() {return Pop_base();}
	public void Push(Object obj) {Push_base(obj);}
	public List_adp XtoList() {
		List_adp list = List_adp_.New();
		for (Object obj : stack)
			list.Add(obj);
		// NOTE: dotnet traverses last to first; java: first to last //#<>list.Reverse();~
		return list;
	}
	final    java.util.Stack stack = new java.util.Stack();//#<>System.Collections.Stack~java.util.Stack
	public StackAdp_base() {}
	public int Count() {return stack.size();}//#<>.Count~.size()
	public void Clear() {stack.clear();}//#<>.Clear~.clear
	protected void Push_base(Object obj) {stack.push(obj);}//#<>.Push~.push
	protected Object Pop_base() {return stack.pop();}//#<>.Pop~.pop
	protected Object Peek_base() {return stack.peek();}//#<>.Peek~.peek
	public java.util.Iterator iterator() {return stack.iterator();}
}
