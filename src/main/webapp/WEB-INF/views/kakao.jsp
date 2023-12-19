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
    // TODO : 오류 해결
    // TODO : 서버에 client_secret 저장 및 서비스 연동
    $(document).ready(function () {
        const searchParam = new URLSearchParams(location.search);
        const code = searchParam.get('code');
        $("#code").val(code);
        fnGetToken();
    });

    function fnGetToken() {
        var code = $("#code").val();
        $.ajax({
            type: "post",
            url: "https://kauth.kakao.com/oauth/token",
            contentType: "application/x-www-form-urlencoded",
            data: {grant_type:code
            , client_id : "027af1e1bbd65e2161d454d88f739af6"
            , redirect_uri:"http://localhost:8081/kakao"},
            success: function (datas) {
                if (datas.success) {
                    tableList = datas.tableList;
                    dmndList = datas.dmndList;
                    fnCodeHtml(tableList, dmndList);
                } else {
                    openAlert("조회값이 없습니다.");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                openAlert("서버와의 통신중에 오류가 발생했습니다.");
            },
        });
    }


    //TODO : 토큰 받기
    //TODO : client_secret 암호화 후 화면에서 복호화 후 post
</script>