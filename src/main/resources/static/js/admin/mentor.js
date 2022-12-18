/*
* admin/mentor.html
* */

let mentorService = (function () {
    function getList(param, callback, error) {
        $.ajax({
            url : "/admin/mentee/list/" + param,
            type : "get",
            success : function (mentees, status, xhr) {
                if (callback) {
                    callback(mentees);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }
    return {getList: getList};
})();