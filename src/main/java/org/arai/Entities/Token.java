package org.arai.Entities;

import java.sql.Date;

import org.arai.Enums.TokenType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_tokens")
@Getter
@Setter
@ToString
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_token")
    private TokenType tipoToken; // JWT, GOOGLE, FACEBOOK, etc.

    @Column(name = "expiracion")
    private Date expiracion; // Opcional, si necesitas fecha de expiración

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private Usuario usuario;

    @Column(name = "activo")
    private boolean activo = true; // Para invalidar tokens cuando sea necesario
}