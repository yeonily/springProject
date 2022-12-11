/*
* myPage/setting.html
* */


let myPageService = (function () {

    function getPgList(param, callback, error){
        $.ajax({
            url: "/mypage/program" ,
            type: "get",
            success: function(programDTO, status, xhr){
                if(callback){
                    callback(programDTO);
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
            url: "/mypage/alba",
            type: "get",
            success: function (albaDTO, status, xhr) {
                if (callback) {
                    callback(albaDTO);
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