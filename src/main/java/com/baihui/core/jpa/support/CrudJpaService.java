package com.baihui.core.jpa.support;

import com.baihui.core.jpa.enums.QueryType;
import com.baihui.core.jpa.enums.SearchFilter;
import com.baihui.core.jpa.specs.DynamicSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseService
 * @Description: 基础Service
 * @param <T>
 * @param <ID>
 * @author feifei.liu
 * @date 2015年9月9日 上午11:40:45
 */
@Component
@Transactional
public abstract class CrudJpaService<T, ID extends Serializable> {

    @Autowired
    public BaseJpaRepository<T, ID> jpaRepository;

    /**
     * @Description: 缓存：将修改的数据发送到数据库服务器端的数据高速缓存中，而不是数据文件中
     * <p/>
     * 注：此方法只同步持久化对象到缓存中，像execute()这种执行不返回持久化对象的操作是不会同步到缓存的,<BR/>
     * 	     此时要精准读取数据库中数据的话需先执行clear()方法，将持久化对象改变为游离态对象,也就是清除缓存
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:32:08
     */
    public void flush() {
        jpaRepository.flush();
    }

    /**
     * @Description: 清除：把实体管理器中所有的实体对象变成游离状态
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:34:52
     */
    public void clear() {
        jpaRepository.clear();
    }

    /**
     * @Description: 事务回滚：回滚未提交的数据
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2016年1月19日 下午7:22:51
     */
    public void rollback() {
        jpaRepository.rollback();
    }

    /**
     * @Description: 查询：根据ID获取T对象
     * @param id
     * @return
     * @return T
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:03:18
     */
    public T getOne(ID id) {
        return jpaRepository.getOne(id);
    }

    /**
     * @Description: 查询：根据ID获取T对象
     * @param id
     * @return
     * @return T
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:03:18
     */
    public T findOne(ID id) {
        return jpaRepository.findOne(id);
    }

    /**
     * @Description: 查询：根据JPA规范获取T对象
     * @param spec
     * @return
     * @return T
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:03:51
     */
    public T findOne(Specification<T> spec) {
        return jpaRepository.findOne(spec);
    }

    /**
     * @Description: 查询：根据对象类型和主键
     * @param entityClass
     * @param primaryKey
     * @return
     * @return Object
     * @throws
     * @author feifei.liu
     * @date 2016年8月14日 下午4:08:43
     */
    public <S> S findOne(Class<S> entityClass, ID primaryKey) {
        return jpaRepository.findOne(entityClass, primaryKey);
    }

    /**
     * @Description: 删除：根据ID物理删除
     * @param id
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月8日 上午10:25:06
     */
    public void delete(ID id) {
        jpaRepository.delete(id);
    }

    /**
     * @Description: 删除：删除T对象
     * @param entity
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:04:58
     */
    public void delete(T entity) {
        jpaRepository.delete(entity);
    }

    /**
     * @Description: 删除：删除T集合
     * @param entities
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:10:32
     */
    public void delete(Iterable<? extends T> entities) {
        jpaRepository.delete(entities);
    }

    /**
     * @Description: 删除：对T映射的数据表进行清空
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:11:13
     */
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    /**
     * @Description: 删除：批量删除T集合
     * @param entities
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:12:09
     */
    public void deleteInBatch(Iterable<T> entities) {
        jpaRepository.deleteInBatch(entities);
    }

    /**
     * @Description: 删除：批量删除T映射的数据表所有数据
     * @return void
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:12:28
     */
    public void deleteAllInBatch() {
        jpaRepository.deleteAllInBatch();
    }

    /**
     * @Description: 计数：获取符合JPA规范的数据数量
     * @param spec	JPA规范
     * @return
     * @return long
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:26:31
     */
    public long count(Specification<T> spec) {
        return jpaRepository.count(spec);
    }

    /**
     * @Description: 保存：保存
     * @param entity
     * @return
     * @return S
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:27:33
     */
    public <S> S merge(S entity) {
        return jpaRepository.merge(entity);
    }

