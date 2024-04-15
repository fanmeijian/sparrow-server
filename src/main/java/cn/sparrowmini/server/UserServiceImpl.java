package cn.sparrowmini.server;

import java.util.*;
import java.util.stream.Collectors;

import cn.sparrowmini.common.BaseEntity;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrowmini.pem.model.SparrowJpaFilter;
import cn.sparrowmini.pem.model.User;
import cn.sparrowmini.pem.service.impl.AbstractUserServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

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
                .map(m -> new User(m.getUsername(), m.getEmail(), "", m.getId(), m.getFirstName(), m.getLastName(),m.isEnabled()))
                .collect(Collectors.toList()), pageable, count);
    }

    @Override
    public Map<String, List<BaseEntity.ErrMsg>> create(Set<User> users) {
        users.forEach(u -> {
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue("keycloakPassword");
            credential.setTemporary(true);

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(u.getUsername());
            userRepresentation.setFirstName(u.getFirstName());
            userRepresentation.setLastName(u.getLastName());
            userRepresentation.setCredentials(List.of(credential));
            userRepresentation.setEnabled(true);

            Response result = null;
            try {
                result = keycloak.realm(keycloakSpringBootProperties.getRealm()).users().create(userRepresentation);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (result == null || result.getStatus() != 201) {
                assert result != null;
                System.err.println("Couldn't create Keycloak user." + result.getStatus());
            } else {
                System.out.println("Keycloak user created.... verify in keycloak!");
            }
        });


        return new HashMap<>();
    }

    @Override
    public void resetPassword(@PathVariable String username, @RequestBody String password){
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(true);

        UsersResource usersResource = keycloak.realm(keycloakSpringBootProperties.getRealm()).users();
        UserRepresentation userRepresentation = usersResource.search(username,true).get(0);
        UserResource userResource = usersResource.get(userRepresentation.getId());
        userResource.resetPassword(credential);

    }

    @Override
    public void enable(@PathVariable String username, @RequestParam Boolean enabled){
        UsersResource usersResource = keycloak.realm(keycloakSpringBootProperties.getRealm()).users();
        UserRepresentation userRepresentation = usersResource.search(username,true).get(0);
        UserResource userResource = usersResource.get(userRepresentation.getId());
        userRepresentation.setEnabled(enabled);
        userResource.update(userRepresentation);
    }

    @Override
    public List<BaseEntity.ErrMsg> update(@PathVariable String username, @RequestBody Map<String, Object> map) {
        UsersResource usersResource = keycloak.realm(keycloakSpringBootProperties.getRealm()).users();
        UserRepresentation userRepresentation = usersResource.search(username,true).get(0);
        UserResource userResource = usersResource.get(userRepresentation.getId());

        map.forEach((key, value) -> {
            switch (key){
                case "firstName":
                    userRepresentation.setFirstName(map.get(key).toString());
                    break;
                case "lastName":
                    userRepresentation.setLastName(map.get(key).toString());
                    break;
                case "email":
                    userRepresentation.setEmail(map.get(key).toString());
                    break;
                default:
                    break;
            }
        });
        userResource.update(userRepresentation);
        return new ArrayList<>();
    }

}
