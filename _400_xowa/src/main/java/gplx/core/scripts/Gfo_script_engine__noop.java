package gplx.core.scripts; import gplx.*; import gplx.core.*;
public class Gfo_script_engine__noop implements Gfo_script_engine {
	public void Load_script(Io_url url) {}
	public Object Get_object(String obj_name) {return null;}
	public void Put_object(String name, Object obj) {}
	public Object Eval_script(String script) {return null;}
	public Object Invoke_method(Object obj, String func, Object... args) {return null;}
	public Object Invoke_function(String func, Object... args) {return null;}
}
