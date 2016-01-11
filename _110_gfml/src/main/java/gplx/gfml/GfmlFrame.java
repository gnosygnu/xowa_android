package gplx.gfml; import gplx.*;
import gplx.core.lists.*; /*StackAdp*/
public interface GfmlFrame {//_20101105
	GfmlLxr Lxr();							// each frame has only one lxr
	int FrameType();
	GfmlObjList WaitingTkns();
	void Build_end(GfmlBldr bldr, GfmlFrame ownerFrame);
	GfmlFrame MakeNew(GfmlLxr newLxr);
}
class GfmlFrameStack {//_20101105
	public int Count() {return stack.Count();}
	public void Push(GfmlFrame frame) {stack.Push(frame);}
	public GfmlFrame Pop() {return (GfmlFrame)stack.Pop();}
	public GfmlFrame Peek() {return (GfmlFrame)stack.Peek();}
	public void Clear() {stack.Clear();}
	StackAdp stack = StackAdp_.new_();
        public static GfmlFrameStack new_() {return new GfmlFrameStack();} GfmlFrameStack() {}
}
