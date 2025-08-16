package org.laredo.sistematienda.repository;

import org.laredo.sistematienda.model.Usuario;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsuarioRepository {
    private final Map<Integer, Usuario> usuarios = new HashMap<>();

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getDni(), usuario);
    }

    public Optional<Usuario> buscarUsuario(int dni) {
        return Optional.ofNullable(usuarios.get(dni));
    }

    public Collection<Usuario> listaUsuarios() {
        return usuarios.values();
    }
}
