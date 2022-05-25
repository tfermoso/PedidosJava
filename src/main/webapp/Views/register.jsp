<%-- 
    Document   : login
    Created on : 24 may 2022, 15:16:00
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/login.css"/>
        <script src="static/js/jquery-3.6.0.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/login.js"></script>

    </head>
    <body>
        <!-- Section: Design Block -->
        <section class=" text-center text-lg-start">
            <style>
                .rounded-t-5 {
                    border-top-left-radius: 0.5rem;
                    border-top-right-radius: 0.5rem;
                }

                @media (min-width: 992px) {
                    .rounded-tr-lg-0 {
                        border-top-right-radius: 0;
                    }

                    .rounded-bl-lg-5 {
                        border-bottom-left-radius: 0.5rem;
                    }
                }
            </style>
            <div class="card mb-3" id="register">
                <div class="row g-0 d-flex align-items-center">
                    <div class="col-lg-4 d-none d-lg-flex">
                        <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"
                             class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
                    </div>
                    <div class="col-lg-8">
                        <div class="card-body">

                            <form method="post" action="">
                                <h3>Nuevo Cliente</h3>
                                 <div class="form-outline ">
                                    <input type="text" id="nombre" name="nombre" class="form-control" required/>
                                    <label class="form-label" for="nombre">Nombre</label>
                                </div>
                                 <div class="form-outline ">
                                    <input type="text" id="cif" name="cif" class="form-control"required />
                                    <label class="form-label" for="cif">DNI</label>
                                </div>
                                 <div class="form-outline ">
                                    <input type="text" id="telefono" name="telefono" class="form-control" required/>
                                    <label class="form-label" for="telefono">Telefono</label>
                                </div>
                                 <div class="form-outline ">
                                    <input type="text" id="email" name="email" class="form-control" required/>
                                    <label class="form-label" for="email">Email</label>
                                </div>
                                <!-- Email input -->
                                <div class="form-outline ">
                                    <input type="text" id="username" name="username" class="form-control" required/>
                                    <label class="form-label" for="username">Username</label>
                                </div>
                                <!-- Password input -->
                                <div class="form-outline ">
                                    <input type="password" id="password" name="password" class="form-control" required/>
                                    <label class="form-label" for="password">Password</label>
                                </div>
                                 <div class="form-outline ">
                                    <input type="password" id="password2"  class="form-control" required/>
                                    <label class="form-label" for="password2"> Repetir Password</label>
                                </div>

                                <!-- 2 column grid layout for inline styling -->
                                <div class="row mb-4">
                                    <div class="col d-flex justify-content-center">
                                        <!-- Checkbox -->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                                            <label class="form-check-label" for="form2Example31"> Remember me </label>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <!-- Simple link -->
                                        <a href="#!">Forgot password?</a>
                                    </div>
                                </div>

                                <!-- Submit button -->
                                <input id="btnRegistrarse" type="submit" class="btn btn-primary btn-block " name="accion" value="Registrarse"/>

                            </form>
                            <a href="./">Ya tengo usuario, logearme</a>
                            <p>${error}</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Section: Design Block -->
    </body>
</html>
