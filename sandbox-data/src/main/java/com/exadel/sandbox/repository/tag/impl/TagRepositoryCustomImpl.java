package com.exadel.sandbox.repository.tag.impl;

import com.exadel.sandbox.model.vendorinfo.Tag;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.tag.TagRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Tag> findAllByCategoryFilter(List<Long> ids) {

        String sqlWhere=getWhereCondition(ids);

        return entityManager.createNativeQuery(
                "SELECT DISTINCT t.* FROM category c "+
                        "INNER JOIN tag t on c.id=t.category_id " +
                        sqlWhere + " ORDER BY t.name ASC ",  Tag.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean drop(Long id) {
        return entityManager
                .createNativeQuery("DELETE FROM tag WHERE id = ? AND " +
                        "NOT exists(SELECT * FROM event_tag WHERE tag_id = ?)")
                .setParameter(1, id)
                .setParameter(2, id)
                .executeUpdate() > 0;
    }

    private String getWhereCondition(List<Long>ids){

        if(ids.isEmpty() || ids.size() ==0 ){
            return "";
        }

        String result="WHERE c.id IN (";
        int numberOfElements=0;

        for(Long id : ids){
            result += (numberOfElements != 0) ?  ", " + id : id;
            numberOfElements++;
        }

        result += ")";

        return result;
    }
}
