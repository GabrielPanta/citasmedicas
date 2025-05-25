package com.hospital.citasmedicas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "usuario"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "usuario")
    private String usuario;

    private String imagePerfil;

    private String sexo;

    private LocalDateTime fechaRegistro;
    
    private String sedeInstitucion;

    private String password;
    
    
    @Column(name = "activo")
    private Boolean activo = false;
    
    @Column(name = "tokenActivacion")
    private String tokenActivacion;
 
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Medico medico;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CitaMedica> citasAgendadas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private Set<Rol> roles = new HashSet<>();
    
    public Usuario(String nombres, String apellidos, String usuario, String imagePerfil, String sexo, LocalDateTime fechaRegistro, String sedeInstitucion, String password, Set<Rol> roles) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.imagePerfil = imagePerfil;
        this.sexo = sexo;
        this.fechaRegistro = fechaRegistro;
        this.sedeInstitucion = sedeInstitucion;
        this.password = password;
        this.roles = (Set<Rol>) roles;
    }
}
