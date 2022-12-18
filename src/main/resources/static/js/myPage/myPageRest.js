/*
* myPage/setting.html
* */


let myPageService = (function () {

    function getPgList(memberId, callback, error){
        $.ajax({
            url: "/mypage/programlist",
            type: "get",
            success: function(programs, status, xhr){
                console.log("getPgList - "+ programs);
                if(callback){
                    callback(programs);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getAbList(param, callback, error) {
        $.ajax({
            url: "/mypage/albalist/" + (param.page || 0),
            type: "get",
            success: function (albas, status, xhr) {
                console.log("getAbList - "+ albas);
                if (callback) {
                    callback(albas);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getBoList(param, callback, error) {
        $.ajax({
            url: "/mypage/boardlist/" + (param.page || 0),
            type: "get",
            success: function (boards, status, xhr) {
                console.log("getBoList - "+ boards);
                if (callback) {
                    callback(boards);
                    console.log("getBoList >> "+ boards);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }


    function getIqList(param, callback, error) {
        $.ajax({
            url: "/mypage/inquirelist/" + (param.page || 0),
            type: "get",
            success: function (inquires, status, xhr) {
                console.log("getIqList - "+ inquires);
                if (callback) {
                    callback(inquires);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }


    function getApplyPgList(memberId, callback, error){
        $.ajax({
            url: "/mypage/program/applylist",
            type: "get",
            success: function(programs, status, xhr){
                console.log("getApplyPgList - "+ programs);
                if(callback){
                    callback(programs);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getApplyAbList(param, callback, error) {
        $.ajax({
            url: "/mypage/alba/applylist/" + (param.page || 0),
            type: "get",
            success: function (albas, status, xhr) {
                console.log("getApplyAbList - "+ albas);
                if (callback) {
                    callback(albas);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getApplyPayList(param, callback, error) {
        $.ajax({
            url: "/mypage/paylist/" + (param.page || 0),
            type: "get",
            success: function (memberPrograms, status, xhr) {
                console.log("getApplyPayList - "+ memberPrograms);
                if (callback) {
                    callback(memberPrograms);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getMentorList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/mentorlist",
            type: "get",
            success: function (mentors, status, xhr) {
                console.log("getMentorList - "+ mentors);
                if (callback) {
                    callback(mentors);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getMenteeList(status, callback, error) {
        $.ajax({
            url: "/mypage/mentee",
            type: "post",
            data : {status: status},
            contentType: "application/json; charset=utf-8",
            success: function (mentees, status, xhr) {
                console.log("getMenteeList - "+ mentees);
                if (callback) {
                    callback(mentees);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getMenteeListConfirm(status, callback, error) {
        $.ajax({
            url: "/mypage/menteelist/confirm",
            type: "post",
            data : {status: status},
            contentType: "application/json; charset=utf-8",
            success: function (mentees, status, xhr) {
                console.log("getMenteeListConfirm - "+ mentees);
                if (callback) {
                    callback(mentees);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            },
            async: false
        });
    }

    function getMenteeListOther(status, callback, error) {
        $.ajax({
            url: "/mypage/menteelist/other",
            type: "post",
            data : {status: $("input[name=applyStatus]").val()},
            contentType: "application/json; charset=utf-8",
            success: function (mentees, status, xhr) {
                console.log("getMenteeListOther - "+ mentees);
                if (callback) {
                    callback(mentees);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            },
            async: false
        });
    }

    return {getPgList: getPgList, getAbList: getAbList, getBoList:getBoList, getIqList:getIqList, getApplyPgList:getApplyPgList,
        getApplyAbList:getApplyAbList, getApplyPayList:getApplyPayList, getMentorList:getMentorList, getMenteeList:getMenteeList,
        getMenteeListConfirm:getMenteeListConfirm, getMenteeListOther:getMenteeListOther}
})();
