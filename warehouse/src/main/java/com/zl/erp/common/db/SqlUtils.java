package com.zl.erp.common.db;

import com.zl.erp.utils.DateHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: sql处理类
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class SqlUtils {

    /**
     * 获取对象的Number形式
     *
     * @param object object
     * @return Numbe
     */
    public static Number getNumber(Object object) {
        Number number;
        if (object instanceof String) {
            if (String.valueOf(object).contains("\\.")) {
                number = Float.parseFloat(String.valueOf(object));
            } else {
                number = Integer.parseInt(String.valueOf(object));
            }
        } else {
            number = (Number) object;
        }
        return number;
    }

    /**
     * 附加排序列表
     *
     * @param criteriaBuilder criteriaBuilder
     * @param criteriaQuery   criteriaQuery
     * @param root            root
     * @param filterOrders    filterOrders
     */
    public static void appendSort(CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root,
                                  FilterOrder... filterOrders) {
        if (null != filterOrders) {
            List<Order> orders = new ArrayList<>();
            for (FilterOrder fo : filterOrders) {
                Order order = null;
                switch (fo.getFilterKeyword()) {
                    case ASC: {
                        order = criteriaBuilder.asc(root.get(fo.getName()));
                        break;
                    }
                    case DESC: {
                        order = criteriaBuilder.desc(root.get(fo.getName()));
                        break;
                    }
                    default:
                        break;

                }
                orders.add(order);
            }
            criteriaQuery.orderBy(orders);
        }
    }

    /**
     * 附加参数列表
     *
     * @param criteriaBuilder criteriaBuilder
     * @param criteriaQuery   criteriaQuery
     * @param root            root
     * @param t               t
     * @param filterTerms     filterTerms
     */
    public static void appendParams(CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root, Object t,
                                    List<FilterTerm> filterTerms) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate predicate = null;
        Predicate predicateOr = null;
        int specialOrCount = 0;
        for (FilterTerm ft : filterTerms) {
            String name = ft.getName();
            try {
                String object = BeanUtils.getProperty(t, name);
                if (FilterRelation.AND == ft.getFilterRelation()) {
                    switch (ft.getFilterKeyword()) {
                        case EQ: {
                            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get(name), object));
                            break;
                        }
                        case LT: {
                            Number number = SqlUtils.getNumber(object);
                            predicate = criteriaBuilder.and(criteriaBuilder.lt(root.get(name), number));
                            break;
                        }
                        case LTE: {
                            Number number = SqlUtils.getNumber(object);
                            predicate = criteriaBuilder.and(criteriaBuilder.le(root.get(name), number));
                            break;
                        }
                        case GT: {
                            Number number = SqlUtils.getNumber(object);
                            predicate = criteriaBuilder.and(criteriaBuilder.gt(root.get(name), number));
                            break;
                        }
                        case GTE: {
                            Number number = SqlUtils.getNumber(object);
                            predicate = criteriaBuilder.and(criteriaBuilder.ge(root.get(name), number));
                            break;
                        }
                        case ISN: {
                            predicate = criteriaBuilder.and(criteriaBuilder.isNull(root.get(name)));
                            break;
                        }
                        case ISNN: {
                            predicate = criteriaBuilder.and(criteriaBuilder.isNotNull(root.get(name)));
                            break;
                        }
                        case LK: {
                            String s = String.valueOf(object);
                            String s1 = s.replace("%", "\\%");
                            predicate = criteriaBuilder.and(criteriaBuilder.like(root.get(name), "%" + s1 + "%"));
                            break;
                        }
                        case NLK: {
                            String s = String.valueOf(object);
                            String s1 = s.replace("%", "\\%");
                            predicate = criteriaBuilder.and(criteriaBuilder.notLike(root.get(name), "%" + s1 + "%"));
                            break;
                        }
                        case BETWEEN: {
                            String[] dateRange = String.valueOf(object).split("#");
                            predicate = criteriaBuilder
                                    .and(criteriaBuilder.between(root.get(name), dateRange[0], dateRange[1]));
                            break;
                        }
                        case DATEBETWEEN: {
                            String[] dateRange = String.valueOf(object).split("#");
                            String tName = name.split("And")[1];
                            Date d1 = DateHelper.weeHours(DateHelper.parseDate(dateRange[0]), 0);
                            Date d2 = DateHelper.weeHours(DateHelper.parseDate(dateRange[1]), 1);
                            predicate = criteriaBuilder.and(criteriaBuilder.between(root.get(tName), d1, d2));
                            break;
                        }
                        case NEQ: {
                            predicate = criteriaBuilder
                                    .and(criteriaBuilder.notEqual(root.get(name), String.valueOf(object)));
                            break;
                        }
                        case IN: {
                            CriteriaBuilder.In in = criteriaBuilder.in(root.get(name));
                            if (object != null) {
                                String splitStr = object;
                                String[] splitArr = splitStr.split(",");
                                for (String str : splitArr) {
                                    in.value(str);
                                }
                            }
                            predicate = in;
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    predicates.add(predicate);
                } else if (FilterRelation.SPECIALOR == ft.getFilterRelation()) {
                    if (ft.getFilterKeyword() == FilterKeyword.IN) {
                        CriteriaBuilder.In in = criteriaBuilder.in(root.get(name));
                        if (object != null) {
                            String[] splitArr = object.split(",");
                            for (String str : splitArr) {
                                in.value(str);
                            }
                        }
                        if (specialOrCount == 0) {
                            predicateOr = criteriaBuilder.or(in, predicate);
                            predicates.remove(0);
                            specialOrCount++;
                        } else if (specialOrCount == 1) {
                            predicateOr = criteriaBuilder.or(predicateOr, in);
                            predicates.remove(0);
                        }
                    }
                    predicates.add(predicateOr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
    }

    /**
     * 构建返回字段映射关系
     *
     * @param query query
     * @param columns columns
     * @param t t
     */
    public static void buildResultMapping(NativeQuery query, List<String> columns, Object t) {
        for (String column : columns) {
            Field field = null;
            try {
                field = t.getClass().getDeclaredField(column);
            } catch (Exception e) {
                try {
                    field = t.getClass().getField(column);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
            if (null != field) {
                Class type = field.getType();
                if (type == String.class) {
                    query.addScalar(column, StandardBasicTypes.STRING);
                } else if (type == Integer.class) {
                    query.addScalar(column, StandardBasicTypes.INTEGER);
                } else if (type == Long.class) {
                    query.addScalar(column, StandardBasicTypes.LONG);
                } else if (type == Float.class) {
                    query.addScalar(column, StandardBasicTypes.FLOAT);
                } else if (type == Double.class) {
                    query.addScalar(column, StandardBasicTypes.DOUBLE);
                } else if (type == BigDecimal.class) {
                    query.addScalar(column, StandardBasicTypes.BIG_DECIMAL);
                } else if (type == Date.class) {
                    query.addScalar(column, StandardBasicTypes.DATE);
                }
            }
        }
    }

    /**
     * 构建查询参数
     *
     * @param query query
     * @param columns columns
     * @param t t
     */
    public static void buildParamMapping(NativeQuery query, List<String> columns, Object t) {

        for (String column : columns) {
            try {
                Object object = BeanUtils.getProperty(t, column);
                query.setParameter(column, object);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }


    /**
     * 构建返回字段列表
     *
     * @param sql sql
     * @return info
     */
    public static List<String> getSelectColumnList(String sql) {

        List<String> columnList = new ArrayList<>();
        StringBuilder newSql = new StringBuilder(sql.trim());
        int fromIndex = newSql.indexOf("from");
        String[] columns = newSql.substring(6, fromIndex).trim().split(",");
        for (String column : columns) {
            String col = column.trim();
            if (col.contains("as") || col.contains("AS")) {
                col = col.replace("as", "AS");
                col = col.split("AS")[1].trim();
            }
            columnList.add(col);
        }

        return columnList;
    }

    /**
     * 获取参数列表
     *
     * @param sql sql
     * @param t t
     * @return info
     */
    public static List<String> getParamColumnList(String sql, Object t) {

        List<String> columnList = new ArrayList<>();

        Field[] declaredFields = t.getClass().getDeclaredFields();
        Field[] fields = t.getClass().getFields();
        if (null != declaredFields) {
            for (Field field : declaredFields) {
                String name = field.getName();
                if (sql.contains(":" + name)) {
                    columnList.add(name);
                }
            }
        }
        if (null != fields) {
            for (Field field : fields) {
                String name = field.getName();
                if (sql.contains(":" + name)) {
                    columnList.add(name);
                }
            }
        }
        return columnList;
    }

}
