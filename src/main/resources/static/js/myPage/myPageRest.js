/*
* myPage/setting.html
* */


let myPageService = (function () {
    function replyAdd(replyDTO, callback, error) {
        alert("들어옴2");
        $.ajax({
            url: "/reply/new",
            type: "post",
            data: JSON.stringify(replyDTO),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    console.log(result)
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

    function getInfo(memberId, callback, error) {
        getTotal();
        $.ajax({
            url: "/mypage/setting/" + memberId,
            type: "get",
            success: function (memberDTO, status, xhr) {
                if (callback) {
                    callback(memberDTO);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function remove(replyId, callback, error) {
        $.ajax({
            url: "/reply/" + replyId,
            type: "delete",
            success: function (text) {
                if (callback) {
                    callback(text);
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

})