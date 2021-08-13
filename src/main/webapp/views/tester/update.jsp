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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
        <div class="container-fluid">
            <!--Contents-->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h4>지원자 수정</h4>
                            <form:form method="post" action="../tester/update" modelAttribute="tester" id="tester-post-form"
                                autocomplete="off">
                                <form:input type="hidden" path="group" />
                                <form:input type="hidden" path="id" />
                                <c:set var="applyIdHasBindError">
                                    <form:errors class="text-danger" path="applyId" />
                                </c:set>

                                <c:set var="nameHasBindError">
                                    <form:errors class="text-danger" path="name" />
                                </c:set>

                                <c:set var="birthdayHasBindError">
                                    <form:errors class="text-danger" path="birthday" />
                                </c:set>

                                <c:set var="phoneHasBindError">
                                    <form:errors class="text-danger" path="phone" />
                                </c:set>

                                <div class="form-group">
                                    <label>지원자 ID<span class="text-danger">*</span></label>
                                    <form:input type="text"
                                        class="form-control ${not empty applyIdHasBindError?'is-invalid':''}"
                                        path="applyId" placeholder="지원자 ID 입력" autofocus="autofocus" maxlength="10"
                                        onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
                                    ${applyIdHasBindError}
                                </div>

                                <div class="form-group">
                                    <label>지원자 이름<span class="text-danger">*</span></label>
                                    <form:input type="text"
                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}" path="name"
                                        placeholder="지원자 이름 입력" />
                                    ${nameHasBindError}
                                </div>

                                <div class="form-group">
                                    <label>성별<span class="text-danger">*</span></label>
                                    <form:select path="gender" class="form-control">
                                        <form:option value="1" label="남" />
                                        <form:option value="0" label="여" />
                                    </form:select>
                                    <form:errors class="text-danger" path="gender" />
                                </div>

                                <div class="form-group">
                                    <label>생년월일<span class="text-danger">*</span></label>
                                    <form:input type="text"
                                        class="form-control ${not empty birthdayHasBindError?'is-invalid':''}"
                                        path="birthday" placeholder="생년월일 입력" maxlength="8"
                                        onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
                                    ${birthdayHasBindError}
                                </div>

                                <div class="form-group">
                                    <label>휴대폰<span class="text-danger">*</span></label>
                                    <form:input type="text"
                                        class="form-control ${not empty phoneHasBindError?'is-invalid':''}" path="phone"
                                        placeholder="휴대폰 입력" />
                                    ${phoneHasBindError}
                                </div>

                                <c:if test="${not empty errorMessage}">
                                    <font color="red">
                                        <c:out value="${errorMessage}" />
                                    </font>
                                </c:if>

                                <div class="text-right">
                                    <a href="../tester/index?groupId=${groupId}"
                                        class="btn btn-secondary float-left">목록</a>
                                    <button type="button" class="btn btn-primary" id="tester-btn-submit" onclick="submit(this)">등록</button>
                                </div>
                            </form:form>
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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $("#birthday").datepicker({
            dateFormat: "yymmdd",
            prevText: "이전 달",
            nextText: "다음 달",
            monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            dayNames: ["일", "월", "화", "수", "목", "금", "토"],
            dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
            dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
            showMonthAfterYear: true,
            changeYear: true,
            yearRange: 'c-70:c+1',
            yearSuffix: "년"
        });

        $(document).ready(function () {
            $(".is-invalid").eq(0).focus();
        });

        function submit(btn) {
            $(btn).attr('disabled', true);
            $(btn).parent("form:first").submit();
        } 
    </script>
</body>

</html>