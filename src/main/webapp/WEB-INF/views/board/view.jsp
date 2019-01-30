<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>write</title>
    <style>
        #article-heading {
            padding:24px; border-bottom:1px solid #ddd;
        }
        .article-subject {
            margin:0px;
            padding:5px;
        }
        .article-name {
            text-align:right;
        }
        .article-name > span {
            padding:0px 5px 0px 5px;
        }
        .article-footer {
            float:right;
        }
        #main-content {
            line-height:25px;
            border:none;
        }
    </style>
    <%@include file="../include/navbar.jsp"%>
</head>
<body>
<div class="container" style="margin-top:20px;">
    <div class="row-fluid">
        <div class="col-sm-12 col-sm-offset-0 toppad" >
            <div class="panel panel-default">
                <div id="article-heading">
                    <h3 class="article-subject">${board.title}</h3>
                    <div class="article-name">
                        <span>${board.nickname}</span>
                        <span>${board.regdate}</span>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td id="main-content">
                                        ${board.content}
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="article-footer">
                                <a href="/list?page=${page}" class="btn btn-primary">목록</a>
                                <a href="/rewrite?id=${board.id}" class="btn btn-primary">답글</a>
                                <c:if test="${sessionScope.logininfo.nickname eq board.nickname}">
                                <a href="/modify?id=${board.id}" class="btn btn-primary">수정</a>
                                <a href="/delete?id=${board.id}" class="btn btn-primary">삭제</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="check" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h3 class="modal-title" id="lineModalLabel">확인</h3>
            </div>
            <div class="modal-body">

                <!-- content goes here -->
                <form>
                    <div class="form-group">
                        <label for="InputName">이름</label>
                        <input type="text" class="form-control" id="InputName" placeholder="이름">
                    </div>
                    <div class="form-group">
                        <label for="InputPassword">비밀번호</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="비밀번호">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="btn-group btn-group-justified" role="group" aria-label="group button">
                    <div class="btn-group" role="group">
                        <button type="button" id="saveImage" class="btn btn-default btn-hover-green" data-action="save" role="button">전송</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../include/footer.jsp"%>
</html>