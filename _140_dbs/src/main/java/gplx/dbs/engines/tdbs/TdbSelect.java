package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.criterias.*; import gplx.core.gfo_ndes.*; import gplx.dbs.qrys.*; import gplx.dbs.sqls.*;
import gplx.core.lists.*; /*ComparerAble*/ import gplx.core.stores.*; /*GfoNdeRdr*/
class TdbSelectWkr implements Db_qryWkr {
	public Object Exec(Db_engine engineObj, Db_qry cmdObj) {
		TdbEngine engine = TdbEngine.cast(engineObj); Db_qry__select_cmd cmd = (Db_qry__select_cmd)cmdObj;
		if (cmd.From().Tbls().Count() > 1) throw Err_.new_("gplx.tdbs", "joins not supported for tdbs", "sql", cmd.Xto_sql());

		TdbTable tbl = engine.FetchTbl(cmd.From().BaseTable().TblName());
		GfoNdeList rv = (cmd.Where() == Db_qry_.WhereAll && cmd.Limit() == Db_qry__select_cmd.Limit_disabled) ? rv = tbl.Rows() : FilterRecords(tbl, cmd.Where(), cmd.Limit());
		if (cmd.GroupBy() != null)
			rv = TdbGroupByWkr.GroupByExec(cmd, rv, tbl);
		if (cmd.OrderBy() != null) {	// don't use null pattern here; if null ORDER BY, then don't call .Sort on GfoNdeList
			ComparerAble comparer = Sql_order_by_sorter.new_((Sql_order_by_itm[])cmd.OrderBy().Flds().To_ary(Sql_order_by_itm.class));
			rv.Sort_by(comparer);
		}
		return GfoNdeRdr_.peers_(rv, false);
	}
	GfoNdeList FilterRecords(TdbTable tbl, Criteria crt, int limit) {
		GfoNdeList rv = GfoNdeList_.new_();
		int count = 0;
		for (int i = 0; i < tbl.Rows().Count(); i++) {
			GfoNde row = (GfoNde)tbl.Rows().FetchAt_asGfoNde(i);
			if (crt.Matches(row)) rv.Add(row);
			++count;
			if (count == limit) break;
		}
		return rv;
	}
	public static final TdbSelectWkr Instance = new TdbSelectWkr(); TdbSelectWkr() {}
}
class TdbGroupByWkr {
	public static GfoNdeList GroupByExec(Db_qry__select_cmd select, GfoNdeList selectRows, TdbTable tbl) {
		GfoNdeList rv = GfoNdeList_.new_();
		Ordered_hash groupByHash = Ordered_hash_.New();
		List_adp groupByFlds = select.GroupBy().Flds();
		GfoFldList selectFldsForNewRow = select.Cols().Flds().XtoGfoFldLst(tbl);
		Sql_select_fld_list selectFlds = select.Cols().Flds();
		for (int rowIdx = 0; rowIdx < selectRows.Count(); rowIdx++) {
			GfoNde selectRow = selectRows.FetchAt_asGfoNde(rowIdx);
			GfoNde groupByRow = FindOrNew(selectFldsForNewRow, groupByFlds, selectRow, groupByHash, rv);
			for (int i = 0; i < selectFlds.Count(); i++) {
				Sql_select_fld_base selectFld = selectFlds.Get_at(i);
				Object val = groupByRow.Read(selectFld.Alias());	// groupByRow is keyed by Alias; EX: Count(Id) AS CountOf
				groupByRow.WriteAt(i, selectFld.GroupBy_eval(val, selectRow.Read(selectFld.Fld()), selectFld.ValType()));
			}
		}
		return rv;
	}
	static GfoNde FindOrNew(GfoFldList selectFldsForNewRow, List_adp groupByFlds, GfoNde selectRow, Ordered_hash groupByRows, GfoNdeList rslt) {
		int len = groupByFlds.Count();
		Ordered_hash curHash = groupByRows;
		GfoNde rv = null;
		for (int i = 0; i < len; i++) {
			String fld = (String)groupByFlds.Get_at(i);
			boolean last = i == len - 1;
			Object val = selectRow.Read(fld);
			Object o = curHash.Get_by(val);
			if (last) {
				if (o == null) {
					Object[] valAry = new Object[selectFldsForNewRow.Count()];
					rv = GfoNde_.vals_(selectFldsForNewRow, valAry);
					curHash.Add(val, rv);
					rslt.Add(rv);
				}
				else
					rv = GfoNde_.as_(o);
			}
			else {
				if (o == null) {
					Ordered_hash nextHash = Ordered_hash_.New();
					curHash.Add(val, nextHash);
					curHash = nextHash;
				}
				else {
					curHash = (Ordered_hash)o;
				}
			}
		}
		return rv;
	}
}
