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

    function getAbList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/albalist",
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

    function saveMentor(mentorDTO, callback, error) {
        $.ajax({
            url: "/mypage/settingMentor",
            type: "put",
            data: JSON.stringify(mentorDTO),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getTotal(boardId, callback, error) {
        $.ajax({
            url: "/reply/" + boardId,
            type: "post",
            data: JSON.stringify(boardId),
            contentType: "application/json; charset=utf-8",
            success: function (reply, status, xhr) {
                if (callback) {
                    callback(reply);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getBoList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/boardlist",
            type: "get",
            success: function (boards, status, xhr) {
                console.log("getBoList - "+ boards);
                if (callback) {
                    callback(boards);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getBoReply(memberId, callback, error) {
        $.ajax({
            url: "/mypage/boardlist",
            type: "post",
            success: function (replyCount, status, xhr) {
                console.log("getBoList - "+ replyCount);
                if (callback) {
                    callback(replyCount);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function getIqList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/inquirelist",
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
    return {getPgList: getPgList, getAbList: getAbList, getBoList:getBoList, getIqList:getIqList, getBoReply:getBoReply}
})();
