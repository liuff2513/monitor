package com.baihui.monitor.entity;

import com.baihui.core.jpa.entity.IdJpaEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: Field
 *
 * @author feifei.liu
 * @Description: 映射字段表实体类
 * @date 2015年9月9日 下午3:32:50
 */
@Entity
@Table(name = "s_field")
public class Field extends IdJpaEntity implements Serializable {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1823786884789797587L;
    private String action;//暂未用
    private String alias;//用来判断字段是否在审批后锁死
    private String appendHtml;//暂未用
    private String appendPosition;//暂未用
    private String cssClass;//暂未用
    private Integer dbLength;//表字段长度
    private String dbType;//表字段类型
    private String defaultValue;//默认值（选择列表可以设置默认值）(自动编号可设置是否更新以前的数据)
    private Boolean isCustom;//是否为自定义字段
    private Boolean isHidden;//是否隐藏
    private Boolean isUpdate;//是否可更新
    private String jsFunction;//暂未用
    private Integer listName;//是否在列表上显示（1：显示 0：不显示）
    private Boolean globalOnly;//暂未用
    private Boolean localOnly;//暂未用
    private Integer localOnlyLevel;//暂未用
    private String name;//表字段名称
    private String pojoName;//实体类字段名称
    private String proDictId;//字典ID
    private String projectId;//项目ID
    private Boolean readonly;//是否只读
    private Boolean required;//是否必填
    private Integer seq;//字段排序
    private Integer setLength;//限制长度
    private String showName;//字段显示名称
    private String showType;//字段显示类型
    private String unchangedPosition;//暂未用
    private String validationType;//暂未用
    private Integer type; //0表示系统默认字段，1表示自定义字段
    private ModuleRelation moduleRelation;//关联数据对象
    private Boolean isDeletable; //是否可删除的
    private Boolean isUpdatable; //是否可更改的
    private Date createdTime;//创建时间
    private Date modifiedTime;//修改时间
    private String creatorId;//创建者ID
    private String modifierId;//修改者ID
    private String creatorName;//创建者姓名
    private String modifierName;//修改者姓名
    private String tableName;//表名称
    private Integer basicSeq;//在详情信息头部的显示顺序(-1表示不在详情信息头部显示)
    private Integer isBasic;//0表示不在详情信息头部显示，1表示在详情头部显示
    protected String paramName; //对外API参数名

    public Field() {
    }


    public Field(Integer dbLength, String dbType, String name, Integer setLength, String showName) {
        super();
        this.dbLength = dbLength;
        this.dbType = dbType;
        this.name = name;
        this.setLength = setLength;
        this.showName = showName;
    }

    public Field(String name, String pojoName, String showName, String showType) {
        super();
        this.name = name;
        this.pojoName = pojoName;
        this.showName = showName;
        this.showType = showType;
    }

    @Column(name = "BASIC_SEQ")
    public Integer getBasicSeq() {
        return basicSeq;
    }


    public void setBasicSeq(Integer basicSeq) {
        this.basicSeq = basicSeq;
    }

    @Column(name = "IS_BASIC")
    public Integer getIsBasic() {
        return isBasic;
    }


    public void setIsBasic(Integer isBasic) {
        this.isBasic = isBasic;
    }


    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "APPEND_HTML")
    public String getAppendHtml() {
        return this.appendHtml;
    }

    public void setAppendHtml(String appendHtml) {
        this.appendHtml = appendHtml;
    }

    @Column(name = "APPEND_POSITION")
    public String getAppendPosition() {
        return this.appendPosition;
    }

    public void setAppendPosition(String appendPosition) {
        this.appendPosition = appendPosition;
    }

    @Column(name = "CSS_CLASS")
    public String getCssClass() {
        return this.cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Column(name = "DB_LENGTH")
    public Integer getDbLength() {
        return this.dbLength;
    }

    public void setDbLength(Integer dbLength) {
        this.dbLength = dbLength;
    }

    @Column(name = "DB_TYPE")
    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Column(name = "DEFAULT_VALUE")
    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "GLOBAL_ONLY")
    public Boolean getGlobalOnly() {
        return this.globalOnly;
    }

    public void setGlobalOnly(Boolean globalOnly) {
        this.globalOnly = globalOnly;
    }

    @Column(name = "IS_CUSTOM")
    public Boolean getIsCustom() {
        return this.isCustom;
    }

    public void setIsCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

    @Column(name = "IS_HIDDEN")
    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    @Column(name = "IS_UPDATE")
    public Boolean getIsUpdate() {
        return this.isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    @Column(name = "JS_FUNCTION")
    public String getJsFunction() {
        return this.jsFunction;
    }

    public void setJsFunction(String jsFunction) {
        this.jsFunction = jsFunction;
    }

    @Column(name = "LIST_NAME")
    public Integer getListName() {
        return this.listName;
    }

    public void setListName(Integer listName) {
        this.listName = listName;
    }

    @Column(name = "LOCAL_ONLY")
    public Boolean getLocalOnly() {
        return this.localOnly;
    }

    public void setLocalOnly(Boolean localOnly) {
        this.localOnly = localOnly;
    }

    @Column(name = "LOCAL_ONLY_LEVEL")
    public Integer getLocalOnlyLevel() {
        return this.localOnlyLevel;
    }

    public void setLocalOnlyLevel(Integer localOnlyLevel) {
        this.localOnlyLevel = localOnlyLevel;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "POJO_NAME")
    public String getPojoName() {
        return this.pojoName;
    }

    public void setPojoName(String pojoName) {
        this.pojoName = pojoName;
    }

    @Column(name = "PRO_DICT_ID")
    public String getProDictId() {
        return this.proDictId;
    }

    public void setProDictId(String proDictId) {
        this.proDictId = proDictId;
    }

    @Column(name = "PROJECT_ID")
    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Boolean getReadonly() {
        return this.readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "SET_LENGTH")
    public Integer getSetLength() {
        return this.setLength;
    }

    public void setSetLength(Integer setLength) {
        this.setLength = setLength;
    }

    @Column(name = "SHOW_NAME")
    public String getShowName() {
        return this.showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Column(name = "SHOW_TYPE")
    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    @Column(name = "UNCHANGED_POSITION")
    public String getUnchangedPosition() {
        return this.unchangedPosition;
    }

    public void setUnchangedPosition(String unchangedPosition) {
        this.unchangedPosition = unchangedPosition;
    }

    @Column(name = "VALIDATION_TYPE")
    public String getValidationType() {
        return this.validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODULE_RELATION_ID")
    public ModuleRelation getModuleRelation() {
        return moduleRelation;
    }

    public void setModuleRelation(ModuleRelation moduleRelation) {
        this.moduleRelation = moduleRelation;
    }

    @Column(name = "IS_DELETABLE")
    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
    }

    @Column(name = "IS_UPDATABLE")
    public Boolean getIsUpdatable() {
        return isUpdatable;
    }

    public void setIsUpdatable(Boolean isUpdatable) {
        this.isUpdatable = isUpdatable;
    }

    @Column(name = "CREATED_TIME")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "MODIFIED_TIME")
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Column(name = "CREATOR_ID")
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Column(name = "MODIFIER_ID")
    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    @Column(name = "CREATOR_NAME")
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Column(name = "MODIFIER_NAME")
    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = creatorName;
    }

    @Column(name = "TABLE_NAME")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "param_name")
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}