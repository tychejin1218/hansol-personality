<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                                <a href="../admin/logout" class="nav-link btn btn-link waves-effect"><i
                                        class="mdi mdi-logout"></i> 로그아웃</a></li>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </header>
    </div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light mt-5 border">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/group/index">채용그룹</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/index">관리자</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/hq/index">본부/지역/부서</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/info/update">연락처 변경</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/resetPassword">비밀번호 변경</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="wrapper mt-2">
        <div class="container-fluid">
            <!--Contents-->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h4>본부 리스트</h1>
                                <table class="table table-border">
                                    <thead>
                                        <th>번호</th>
                                        <th>이름</th>
                                        <th>관리</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="hq" items="${list}" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td>
                                                <td><a href="../region/index?hqId=${hq.id}">${hq.name}</a></td>
                                                <td>
                                                    <form:form method="post" action="../hq/delete?id=${hq.id}">
                                                        <a href="../hq/update?id=${hq.id}"
                                                            class="btn btn-sm btn-success">수정</a>
                                                        <button type="button" class="btn btn-sm btn-danger"
                                                            onclick="deleteHqConfirm(this)">삭제</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="text-right">
                                    <a href="../hq/post" class="btn btn-primary">추가</a>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body" style="background-color: lightgrey;">
                            <p style="font-size: 16px;">
                                개인정보보호법 제59조 업무상 알게 된 개인정보(고객,회원,임직원 등)를 누설하거나 권한 없이 다른 사람이 이용하도록 하는 자 및 정당한 권한 없이 허용된
                                권한을 초과하여 다른 사람의 개인정보를 훼손, 멸실, 변경, 위조 또는 유출하는 자는 5년 이하의 징역 또는 5천만원 이하의 벌금에 처해질 수 있습니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/Contents-->
    </div>
    <script src="/resources/static/js/jquery.min.js"></script>
    <script src="/resources/static/js/bootstrap.bundle.min.js"></script>
    <script src="/resources/static/js/modernizr.min.js"></script>
    <script src="/resources/static/js/waves.js"></script>
    <script src="/resources/static/js/jquery.slimscroll.js"></script>
    <script src="/resources/static/js/app.js"></script>
    <script src="/resources/static/js/site.js"></script>
</body>

</html>