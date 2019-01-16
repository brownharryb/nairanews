$(window).load(function() {
  $('.post-module').hover(function() {
    $(this).find('.description').stop().animate({
      height: "toggle",
      opacity: "toggle"
    }, 300);
  });
});

$(document).ready(function(){
	$(".newspaper-select").on("change",function(e){
		var val = $(this).find(":selected").val();
		window.location.href = "/nairanews/news/"+val;
	})
});