<%-- 
    Document   : ItemViewer
    Created on : Oct 29, 2023, 1:55:08 PM
    Author     : Lashan
--%>


<div id="itemviewer">

</div>

<script>
    $(document).ready(function () {

        $.ajax({
            type: 'POST',
            url: "ItemLoader",
            success: function (data) {
                console.log(data);
                $("#itemviewer").html(data);
            },
            error: function () {

            }
        });
    });
</script>

