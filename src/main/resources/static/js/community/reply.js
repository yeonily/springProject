
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
    return {replyAdd: replyAdd}
})();