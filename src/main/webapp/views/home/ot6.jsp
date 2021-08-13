<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
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
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div style="padding-left: 100px;padding-right: 100px;">
                                <h3 style="color:#f7b719; border-bottom: 2px solid #f7b719;">응답연습</h1>
                                    <div style="background-color: #f2f2f2;padding:20px">
                                        <p style="font-size: 25px; font-weight: lighter;">숙지하신 응답요령을 바탕으로 하단 문항의 응답 1에
                                            응답해 보시기 바랍니다.</p>
                                        <h4 style="font-weight: bold;">응답이 잘못 되었을 경우, 응답요령을 다시 한번 정확히 숙지하시고 다음 페이지로
                                            넘어가십시오.</h4>
                                    </div>
                                    <table class="table table-hover table-bordered mt-5 mb-5">
                                        <thead>
                                            <tr>
                                                <th rowspan="2" class="text-center" style="width:70px">문항군<br />번호</th>
                                                <th rowspan="2" class="text-center" style="width:50px">문항<br />번호</th>
                                                <th colspan="1" rowspan="2" class="text-center">문항</th>
                                                <th colspan="5" class="text-center">응답1</th>
                                                <th colspan="2" class="text-center" style="background-color:grey">응답2
                                                </th>
                                            </tr>

                                            <tr class="text-center">
                                                <th style="width:80px">전혀<br />그렇지<br />않다</th>
                                                <th style="width:80px">그렇지<br />않다</th>
                                                <th style="width:80px">보통이다</th>
                                                <th style="width:80px">그렇다</th>
                                                <th style="width:80px">매우<br />그렇다</th>
                                                <th style="width:80px;background-color:grey">가장<br />멀다</th>
                                                <th style="width:80px;background-color:grey">가장<br />가깝다</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td rowspan="3" class="text-center">1</td>
                                                <td class="text-center">
                                                    A
                                                    <input type="hidden" class="question-no-1" value="A"/>
                                                </td>
                                                <td>웬만하선 시간 약속에 늦는 법이 없다</td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-1"
                                                            onclick="updateMark1(1, 1, 1)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-1"
                                                            onclick="updateMark1(1, 1, 2)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-1"
                                                            onclick="updateMark1(1, 1, 3)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-1"
                                                            onclick="updateMark1(1, 1, 4)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-1"
                                                            onclick="updateMark1(1, 1, 5)" />
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input mark2-far1"
                                                            name="mark2-far1" onclick="updateMark2(1, 1, 1, true)" disabled/>
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio"
                                                            class="form-check-input mark2-near1 disalbed"
                                                            name="mark2-near1" onclick="updateMark2(1, 1, 2, false)" disabled/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    B
                                                    <input type="hidden" class="question-no-2" value="B"/>
                                                </td>
                                                <td>문학보다 수학 공부를 좋아한다</td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-2"
                                                            onclick="updateMark1(1, 2, 1)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-2"
                                                            onclick="updateMark1(1, 2, 2)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-2"
                                                            onclick="updateMark1(1, 2, 3)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-2"
                                                            onclick="updateMark1(1, 2, 4)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-2"
                                                            onclick="updateMark1(1, 2, 5)" />
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input mark2-far1"
                                                            name="mark2-far1" onclick="updateMark2(1, 2, 1, true)" disabled/>
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio"
                                                            class="form-check-input mark2-near1"
                                                            name="mark2-near1" onclick="updateMark2(1, 2, 2, false)" disabled/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    C
                                                    <input type="hidden" class="question-no-3" value="C"/>
                                                </td>
                                                <td>격렬한 스포츠 활동을 즐기는 편이다</td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-3"
                                                            onclick="updateMark1(1, 3, 1)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-3"
                                                            onclick="updateMark1(1, 3, 2)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-3"
                                                            onclick="updateMark1(1, 3, 3)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-3"
                                                            onclick="updateMark1(1, 3, 4)" />
                                                    </div>
                                                </td>
                                                <td class="text-center">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input" name="mark1-3"
                                                            onclick="updateMark1(1, 3, 5)" />
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio" class="form-check-input mark2-far1"
                                                            name="mark2-far1" onclick="updateMark2(1, 3, 1, true)" disabled/>
                                                    </div>
                                                </td>
                                                <td class="text-center" style="background-color:grey">
                                                    <div class="form-check-inline align-middle text-center">
                                                        <input type="radio"
                                                            class="form-check-input mark2-near1"
                                                            name="mark2-near1" onclick="updateMark2(1, 3, 2, false)" disabled/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                            </div>
                            <hr />
                            <div>
                                <a class="btn btn-secondary" href="../ot5">이전</a>
                                <button class="btn btn-primary float-right text-white" onclick="markCheck()">다음</button>
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
    <script>
        function markCheck() {
            var firstQuestionId = 1;
            var lastQuestionId = 3;
            var firstQuestionSetId = 1;
            var lastQuestionSetId = 1;

            for (var i = firstQuestionId; i <= lastQuestionId; i++) {

                if (!$("input[name=mark1-" + i).is(":checked")) {
                    alert("응답 1에 체크하지 않은 문항이 있습니다. \n응답 1은 A, B, C 3개의 개별 문항 각각에 대해 모두 응답해야 합니다. 다시 한 번 확인하십시오.");
                    $("input[name=mark1-" + i).focus();
                    return;
                }
            }
            alert("응답 1에 정확히 응답하셨습니다. 다음 페이지로 이동합니다.");
            location.href="/ot7";
        }
    </script>
</body>

</html>