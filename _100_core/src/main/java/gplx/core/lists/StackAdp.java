package gplx.core.lists; import gplx.*; import gplx.core.*;
public interface StackAdp extends EnumerAble {//_20110320
	int Count();
	void Clear();
	void Push(Object obj);
	Object Pop();
	Object Peek();
	List_adp XtoList();
}	
