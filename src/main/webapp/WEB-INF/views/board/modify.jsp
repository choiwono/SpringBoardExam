<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>write</title>
    <style>
        .my-form {
            margin-top:20px;
        }
        #list > a {
            color:white;
            text-decoration:none;
        }
    </style>
    <%@include file="../include/navbar.jsp"%>
</head>
<body>
<main class="my-form">
    <div class="container">
        <div class="row justify-content-center" >
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">글쓰기</div>
                    <div class="card-body">
                        <div class="card-body-center">
                            <form name="my-form" action="/modify" method="post">
                                <div class="form-group row">
                                    <label for="present_address" class="col-md-2 col-form-label text-md-right">닉네임</label>
                                    <div class="col-md-9">
                                        <input type="hidden" name="id" value="${board.id}"/>
                                        <input type="hidden" name="email" value="${sessionScope.logininfo.email}" />
                                        <input type="hidden" name="name" value="${sessionScope.logininfo.name}"/>
                                        <input value="${board.nickname}" id="present_address" class="form-control" disabled/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="title" class="col-md-2 col-form-label text-md-right">제목</label>
                                    <div class="col-md-9">
                                        <input type="text" name="title" id="title" value="${board.title}" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="content" class="col-md-2 col-form-label text-md-right">내용</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" name="content" id="content" rows="8">${board.content}</textarea>
                                    </div>
                                </div>
                                <div class ="d-flex flex-row-reverse">
                                    <div class="d-flex flex-row-reverse">
                                        <button type="submit" class="btn btn-primary">
                                            등록
                                        </button>
                                        <button id="list" type="button" class="btn btn-primary">
                                            <a href="/list">목록</a>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<%@include file="../include/footer.jsp"%>
</html>
