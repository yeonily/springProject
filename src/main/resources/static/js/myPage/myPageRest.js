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

    function getBoList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/boardlist",
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

    function getApplyAbList(memberId, callback, error) {
        $.ajax({
            url: "/mypage/alba/applylist",
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

    return {getPgList: getPgList, getAbList: getAbList, getBoList:getBoList, getIqList:getIqList, getApplyPgList:getApplyPgList, getApplyAbList:getApplyAbList}
})();
