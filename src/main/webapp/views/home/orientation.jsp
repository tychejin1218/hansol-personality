<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <div class="header-bg">
        <header id="topnav">
            <div class="topbar-main">
                <div class="container-fluid">
                    <div>
                        <a class="logo" style="margin-top:10px;margin-bottom:10px">
                            <img src="/resources/static/images/toplogo.png" alt=""
                                style="width:116px!important;height:48px!important">
                        </a>
                    </div>
                    <div class="menu-extras topbar-custom navbar p-0 float-right">
                        <ul class="list-inline d-none d-lg-block mb-0">
                            <li class="list-inline-item notification-list">
                                <a href="#" class="nav-link btn btn-link waves-effect" onclick="confirmLogout()"><i
                                        class="mdi mdi-logout"></i> 로그아웃</a></li>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </header>
    </div>
    <div class="wrapper" style="margin-top : 50px">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="text-center">
                                <img src="/resources/static/images/orientations/${vm.orientation.imagePath}" alt="OT"
                                    style="width:80%" />
                            </div>
                            <hr />
                            <div>
                                <c:if test="${vm.getPage() == 1}">
                                    <a class="btn btn-secondary" href="#" onclick="alert('검사 오리엔테이션 첫 페이지 입니다.')">첫
                                        페이지</a>
                                </c:if>

                                <c:if test="${vm.getPage() > 1}">
                                    <a class="btn btn-secondary" href="../orientation?pageNo=${vm.getPage() - 1}">이전</a>
                                </c:if>

                                <c:if test="${vm.orientation.isLast == false}">
                                    <a class="btn btn-primary float-right text-white"
                                        href="../orientation?pageNo=${vm.getPage() + 1}">다음</a>
                                </c:if>

                                <c:if test="${vm.orientation.isLast == true}">
                                    <form:form method="post" action="../test" class="float-right form-inline">
                                        <button type="button" class="btn btn-danger float-right text-white" onclick="confirmEndOrientation(this)">검사 시작</button>
                                    </form:form>
                                </c:if>
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
    <script src="/resources/static/js/site.js"></script>
</body>

</html>