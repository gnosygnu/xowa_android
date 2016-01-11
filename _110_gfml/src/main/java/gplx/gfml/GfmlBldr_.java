package gplx.gfml; import gplx.*;
class GfmlBldr_ {
	@gplx.Internal protected static GfmlBldr new_() {return new GfmlBldr();}
	@gplx.Internal protected static GfmlBldr default_() {			
		GfmlBldr rv = new GfmlBldr();
		GfmlLxr rootLxr = GfmlDocLxrs.Root_lxr();
		GfmlDocLxrs.Default_lxr(rv.Doc().LxrRegy(), rootLxr);
		rv.Doc().RootLxr_set(rootLxr);
		InitDocBldr(rv
			, GfmlPragmaVar.new_()
			, GfmlPragmaType.new_()
			, GfmlPragmaDefault.new_()
			, GfmlPragmaLxrSym.new_()
			, GfmlPragmaLxrFrm.new_()
			);
		return rv;
	}
	static void InitDocBldr(GfmlBldr bldr, GfmlPragma... ary) {
		GfmlTypeMakr makr = GfmlTypeMakr.new_();
		GfmlTypRegy regy = bldr.TypeMgr().TypeRegy(); GfmlPragmaMgr cmdMgr = bldr.Doc().PragmaMgr();

		for (GfmlPragma pragma : ary) {
			regy.Add_ary(pragma.MakePragmaTypes(makr));
			cmdMgr.Pragmas_add(pragma);
		}
	}
}
