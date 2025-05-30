package com.gestion.web.controller;

import com.gestion.web.dto.UsuarioRegistroDTO;
import com.gestion.web.model.Usuario;

import com.gestion.web.repository.UsuarioRepository;
import com.gestion.web.services.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioServicio usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getName().equals("anonymousUser")) {

            String username = authentication.getName();


            Usuario usuario = usuarioRepository.findByEmail(username);

            if (usuario == null) {
                usuario = usuarioRepository.findByDni(username);
            }

            if (usuario != null) {

                model.addAttribute("nombres", usuario.getNombres());
                model.addAttribute("apellidos", usuario.getApellidos());
                model.addAttribute("dni", usuario.getDni());
                model.addAttribute("email", usuario.getEmail());
                model.addAttribute("sexo", usuario.getSexo());
                model.addAttribute("usuarioAutenticado", true);


                model.addAttribute("roles", usuario.getRoles());

              /*
                if (usuario.getPaciente() != null) {
                    model.addAttribute("esPaciente", true);
                    model.addAttribute("paciente", usuario.getPaciente());
                }

                if (usuario.getMedico() != null) {
                    model.addAttribute("esMedico", true);
                    model.addAttribute("medico", usuario.getMedico());
                }

                // Si tienes citas médicas
                if (usuario.getCitasAgendadas() != null && !usuario.getCitasAgendadas().isEmpty()) {
                    model.addAttribute("tieneCitas", true);
                    model.addAttribute("numeroCitas", usuario.getCitasAgendadas().size());
                }*/
            }
        } else {
            model.addAttribute("usuarioAutenticado", false);
        }

        return "Public/Home";
    }

    @GetMapping("/Public/Login")
    public String mostrarLogin() {
        return "Public/Login";
    }




    @GetMapping("/login")
    public String mostrarFormLogin() {
        return "Public/Login";
    }


    @GetMapping("/register")
    public String mostrarFormRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioRegistroDTO()); // Agregar objeto usuario al modelo
        return "Public/register";
    }


    @PostMapping("/procesarRegistro") // Cambiar de /registroexitoso a /procesarRegistro
    public String RegistrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioRegistroDTO, Model model, RedirectAttributes redirectAttributes) {


        if (usuarioService.existeEmail(usuarioRegistroDTO.getEmail())) {
            model.addAttribute("errorMessage", "Este correo ya está registrado.");
            model.addAttribute("usuario", usuarioRegistroDTO); // Mantener datos del formulario
            return "Public/register";
        }


        if (usuarioService.existeDni(usuarioRegistroDTO.getDni())) {
            model.addAttribute("errorMessage", "Este DNI ya está registrado.");
            model.addAttribute("usuario", usuarioRegistroDTO);
            return "Public/register";
        }


        if (usuarioRegistroDTO.getDni().length() != 8) {
            model.addAttribute("errorMessage", "El DNI debe tener exactamente 8 dígitos.");
            model.addAttribute("usuario", usuarioRegistroDTO);
            return "Public/register";
        }


        if (!usuarioRegistroDTO.getDni().matches("\\d{8}")) {
            model.addAttribute("errorMessage", "El DNI debe contener solo números.");
            model.addAttribute("usuario", usuarioRegistroDTO);
            return "Public/register";
        }

        try {
            usuarioService.GuardarUsuario(usuarioRegistroDTO);
            redirectAttributes.addFlashAttribute("usuario", usuarioRegistroDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Registro exitoso. Revisa tu correo para activar tu cuenta.");

            return "redirect:/registroexitoso";
        } catch (Exception e) {
            // Agregar logging para ver el error específico
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();

            model.addAttribute("errorMessage", "Error al registrar usuario. Inténtalo de nuevo.");
            model.addAttribute("usuario", usuarioRegistroDTO);
            return "Public/register";
        }
    }


    @GetMapping("/registroexitoso")
    public String mostrarRegistroExitoso() {
        return "Public/registroexitoso";
    }

    // Confirmar cuenta con token
    @GetMapping("/confirmar-cuenta")
    public String confirmarCuenta(@RequestParam("token") String token, Model model) {
        System.out.println("Token recibido: " + token);
        boolean isConfirmed = usuarioService.confirmarCuenta(token);

        if (isConfirmed) {
            model.addAttribute("successMessage", "Cuenta activada exitosamente. Ahora puedes iniciar sesión.");
            return "Public/Login";
        } else {
            model.addAttribute("errorMessage", "Token de activación inválido o expirado.");
            return "Public/error";
        }
    }

    // Mostrar perfil de usuario (versión simplificada)
    @GetMapping("/perfil")
    public String MostrarPerfilUsuario(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Como Spring Security usa email como username, buscamos por email
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());

        if (usuario == null) {
            return "redirect:/login";
        }

        UsuarioRegistroDTO perfilDTO = new UsuarioRegistroDTO(usuario);

        model.addAttribute("perfil", perfilDTO);
        model.addAttribute("usuario", usuario);

        return "perfil"; // Crear esta vista después
    }

    // Dashboard principal después del login
    @GetMapping("/dashboard")
    public String mostrarDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("nombreCompleto", usuario.getNombres() + " " + usuario.getApellidos());

        return "dashboard"; // Crear esta vista después
    }



    // Página de error
    @GetMapping("/error")
    public String mostrarError() {
        return "Public/error";
    }
}