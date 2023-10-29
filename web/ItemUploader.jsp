<%-- 
    Document   : ItemUploader
    Created on : Oct 29, 2023, 10:12:58 AM
    Author     : Lashan
--%>

<%@page import="dao.ItemDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Uploader</title>
        <%@include file="MyWebResouce.jsp" %>
    </head>
    <body>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Item Register</h1>                
            </div>
        </div>
        
        <div class="row">
            <div class="col-sm-3">
                <div class="container">
                    <div class="form-group">
                        <form method="post"class="row g-3" action="ItemUpload" enctype="multipart/form-data">
                            <div class="col-sm-12">
                                <%
                                    ItemDAO itemDAO = new ItemDAO();
                                    int size = itemDAO.searchAll().size();
                                    %>
                                    <label class="form-label">Item ID  (Previous ID:IT<%=size %> )</label>
                                <input type="text" class="form-control" id="id" name="id">
                            </div>
                            
                            <div class="col-sm-12">
                                <label class="form-label">Item Name</label>
                                <input type="text" class="form-control" id="itemname" name="itemname">
                            </div>
                            
                            <div class="col-sm-12">
                                <label class="form-label">File</label>
                                <input type="file" class="form-control" id="file" name="file">
                            </div>
                            
                            <div class="col-sm-12">
                                <label class="form-label">Item Status</label> &nbsp;&nbsp;
                                <input type="checkbox" class="form-check-input" id="itemstatus" name="itemstatus" checked>
                            </div>
                            
                            <div class="col-sm-6">
                                
                                <input type="submit" class="btn btn-danger" value="Upload">
                            </div>
                        </form>
                        
                    </div>
                    
                </div>
            </div>
                                <div class="col-sm-9">
                                    <%@include file="ItemViewer.jsp" %>
                                </div>
        </div>
    </body>
</html>
