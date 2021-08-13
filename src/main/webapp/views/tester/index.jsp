<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/user/index">관리자</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/hq/index">본부/지역/부서</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/info/update">연락처 변경</a>
                        </li>
                    </sec:authorize>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/resetPassword">비밀번호 변경</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="wrapper mt-2">
        <div class="container-fluid" style="max-width: none;">
        <!-- <div class="container-fluid"> -->
            <!--Contents-->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="float-left">지원자 리스트</h4>
                                <select class="form-control float-right" id="tester-row" style="width:100px">
                                    <c:if test="${row==10}">
                                        <option value="10" selected="selected">10개</option>
                                    </c:if>
                                    <c:if test="${row!=10}">
                                        <option value="10">10개</option>
                                    </c:if>

                                    <c:if test="${row==20}">
                                        <option value="20" selected="selected">20개</option>
                                    </c:if>
                                    <c:if test="${row!=20}">
                                        <option value="20">20개</option>
                                    </c:if>

                                    <c:if test="${row==30}">
                                        <option value="30" selected="selected">30개</option>
                                    </c:if>
                                    <c:if test="${row!=30}">
                                        <option value="30">30개</option>
                                    </c:if>

                                    <c:if test="${row==50}">
                                        <option value="50" selected="selected">50개</option>
                                    </c:if>
                                    <c:if test="${row!=50}">
                                        <option value="50">50개</option>
                                    </c:if>

                                    <c:if test="${row==100}">
                                        <option value="100" selected="selected">100개</option>
                                    </c:if>
                                    <c:if test="${row!=100}">
                                        <option value="100">100개</option>
                                    </c:if>
                                </select>
                                <table class="table table-border">
                                    <thead>
                                        <th>번호</th>
                                        <th>지원자 ID</th>
                                        <th>이름</th>
                                        <th>성별</th>
                                        <th>생년월일</th>
                                        <th>휴대폰</th>
                                        <th>인증키</th>
                                        <th>적합/부적합</th>
                                        <th>검사상태</th>
                                        <th>생성일자</th>
                                        <th>완료일자</th>
                                        <th>리포트</th>
                                        <th>관리</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="tester" items="${list}">
                                            <tr>
                                                <td>${tester.id}</td>
                                                <td>
                                                    ${tester.applyId}
                                                    <c:if test="${tester.isRetry}">
                                                        <span class="badge badge-primary">R</span>
                                                    </c:if>
                                                </td>
                                                <td>${tester.name}</td>
                                                <td>
                                                    <c:if test="${tester.gender == true}">
                                                        <span class="badge badge-primary">남</span>
                                                    </c:if>
                                                    <c:if test="${tester.gender == false}">
                                                        <span class="badge badge-danger">여</span>
                                                    </c:if>
                                                </td>
                                                <td>${tester.birthday}</td>
                                                <td>${tester.phone}</td>
                                                <td>${tester.authKey}</td>
                                                <td>
                                                    <c:if test="${tester.testState == 2}">
                                                        <c:if test="${tester.testResult}">
                                                            <span class="badge badge-success">적합</span>
                                                        </c:if>
                                                        <c:if test="${!tester.testResult}">
                                                            <span class="badge badge-danger">부적합</span>
                                                        </c:if>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${tester.testState==0}">
                                                        <span class="badge badge-danger">미완료</span>
                                                    </c:if>
                                                    <c:if test="${tester.testState==1}">
                                                        <span class="badge badge-warning">진행중</span>
                                                    </c:if>
                                                    <c:if test="${tester.testState==2}">
                                                        <span class="badge badge-success">완료</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <fmt:parseDate value="${tester.createdDateTime}"
                                                        pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" type="both" />
                                                    <fmt:formatDate pattern="yyyy.MM.dd HH:mm"
                                                        value="${parseDateTime}" />
                                                </td>
                                                <td>
                                                    <c:if test="${tester.testState==2}">
                                                        <fmt:parseDate value="${tester.completedDateTime}"
                                                            pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" type="both" />
                                                        <fmt:formatDate pattern="yyyy.MM.dd HH:mm"
                                                            value="${parseDateTime}" />    
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${tester.testState==2}">
                                                        <form>
                                                            <a class="btn btn-sm btn-info" href="../tester/downloadPersonalReport?testerId=${tester.id}">개인 리포트</button>
                                                        </form>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <form:form method="post" action="../tester/delete?id=${tester.id}">
                                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                            <a href="../tester/update?id=${tester.id}"
                                                                class="btn btn-sm btn-success">수정</a>
                                                            <button type="button" class="btn btn-sm btn-danger"
                                                                onclick="deleteTesterConfirm(this)">삭제</button>
                                                        </sec:authorize>

                                                        <sec:authorize access="!hasRole('ROLE_ADMIN')">
                                                            <c:if test="${tester.testState == 0}">
                                                                <a href="../tester/update?id=${tester.id}"
                                                                    class="btn btn-sm btn-success">수정</a>
                                                                <button type="button" class="btn btn-sm btn-danger"
                                                                    onclick="deleteTesterConfirm(this)">삭제</button>
                                                            </c:if>
                                                        </sec:authorize>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="text-right">
                                    <a href="../group/index" class="btn btn-secondary float-left">채용 그룹 목록</a>
                                    <a href="../tester/post?groupId=${groupId}" class="btn btn-primary">추가</a>
                                </div>
                                <div>
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${pageNo <= pageCount}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <c:if test="${pageNo > pageCount}">
                                                <li class="page-item">
                                                    <a class="page-link"
                                                        href="../tester/index?groupId=${groupId}&pageNo=${previusPageNo}&row=${row}"
                                                        aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
                                                <c:if test="${pageNo == i}">
                                                    <li class="page-item active">
                                                        <a class="page-link"
                                                        href="../tester/index?groupId=${groupId}&pageNo=${i}&row=${row}">${i}</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${pageNo != i}">
                                                    <li class="page-item">
                                                        <a class="page-link"
                                                        href="../tester/index?groupId=${groupId}&pageNo=${i}&row=${row}">${i}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${pageNo == totalPageNo}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pageNo != totalPageNo}">
                                                <li class="page-item">
                                                    <a class="page-link"
                                                        href="../tester/index?groupId=${groupId}&pageNo=${nextPageNo}&row=${row}"
                                                        aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
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
    <script>
        $("#tester-row").change(function(){
            location.href="/tester/index?groupId=${groupId}&pageNo=" + '${pageNo}' + "&row=" + this.value;
        });
    </script>
</body>

</html>