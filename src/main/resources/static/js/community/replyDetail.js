

    $("r-input").on("click", function () {
        console.log("댓글작성");
        saveReply();
    })
    function saveReply() {
        replyService.replyAdd({
            memberId: $("#sessionMember").val(),
            replyId: replyId,
            boardId: $("#listMember").val(),
        }, function () {
            
            show();
        });
    }



//==============================
// $reviewSubmitBtn.on("click", function(){
//     console.log("리뷰작성");
//     saveReview();
// })
//
// function saveReview(){
//     reviewService.save({
//         productId: productId,
//         memberId: 1,
//         reviewContent : $reviewContent.val(),
//         reviewFileName: $("input[name='uploadFile']").val(),
//         reviewStar: globalThis.starRateTemp,
//         reviewFilePath: $("input[name='reviewFilePath']").val(),
//         reviewFileUuid: $("input[name='reviewFileUuid']").val(),
//     }, function(){
//         $reviewContent.val('');
//         console.log("리뷰 작성 성공");
//         showReview();
//     });
// }