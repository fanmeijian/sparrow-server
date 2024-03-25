package cn.sparrowmini.server;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Configuration;

import cn.sparrowmini.common.EntityManagerHelper;

@Configuration
public class EntityManagerBean {

	public EntityManagerBean(EntityManager emf) {
		EntityManagerHelper.entityManagerFactory = emf.getEntityManagerFactory();
	}

}
