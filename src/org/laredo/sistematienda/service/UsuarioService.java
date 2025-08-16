package org.laredo.sistematienda.service;

import org.laredo.sistematienda.exeptions.UsuarioException;
import org.laredo.sistematienda.model.Usuario;
import org.laredo.sistematienda.repository.UsuarioRepository;

import java.util.Collection;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void crearUsuario(Usuario usuario) throws UsuarioException {
        usuarioRepository.registrarUsuario(usuario);
    }

    public Usuario buscarUsuario(int dni) throws UsuarioException {
        return usuarioRepository.buscarUsuario(dni)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
    }

    public Collection<Usuario> listarUsuarios() throws UsuarioException {
        return usuarioRepository.listaUsuarios();
    }
}