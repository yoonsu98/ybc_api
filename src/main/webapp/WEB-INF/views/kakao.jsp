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
        <%--        <script async src="/js/ajax-item-admin.js"></script>--%>
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
</body>
</html>

<script type="text/javascript">
    $(document).ready(function () {
        fnGetCode();
        fnGetTeamList();
    });

    function fnGetCode() {
        const searchParam = new URLSearchParams(location.search);
        const code = searchParam.get('code');

        if(code != null) {
            $("#code").val(code);
            fnGetTokenInfo(code);
        }
    };

    function fnGetTokenInfo(code) {
        $.ajax({
            type: "post",
            url: "http://localhost:8081/user/getTokenInfo",
            contentType: "application/json",
            data: JSON.stringify({code: code}),
            success: function (response) {
                const {accessToken, refreshToken, kakaoId, nickname} = response.data;
                if (accessToken != null) {
                    access_cookie = `access_token=${accessToken};`;
                    refresh_cookie = `refresh_token=${refreshToken};`;
                    document.cookie = access_cookie;
                    document.cookie = refresh_cookie;
                    alert(`${nickname}님 안녕하세요.`);
                    location.href = "http://localhost:8081/main";
                }
                $("#kakaoId").val(kakaoId);
            },
            error: function (error) {

            }
        });
        $("#divTeam").hide();
    }

    function fnSetUser() {
        var nickname = $("#nickname").val();

        if (nickname.trim() == "") {
            alert("사용할 닉네임을 입력해주세요.");
            return false;
        }
        $("#divNickname").hide();
        $("#divTeam").show();
    }

    function fnGetTeamList() {
        $.ajax({
            type: "get",
            url: "http://localhost:8081/code/findCodeByColumnNm",
            data: {columnNm: "teamDcd"},
            success: function (response) {
                fnSetTeamHtml(response.data);
            },
            error: function (error) {
                // 오류가 발생했을 때 수행할 작업
                console.log('Error:', error);
            }
        });
    }

    function fnSetTeamHtml(data) {
        for (const team of data) {
            const html = `<option value="${team.cd}">${team.cdNm}</option>`;
            $("#teamList").append(html);
        }
    }

    function fnRegUser() {
        const kakaoId = $("#kakaoId").val();
        const teamDcd = $("#teamList").val();
        const nickname = $("#nickname").val();

        $.ajax({
            type: "post",
            url: "http://localhost:8081/user/registryUserInfo",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({kakaoId, teamDcd, nickname}),
            success: function (response) {
                alert(`${nickname}님 안녕하세요.`);
                location.href = "http://localhost:8081/main";
            },
            error: function (error) {
                // 오류가 발생했을 때 수행할 작업
                console.log('Error:', error);
            }
        });
    }
</script>