
let replyService = (function () {
    function replyAdd(reply, callback, error) {
        $.ajax({
            url :"/reply/new",
            type : "post",
            data: JSON.stringify(reply),
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
            success: function(text){
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



    return {replyAdd: replyAdd, getList: getList, remove: remove}
})();