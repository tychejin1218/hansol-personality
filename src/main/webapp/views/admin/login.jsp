<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <div class="col-lg-6 offset-lg-3 pl-5 pr-5">
                    <div class="card mb-0">
                        <div class="card-body">
                            <div class="p-2 d-flex justify-content-between">
                                <div style="background-color:#2ecc71; padding : 10px; border-radius:0.3em">
                                    <a class="logo logo-admin" href="/">
                                        <img src="/resources/static/images/toplogo.png" alt="logo">
                                    </a>
                                </div>
                                <p style="margin-top:auto;font-size:22px;" class="text-right">교사 인적성검사<br/>관리자 로그인</p>
                            </div>
                            <div class="p-2">
                                <p class="font-14 text-muted mb-1 mt-2">관리자 ID와 비밀번호를 입력하세요.</p>
                            </div>
                            <div class="p-2">
                                <form:form class="form-horizontal m-t-20" method="post" action="/admin/login"
                                    modelAttribute="admin">
                                    <input type="hidden" name="${_csrf.parameterName}" Value="${_csrf.token}" />
                                    <div class="form-group row">
                                        <div class="col-12">
                                            <input type="text" class="form-control" name="username" placeholder="ID"
                                                required autofocus value="">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-12">
                                            <input type="password" class="form-control" name="password"
                                                placeholder="비밀번호" required value="">
                                        </div>
                                    </div>

                                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                                        <font color="red">
                                            <p>ID 혹은 비밀번호가 올바르지 않습니다.</p>
                                            <script>alert('ID 혹은 비밀번호가 올바르지 않습니다.')</script>
                                            <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
                                        </font>
                                    </c:if>

                                    <div class="form-group text-center row m-t-20">
                                        <div class="col-12">
                                            <button type="submit" value="Submit"
                                                class="btn btn-primary btn-block waves-effect waves-light"
                                                style="background-color:#2A2A66!important; border-color:#2A2A66!important">접속</button>
                                        </div>
                                    </div>
                                    <div class="form-group row" style="padding-left:18px;margin-bottom:5px;">
                                        <p class="text-justify mb-0">
                                            접속에 문제가 있을 경우, 채용 담당자(${info.getTel()})에게 연락 주시기 바랍니다.
                                        </p>
                                        <p class="text-justify text-muted">
                                            본 웹 사이트는 크롬(Chrome) 브라우저에 최적화 되어있습니다.
                                        </p>
                                    </div>
                                </form:form>
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
    <script src="/resources/static/js/jquery.slimscroll.js"></script>
    <script src="/resources/static/js/app.js"></script>
</body>

</html>