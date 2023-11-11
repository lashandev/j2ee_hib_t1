<%-- 
    Document   : ShopingCartViver
    Created on : Nov 11, 2023, 3:35:48 PM
    Author     : Lashan
--%>

<%@page import="dto.SessionCartItem"%>
<%@page import="dto.SessionCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Viewer</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
              rel="stylesheet"/>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
              rel="stylesheet"/>
        <!-- MDB -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
              rel="stylesheet"/>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.js" ></script>
        <%@include file="MyWebResouce.jsp" %>
    </head>
    <body>
        <div>
            <section class="h-100" style="background-color: #eee;">
                <div class="container h-100 py-5">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-10">

                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0 text-black">Shopping Cart</h3>
                                <div>
                                    <p class="mb-0"><span class="text-muted">Sort by:</span> <a href="#!" class="text-body">price <i
                                                class="fas fa-angle-down mt-1"></i></a></p>
                                </div>
                            </div>

                            <%
                                double total = 0;
                                if (session.getAttribute("cart") != null) {
                                    SessionCart sescart = (SessionCart) session.getAttribute("cart");
                                    if (sescart.getItemList() != null) {
                                        for (SessionCartItem cartItem : sescart.getItemList()) {
                                        total += cartItem.getTotal();

                            %>
                            <div class="card rounded-3 mb-4">
                                <div class="card-body p-4">
                                    <div class="row d-flex justify-content-between align-items-center">
                                        <div class="col-md-2 col-lg-2 col-xl-2">
                                            <img
                                                src="<%=cartItem.getImage()%>"
                                                class="img-fluid rounded-3" alt="<%=cartItem.getItem().getName()%>">
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-xl-3">
                                            <p class="lead fw-normal mb-2"><%=cartItem.getItem().getName()%></p>
                                            <p><span class="text-muted">Size: </span>M <span class="text-muted">Color: </span>Grey</p>
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                            <button class="btn btn-link px-2"
                                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                <i class="fas fa-minus"></i>
                                            </button>

                                            <input id="form1" min="0" name="quantity" value="<%=cartItem.getQty()%>" type="number"
                                                   class="form-control form-control-sm" />

                                            <button class="btn btn-link px-2"
                                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                <i class="fas fa-plus"></i>
                                            </button>
                                        </div>
                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                            <h5 class="mb-0">LKR <%=cartItem.getUnitprice()%></h5>
                                        </div>
                                        <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                            <a href="#!" class="text-danger"><i class="fas fa-trash fa-lg"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%                                        }
                                    }
                                }

                            %>

                            <div class="card mb-4">
                                <div class="card-body p-4 d-flex flex-row">
                                    <div class="form-outline flex-fill">
                                        <input type="text" id="form1" class="form-control form-control-lg" />
                                        <label class="form-label" for="form1">Discound code</label>
                                    </div>
                                    <button type="button" class="btn btn-outline-warning btn-lg ms-3">Apply</button>
                                </div>
                            </div>

                            <div class="card mb-5">
                                <div class="card-body p-4">

                                    <div class="float-end">
                                        <p class="mb-0 me-5 d-flex align-items-center">
                                            <span class="small text-muted me-2">Order total:</span> <span
                                                class="lead fw-normal">LKR <%=total %></span>
                                        </p>
                                    </div>

                                </div>
                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <button type="button" class="btn btn-warning btn-block btn-lg">Proceed to Pay</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
