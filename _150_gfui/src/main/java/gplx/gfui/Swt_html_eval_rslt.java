package gplx.gfui;

public class Swt_html_eval_rslt {
	public void Clear() {error = null; result = null;}
	public boolean Result_pass() {return error == null;}
	public Object Result() {return result;} public void Result_set(Object v) 	{result = v; error = null;} private Object result;
	public String Error () {return error;} 	public void Error_set(String v) 	{error = v; result = null;} private String error;
}
