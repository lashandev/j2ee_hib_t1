<%-- 
    Document   : UserCreation
    Created on : Oct 21, 2023, 2:55:26 PM
    Author     : Lashan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
</head>
<body>
    <h1>User Creation</h1>

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
            <table class="table table-danger">
                <tr>
                    <td>Employee NO</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Mobile No</td>
                    <td>Email</td>
                    <td>Add or Update User Account</td>
                </tr>
                <tbody id="tabledata">

                </tbody>
            </table>
        </div>
        <div class="col-sm-1"></div>
    </div>
    <!-- Button trigger modal -->


    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                </div>
            </div>
        </div>
    </div>
</body>

<script>
    $(document).ready(function () {
        search();
    });
    function search() {
        var xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            document.getElementById("tabledata").innerHTML = this.responseText;
        }
        xhttp.open("POST", "EmployeeSearchForUser");
        xhttp.send();
    }


    function setUserdata(id, fname, lname) {
        $('#exampleModal').modal('toggle');
        var text = "<form>\n" +
                "  <div class=\"form-group\">\n" +
                "    <input type=\"hidden\" id='id' value='" + id + "'>\n" +
                "    <label for=\"exampleInputEmail1\">First Name</label>\n" +
                "    <input type=\"text\" class=\"form-control\" id=\"fname\" value='" + fname + "' aria-describedby=\"emailHelp\" placeholder=\"Enter First Name\">\n" +
                "    <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your Data with anyone else.</small>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"exampleInputEmail1\">Last Name</label>\n" +
                "    <input type=\"text\" class=\"form-control\" id=\"lname\" value='" + lname + "' aria-describedby=\"emailHelp\" placeholder=\"Enter Last Name\">\n" +
                "    <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your Data with anyone else.</small>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"exampleInputEmail1\">Email address</label>\n" +
                "    <input type=\"text\" class=\"form-control\" id=\"username\" aria-describedby=\"emailHelp\" placeholder=\"Enter email\">\n" +
                "    <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your Username with anyone else.</small>\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label for=\"exampleInputPassword1\">Password</label>\n" +
                "    <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Password\">\n" +
                "  </div>\n" +
                "  <button type=\"button\" class=\"btn btn-primary\" onclick=\"saveUser()\">Submit</button>\n" +
                "</form>";

        $('.modal-body').html(text);

    }
    function saveUser() {
        var employeeid = $('#id').val();
        var username = $('#username').val();
        var password = $('#password').val();

        var xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            $('#exampleModal').modal('toggle');
            alert(this.responseText);
            search();
        }
        var data = "?username=" + username + "&password=" + password + "&employeeid=" + employeeid;
        xhttp.open("POST", "UserSave" + data);
        xhttp.send();
    }
</script>
</html>
