package org.arai.Controller;

import org.arai.Annotations.JwtClaim;
import org.arai.Model.JwtClaim.JwtAudit;
import org.arai.Persistence.Entities.Rol;
import org.arai.Persistence.Repositories.RolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {


    private RolRepository rolRepository;

    public RolesController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @GetMapping("/todos")
    public ResponseEntity<?> getAllRoles(@JwtClaim JwtAudit claims){
        List<Rol> roles = rolRepository.findAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }



}
