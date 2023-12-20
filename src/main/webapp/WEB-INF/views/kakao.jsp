<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %> <%-- <%@ taglib uri="http://www.springframework.org/tags"
prefix="spring"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>로그인 화면</title>
        <script async src="/webjars/jquery/3.6.4/jquery.min.js"></script>
        <script async src="/js/ajax-item-admin.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css">
        <style type="text/css">
            .container {
                max-width: 960px;
            }

            .form-control-borderless {
                border: none;
            }

            .form-control-borderless:hover, .form-control-borderless:active,
            .form-control-borderless:focus {
                border: none;
                outline: none;
                box-shadow: none;
            }

            #item-list {
                border-style: outset;
            }
        </style>
    </head>

    <input type="hidden" id="code"/>
<body class="bg-light">
<script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <div class="py-5 text-center">
        <p class="lead">redirect 확인하기</p>
    </div>
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <form class="card card-sm">
                <div class="card-body row no-gutters align-items-center">
                    <button class="btn btn-lg btn-success" type="button" id="login-btn" onclick="fnLogin();">로그인
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    // TODO : 신규 회원가입과 기존 가입 구분하기
    $(document).ready(function () {
        const searchParam = new URLSearchParams(location.search);
        const code = searchParam.get('code');
        $("#code").val(code);
        fnGetToken();
    });

    function fnGetToken() {
        var data = {
            code : $("#code").val()
            ,client_id : "027af1e1bbd65e2161d454d88f739af6"
            ,redirect_url : "http://localhost:8081/kakao"
            ,grant_type : "authorization_code"
            ,client_secret : "FTCBzIYWbw0EmCnDDb5EIeRgS3l47Xkz"
        }

        $.ajax({
            type: "post",
            url: "https://kauth.kakao.com/oauth/token",
            contentType: "application/x-www-form-urlencoded",
            data: $.param(data),
            success: function(response) {
                console.log('Success:', response);
                console.log(response.access_token);
            },
            error: function(error) {
                // 오류가 발생했을 때 수행할 작업
                console.log('Error:', error);
            }
        });
    }


    //TODO : client_secret 암호화 후 화면에서 복호화 후 post
</script>