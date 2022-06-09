package com.dilido.twoauth.function;

import com.dilido.twoauth.database.DbConnection;
import com.dilido.twoauth.model.Usuario;
import com.dilido.twoauth.utils.Email;
import com.dilido.twoauth.utils.UtilsMessage;
import com.dilido.twoauth.utils.RandomString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDao {

    UtilsMessage message = new UtilsMessage();
    String sendMessage;

    public String getSendMessage() {
        return sendMessage;
    }
    public Usuario findByEmail(String email) {
        Usuario usuario = new Usuario();
        String sql = "select * from usuario where email = ?";
        Connection connection = DbConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                usuario.setIdUsuario(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setEmail(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setEstado(rs.getString(5));
                usuario.setTrabajador(rs.getString(6));
                usuario.setIdEntidad(rs.getString(7));
                usuario.setCreated_at(rs.getString(8));
                usuario.setUpdated_at(rs.getString(9));
            }else{
                message.setMessage("Error: El correo no existe");
                sendMessage = message.getMessage();
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            message.setMessage("Error: "+e);
            sendMessage = message.getMessage();
        }

        return usuario;
    }

    public boolean sendEmailSaveRandom(String email){
        boolean status = false;
        String sql = "update usuario set `2fact-auth` = ? where email = ?;";
        Connection connection = DbConnection.getConnection();
        String randomString = RandomString.getRandomString();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, randomString);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            connection.close();
            Email.sendEmail(email, randomString);
            status = true;
        } catch (SQLException e) {
            message.setMessage("Error: "+e);
            sendMessage = message.getMessage();
        }
        return status;
    }

    public boolean validUuidEmail(String email, String uuid){
        boolean status = false;
        String sql = "select * from usuario where email = ? and `2fact-auth` = ?;";
        Connection connection = DbConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                status = true;
            }else{
                message.setMessage("Error: Codigo incorrecto");
                sendMessage = message.getMessage();
            }

        } catch (SQLException e) {
            message.setMessage("Error: "+e);
            sendMessage = message.getMessage();
        }

        return status;
    }

    public String changePassword(String email, String password){
        Usuario usuario = findByEmail(email);
        usuario.setPassword(password);
        return usuario.toString();
    }

}
