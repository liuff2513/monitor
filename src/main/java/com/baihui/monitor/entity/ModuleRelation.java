package com.baihui.monitor.entity;

import com.baihui.core.jpa.entity.IdJpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the s_module_relation database table.
 * 
 */
@Entity
@Table(name="s_module_relation")
public class ModuleRelation extends IdJpaEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3284643527890253417L;
	private String projectId;// 项目ID
	private String entityNameA;// 当前模块实体类名
	private String entityNameB;// 关联模块实体类名
	private String colId;//n的一方字段ID名
	private String colName;//n的一方字段NAME名
	private String tableNameA;// 当前模块表名(n的一方)
	private String tableNameB;// 关联模块表名(1的一方)
	private String nameSpaceA;// 当前模块nameSpace
	private String nameSpaceB;// 关联模块nameSpace
	private String actionNameA;// 当前模块actionName
	private String actionNameB;// 关联模块actionName
	private String relatedName;//被关联模块内列表显示名称(n作为1的关联模块的显示名)
	private String relationDesc;// 关联简单描述
	private Boolean isHidden;//是否隐藏

	public ModuleRelation() {
	}

	@Column(name="ENTITY_NAME_A")
	public String getEntityNameA() {
		return this.entityNameA;
	}

	public void setEntityNameA(String entityNameA) {
		this.entityNameA = entityNameA;
	}


	@Column(name="ENTITY_NAME_B")
	public String getEntityNameB() {
		return this.entityNameB;
	}

	public void setEntityNameB(String entityNameB) {
		this.entityNameB = entityNameB;
	}


	@Column(name="COL_ID")
	public String getColId() {
		return this.colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}


	@Column(name="COL_NAME")
	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	@Column(name="TABLE_NAME_A")
	public String getTableNameA() {
		return this.tableNameA;
	}

	public void setTableNameA(String tableNameA) {
		this.tableNameA = tableNameA;
	}


	@Column(name="TABLE_NAME_B")
	public String getTableNameB() {
		return this.tableNameB;
	}

	public void setTableNameB(String tableNameB) {
		this.tableNameB = tableNameB;
	}

	@Column(name="PROJECT_ID")
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	
	@Column(name="NAME_SPACE_A")
	public String getNameSpaceA() {
		return nameSpaceA;
	}
	
	public void setNameSpaceA(String nameSpaceA) {
		this.nameSpaceA = nameSpaceA;
	}
	
	@Column(name="NAME_SPACE_B")
	public String getNameSpaceB() {
		return nameSpaceB;
	}

	public void setNameSpaceB(String nameSpaceB) {
		this.nameSpaceB = nameSpaceB;
	}

	@Column(name="ACTION_NAME_A")
	public String getActionNameA() {
		return actionNameA;
	}
	
	public void setActionNameA(String actionNameA) {
		this.actionNameA = actionNameA;
	}
	
	@Column(name="ACTION_NAME_B")
	public String getActionNameB() {
		return actionNameB;
	}

	public void setActionNameB(String actionNameB) {
		this.actionNameB = actionNameB;
	}
	
	@Column(name="RELATION_DESC")
	public String getRelationDesc() {
		return relationDesc;
	}

	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}

	@Column(name="IS_HIDDEN")
	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}
	

}