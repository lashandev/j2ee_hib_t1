<%-- 
    Document   : Department
    Created on : Oct 14, 2023, 9:34:15 AM
    Author     : Lashan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-4">

                <div class="form-group">
                    <label>Department ID</label>
                    <input type="text" class="form-control" id="depid" placeholder="Department ID Here">
                </div>
                <div class="form-group">
                    <label>Department Name</label>
                    <input type="text" class="form-control" id="depname" placeholder="Department Name Here">
                </div>
                <div class="form-group">
                    <label>Department Status</label>
                    <select id="status" class="form-control">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>
                <br>
                <div class="form-group">
                    <button class="btn btn-primary btn-block" onclick="saveData()">Save New Department</button>
                    <button class="btn btn-success btn-block" onclick="search()">Search</button>
                </div>

            </div>
            <div class="col-sm-6">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                            <table class="table table-striped table-primary" id="mytable">
                                <tr>
                                    <th>Department ID</th>
                                    <th>Department Name</th>
                                    <th>Department Status</th>
                                    <th>Update Action</th>
                                    <th>Delete Action</th>
                                </tr>
                                <tbody id="tabledata"></tbody>
                            </table>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>

                </div>
            </div>
            <div class="col-sm-1"></div>
        </div>


        <div class="container">

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Department Saving</h4>
                        </div>
                        <div class="modal-body">
                            <p>Department Save Complete.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

        </div>


    </body>
    <script>
        $(document).ready(function (){
            search();
        });
        function saveData() {
            var depid = document.getElementById('depid').value;
            var depname = document.getElementById('depname').value;
            var status = document.getElementById('status').value;

            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                $('#myModal').modal('toggle');
                $("input:text").val("");
                search();
            }
            xhttp.open("POST", "DepartmentSave?depid=" + depid + "&depname=" + depname + "&status=" + status);
            xhttp.send();

        }

        function search() {
            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                console.log(this.responseText);
                document.getElementById("tabledata").innerHTML = this.responseText;
            }
            xhttp.open("POST", "DepartmentSearchData");
            xhttp.send();
        }
    </script>
</html>
