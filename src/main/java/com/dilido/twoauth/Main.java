package com.dilido.twoauth;

import com.dilido.twoauth.function.UsuariosDao;
import com.dilido.twoauth.model.Usuario;
import com.dilido.twoauth.utils.Email;
import com.dilido.twoauth.utils.RandomString;

public class Main {
    public static void main(String[] args) {
        //Email.sendEmail("luigivis98@gmail.com", RandomString.getRandomString());
        UsuariosDao dao = new UsuariosDao();
        Usuario usuario = dao.findByEmail("luigivis98@gmail.com");
        System.out.println(usuario);
    }
}
