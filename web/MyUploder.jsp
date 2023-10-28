<%-- 
    Document   : MyUploder
    Created on : Oct 28, 2023, 2:09:27 PM
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
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <div class="container">
                    <div class="form-group">
                        <form method="post"class="row g-3" action="MyUpload" enctype="multipart/form-data">
                            <div class="col-sm-12">
                                <label class="form-label">File ID</label>
                                <input type="text" class="form-control" id="id" name="id">
                            </div>
                            
                            <div class="col-sm-12">
                                <label class="form-label">File</label>
                                <input type="file" class="form-control" id="file" name="file">
                            </div>
                            
                            <div class="col-sm-3">
                                
                                <input type="submit" class="btn btn-danger" value="Upload">
                            </div>
                        </form>
                        
                    </div>
                    
                </div>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </body>
</html>
