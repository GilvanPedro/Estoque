package br.com.Controller;

import br.com.Criptografia.Password;
import br.com.Entity.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private List<Usuario> listaUsuario = new ArrayList<>();
    private final String path = "usuarios.txt";
    private Password password = new Password();

    public UsuarioController() {
        carregarUsuarios();
    }

    // ================== CADASTRAR ==================
    public void novoUsuario(String nome, String email, String senha) {

        if (emailJaExiste(email)) {
            System.out.println("Este email já está cadastrado!");
            return;
        }

        long id = gerarId();
        String hash = password.CriptografarSenha(senha);

        Usuario usuario = new Usuario(id, nome, email, hash);
        listaUsuario.add(usuario);

        escreverUsuario(id, nome, email, hash);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // ================== LOGIN ==================
    public boolean autenticar(String email, String senhaDigitada) {
        carregarUsuarios();
        Usuario user = buscarPorEmail(email);

        return user != null && password.verificarSenha(senhaDigitada, user.getSenha());
    }

    // ================== BUSCAR ==================
    public Usuario buscarPorEmail(String email) {
        return listaUsuario.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // ================== UTIL ==================
    private long gerarId() {
        long maior = 0;
        for (Usuario u : listaUsuario) {
            if (u.getId() > maior) maior = u.getId();
        }
        return maior + 1;
    }

    private boolean emailJaExiste(String email) {
        carregarUsuarios();
        return listaUsuario.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    // ================== ARQUIVO ==================
    private void escreverUsuario(long id, String nome, String email, String hash) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(id + ";" + nome + ";" + email + ";" + hash + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário!");
        }
    }

    private void carregarUsuarios() {
        listaUsuario.clear();
        File file = new File(path);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue;

                String[] d = linha.split(";");
                if (d.length < 4) continue;

                long id = Long.parseLong(d[0]);
                String nome = d[1];
                String email = d[2];
                String hash = d[3];

                listaUsuario.add(new Usuario(id, nome, email, hash));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários!");
        }
    }
}
