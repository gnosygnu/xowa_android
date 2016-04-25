package gplx.core.progs; import gplx.*; import gplx.core.*;
public interface Gfo_prog_task {
	Gfo_prog_task Prog__owner();
	long Prog__all();
	long Prog__cur();
	void Prog__start();
	void Prog__cancel();
	void Prog__pause();
	void Prog__resume();
}
