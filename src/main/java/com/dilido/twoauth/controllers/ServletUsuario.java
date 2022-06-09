package com.dilido.twoauth.controllers;

import com.dilido.twoauth.function.UsuariosDao;
import com.dilido.twoauth.model.Usuario;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.*;

import static java.util.stream.Collectors.joining;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@WebServlet(name = "ServletUsuario", value = "/Usuario")
public class ServletUsuario extends HttpServlet {

    Gson _gson = null;

    public static String readInputStream(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
    }

    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = _gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url_redirect = "index.jsp";
        Usuario usuario = new Usuario();
        UsuariosDao usuariosDao = new UsuariosDao();
        String accion = request.getParameter("accion");
        HttpSession httpSession = request.getSession();

        switch (accion) {
            case "checkEmail": {
                String email = request.getParameter("email");
                usuario = usuariosDao.findByEmail(email);
                System.out.println(usuariosDao.getSendMessage());
                request.setAttribute("message", usuariosDao.getSendMessage());
                if (usuario.getIdUsuario() != null) {
                    httpSession.setAttribute("emailExist", email);
                    usuariosDao.sendEmailSaveRandom(email);
                    url_redirect = "insert_code.jsp";
                }
                break;
            }

            case "validateCode": {
                String email = httpSession.getAttribute("emailExist").toString();
                String code = request.getParameter("uuid");
                if (usuariosDao.validUuidEmail(email, code)) {
                    url_redirect = "insert_new_password.jsp";
                } else {
                    request.setAttribute("message", usuariosDao.getSendMessage());
                    url_redirect = "insert_code.jsp";
                }
                break;
            }

            case "validatePassword": {
                String passwordI = request.getParameter("passwordI");
                String passwordF = request.getParameter("passwordF");
                String email = httpSession.getAttribute("emailExist").toString();

                if (passwordF.equals(passwordI)) {
                    Usuario usuario1 = usuariosDao.findByEmail(email);
                    System.out.println(usuario1);
                    usuario1.setPassword(passwordI);

                    System.out.println(usuario1);

                    TrustManager[] trustAllCerts =
                            new TrustManager[] {
                                    new X509TrustManager() {
                                        @Override
                                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                                                throws CertificateException {}

                                        @Override
                                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                                                throws CertificateException {}

                                        @Override
                                        public X509Certificate[] getAcceptedIssuers() {
                                            return new X509Certificate[0];
                                        }
                                    }
                            };

                    try {
                        SSLContext sc = SSLContext.getInstance("SSL");
                        sc.init(null, trustAllCerts, new java.security.SecureRandom());
                        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                        URL url = new URL("https://dlido.herokuapp.com/usuario/update/"+usuario1.getIdUsuario());
                        java.net.URLConnection connection = url.openConnection();
                        HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
                        httpConnection.setDoOutput(true);
                        httpConnection.setRequestMethod("PUT");
                        httpConnection.setRequestProperty("Content-Type", "application/json");
                        httpConnection.setRequestProperty("Accept", "application/json");

                        DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
                        wr.write(usuario1.toString().getBytes());
                        Integer responseCode = httpConnection.getResponseCode();

                        BufferedReader bufferedReader;

                        // Creates a reader buffer
                        if (responseCode > 199 && responseCode < 300) {
                            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                        } else {
                            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
                        }

                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        bufferedReader.close();


                        url_redirect = "redirect.html";

                    } catch (NoSuchAlgorithmException | KeyManagementException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    request.setAttribute("message", usuariosDao.getSendMessage());
                    url_redirect = "insert_new_password.jsp";
                }
            }
            break;
        }

        request.getRequestDispatcher(url_redirect).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
