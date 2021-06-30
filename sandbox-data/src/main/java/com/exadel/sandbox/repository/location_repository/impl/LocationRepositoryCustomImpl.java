package com.exadel.sandbox.repository.location_repository.impl;

import com.exadel.sandbox.model.LocationFilter;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.location_repository.LocationRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LocationRepositoryCustomImpl implements LocationRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<LocationFilter> findAllByCategoryFilterIds(List<Long> ids) {
        String sqlWhere=getWhereCondition(ids, "category_id");

        List<Object[]>listObjects=entityManager.createNativeQuery(
                "SELECT DISTINCT cn.id as countryId, " +
                        "cn.name as countryName, " +
                        "ct.id as cityId, " +
                        "ct.name as cityName FROM category c "+
                        "INNER JOIN event e on c.id=e.category_id " +
                        "INNER JOIN event_location el on e.id= el.event_id " +
                        "INNER JOIN location l on el.location_id=l.id " +
                        "INNER JOIN city ct on l.city_id=c.id " +
                        "INNER JOIN country cn on ct.country_id=cn.id " +
                         sqlWhere + " ORDER BY cn.name ASC, ct.name ASC ")
                .getResultList();

        return listObjects.stream()
                .map(this::transformObjectToLocationFilter)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationFilter> findAllByVendorFilterIds(List<Long> ids) {
        String sqlWhere=getWhereCondition(ids, "vendor_id");

        List<Object[]>listObjects = entityManager.createNativeQuery(
                "SELECT DISTINCT cn.id as countryId, " +
                        "cn.name as countryName, " +
                        "ct.id as cityId, " +
                        "ct.name as cityName FROM vendor v " +
                        "INNER JOIN event e on v.id=e.vendor_id " +
                        "INNER JOIN event_location el on e.id= el.event_id " +
                        "INNER JOIN location l on el.location_id=l.id " +
                        "INNER JOIN city ct on l.city_id=ct.id " +
                        "INNER JOIN country cn on ct.country_id=cn.id " +
                        sqlWhere + " ORDER BY cn.name ASC, ct.name ASC ")
                .getResultList();

        return listObjects.stream()
                .map(this::transformObjectToLocationFilter)
                .collect(Collectors.toList());

    }

    private LocationFilter transformObjectToLocationFilter(Object[] obj){
        LocationFilter locationFilter= new LocationFilter(convertToLong(obj[0]),String.valueOf(obj[1]),convertToLong(obj[2]),String.valueOf(obj[3]));
        return new LocationFilter(convertToLong(obj[0]),String.valueOf(obj[1]),convertToLong(obj[2]),String.valueOf(obj[3]));
    }

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }


    private String getWhereCondition(List<Long>ids, String fieldForWhere){

        if(ids.isEmpty() || ids.size() ==0 ){
            return "";
        }

        String result="WHERE e." + fieldForWhere + " IN (";
        int numberOfElements=0;

        for(Long id : ids){
            result += (numberOfElements != 0) ?  ", " + id : id;
            numberOfElements++;
        }

        result += ")";

        return result;
    }
}
