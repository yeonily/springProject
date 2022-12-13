
let replyService = (function () {
    function replyAdd(replyDTO, callback, error) {
        $.ajax({
            url :"/reply/new",
            type : "post",
            data: JSON.stringify(replyDTO),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });
    }

    function getList(param, callback, error) {
        $.ajax({
            url: "/reply/list/" + param,
            type: "get",
            success: function (replyDTO, status, xhr) {
                if(callback){
                    callback(replyDTO);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function remove(replyId, callback, error){
        $.ajax({
            url: "/reply/" + replyId,
            type: "delete",
            success: function(text, status, xhr){
                if(callback){
                    callback(text);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getTotal(boardId, callback, error){
        $.ajax({
            url: "/reply/" + boardId,
            type: "post",
            // data: boardId,
            success: function(reply, status, xhr){
                if(callback){
                    callback(reply);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }



    // function replyUpdate(reply, callback, error) {
    //     $.ajax({
    //         url :"/reply/" + reply.replyId,
    //         type : "patch",
    //         data: JSON.stringify(reply),
    //         success: function (text) {
    //             if(callback){
    //                 callback(text);
    //             }
    //         },
    //         error: function (xhr, status, err) {
    //             if(error){
    //                 error(err);
    //             }
    //         }
    //     });
    // }





    return {replyAdd: replyAdd, getList: getList, remove: remove, getTotal: getTotal, timeForToday:timeForToday}
})();