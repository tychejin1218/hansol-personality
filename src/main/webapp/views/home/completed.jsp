<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex,nofollow" />
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- App Icons -->
    <title>한솔교육 온라인 인성검사 관리자</title>
    <link rel="shortcut icon" type="text/css" href="/resources/static/favicon.ico"><!-- morris css -->
    <link rel="stylesheet" type="text/css" href="/resources/static/css/morris.css"><!-- App css -->
    <link rel="stylesheet" type="text/css" href="//cdn.materialdesignicons.com/3.6.95/css/materialdesignicons.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/icons.css">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css">
</head>
<body>
    <div style="background-color:rgba(0,0,0,0.3);position:absolute; top:0; bottom:0; left:0;right:0;"></div>
    <div class="account-pages" style="opacity:0.9">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6 offset-lg-3 pl-0 pr-0">
                    <div class="card mb-0">
                        <div class="card-body">
                            <div class="p-2 d-flex justify-content-between">
                                <div style="background-color:#F7B719; padding : 10px; border-radius:0.3em">
                                    <a class="logo logo-admin" href="/">
                                        <img src="/resources/static/images/toplogo.png"  alt="logo">
                                    </a>
                                </div>
                                <p style="margin-top:auto;font-size:22px;">한솔교육 교사 인적성검사</p>
                            </div>
                            <div class="p-2">
                                <h5>"${name}"님은 이미 검사가 완료되었습니다.</h5>
                                <h5>재검사가 필요한 경우, 채용 담당자에게 문의해 주세요.</h5>
                                <hr/>
                                <a href="/" class="btn btn-block btn-primary">로그인 페이지로 이동</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/resources/static/js/jquery.min.js"></script>
    <script src="/resources/static/js/bootstrap.bundle.min.js"></script>
    <script src="/resources/static/js/modernizr.min.js"></script>
    <script src="/resources/static/js/waves.js"></script>
    <script src="/resources/static/js/jquery.slimscroll.js"></script><!-- App js -->
    <script src="/resources/static/js/app.js"></script>
</body>
</html>