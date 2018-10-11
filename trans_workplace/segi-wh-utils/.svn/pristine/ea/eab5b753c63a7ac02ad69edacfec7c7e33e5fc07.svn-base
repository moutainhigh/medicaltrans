package com.segi.uhomecp.wh.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 变化对象
 *     
 * 包: com.segi.uhomecp.lsmanager.car.msg 
 * 类名称: ColumnsChangeEntry.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午4:25:57
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class ColumnsChangeEntry implements java.io.Serializable {

	private static final long serialVersionUID = -6650674416221910752L;
	
	public static final String before = "beforeColumns";
	
	public static final String after = "afterColumns";
	
	private Map<String, List<List<Column>>> rowData = new HashMap<String, List<List<Column>>>();
	
	private String operation;
	
	private String table;

	public Map<String, List<List<Column>>> getRowData() {
		return rowData;
	}

	public void setRowData(Map<String, List<List<Column>>> rowData) {
		this.rowData = rowData;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public void putBeforeRows(List<Column> row) {
		if (this.getRowData().get(before) == null) {
			this.getRowData().put(before, new ArrayList<List<Column>>());
		}
		this.getRowData().get(before).add(row);
	}
	
	public List<List<Column>> getBeforeRows() {
		if (this.getRowData().get(before) == null) {
			this.getRowData().put(before, new ArrayList<List<Column>>());
		}
		return this.getRowData().get(before);
	}
	
	public void putAfterRows(List<Column> row) {
		if (this.getRowData().get(after) == null) {
			this.getRowData().put(after, new ArrayList<List<Column>>());
		}
		this.getRowData().get(after).add(row);
	}
	
	public List<List<Column>> getAfterRows() {
		if (this.getRowData().get(after) == null) {
			this.getRowData().put(after, new ArrayList<List<Column>>());
		}
		return this.getRowData().get(after);
	}
	
}
