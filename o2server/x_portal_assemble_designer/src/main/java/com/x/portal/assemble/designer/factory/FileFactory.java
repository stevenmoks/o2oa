package com.x.portal.assemble.designer.factory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.x.portal.assemble.designer.AbstractFactory;
import com.x.portal.assemble.designer.Business;
import com.x.portal.core.entity.File;
import com.x.portal.core.entity.File_;

public class FileFactory extends AbstractFactory {

	public FileFactory(Business business) throws Exception {
		super(business);
	}

	public List<String> listWithPortal(String portalId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(File.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<File> root = cq.from(File.class);
		Predicate p = cb.equal(root.get(File_.portal), portalId);
		cq.select(root.get(File_.id)).where(p);
		return em.createQuery(cq).getResultList();
	}

	public List<File> listWithPortalObject(String portalId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(File.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<File> cq = cb.createQuery(File.class);
		Root<File> root = cq.from(File.class);
		Predicate p = cb.equal(root.get(File_.portal), portalId);
		cq.select(root).where(p);
		return em.createQuery(cq).getResultList();
	}

	public <T extends File> List<T> sort(List<T> list) {
		list = list.stream().sorted(Comparator.comparing(File::getName, Comparator.nullsLast(String::compareTo)))
				.collect(Collectors.toList());
		return list;
	}

}