<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <form method="get" class="bg-white rounded shadow-5-strong p-5" action="<%=request.getContextPath()%>/Usuario">
                            <input name="accion" value="checkEmail" hidden>
                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email" name="email" required id="form1Example1" class="form-control" />
                                <label class="form-label" for="form1Example1">Correo Electronico</label>
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