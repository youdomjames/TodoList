package com.keycloakadmin.service;

import com.keycloakadmin.util.exception.ObjectCreationException;
import com.keycloakadmin.util.exception.ObjectDeletionException;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
@Slf4j
@Service
public record UserService(Keycloak keycloak,
                          @Value("${keycloak.realm}") String realm) {

    public void createUser(String email, String password){
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setEmail(email);
        user.setUsername(email);
        user.setEnabled(true);
        user.setCredentials(List.of(credentialRepresentation));
        user.setRequiredActions(List.of("VERIFY_EMAIL"));
        Response response = keycloak.realm(realm).users().create(user);
        if (response.getStatus() != 201) {
            throw new ObjectCreationException("Failed to create new user, Response STATUS = " + response.getStatus());
        }
        log.info("User created successfully");}

    public void deleteUser(String email){
        keycloak.realm(realm).users().searchByEmail(email, true).stream().findFirst().ifPresent(userRepresentation -> {
                    Response response = keycloak.realm(realm).users().delete(userRepresentation.getId());
                    if (response.getStatus() != 204) {
                        log.warn("Failed to delete user, Response STATUS = " + response.getStatus());
                        throw new ObjectDeletionException("Failed to delete user, Response STATUS = " + response.getStatus());
                    }
                });
        if (!keycloak.realm(realm).users().searchByEmail(email, true).isEmpty()){
            throw new ObjectDeletionException("Failed to delete user");
        }
    }
}
