<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Title</title>

        <style>
            #keyword {
                display:inline-table;
            }
            @media (max-width: 768px)  {
                #keyword {
                    display:block;
                }
            }
            #submit > a {
                color:white;
                text-decoration:none;
            }
            #pagenation {
                display: table;
                margin-left: auto;
                margin-right: auto;
            }
            #pagenation > ul {
                display:flex;
            }
            .point-btn {
                height:34px;
            }
            .page-link > a {
                height:34px;
            }
            .disabled-atag {
                pointer-events: none;
                cursor: default;
                opacity: 0.6;
            }

        </style>
        <%@include file="../include/navbar.jsp"%>
    </head>
    <body>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h4>게시판</h4>
                <div class="table-responsive">
                    <table id="mytable" class="table table-bordered table-hover col-sm-12">
                        <thead>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>등록일</th>
                        <th>조회수</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${boards}" var="board">
                                <tr>
                                    <td>${board.id}</td>
                                    <td>
                                        <c:forEach begin="1" end="${board.group_depth}">
                                            <span style="font-weight:700; margin-left:5px; color:#1E90FF">[RE]</span>
                                        </c:forEach>
                                        <!--<a href="/read?id=${board.id}">${board.title}</a>-->
                                        <a href="/view?id=${board.id}">${board.title}</a>
                                    </td>
                                    <td>${board.nickname}</td>
                                    <td>${board.regdate}</td>
                                    <td>${board.read_count}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div>
            <form class="form-inline" action="/list" method="get">
                <select name="search" class="form-control" id="inlineFormInput">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>

                <label class="sr-only" for="keyword">Username</label>
                <div class="input-group">
                    <input type="text" name="keyword" value="" class="form-control" id="keyword" placeholder="search...">
                </div>
                <button type="submit" class="btn btn-primary">검색</button>
                <div class="input-group">
                    <button id="submit" type="button" class="btn btn-primary">
                        <a href="/write">글쓰기</a>
                    </button>
                </div>
            </form>
            <nav id="pagenation" aria-label="Page navigation example">
                <ul style="display:flex;" class="pagination justify-content-end">
                    <c:if test="${page != 1}">
                        <li class="page-item">
                            <a class="point-btn page-link" href="/list?page=1&search=${search}&keyword=${keyword}"><span class="glyphicon glyphicon-backward"></span></a>
                        </li>
                    </c:if>
                    <c:if test="${page == 1}">
                        <li class="page-item disabled">
                            <a class="point-btn page-link disabled-atag" href="#"><span class="glyphicon glyphicon-chevron-left"></span></a>
                        </li>
                    </c:if>
                    <c:if test="${page > 1}">
                        <li class="page-item">
                            <a class="point-btn page-link" href="/list?page=${page-1}&search=${search}&keyword=${keyword}"><span class="glyphicon glyphicon-chevron-left"></span></a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${totalPage}">
                        <li <c:if test="${i eq page}">class="page-item active"</c:if>>
                            <a class="page-link" href="/list?page=${i}&search=${search}&keyword=${keyword}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${page == totalPage}">
                        <li class="page-item disabled">
                            <a class="point-btn page-link disabled-atag" href="#"><span class="glyphicon glyphicon-chevron-right"></span></a>
                        </li>
                    </c:if>
                    <c:if test="${page < totalPage}">
                        <li class="page-item">
                        <a class="point-btn page-link" href="/list?page=${page-1}&search=${search}&keyword=${keyword}"><span class="glyphicon glyphicon-chevron-right"></span></a>
                        </li>
                    </c:if>
                    <c:if test="${page != totalPage}">
                        <li class="page-item">
                            <a class="point-btn page-link" href="/list?page=${totalPage}&search=${search}&keyword=${keyword}"><span class="glyphicon glyphicon-forward"></span></a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
    </div>
    </div>
    </body>
    <%@include file="../include/footer.jsp"%>
</html>
