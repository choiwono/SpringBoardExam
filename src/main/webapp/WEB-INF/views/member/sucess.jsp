<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
</body>
<script type="text/javascript">
    var message = "${message}";
    alert(message);

    function goRedirect(path){
        document.location.href = path;
    }
    goRedirect("/list");
</script>
</html>
