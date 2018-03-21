package com.baihui.core.jpa.support;

import com.baihui.core.jpa.enums.QueryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseJpaRepository
 * @Description: 公共基础Repository，继承此Repository可使用该接口定义的公共方法
 * @param <T> 实体类类型
 * @param <ID> 实体类主键类型
 * @author feifei.liu
 * @date 2015年9月9日 下午1:03:28
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Class<T> getDomainClass();

    void clear();

    void rollback();

    T getOne(ID id);

    <S> S getOne(Class<S> clazz, ID id);

    T findOne(ID id);

    <S> S findOne(Class<S> clazz, ID id);

    T findOne(Specification<T> spec);

    <S> S persist(S entity);

    <S> S merge(S entity);

    Page<T> findAll(Specification<T> spec, Pageable pageable);

    List<T> findAll(Specification<T> spec);

    List<T> findAll(Specification<T> spec, Sort sort);

    long count(Specification<T> spec);

    List findAll(String queryString);

    List findBySql(String sqlString);

    Map<QueryType, Object> findBySql(String sqlString, int start, int length);

    List findBySql(String sqlString, Class<?> resultClass);

    List findBySql(String sqlString, String resultSetMapping);

    Page<T> findAll(String queryString, Pageable pageable);

    Query createNativeQuery(String sqlString);

    Query createNativeQuery(String sqlString, Class<?> clazz);

    Query createNativeQuery(String sqlString, String resultSetMapping);

    StoredProcedureQuery createStoredProcedureQuery(String procedureName);

    StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class<?>... resultClasses);

    StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings);

    int execute(String sqlString);

    int updateQuery(String updateQuery);

    int execNativeQuery(String sqlString, Object... objects);

    Page<T> findByAuto(T example, Pageable pageable);
}
