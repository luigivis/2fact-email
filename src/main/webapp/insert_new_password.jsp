<%--
  Created by IntelliJ IDEA.
  User: luigi
  Date: 06/08/22
  Time: 6:13 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Material Design for Bootstrap</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css" />
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<!--Main Navigation-->
<header>

    <!-- Background image -->
    <div id="intro" class="bg-dark">
        <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0, 0, 0, 0.8);">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-5 col-md-8">
                        <form class="bg-white rounded shadow-5-strong p-5">
                            <input name="accion" value="validatePassword" hidden>

                            <div class="form-outline mb-4">
                                <input type="password" name="passwordI" required id="form1Example1" class="form-control" />
                                <label class="form-label" for="form1Example1">Validar Contraseña</label>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="text" name="passwordF" required id="form1Example2" class="form-control" />
                                <label class="form-label" for="form1Example2">Repetir contraseña</label>
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
                        </form>
                        <br>
                        ${message}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Background image -->
</header>
<!--Main Navigation-->
<!-- MDB -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>