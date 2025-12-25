package br.com.Controller;

import br.com.Criptografia.Password;
import br.com.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private Password password = new Password(); // 👈 inicializado
    private List<User> listaUsuario = new ArrayList<>();
    private long id = 1;

    private long gerarId() {
        return id++;
    }

    public void novoUsuario(String nome, String email, String senha) {
        String hash = password.CriptografarSenha(senha);

        User usuario = new User(gerarId(), nome, email, hash);
        listaUsuario.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public User buscarPorEmail(String email){
        return listaUsuario.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean autenticar(String email, String senhaDigitada){
        User user = buscarPorEmail(email);
        if(user == null) return false;

        return password.verificarSenha(senhaDigitada, user.getSenha());
    }
}
