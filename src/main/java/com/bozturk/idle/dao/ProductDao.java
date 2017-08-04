package com.bozturk.idle.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.Product;

@Repository("productDao")
public class ProductDao extends AbstractDao<Integer, Product>  {
  
    @SuppressWarnings("unchecked")
	public List<Product> findDistinctProductsByCategory(Long categoryId, String makeLike) {
        
    	Criteria criteria = createEntityCriteria();
        
    	criteria.add(Restrictions.eq("cateoryId", categoryId));
        criteria.add(Restrictions.like("make", makeLike, MatchMode.START));

        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("make"));
        criteria.setProjection(Projections.distinct(projList));
        
        return (List<Product>)criteria.list();
    }
}