package com.exadel.sandbox.repository.category.impl;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.category.CategoryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Category> findAllByVendorId(Long vendorId) {
        return entityManager.createNativeQuery(
                "SELECT DISTINCT category.*\n" +
                        "FROM category\n" +
                        "         JOIN event ON category.id = event.category_id\n" +
                        "WHERE vendor_id = ?\n" +
                        "ORDER BY name",
                Category.class)
                .setParameter(1, vendorId)
                .getResultList();
    }


    @Override
    public List<Category> findAllByLocationFilterId(Long id, boolean isCountry) {

        String sqlWhere="";
        sqlWhere =(isCountry) ?  "WHERE cn.id=?" : "WHERE ct.id=?";

        return entityManager.createNativeQuery(
                "SELECT DISTINCT c.* FROM category c "+
                    "INNER JOIN event e on c.id=e.category_id " +
                    "INNER JOIN event_location el on e.id= el.event_id " +
                    "INNER JOIN location l on el.location_id=l.id " +
                    "INNER JOIN city ct on l.city_id=c.id " +
                    "INNER JOIN country cn on ct.country_id=cn.id " +
                 sqlWhere + " ORDER BY c.name ASC ",
                Category.class)
                .setParameter(1, id )
                .getResultList();

    }

    @Override
    public List<Category> findAllByVendorFilterIds(List<Long> ids) {

        String sqlWhere=getWhereCondition(ids);

        return entityManager.createNativeQuery(
                "SELECT DISTINCT c.* FROM category c "+
                        "INNER JOIN event e on c.id=e.category_id " +
                        sqlWhere + " ORDER BY c.name ASC ",
                Category.class)
                .getResultList();
    }

    private String getWhereCondition(List<Long>ids){

        if(ids.isEmpty() || ids.size() ==0 ){
            return "";
        }

        String result="WHERE e.vendor_id IN (";
        int numberOfElements=0;

        for(Long id : ids){
            result += (numberOfElements != 0) ?  ", " + id : id;
            numberOfElements++;
        }

        result += ")";

        return result;
    }
}