    /**
     * @Description: 保存：保存并刷新S对象
     * @param entity
     * @return
     * @return S
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:27:33
     */
    public <S extends T> S saveAndFlush(S entity) {
        return jpaRepository.saveAndFlush(entity);
    }

    /**
     * @Description: 保存：保存S集合
     * @param entities
     * @return
     * @return List<S>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:28:21
     */
    public <S extends T> List<S> save(Iterable<S> entities) {
        return jpaRepository.save(entities);
    }

    /**
     * @Description: 保存：保存T对象
     * @param entity
     * @return
     * @return T
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:28:49
     */
    public T save(T entity) {
        return jpaRepository.save(entity);
    }


    /**
     * @Description: 执行HQL更新语句
     * @param updateQuery
     * @return
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年10月10日 下午1:18:26
     */
    public int updateQuery(String updateQuery){
        return jpaRepository.updateQuery(updateQuery);
    }

    /**
     * @Description: 操作：执行SQL语句操作, 返回Query对象
     * @param sqlString
     * @return
     * @return javax.persistence.Query
     * @throws
     * @author feifei.liu
     * @date 2016年6月2日 下午1:51:31
     */
    public javax.persistence.Query createNativeQuery(String sqlString) {
        return jpaRepository.createNativeQuery(sqlString);
    }

    public javax.persistence.Query createNativeQuery(String sqlString, Class<?> clazz) {
        return jpaRepository.createNativeQuery(sqlString, clazz);
    }

    public javax.persistence.Query createNativeQuery(String sqlString, String resultSetMapping) {
        return jpaRepository.createNativeQuery(sqlString, resultSetMapping);
    }

