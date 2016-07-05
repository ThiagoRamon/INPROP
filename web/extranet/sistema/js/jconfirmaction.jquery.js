/*
 * jQuery Plugin : jConfirmAction
 * 
 * by Hidayat Sagita
 * http://www.webstuffshare.com
 * Licensed Under GPL version 2 license.
 *
 */
(function($){

	jQuery.fn.jConfirmAction = function (options) {
		
		// Some jConfirmAction options (limited to customize language) :
		// question : a text for your question.
		// yesAnswer : a text for Yes answer.
		// cancelAnswer : a text for Cancel/No answer.
		var theOptions = jQuery.extend ({
			question: "Are You Sure ?",
			yesAnswer: "Sim",
			cancelAnswer: "Não"
		}, options);
		
		return this.each (function () {
			
			$(this).bind('click', function(e) {

				e.preventDefault();
				thisHref	= $(this).attr('rel');
				  rel = thisHref;
				if($(this).next('.question').length <= 0)
					$(this).after('<div class="question">'+theOptions.question+'<br/> <span class="yes">'+theOptions.yesAnswer+'</span><span class="cancel">'+theOptions.cancelAnswer+'</span></div>');
				
				$(this).next('.question').animate({opacity: 1}, 300);
				
				$('.yes').bind('click', function(){
                                   $(this).parents('.question').fadeOut(300, function() {
					                           //                   if($(this).attr("rel")){
                     
                       var url = null;
//                   }
                   if(rel.match(/^Delete/)){
                             var arr=new Array();
                           arr = rel.split(',');
                           url=".."+arr[0];
                           rel ="0&id="+arr[1];
                       }
                    $.ajax({
//                          scriptCharset: "iso-8895-1" ,
//                          contentType: "application/x-www-form-urlencoded; charset=iso-8895-1",
                          type:"post",
                          data:"page="+rel,
                          url:url,
                          success:function(retorno){
                              $("#corpo").html(retorno);
                          }
                       });


                                                $(this).remove();
					});
				//	window.location = thisHref;
				});
		
				$('.cancel').bind('click', function(){
					$(this).parents('.question').fadeOut(300, function() {
					           
                                                   $(this).remove();
					});
				});
				
			});
			
		});
	}
	
})(jQuery);