package com.zl.erp.repository;

import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: BaseRepository
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {


    /**
     * 多条件查询（即实现查询SQL动态化）
     *
     * @param t            参数对象
     * @param filterTerms  参数项列表
     * @param filterOrders //排序列表（可为空）
     * @return T
     */
    List<T> commonQueryByMultiParam(T t, List<FilterTerm> filterTerms, FilterOrder... filterOrders);

    /**
     * 多条件查询-可分页（即实现查询SQL动态化）
     *
     * @param t            参数对象
     * @param filterTerms  参数项列表
     * @param pageable     分页对象
     * @param filterOrders //排序列表（可为空）
     * @return T
     */
    Page<T> commonQueryByMultiParamWithPage(T t, List<FilterTerm> filterTerms, Pageable pageable, FilterOrder... filterOrders);

    /**
     * 可变字段多条件查询
     *
     * @param sql                            【如：select login_name as loginName from sys_user where login_name=:loginName】
     * @param t                              业务实体对象
     * @param resultClass【业务实体bean,Map,List】
     * @return T
     */
    List<T> commonQueryDynamicColumnByMultiParam(String sql, T t, Class resultClass);

    /**
     * 可变字段多条件查询-可分页
     *
     * @param sql                            【如：select login_name as loginName from sys_user where login_name=:loginName】
     * @param t                              业务实体对象
     * @param resultClass【业务实体bean,Map,List】
     * @return T
     */
    Page<T> commonQueryDynamicColumnByMultiParamWithPage(String sql, T t, Class resultClass, Pageable pageable);

    /**
     * 获取EntityManager对象
     *
     * @return T
     */
    EntityManager getEntityManager();
}
