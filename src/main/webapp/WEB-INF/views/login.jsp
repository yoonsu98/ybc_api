<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title>로그인 화면</title>
        <script async src="/webjars/jquery/3.6.4/jquery.min.js"></script>
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
<body class="bg-light">
<script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <div class="py-5 text-center">
        <h1>메인화면</h1>
        <p class="lead">안녕하세요~~</p>
    </div>
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <form class="card card-sm">
                <div class="card-body row no-gutters align-items-center">
                    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=027af1e1bbd65e2161d454d88f739af6&redirect_uri=http://localhost:8081/kakao">로그인</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
</script>