<link rel="icon" href="/icon.ico" type="image/x-icon">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>로그인 화면</title>
        <script async src="/webjars/jquery/3.6.4/jquery.min.js"></script>
        <script src="/js/kakao.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" type="text/css" href="/css/blog.css">

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
    <input type="hidden" id="token"/>
    <input type="hidden" id="kakaoId"/>
<body class="bg-light">
<script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <form class="card card-sm">
                <div class="card-body row no-gutters align-items-center" id="divNickname">
                    <p>닉네임 뭘로 하실래요?</p>
                    <input type="text" id="nickname"/>
                    <input type="button" value="결정" onclick="fnSetUser();"/>
                </div>
                <div class="card-body row no-gutters align-items-center" id="divTeam">
                    <p>응원하는 팀이 있나요?</p>
                    <select id="teamList">
                    </select>
                    <input type="button" value="선택" onclick="fnRegUser();"/>
                </div>
            </form>
        </div>
    </div>
</div>

<footer class="blog-footer">
    <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.
    </p>
    <p>
        <a href="#">Back to top</a>
    </p>
</footer>

</body>
</html>