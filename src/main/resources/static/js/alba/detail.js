/*-----------------------------------------------------------*/
                    /*지원하기쪽 위치 고정*/
/*-----------------------------------------------------------*/
const content = document.querySelector('.content');
const sidebox = document.querySelector('#main_fixed');
const contentTop = content.getBoundingClientRect().top + window.scrollY;

console.log("content : " + content);
console.log("sidebox : " + sidebox);
console.log("contentTop : " + contentTop);


window.addEventListener('scroll', function(){
    if(window.scrollY >= contentTop) {
        sidebox.classList.add('side_fixed');
    }else{
        sidebox.classList.remove('side_fixed');
    }

});

/*-----------------------------------------------------------*/
                        /*모집마감일 경우*/
/*-----------------------------------------------------------*/
var $status = $("#main_fixed_category"); // 모집 상태를 저장

if($("#main_fixed_category").text() == "모집마감" || $(".left-div span").text() == "모집마감") {
    $("#main_fixed_category").text("모집마감");
    $(".left-div span").text("모집마감");
    $("#main_fixed_button").attr("disabled", "false");
    $("#main_fixed_button").html("모집마감");
    $("#main_fixed_button").css({background : "#357e55", cursor : "default"});
}


/*-----------------------------------------------------------*/
                    /*지원하기 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#main_fixed_button").on("click", function(){
    $("#modal").show();
});

$("div.modalWrap").on("click","button.cancel" ,function(){
    $("#modal").hide();
});

/*메일전송 완료 버튼 클릭 시*/
// $("#modal button.applyBtn").on("click", function(){
//     $("#modalSuccess .m-title").text("아르바이트에 지원해주셔서 감사합니다!");
//     $("#modalSuccess .m-c-title").text("빠른 시일 내에 지원결과를 알려드리도록 하겠습니다.");
//     $("#modal").hide();
//     $("#modalSuccess").show();
// });



/*-----------------------------------------------------------*/
                    /*지원취소 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#cancel").on("click", function(){
    $("#cancelModal").show();
});
$("div.cancelWrap").on("click","button.cancel" ,function(){
    $("#cancelModal").hide();
});

/*예 버튼 클릭 시*/
// $("#cancelModal button.applyBtn").on("click", function(){
//     $("#cancelModal").hide();
//     $("#modalSuccess .m-title").css("text-align", "center");
//     $("#modalSuccess .m-title").text("아르바이트 지원을 정상적으로 취소하였습니다.");
//     $("#modalSuccess .m-c-title").text("기회가 되면 다시 지원해주세요.");
//     $("#modalSuccess").show();
// });


/*-----------------------------------------------------------*/
                    /*삭제하기 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#remove_button").on("click", function(){
    $("#removeModal").show();
});
$("#removeModal .cancel").on("click", function(){
    $("#removeModal").hide();
});

/*예 버튼 클릭 시*/
// $("#removeModal button.applyBtn").on("click", function(){
//     $("#removeModal").hide();
//     $("#modalSuccess .m-title").css("text-align", "center");
//     $("#modalSuccess .m-title").text("게시글이 정상적으로 삭제되었습니다.");
//     $("#modalSuccess .m-c-title").text("이후 해당 서비스를 다시 이용해주세요.");
//     $("#modalSuccess").show();
// });



/*-----------------------------------------------------------*/
                            /*지도 api*/
/*-----------------------------------------------------------*/
mapSearch();

function mapSearch() {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch($("#locations").text(), function(result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
            $("div#map").show();

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:6px 0;">아르바이트 장소</div>'
            });
            infowindow.open(map, marker);
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        } else {
            // alert("주소를 정확하게 다시 입력해주세요!");
            $("div#map").css("display", "none");
        }
    });
}

let albaApplyService = (function (){
    function add(){
        $.ajax({
            url:"/alba/apply",
            type:"post",
            success: function (albaId){
                console.log(albaId);
            }
        });
    }
    function cancel(){
        $.ajax({
            url: "/alba/applyCancel",
            type: "post",
            success:function (apply){
                console.log(apply);
            }
        })
    }
})
