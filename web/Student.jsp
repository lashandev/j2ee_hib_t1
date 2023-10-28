<%-- 
    Document   : Student
    Created on : Oct 28, 2023, 12:07:54 PM
    Author     : Lashan
--%>

<%@page import="dto.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="MyWebResouce.jsp" %>
    </head>
    <body>
        <h1>Student Data</h1>
        <div class="container">
            <div class="form-group">
                <div class="form-group">
                    <label>Student ID</label>
                    <input type="text" name="id" id="id" class="form-control">
                </div>
                <div class="form-group">
                    <label>Student Name</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Student Age</label>
                    <input type="text" name="age" id="age" class="form-control">
                </div>
                <br>
                <div class="form-group">
                    <button class="btn btn-danger" id="savebutton">Save Data</button>
                </div>
            </div>
            <br>
            <br>
            <div class="container">
                <%
                    if (session.getAttribute("studentlist") != null) {
                %>
                <div>
                    <table class="table table-bordered table-primary">
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Student Age</th>
                            <th>Delete Action</th>
                        </tr>
                        <%
                            List<Student> list = (List<Student>) session.getAttribute("studentlist");
                            for (Student student : list) {
                        %>
                        <tr>
                            <td><%=student.getId()%></td>
                            <td><%=student.getName()%></td>
                            <td><%=student.getAge()%></td>
                            <td><a href="DeleteStudent?id=<%=student.getId() %>"><button class="btn btn-warning">Delete</button></a></td>
                        </tr>
                        <%
                            }

                        %>
                    </table>
                </div>
                <%                                }
                %>
            </div>

        </div>
    </body>

    <script>
        $(document).ready(function () {
            $("#savebutton").click(function () {
                var id = $("#id").val();
                var name = $("#name").val();
                var age = $("#age").val();

                $.ajax({
                    type: 'POST',
                    url: "SaveStudent",
                    data: {"id": id, "name": name, "age": age},
                    success: function (data) {
                        window.location = "Student.jsp";

                    },
                    error: function () {

                    }
                });
            });
        });
    </script>
</html>
