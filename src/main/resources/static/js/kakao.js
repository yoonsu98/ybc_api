$(document).ready(function () {
    fnGetCode();
    fnGetTeamList();
})
;

function fnGetCode() {
    const searchParam = new URLSearchParams(location.search);
    const code = searchParam.get('code');

    if (code != null) {
        $("#code").val(code);
        fnGetTokenInfo(code);
    }
};

function fnGetTokenInfo(code) {
    $.ajax({
        type: "post",
        url: "http://localhost:8081/user/token-info",
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
        url: "http://localhost:8081/code/column",
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
        url: "http://localhost:8081/user/user-info",
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