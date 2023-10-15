<%-- 
    Document   : SearchDepartment
    Created on : Oct 8, 2023, 2:09:49 PM
    Author     : Lashan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Search Department via Department Code</h1>
                </div>
                <div class="col-sm-2"></div>
            </div>

            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-2">
                    <label>Department ID :</label>
                </div>
                <div class="col-sm-3">
                    <input type="text" name="depid" id="depid" class="form-control">
                </div>
                <div class="col-sm-1">
                    <button class="btn btn-warning btn-block" onclick="search()">Search</button>
                </div>
                <div class="col-sm-4"></div>
            </div>

            <div class="row"></div>
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <table class="table" id="deptb1">
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Status</th>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>


    </body>
    <script>
        function search() {
            var depid = document.getElementById('depid').value;
            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                var response = this.responseText;
                document.getElementById('deptb1').innerHTML = response;
            }
            xhttp.open('GET', 'DepartmentSearch?depid=' + depid);
            xhttp.send();
        }
    </script>
</html>
