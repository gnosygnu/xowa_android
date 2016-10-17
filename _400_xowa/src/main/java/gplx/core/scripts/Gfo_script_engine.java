package gplx.core.scripts; import gplx.*; import gplx.core.*;
public interface Gfo_script_engine {
	void Load_script(Io_url url);
	Object Get_object(String obj_name);
	void Put_object(String name, Object obj);
	Object Eval_script(String script);
	Object Invoke_method(Object obj, String func, Object... args);
	Object Invoke_function(String func, Object... args);
}
