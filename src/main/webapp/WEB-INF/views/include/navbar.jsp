<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#alignment-example" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/list">Home</a>
        </div>

        <!-- COLLAPSIBLE NAVBAR -->
        <div class="collapse navbar-collapse" id="alignment-example">

            <!-- Button -->
            <c:if test="${sessionScope.logininfo eq null}">
                <button type="button" onClick="goPath('/loginform')" class="btn btn-default navbar-btn navbar-right">
                    로그인
                </button>
            </c:if>
            <c:if test="${sessionScope.logininfo ne null}">
                <button type="button" onClick="goPath('/logout')" class="btn btn-default navbar-btn navbar-right">
                    로그아웃
                </button>
                <button type="button" style="pointer-events:none; margin-right:10px;" class="btn btn-default navbar-btn navbar-right">
                    <span>${sessionScope.logininfo.name}</span>
                </button>
            </c:if>
        </div>

    </div>
</nav>
<script type="text/javascript">
    var goPath = function(path) {
        location.href=path;
    }
</script>
<!-- Initialize Bootstrap functionality -->

