package gplx.gfui.draws; import gplx.*; import gplx.gfui.*;
public class ColorAdpCache {//_20101206
	public java.awt.Color GetNativeColor(ColorAdp color) {//#<>System.Drawing.Color~java.awt.Color
		Object rv = hash.Get_by(color.Value()); if (rv != null) return (java.awt.Color)rv;//#<>System.Drawing.Color~java.awt.Color
		rv = new java.awt.Color(color.Red(), color.Green(), color.Blue(), color.Alpha());//#<>System.Drawing.Color.FromArgb(color.Alpha(), color.Red(), color.Green(), color.Blue())~new java.awt.Color(color.Red(), color.Green(), color.Blue(), color.Alpha())
		hash.Add(color.Value(), rv);
		return (java.awt.Color)rv;//#<>System.Drawing.Color~java.awt.Color
	}
	Hash_adp hash = Hash_adp_.New();
	public static final    ColorAdpCache Instance = new ColorAdpCache(); ColorAdpCache() {}
}
