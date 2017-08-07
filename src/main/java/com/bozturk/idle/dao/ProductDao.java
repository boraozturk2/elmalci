package com.bozturk.idle.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.Product;

@Transactional
@Repository("productDao")
public class ProductDao extends AbstractDao<Integer, Product>  {
  
    @SuppressWarnings("unchecked")
	public List<Product> findDistinctProductsByCategoryMake(Long categoryId, String makeLike) {
        
    	Criteria criteria = createEntityCriteria();
        
    	criteria.add(Restrictions.eq("categoryId", categoryId));
        criteria.add(Restrictions.like("make", makeLike, MatchMode.START));

        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("make"));
        criteria.setProjection(Projections.distinct(projList));
        
        return (List<Product>)criteria.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Product> findDistinctProductsByCategoryModel(Long categoryId, String make, String modelLike) {
        
    	Criteria criteria = createEntityCriteria();
        
    	criteria.add(Restrictions.eq("categoryId", categoryId));
    	criteria.add(Restrictions.eq("make", make));
        criteria.add(Restrictions.like("model", modelLike, MatchMode.START));

        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("model"));
        criteria.setProjection(Projections.distinct(projList));
        
        return (List<Product>)criteria.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Product> findDistinctProductsByCategorySerial(Long categoryId, String make, String model, String serialLike) {
        
    	Criteria criteria = createEntityCriteria();
        
    	criteria.add(Restrictions.eq("categoryId", categoryId));
    	criteria.add(Restrictions.eq("make", make));
    	criteria.add(Restrictions.eq("model", model));
        criteria.add(Restrictions.like("serial", serialLike, MatchMode.START));

        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.property("serial"));
        criteria.setProjection(Projections.distinct(projList));
        
        return (List<Product>)criteria.list();
    }
}