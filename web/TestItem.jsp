<%-- 
    Document   : TestItem
    Created on : Oct 22, 2023, 7:48:39 PM
    Author     : Lashan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="MyWebResouce.jsp" %>
    </head>
    <body>
        <h1>File Upload</h1>
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Item Upload</h5>
                <h6 class="card-subtitle mb-2 text-body-secondary">Item Registry</h6>
                <div class="mb-3">
                    <label for="itemid" class="form-label">Item ID</label>
                    <input type="text" class="form-control" id="itemid" placeholder="I001">
                </div>
                <div class="mb-3">
                    <label for="itemname" class="form-label">Item Name</label>
                    <input type="text" class="form-control" id="itemname" placeholder="Item Name here">
                </div>
                <div class="mb-3">
                    <label for="img" class="form-label">Item Image</label>
                    <input type="file" class="form-control" id="img" placeholder="Item Image">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-danger" id="btnsaveitem"> Item Save </button>
                </div>

            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $("#btnsaveitem").click(function () {
                $.ajax({
                    type: 'POST',
                    url: "TestSaveItem"
                });
            });

        });
    </script>
</html>
