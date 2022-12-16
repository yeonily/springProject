var $status = $(".list_td .title span"); // 모집 상태를 저장


/*-----------------------------------------------------------*/
                    //필터 선택 시 검정글자로 표시
/*-----------------------------------------------------------*/
$(".top-div-filter span").on("click", function() {
    $(this).css("color", "#000000");
    $(".top-div-filter span").not(this).css({color: '#959595'});
});


/*-----------------------------------------------------------*/
                    //모집마감일 경우 글씨 어둡게
/*-----------------------------------------------------------*/
for(var i = 0; i < $status.length; i++) {
    if($status.eq(i).text() == "모집마감") {
        $status.eq(i).parent().parent().parent().css("color", "#dbdbdb");
        $status.eq(i).parent().css("color", "#dbdbdb");
    }
}

/*-----------------------------------------------------------*/
                        // REST
/*-----------------------------------------------------------*/

let albaService = (function(){
    /* 알바 8개 리스트 */
    function mainList(main, callback, error){
        $.ajax({
            url: "/alba/list/main",
            type: "post",
            success: function (code,status,xhr){
                if (callback){
                    callback(code);
                }
            },
            error: function (xhr,status,err){
                if (error){
                    error(err);
                }
            }
        });
    }
    /* 알바 게시글 수 */
    function countList(list, callback, error){
        $.ajax({
            url: "/alba/list/count",
            type: "post",
            success: function (count,status,xhr){
                if(callback){
                    callback(count);
                }
            },
            error:function (xhr, status, err){
                console.log(xhr, status, err);
                if(error){
                    error(err);
                }
            }
        });
    }
    /* 최신순 */
    function mainNewList(param, callback, error){
        $.ajax({
            url: "/alba/list/newlist/" + (param.page || 0),
            type: "get",
            success: function (alba, status, xhr){
                console.log("alba : " + alba);
                if(callback){
                    callback(alba);
                }
            },
            error: function (xhr, status, err){
                console.log(xhr, status, err);
                if(error){
                    error(err);
                }
            }
        });
    }
    /* 최신순 */
    function newList(param, callback, error){
        $.ajax({
            url: "/alba/list/newlist/" + (param.page || 0),
            type: "get",
            success: function (result, status, xhr){
                console.log("result : " + result);
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, err){
                console.log(xhr, status, err);
                if(error){
                    error(err);
                }
            }
        });
    }
    /* 시급순 */
    function payList(param, callback, error){
        $.ajax({
            url: "/alba/list/paylist/" + (param.page || 0),
            type: "get",
            success: function (albaDTO, status, xhr){
                console.log("albaDTO : " + albaDTO);
                console.log(status);
                console.log(xhr);
                if(callback){
                    callback(albaDTO);
                }
            },
            error: function (xhr, status, err){
                console.log(xhr, status, err);
                if(error){
                    error(err);
                }
            }
        });
    }
    /* 마감순 */
    function endList(param, callback, error){
        $.ajax({
            url: "/alba/list/endlist/" + (param.page || 0),
            type: "get",
            success: function (list, status, xhr){
                console.log("list : " + list);
                console.log(status);
                console.log(xhr);
                if(callback){
                    callback(list);
                }
            },
            error: function (xhr, status, err){
                console.log(xhr, status, err);
                if(error){
                    error(err);
                }
            }
        });
    }
    return {mainList : mainList, countList : countList, mainNewList : mainNewList, newList : newList, payList: payList, endList : endList}
})();