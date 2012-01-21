$(document).ready(function(){
if (!($.browser.msie && parseInt($.browser.version) < 9)) {
    $('.pricing-table article').hover(
    function(){
        $('.pricing-table article.selected').removeClass('selected').stop().animate({shadow: '0 0 0'});
        $(this).stop().animate({shadow: '0 1px 10px #009DF9'});
    },
    function(){
        $(this).stop().animate({shadow: '0 0 0'});
    }).filter('.selected').animate({shadow: '0 1px 10px #009DF9'});
}
});