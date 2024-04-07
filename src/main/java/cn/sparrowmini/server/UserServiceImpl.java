package cn.sparrowmini.server;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrowmini.pem.model.SparrowJpaFilter;
import cn.sparrowmini.pem.model.User;
import cn.sparrowmini.pem.service.impl.AbstractUserServiceImpl;

@Service
public class UserServiceImpl extends AbstractUserServiceImpl {
	@Autowired
	private Keycloak keycloak;

	@Autowired
	private KeycloakSpringBootProperties keycloakSpringBootProperties;

	@Override
	public void synchronize() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<User> getAllUsers(Pageable pageable, List<SparrowJpaFilter> jpaFilters) {
		List<UserRepresentation> usersList = keycloak.realm(keycloakSpringBootProperties.getRealm()).users()
				.list(pageable.getPageNumber(), pageable.getPageSize());
		long count = keycloak.realm(keycloakSpringBootProperties.getRealm()).users().count();
		return new PageImpl<>(usersList.stream()
				.map(m -> new User(m.getUsername(), m.getEmail(), "", m.getId(), m.getFirstName(), m.getLastName()))
				.collect(Collectors.toList()), pageable, count);
	}

}
