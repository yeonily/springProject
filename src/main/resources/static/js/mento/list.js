/*for(var i = 0; i < 20; i++){
    $("#mentor_list_flex_inner3").append(text);
}*/

/*헤더 멘토 표시*/
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(1)").children("a").addClass("selected-header");
})

// var page = 1;
// console.log("들어옴1");
// $(function () {
//     $(window).scroll(
//
//         function() {
//             let text = "";
//             console.log($(window).scrollTop());
//             console.log($(document).height());
//             console.log($(window).height());
//             if (($(window).scrollTop() + 100) > $(document).height() - $(window).height()) {
//                 text += '<div class="mentor_info">';
//                 text += '<a href="/mento/detail">';
//                 text += '<article class="mentor_info_inner1">';
//                 text += '<div class="mentor_info_inner2">';
//                 text += '<picture class="mentor_info_image">';
//                 text += '<source srcset="https://cdn.comento.kr/images/vod/lecture/braze.png?s=518x600&amp;q=75" type="image/webp">';
//                 text += '<img src="https://cdn.comento.kr/images/vod/lecture/braze.png" alt="" width="259" height="300"></picture></div>';
//                 text += '<div class="mentor_info_inner3">';
//                 text += '<div class="mentor_info_subTitle">';
//                 text += '<div class="mentor_info_subTitle2">감자</div></div>';
//                 text += '<div class="mentor_info_title">';
//                 text += '<div class="mentor_info_title2">멘토가 알려주는 감자 농사법</div></div>';
//                 text += '<div class="mentor_info_caption">';
//                 text += '<div class="mentor_star">';
//                 text += '<svg width="16" height="16" viewBox="0 0 16 16" fill="black" xmlns="http://www.w3.org/2000/svg" element="span" class="mentor_star_inner1">';
//                 text += '<path d="M7.54402 1.51325C7.72091 1.12016 8.27905 1.12016 8.45594 1.51325L10.25 5.5L14.549 5.8944C14.9851 5.93442 15.1627 6.47608 14.8349 6.76654L11.75 9.5L12.5463 13.8799C12.6242 14.3082 12.1555 14.6245 11.7874 14.392L7.99998 12L4.21258 14.392C3.8445 14.6245 3.37577 14.3082 3.45365 13.8799L4.24998 9.5L1.16508 6.76654C0.837274 6.47608 1.01485 5.93442 1.45099 5.8944L5.74998 5.5L7.54402 1.51325Z"></path></svg>';
//                 text += '<span class="mentor_star_span">4.9</span>';
//                 text += '<div class="mentor_star_avg">(221)</div></div></div></div></article></a></div>';
//                 // text += `<p id="result">` + page++ +`</p>`;
//                 for(var i = 0;i<20;i++) {
//                     $("#mentor_list_flex_inner3").append(text);
//                 }
//             }});
// })

