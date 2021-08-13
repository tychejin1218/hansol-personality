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
    <link rel="shortcut icon" type="text/css" href="/resources/static/favicon.ico">
    <!-- <link rel="stylesheet" type="text/css" href="/resources/static/css/morris.css"> -->
    <!-- <link rel="stylesheet" type="text/css" href="//cdn.materialdesignicons.com/3.6.95/css/materialdesignicons.min.css"> -->
    <link rel="stylesheet" type="text/css" href="/resources/static/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" type="text/css" href="/resources/static/css/icons.css"> -->
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css">
    <style>
        input[type=radio] {
            /* Double-sized Checkboxes */
            -ms-transform: scale(1.5);
            /* IE */
            -moz-transform: scale(1.5);
            /* FF */
            -webkit-transform: scale(1.5);
            /* Safari and Chrome */
            -o-transform: scale(1.5);
            /* Opera */
            transform: scale(1.5);
            padding: 10px;
            margin-right: 0;
        }

        .form-check-inline,
        .form-check-input {
            margin-right: 0 !important;
        }
    </style>
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
            <div class="row" style="min-width:1300px">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <input type="hidden" id="first-question-id" value="${vm.questions.get(0).getId()}" />
                            <input type="hidden" id="last-question-id"
                                value="${vm.questions.get(vm.questions.size() - 1).getId()}" />
                            <input type="hidden" id="first-question-set-id"
                                value="${vm.questions.get(0).getQuestionSetId()}" />
                            <input type="hidden" id="last-question-set-id"
                                value="${vm.questions.get(vm.questions.size() - 1).getQuestionSetId()}" />
                            <input type="hidden" id="time" value="${vm.getTime()}" />

                            <div>
                                <h5 class="text-center text-danger" id="time">
                                    남은시간 <span class="text-danger">
                                        <input type="text" class="text-danger text-right" id="minutes" value=""
                                            style="background: none; border: none; width: 30px;" readonly><span
                                            class="text-danger"> 분</span>
                                        <input class="text-danger text-right" id="seconds" value=""
                                            style="background: none; border: none; width: 30px;" readonly><span
                                            class="text-danger"> 초</span>
                                    </span>
                                </h5>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th rowspan="2" class="text-center" style="width:70px">문항군<br />번호</th>
                                            <th rowspan="2" class="text-center" style="width:50px">문항<br />번호</th>
                                            <th colspan="1" rowspan="2" class="text-center">문항</th>
                                            <th colspan="5" class="text-center">응답1</th>
                                            <th colspan="2" class="text-center">응답2</th>
                                        </tr>

                                        <tr class="text-center">
                                            <th style="width:80px">전혀<br />그렇지<br />않다</th>
                                            <th style="width:80px">그렇지<br />않다</th>
                                            <th style="width:80px">보통이다</th>
                                            <th style="width:80px">그렇다</th>
                                            <th style="width:80px">매우<br />그렇다</th>
                                            <th style="width:80px">가장<br />멀다</th>
                                            <th style="width:80px">가장<br />가깝다</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="question" items="${vm.questions}" varStatus="status">
                                            <c:if test="${status.index % 3 == 0 }">
                                                <tr>
                                                    <td colspan="11"></td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <c:if test="${question.getQuestionId()%3 == 1}">
                                                    <td rowspan="3" class="text-center">${question.getQuestionSetId()}
                                                    </td>
                                                </c:if>
                                                <td class="text-center">
                                                    ${question.getQuestionChar()}
                                                    <input type="hidden" class="question-no-${question.getId()}-with-set-id"
                                                        value="${question.getQuestionSetId()}" />
                                                    <input type="hidden" class="question-no-${question.getId()}"
                                                        value="${question.getQuestionChar()}" />
                                                </td>
                                                <td class="text-center">${question.getText()}</td>
                                                <c:forEach var="num" begin="1" end="5" step="1">
                                                    <td class="text-center">
                                                        <div class="form-check-inline align-middle text-center">
                                                            <c:if
                                                                test="${vm.tempMark1s.get(status.index).getMark1() == num.toString()}">
                                                                <input type="radio" class="form-check-input"
                                                                    name="mark1-${question.getId()}"
                                                                    onclick="updateMark1('${question.getPageNo()}', '${question.getId()}', '${num}')"
                                                                    checked />
                                                            </c:if>
                                                            <c:if
                                                                test="${empty vm.tempMark1s.get(status.index).getMark1() || vm.tempMark1s.get(status.index).getMark1() != num.toString() }">
                                                                <input type="radio" class="form-check-input"
                                                                    name="mark1-${question.getId()}"
                                                                    onclick="updateMark1('${question.getPageNo()}', '${question.getId()}', '${num}')" />
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </c:forEach>
                                                <td class="text-center" style="background-color:#f5f6fa">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <c:if
                                                            test="${vm.tempMark2s.get(status.index).getMark2() == '1'}">
                                                            <input type="radio"
                                                                class="form-check-input mark2-far${question.getId()}"
                                                                name="mark2-far${question.getQuestionSetId()}"
                                                                onclick="updateMark2('${question.getQuestionSetId()}', '${question.getId()}', 1, true)"
                                                                checked />
                                                        </c:if>
                                                        <c:if
                                                            test="${vm.tempMark2s.get(status.index).getMark2() != '1'}">
                                                            <input type="radio"
                                                                class="form-check-input mark2-far${question.getId()}"
                                                                name="mark2-far${question.getQuestionSetId()}"
                                                                onclick="updateMark2('${question.getQuestionSetId()}', '${question.getId()}', 1, true)" />
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:#f5f6fa">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <c:if
                                                            test="${vm.tempMark2s.get(status.index).getMark2() == '2'}">
                                                            <input type="radio"
                                                                class="form-check-input mark2-near${question.getId()}"
                                                                name="mark2-near${question.getQuestionSetId()}"
                                                                onclick="updateMark2('${question.getQuestionSetId()}', '${question.getId()}', 2, false)"
                                                                checked />
                                                        </c:if>
                                                        <c:if
                                                            test="${vm.tempMark2s.get(status.index).getMark2() != '2'}">
                                                            <input type="radio"
                                                                class="form-check-input mark2-near${question.getId()}"
                                                                name="mark2-near${question.getQuestionSetId()}"
                                                                onclick="updateMark2('${question.getQuestionSetId()}', '${question.getId()}', 2, false)" />
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>

                                        </c:forEach>
