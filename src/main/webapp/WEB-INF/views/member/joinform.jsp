<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>회원 가입 폼</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <%@include file="../include/navbar.jsp"%>
</head>
<style>

    body, html{
        height: 100%;
        font-family: 'Oxygen', sans-serif;
    }

    .main{
        margin-top: 25px;
        margin-bottom: 50px;
    }

    h1.title {
        font-size: 50px;
        font-weight: 400;
    }

    hr{
        width: 10%;
        color: #fff;
    }

    .form-group{
        margin-bottom: 15px;
    }

    label{
        margin-bottom: 15px;
    }

    input,
    input::-webkit-input-placeholder {
        font-size: 11px;
        padding-top: 3px;
    }

    .main-login{
        background-color: #fff;
        /* shadows and rounded borders */
        -moz-border-radius: 2px;
        -webkit-border-radius: 2px;
        border-radius: 2px;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

    }

    .main-center{
        margin-top: 30px;
        margin-bottom: 40px;
        margin: 0 auto;
        max-width: 350px;
        padding: 40px 40px;

    }

    .login-button{
        margin-top: 5px;
    }

    label.error {
        font-weight:600;
        padding:3px 0 0 10px;
        color:#FF0000;
        display:block;
    }

    #check-email {
        width:100%;
    }
    .input-group {
        display:block;
    }
</style>
<body>
        <div class="container">
            <div class="row main">
                <div class="panel-heading">
                    <div class="panel-title text-center">
                        <h3 class="title">회원가입</h3>
                        <hr />
                    </div>
                </div>
                <div class="main-login main-center">
                    <form id="registerForm" class="form-horizontal" data-parsley-validate="true" method="post" action="/join">
                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label">이름</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="name" id="name" minlength="2" placeholder="이름.." required/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nickname" class="cols-sm-2 control-label">닉네임</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="nickname" id="nickname" minlength="2" placeholder="닉네임" required/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="check-email">
                            <label for="email" class="cols-sm-2 control-label">이메일</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="email" id="email"  placeholder="이메일.." required/>
                                </div>
                                <button type="button" id="check-id" style="margin-top:7.5px;" class="btn btn-primary">중복체크</button>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="passwd" class="cols-sm-2 control-label">비밀번호</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="password" class="form-control" name="passwd" id="passwd"  placeholder="비밀번호" required/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="passwd2" class="cols-sm-2 control-label">비밀번호 확인</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="password" class="form-control" name="passwd2" id="passwd2"  placeholder="비밀번호 확인" required/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group ">
                            <button type="submit" class="btn btn-primary btn-lg btn-block login-button">등록</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
</body>
<script type="text/javascript">
    $(function() {
        $("#registerForm").validate({
            rules: {
                nickname : {
                    required : true
                },
                email : {
                    required : true,
                    email: true
                },
                passwd1 : {
                    required: "required"
                },
                passwd2 : {
                    required:true,
                    equalTo:'#passwd'
                },
                name : {
                    required : true
                }
            }, messages: {
                name: {
                    required: "이름을 입력하세요"
                },
                nickname: {
                    required: "닉네임을 입력하세요"
                },
                email: {
                    required: "이메일을 입력하세요",
                    email:"올바른 이메일 형식이 아닙니다"
                },
                passwd1: {
                    required: "암호을 입력하세요"
                },
                passwd2: {
                    required: "암호를 입력하세요",
                    equalTo:"암호가 일치하지 않습니다"
                }
            },
            submitHandler: function(frm) {
                alert("회원가입이 되었습니다.");
                frm.submit();
            },
            success: function(e) {

            }
        });
    });
    var check_id = function() {
        $("#check-id").click(function(){
            var userid = $("#email").val();
            var userData = {"id":userid};

            $.ajax({
                type : "POST",
                url : "/checkEmail",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data : userData,
                dataType : "json",
                success : function(result) {
                    if(result == 0){
                        $("#check-id").attr('class','btn btn-success');
                        $("#check-id").html("사용가능");
                    } else {
                        $("#check-id").attr('class','btn btn-danger');
                        $("#check-id").html("사용불가능");
                    }
                },
                error : function(error) {
                    alert("서버가 응답하지 않습니다. \n다시 시도해주시길 바랍니다.");
                }
            })
        })
    }
    check_id();
</script>
<%@include file="../include/footer.jsp"%>
</html>