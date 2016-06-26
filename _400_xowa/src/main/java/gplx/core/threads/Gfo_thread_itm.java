package gplx.core.threads; import gplx.*; import gplx.core.*;
public interface Gfo_thread_itm {
	String		Thread__name();
	void		Thread__exec();
	void		Thread__stop();
	boolean		Thread__can_delete(String key);
}