    /**
     * @Description: 存储过程调用
     * @param procedureName
     * @return
     * @return StoredProcedureQuery
     * @throws
     * @author feifei.liu
     * @date 2016年6月1日 下午9:25:20
     */
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName){
        return jpaRepository.createStoredProcedureQuery(procedureName);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class<?>... resultClasses) {
        return jpaRepository.createStoredProcedureQuery(procedureName, resultClasses);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return jpaRepository.createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    /**
     * @Description:执行
     * @param sqlString
     * @param objects
     * @return int
     * @throws
     * @author chong.cheng
     * @date 2017年5月12日 下午1:14:54
     */
    public int execNativeQuery(String sqlString, Object...objects){
        return jpaRepository.execNativeQuery(sqlString, objects);
    }

    /**
     * @Description: 操作：执行SQL语句操作
     * @param sqlString
     * @return
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年9月21日 下午2:37:19
     */
    public int execute(String sqlString){
        return jpaRepository.execute(sqlString);
    }

    /**
     * @Description: 查询：所有
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年9月21日 下午2:37:19
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }
    /**
     * @Description: 查询：分页查询
     * @param pageNumber	页码
     * @param pageSize	每页个数
     * @param sortType	排序
     * @return
     * @return Page<T>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:22:18
     */
    public Page<T> findAll(int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        return jpaRepository.findAll(pageRequest);
    }

    /**
     * @Description: 查询：分页查询
     * @param pageable	分页信息对象
     * @return
     * @return Page<T>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:23:19
     */
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    /**
     * @Description: 查询：分页查询
     * @param spec	JPA规范
     * @param pageable	分页信息对象
     * @return
     * @return Page<T>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:23:32
     */
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return jpaRepository.findAll(spec, pageable);
    }

    /**
     * @Description: 查询：根据JPA规范查询T集合
     * @param spec	JPA规范
     * @return
     * @return List<T>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:24:41
     */
    public List<T> findAll(Specification<T> spec) {
        return jpaRepository.findAll(spec);
    }

    /**
     * @Description: 查询：根据JPA规范和排序规范查询T集合
     * @param spec	JPA规范
     * @param sort	排序规范
     * @return
     * @return List<T>
     * @throws
     * @author feifei.liu
     * @date 2015年12月11日 上午11:25:48
     */
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return jpaRepository.findAll(spec, sort);
    }

    @SuppressWarnings("rawtypes")
    public List findAll(String queryString) {
        return jpaRepository.findAll(queryString);
    }

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString) {
        return jpaRepository.findBySql(sqlString);
    }

    public Map<QueryType, Object> findBySql(String sqlString, int start, int length) {
        return jpaRepository.findBySql(sqlString, start, length);
    }

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString, Class resultClass) {
        return jpaRepository.findBySql(sqlString, resultClass);
    }

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString, String resultSetMapping) {
        return jpaRepository.findBySql(sqlString, resultSetMapping);
    }

    public Page<T> findAll(String queryString, Pageable pageable) {
        return jpaRepository.findAll(queryString, pageable);
    }

    @SuppressWarnings("rawtypes")
    public List findMasterBySql(String sqlString) {
        return jpaRepository.findBySql(sqlString);
    }
    /**
     * 分页查询
     *
     * @param searchParams
     *            搜索条件 Map<key,value>
     * @param pageable
     *            包含页数、每页条数和排序的pageable对象
     * @return Page<T>
     */
    public Page<T> findAll(Map<String, Object> searchParams, Pageable pageable) {
        Specification<T> spec = buildSpecification(searchParams);
        return jpaRepository.findAll(spec, pageable);
    }

    /**
     * @Description: 分页查询
     * @param searchParams 搜索条件 Map<key,value>
     * @param pageNumber 页数
     * @param pageSize 每页条数
     * @param sortType 排序
     * @return
     * @return Page<T>
     * @throws
     * @author feifei.liu
     * @date 2015年9月10日 下午1:03:08
     */
    public Page<T> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
        Pageable pageable = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<T> spec = buildSpecification(searchParams);
        return jpaRepository.findAll(spec, pageable);
    }

    /**
     * @Description: 创建分页请求
     * @param pageNumber
     * @param pagzSize
     * @param sortType
     * @return
     * @return PageRequest
     * @throws
     * @author feifei.liu
     * @date 2015年9月10日 下午1:03:00
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        List<Order> orders = new ArrayList<>();
        if ("auto".equals(sortType)) {
            orders.add(new Order(Direction.DESC, "id"));
        } else if (!"".equals(sortType)) {
            if (sortType.contains(",")) {
                String[] args = sortType.split(",");
                for (String arg : args) {
                    if (arg.contains(" ")) {
                        String[] strs = arg.split(" ");
                        orders.add(new Order("desc".equals(strs[1].toLowerCase()) ? Direction.DESC : Direction.ASC,
                                strs[0]));
                    } else {
                        orders.add(new Order(Direction.ASC, arg));
                    }
                }
            } else {
                if (sortType.contains(" ")) {
                    String[] strs = sortType.split(" ");
                    orders.add(
                            new Order("desc".equals(strs[1].toLowerCase()) ? Direction.DESC : Direction.ASC, strs[0]));
                } else {
                    orders.add(new Order(Direction.ASC, sortType));
                }
            }
        }
        return new PageRequest(pageNumber - 1, pagzSize, new Sort(orders));
    }

    /**
     * @Description: 创建动态查询条件组合
     * @param searchParams
     * @return
     * @return Specification<T>
     * @throws
     * @author feifei.liu
     * @date 2015年9月10日 下午1:02:27
     */
    @SuppressWarnings("unchecked")
    protected Specification<T> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Specification<T> spec = (Specification<T>) DynamicSpecifications.bySearchFilter(filters.values(),
                type.getActualTypeArguments()[0].getClass());
        // System.out.println("------------type:"+type);
        // System.out.println("------------entityClass:"+type.getActualTypeArguments()[0]);
        // System.out.println("------------type:"+type.getOwnerType());
        // System.out.println("------------type:"+type.getRawType());
        // System.out.println("------------type:"+type.getClass());
        return spec;
    }

}
