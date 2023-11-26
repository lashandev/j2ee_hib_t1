<%-- 
    Document   : Login
    Created on : Oct 22, 2023, 10:51:14 AM
    Author     : Lashan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <%@include file="MyWebResouce.jsp" %>
    </head>
    <body>
        <%
            if(request.getSession().getAttribute("userdata") != null){
                response.sendRedirect("Home.jsp");
            }
            %>
        <div class="card text-center">
            <div class="card-header">
                Login
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                        <form>
                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="text" id="forUsername" class="form-control" value="admin" />
                                <label class="form-label" for="forUsername">Username</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="forPassword" class="form-control" value="123" />
                                <label class="form-label" for="forPassword">Password</label>
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
                            <button type="button" id="loginButton" class="btn btn-primary btn-block mb-4">Sign in</button>

                            <!-- Register buttons -->
                            <div class="text-center">
                                <p>Not a member? <a href="#!">Register</a></p>
                                <p>or sign up with:</p>
                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-facebook-f"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-google"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-twitter"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-github"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-4"></div>
                </div>
            </div>
            <div class="card-footer text-muted">
                Welcome to My App
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $("#loginButton").click(function () {
                var username = $("#forUsername").val();
                var password = $("#forPassword").val();

                $.ajax({
                    type: 'POST',
                    url: "UserLogin",
                    data: {"username": username, "password": password},
                    success: function (data) {
                        if (data === "00") {
                            window.location = "Home.jsp";
                        } else {
                            window.location = "Login.jsp";
                        }
                    },
                    error: function () {

                    }
                });
            });
        });
    </script>
</html>
