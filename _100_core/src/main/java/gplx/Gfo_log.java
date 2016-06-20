package gplx;
public interface Gfo_log {
	List_adp	Itms();
	Gfo_log		Itms_(List_adp v);
	void		Warn(String msg, Object... args);
	void		Note(String msg, Object... args);
	void		Info(String msg, Object... args);
	void		Prog(String msg, Object... args);
	void		Flush();
}	
