package org.arai.Controller;

import org.arai.Entities.Usuario;
import org.arai.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        return new ResponseEntity<>(usuarioService.ObtenerTodosLosUsuarios(), HttpStatus.OK);
    }


}

