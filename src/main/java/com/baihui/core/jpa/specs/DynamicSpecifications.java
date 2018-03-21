package com.baihui.core.jpa.specs;

import com.baihui.core.jpa.enums.SearchFilter;
import com.baihui.core.util.collection.Collections3;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: CustomSpecification
 * @Description: Jpa Specification查询构造器
 * @author feifei.liu
 * @date 2017/6/28 18:50
 */
public class DynamicSpecifications {

	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {
					List<Predicate> predicates = new ArrayList<>();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
							case EQ:
								predicates.add(builder.equal(expression, filter.value));
								break;
							case LIKE:
								predicates.add(builder.like(expression, "%" + filter.value + "%"));
								break;
							case GT:
								predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
								break;
							case LT:
								predicates.add(builder.lessThan(expression, (Comparable) filter.value));
								break;
							case GTE:
								predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case LTE:
								predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
								break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

	public static <T>Specification<T> byAuto(final EntityManager entityManager, final T example) {
		final Class<T> type = (Class<T>) example.getClass();
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				EntityType<T> entity = entityManager.getMetamodel().entity(type);
				for(Attribute<T, ?> attr:entity.getDeclaredAttributes()) {
					Object attrValue = getValue(example, attr);
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) {
							if (!org.springframework.util.StringUtils.isEmpty(attrValue)) {
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String) attrValue)));
							}
						} else {
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
						}
					}
				}
				return predicates.isEmpty()?cb.conjunction():cb.and(Iterables.toArray(predicates, Predicate.class));
			}
			private <T> Object getValue(T example, Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			private  <E, T>SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
															 Class<E> fieldClass) {
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}
		};
	}

	/**
	 * 构造like的查询模式，即前后加%
	 * @param str
	 * @return
	 */
	private static String pattern(String str) {
		return "%"+str+"%";
	}
}
