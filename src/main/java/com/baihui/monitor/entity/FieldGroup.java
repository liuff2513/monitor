package com.baihui.monitor.entity;

import com.baihui.core.jpa.entity.IdJpaEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FieldGroup
 * @Description: 映射字段分组表实体类（s_field_group 为主子表）
 * @author feifei.liu
 * @date 2015年9月9日 下午3:53:32
 */
@Entity
@Table(name = "s_field_group")
public class FieldGroup extends IdJpaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;//布局分组名称
	private Integer type;//布局分组类型（1：单列 2：双列）
	private Integer fieldFloat;//布局字段位置（ 0：靠左 1：靠右）
	private Integer seq;//布局排序，包括分组的排序和字段的排序
	private String projectId;//项目ID
	private Boolean required;//暂未用
	private Integer defaultColNum;//暂未用
	private String defaultColumn;//暂未用
	private FieldGroup parent;//布局字段的上级（布局分组）
	private Field field;//布局字段对应的映射字段
	private List<FieldGroup> children=new ArrayList<>();// 子表数据映射字段实体集合
	private String tableName;//对应的业务表名

	public FieldGroup() {
	}

	@Column(name = "DEFAULT_COL_NUM")
	public Integer getDefaultColNum() {
		return this.defaultColNum;
	}

	public void setDefaultColNum(Integer defaultColNum) {
		this.defaultColNum = defaultColNum;
	}

	@Column(name = "DEFAULT_COLUMN")
	public String getDefaultColumn() {
		return this.defaultColumn;
	}

	public void setDefaultColumn(String defaultColumn) {
		this.defaultColumn = defaultColumn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PROJECT_ID")
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Boolean getRequired() {
		return this.required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	public Integer getFieldFloat() {
		return fieldFloat;
	}

	public void setFieldFloat(Integer fieldFloat) {
		this.fieldFloat = fieldFloat;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
//	@JsonIgnoreProperties(value = {"parent","children"})
	public FieldGroup getParent() {
		return parent;
	}

	public void setParent(FieldGroup parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
//	@JsonIgnoreProperties(value = {"children","children"})
	public List<FieldGroup> getChildren() {
		return children;
	}

	public void setChildren(List<FieldGroup> children) {
		this.children = children;
	}

	public FieldGroup addChild(FieldGroup child) {
		getChildren().add(child);
		child.setParent(this);

		return child;
	}

	public FieldGroup removeChild(FieldGroup child) {
		getChildren().remove(child);
		child.setParent(null);

		return child;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "FIELD_ID")
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}