//$(function(){
//  $("#send").click(function(){
//    let msg = $("#msg").val();
//    if(!msg) return;
//    $("#chat").append(`<div class='message user'><b>You:</b> ${msg}</div>`);
//    $("#msg").val('');
//    $("#chat").append(`<div class='message bot'><b>Bot:</b> ...waiting</div>`);
//    $.ajax({
//      url:'/api/chat',
//      method:'POST',
//      contentType:'application/json',
//      data: JSON.stringify({message: msg}),
//      success: function(res){
//        // remove the last waiting message
//        $("#chat .message.bot").last().remove();
//        $("#chat").append(`<div class='message bot'><b>Bot:</b> ${res.botResponse}</div>`);
//        $("#chat").scrollTop($("#chat")[0].scrollHeight);
//      },
//      error: function(err){
//        $("#chat .message.bot").last().remove();
//        $("#chat").append(`<div class='message bot'><b>Bot:</b> Error contacting server</div>`);
//      }
//    });
//  });
//
//  // Enter key
//  $("#msg").keypress(function(e){ if(e.which===13) $("#send").click(); });
//});
