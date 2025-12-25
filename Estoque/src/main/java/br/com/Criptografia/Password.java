package br.com.Criptografia;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    public String CriptografarSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean verificarSenha(String senha, String hash){
        return BCrypt.checkpw(senha, hash);
    }
}
