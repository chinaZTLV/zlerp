package com.zl.erp.repository.impl;

import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.common.db.SqlUtils;
import com.zl.erp.repository.BaseRepository;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: BaseRepositoryImpl
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@SuppressWarnings({"unchecked"})
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }


    @Override
    public List<T> commonQueryByMultiParam(T t, List<FilterTerm> filterTerms, FilterOrder... filterOrders) {
        CriteriaQuery<T> query = commonParamsConstructor(t, filterTerms, filterOrders);
        return this.entityManager.createQuery(query).getResultList();

    }

    @Override
    public Page<T> commonQueryByMultiParamWithPage(T t, List<FilterTerm> filterTerms, Pageable pageable, FilterOrder... filterOrders) {
        CriteriaQuery<T> query = commonParamsConstructor(t, filterTerms, filterOrders);
        // 查询
        TypedQuery<T> createQuery = this.entityManager.createQuery(query);
        // 查询条数
        TypedQuery<T> createCountQuery = this.entityManager.createQuery(query);
        // 分页参数
        int pageSize = pageable.getPageSize();
        Integer pageNo = pageable.getPageNumber();
        int startIndex = pageSize * (pageNo - 1);
        createQuery.setFirstResult(startIndex);
        createQuery.setMaxResults(pageSize);
        return new NewPageImpl<>(createQuery.getResultList(), pageable, createCountQuery.getResultList().size());
    }

    @SuppressWarnings({"unchecked"})
    private CriteriaQuery<T> commonParamsConstructor(T t, List<FilterTerm> filterTerms, FilterOrder... filterOrders) {
        Class<T> tClass = (Class<T>) t.getClass();
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);
        // 封装条件列表
        if (null != filterTerms) {
            SqlUtils.appendParams(criteriaBuilder, criteriaQuery, root, t, filterTerms);
        }
        // 排序列表
        if (null != filterOrders) {
            SqlUtils.appendSort(criteriaBuilder, criteriaQuery, root, filterOrders);
        }
        return criteriaQuery;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<T> commonQueryDynamicColumnByMultiParam(String sql, T t, Class resultClass) {
        NativeQuery query = commonNativeQuery(sql, t, resultClass);
        List<String> paramColumns = SqlUtils.getParamColumnList(sql, t);
        SqlUtils.buildParamMapping(query, paramColumns, t);
        return query.list();
    }

    private NativeQuery commonNativeQuery(String sql, T t, Class resultClass) {
        NativeQuery query = this.entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql);
        List<String> selectColumns = SqlUtils.getSelectColumnList(sql);
        SqlUtils.buildResultMapping(query, selectColumns, t);
        if (resultClass == Map.class) {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        } else if (resultClass == List.class) {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.TO_LIST);
        } else {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        }
        return query;
    }


    @Override
    public Page<T> commonQueryDynamicColumnByMultiParamWithPage(String sql, T t, Class resultClass, Pageable pageable) {
        NativeQuery query = commonNativeQuery(sql, t, resultClass);
        List<String> paramColumns = SqlUtils.getParamColumnList(sql, t);
        SqlUtils.buildParamMapping(query, paramColumns, t);
        int total = query.list().size();
        // 分页参数
        int pageSize = pageable.getPageSize();
        Integer pageNo = pageable.getPageNumber();
        int startIndex = pageSize * (pageNo - 1);
        query.setMaxResults(pageSize);
        query.setFirstResult(startIndex);
        return new NewPageImpl<T>(query.getResultList(), pageable, total);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}

class NewPageImpl<T> extends PageImpl<T> {

    private long totalElements;

    private int totalPages;


    public NewPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.totalElements = total;
        this.totalPages = total % pageable.getPageSize() == 0 ? (int) (total / pageable.getPageSize()) : (int) (total / pageable.getPageSize()) + 1;

    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

}
