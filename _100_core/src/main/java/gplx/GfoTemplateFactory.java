package gplx;
public class GfoTemplateFactory implements Gfo_invk {
	public void Reg(String key, GfoTemplate template) {hash.Add(key, template);}
	public Object Make(String key) {
		GfoTemplate template = (GfoTemplate)hash.Get_by(key);
		return template.NewCopy(template);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		ctx.Match(k, k);
		Object o = hash.Get_by(k);
		return o == null ? Gfo_invk_.Rv_unhandled : o;
	}
        public static final    GfoTemplateFactory Instance = new GfoTemplateFactory(); GfoTemplateFactory() {}
	Hash_adp hash = Hash_adp_.New();
}