<!-- 
                                        <tr style="background-color:white;">
                                            <td colspan="8">&nbsp;</td>
                                            <td colspan="2">응답2에는 3개 문항 중 나와 가장 가까운 문항, 가장 먼 문항을 골라주세요.</td>
                                        </tr> -->
                                    </tbody>
                                </table>
                            </div>
                            <div>
                                <!-- <c:if test="${vm.getPageNo() == 1}">
                                    <form method="post" class="float-left form-inline">
                                        <button type="button" class="btn btn-outline-secondary"
                                            onclick="alert('검사 첫 페이지 입니다.')">첫 페이지</button>
                                    </form>
                                </c:if>

                                <c:if test="${vm.getPageNo() > 1}">
                                    <form:form method="post" class="float-left form-inline"
                                        action="../test?pageNo=${vm.getPrevPageNo()}">
                                        <button type="submit" class="btn btn-secondary">이전</button>
                                    </form:form>
                                </c:if> -->

                                <c:if test="${vm.getIsLast() == true}">
                                    <!-- <form method="post" class="float-right form-inline" action="../end">
                                        <button type="button" class="btn btn-danger" onclick="end(this)">검사 완료</button>
                                    </form> -->
                                    <button type="button" class="btn btn-danger float-right" onclick="end()">검사 완료</button>
                                </c:if>

                                <c:if test="${vm.getIsLast() == false}">
                                    <form:form method="post" class="float-right form-inline"
                                        action="../test?pageNo=${vm.getNextPageNo()}">
                                        <button type="button" class="btn btn-primary" onclick="next(this)">다음</button>
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
    <!-- <script src="/resources/static/js/modernizr.min.js"></script>
    <script src="/resources/static/js/waves.js"></script>
    <script src="/resources/static/js/jquery.slimscroll.js"></script> -->
    <!-- <script src="/resources/static/js/app.js"></script> -->
    <script src="/resources/static/js/site.js"></script>
    <script>
        var timerId = setInterval(TimeCount, 1000);
        function TimeCount() {
            var time = Number($("#time").val());

            if (time > 0) {
                $("#minutes").val(Math.floor(time / 60));

                if (time % 60 < 10) {
                    $("#seconds").val("0" + time % 60);
                } else {
                    $("#seconds").val(time % 60);
                }

                $.ajax({
                    url: "/api/updateTime?time=" + time,
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    success: function () { }
                });
            } else {
                $.ajax({
                    url: "/api/updateTime?time=" + 0,
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    success: function (result) {
                        alert("제한시간이 모두 소진 되었습니다.\n확인 버튼을 누르시면 검사가 종료됩니다.");
                        location.href = "/end";
                    }
                });
                clearInterval(timerId); // 타이머 중지
                return;
            }
            $("#time").val(time - 1);
        }
    </script>
</body>
</html>