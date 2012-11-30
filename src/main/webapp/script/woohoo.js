play = function(){
  $.ajax("play");
}

$(document).ready(function() {
   $("#woohoo").click(play);
});