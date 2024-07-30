package br.com.cupacke.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractDao implements Serializable {

	private static final long serialVersionUID = -8719908598616583584L;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * EntityManager.
	 *
	 * @return
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	

}
