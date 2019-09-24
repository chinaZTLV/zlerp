package com.zl.erp.config;

import com.zl.erp.repository.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @Description: BaseRepositoryFactoryBeanConfiguration
 * @Author: zhutao
 * @Date: 2019/9/20
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseRepositoryFactoryBeanConfiguration<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {


    public BaseRepositoryFactoryBeanConfiguration(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }


    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }


    /**
     * 创建一个内部类，该类不用在外部访问
     */
    private static class BaseRepositoryFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        /**
         * 设置具体的实现类是BaseRepositoryImpl
         * @param information information
         * @return Object
         */
        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em);
        }

        /**
         * 设置具体的实现类的class
         * @param metadata metadata
         * @return Class
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }


}
