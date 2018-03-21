package com.baihui.core.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * ClassName: IdJpaEntity
 * @Description: 统一定义id的entity基类.
 * <p>基类统一定义id的属性名称、数据类型、列名映射及生成策略.</p>
 * <p>Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。</p>
 * @author feifei.liu
 * @date 2017/6/27 20:40
 */
@MappedSuperclass
public abstract class IdJpaEntity implements Serializable {
	private static final long serialVersionUID = -8387872691238961417L;
	protected String id;

	@Id
	@Column(length = 32, nullable = false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
