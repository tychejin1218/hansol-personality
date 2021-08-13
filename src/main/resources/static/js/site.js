// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.

function deleteGroupConfirm(btn) {
    if (confirm("선택한 채용 그룹을 삭제하시겠습니까?")) {
        $(btn).parent("form:first").submit();
    }
}

function deleteTesterConfirm(btn) {
    if (confirm("선택한 지원자를 삭제하시겠습니까?")) {
        $(btn).parent("form:first").submit();
    }
}

function deleteHqConfirm(btn) {
    if (confirm("선택한 본부를 삭제하시겠습니까?")) {
        $(btn).parent("form:first").submit();
    }
}

function deleteRegionConfirm(btn) {
    if (confirm("선택한 지역국을 삭제하시겠습니까?")) {
        $(btn).parent("form:first").submit();
    }
}

function deleteDepartmentConfirm(btn) {
    if (confirm("선택한 부서를 삭제하시겠습니까?")) {
        $(btn).parent("form:first").submit();
    }
}

function confirmEndOrientation(btn) {
    if (confirm("오리엔테이션이 종료 됩니다. 정말 검사를 시작하시겠습니까?")) {
        // location.href="/test";
        $(btn).parent("form:first").submit();
    }
}

// 응답 1 응답데이터 저장
function updateMark1(pageNo, questionId, markValue) {

    var form = {
        "testerId": 0,
        "normPersonalityId": 0,
        "questionId": questionId,
        "pageNo": pageNo,
        "mark1": markValue.toString()
    };

    $.ajax({
        url: '/api/TempMark1',
        method: 'POST',
        type: 'json',
        data: JSON.stringify(form),
        contentType: "application/json; charset=utf-8",
        beforeSend: function (jqXHR) { },
        success: function (jqXHR) {
        },
        error: function (jqXHR) {
            alert("응답 데이터 저장에 실패하였습니다. 다시 시도하세요.");
        },
        complete: function (jqXHR) { }
    });
}

// 응답 2 응답데이터 저장
function updateMark2(questionSetId, questionId, markValue, isFar) {
    if (isFar) {
        // 멀다 클릭시
        $(".mark2-near" + questionId).prop("checked", false);
    } else {
        // 가깝다 클릭시
        $(".mark2-far" + questionId).prop("checked", false);
    }

    $.ajax({
        url: "/api/TempMark2?questionSetId=" + questionSetId + "&questionId=" + questionId + "&markValue=" + markValue,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        beforeSend: function (jqXHR) { },
        success: function (jqXHR) {
        },
        error: function (jqXHR) {
            alert("응답 데이터 저장에 실패 하였습니다. 관리자에게 문의 바랍니다.");
        },
        complete: function (jqXHR) { }
    });
}

function updateMark1s() {
    var firstQuestionId = $("#first-question-id").val();
    var lastQuestionId = $("#last-question-id").val();
    var pageNo = $("#page-no").val();

    var data = [];

    for (var i = firstQuestionId; i <= lastQuestionId; i++) {
        var markValue = "";

        for (var j = 0; j < 5; j++) {
            if ($('input[name="mark1-' + i + '"]').eq(j).is(':checked')) {
                markValue = (j + 1).toString();
                break;
            }
        }
        data.push({
            "TesterId": 0,
            "PageNo": pageNo,
            "NormId": 0,
            "QuestionId": i.toString(),
            "Mark1": markValue.toString(),
            "Comment": $("#mark1-comment-" + i).val()
        });
    }

    $.ajax({
        url: '/api/TempMark1s',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        beforeSend: function (jqXHR) { },
        success: function (jqXHR) {
        },
        error: function (jqXHR) {
            console.log("응답 데이터 저장에 실패하였습니다. 다시 시도하세요.");
        },
        complete: function (jqXHR) { }
    });
}

// 다음 페이지로 이동
function next(btn) {
    var markCheckResult = markCheck();
    if (markCheckResult) {
        // updateMark1s();
        $(btn).parent("form:first").submit();
    }
}

// 종료 페이지로 이동
function end() {
    var markCheckResult = markCheck();
    if (markCheckResult) {
        // updateMark1s();
        // if (confirm("모든 문항에 응답 하셨습니다. \n\n \"확인\"을 누르면 검사가 종료됩니다.")) {
        //     // $(btn).parent("form:first").submit();
        //     location.href="/end";
        // }

        alert("검사가 완료되었습니다.\n고생하셨습니다.");
        location.href = "/end";
    }
}

// 응답 체크
function markCheck() {
    var firstQuestionId = $("#first-question-id").val();
    var lastQuestionId = $("#last-question-id").val();
    var firstQuestionSetId = $("#first-question-set-id").val();
    var lastQuestionSetId = $("#last-question-set-id").val();
    var pageNo = $("#page-no").val();

    // 응답 1 정상 응답 확인
    for (var i = firstQuestionId; i <= lastQuestionId; i++) {
        console.log(i);
        if (!$("input[name=mark1-" + i).is(":checked")) {
            alert("[응답 1] 문항군 " + $(".question-no-" + i + "-with-set-id").val() + "번에 문항번호 " + $(".question-no-" + i).val() + "를 응답하지 않았습니다.");
            $("input[name=mark1-" + i).focus();
            return false;
        }
    }
    var isChecked = false;

    // 응답 2 정상 응답 확인
    for (var j = firstQuestionSetId; j <= lastQuestionSetId; j++) {
        isChecked = false;

        if ($("input[name=mark2-far" + j).is(":checked")) {
            isChecked = true;
        }
        if (isChecked === false) {
            alert("[응답 2] 문항군 " + j + " \"가장 멀다\"에 응답되지 않았습니다.");
            return false;
        }
        isChecked = false;

        if ($("input[name=mark2-near" + j).is(":checked")) {
            isChecked = true;
        }

        if (isChecked === false) {
            alert("[응답 2] 문항군 " + j + " \"가장 가깝다\"에 응답되지 않았습니다.");
            return false;
        }
    }
    return true;
}

function confirmLogout() {
    if (confirm("정말 검사를 일시 종료하고 로그아웃을 하시겠습니까?")) {
        location.href = "/";
    }
}

function confirmResetPassword(userId) {
    if (confirm("선택한 관리자의 비밀번호를 초기화 하시겠습니까? \n- 초기화 비밀번호 : hansol + 해당 연도\n- 예: 올해가 20년인 경우 hansol2020")) {
        location.href = "/user/resetPassword?id=" + userId;
    }
}
