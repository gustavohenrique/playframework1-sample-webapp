$(document).ready(function(){
    $('.button').mousedown(function(){
        $(this).addClass('active');
    }).bind('mouseup mouseout', function(){
        $(this).removeClass('active');
    });
});