<%-- 
    Document   : ItemViewer
    Created on : Oct 29, 2023, 1:55:08 PM
    Author     : Lashan
--%>
<style>
    .pagination {
        display: inline-block;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        transition: background-color .3s;
    }

    .pagination a.active {
        background-color: #4CAF50;
        color: white;
    }

    .pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<div class="pagination" id="paginate">
    <a onclick="changePage(0)">&laquo;</a>
    <a onclick="changePage(0)" class="active">1</a>
    <a onclick="changePage(1)">2</a>
    <a onclick="changePage(2)">3</a>
    <a onclick="changePage(3)">4</a>
    <a onclick="">&raquo;</a>
</div>
<div id="itemviewer">

</div>

<script>
    $(document).ready(function () {

        $.ajax({
            type: 'POST',
            url: "ItemLoader",
            success: function (data) {
                $("#itemviewer").html(data);
            },
            error: function () {

            }
        });
        
        $.ajax({
            type: 'POST',
            url: "PageNumberLoad",
            success: function (data) {
                $("#paginate").html(data);
            },
            error: function () {

            }
        });
    });

    function changePage(offset) {
        var anchors = document.querySelectorAll('.pagination a');
        anchors.forEach(function (anchor) {
            anchor.classList.remove('active');
        });
        event.currentTarget.classList.add('active');
        itemList(offset);
    }

    function itemList(fr) {

        console.log(fr);
        $.ajax({
            type: 'POST',
            url: "ItemLoader",
            data: {"fr": fr},
            success: function (data) {
                console.log(data);
                $("#itemviewer").html(data);
            },
            error: function () {

            }
        });

    }
    function addToCart(id) {
        $.ajax({
            type: 'POST',
            url: "AddToCart",
            data: {"item": id},
            success: function (data) {
                window.location = "Home.jsp";

            },
            error: function () {

            }
        });
    }
</script>

