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
            <a class="navbar-brand" href="#">Your Brand</a>
        </div>

        <!-- COLLAPSIBLE NAVBAR -->
        <div class="collapse navbar-collapse" id="alignment-example">

            <!-- Links -->
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link 1 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link 2</a></li>
                <li><a href="#">Link 3</a></li>
            </ul>

            <!-- Button -->
            <c:if test="${sessionScope.logininfo eq null}">
                <button type="button" class="btn btn-default navbar-btn navbar-right">
                    <a href="/loginform">로그인</a>
                </button>
            </c:if>
            <c:if test="${sessionScope.logininfo ne null}">
                <button type="button" class="btn btn-default navbar-btn navbar-right">
                    <a href="/logout">로그아웃</a>
                </button>
                <button type="button" style="pointer-events:none; margin-right:10px;" class="btn btn-default navbar-btn navbar-right">
                    <span>${sessionScope.logininfo.name}</span>
                </button>
            </c:if>
        </div>

    </div>
</nav>
<!-- Initialize Bootstrap functionality -->

