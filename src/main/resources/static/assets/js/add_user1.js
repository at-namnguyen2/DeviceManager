//$(document).ready(function() {
// console.log("test-add-user");
//// 
//////to do change password
//	$('#add-user').click(function() {
//		console.log("tu viet van");
//		var URL = "api/users/addUser";
//		var passwordForm = $('#form-user').serializeJSON();
//		console.log(passwordForm);
////		var jsonStringPassword = JSON.stringify(passwordForm);
////		console.log(jsonStringPassword);
////		$.ajax({
////			url : URL,
////			type : 'PUT',
////			contentType : "application/json; charset=utf-8",
////			data : jsonStringPassword,
////			dataType : 'json',
////			complete : function(res) {
////				if (res.status === 200 || res.status ===201){
////					$('.msg-error').fadeIn(1000);
////					$('.msg-error').addClass('btn-success');
////					$('.msg-error').text('Update Password Success!');
////					setTimeout(function() {
////						$('.msg-error').fadeOut(1000);
////					}, 8000);
////				}
////				if (res.status===409 || res.status===400){
////					$('.msg-error').fadeIn(1000);
////					$('.msg-error').addClass('btn-danger');
////					$('.msg-error').text(res.responseText);
////					setTimeout(function() {
////						$('.msg-error').fadeOut(1000);
////					}, 8000);
////				}
////			}
////		});
//	});
////	
////
////
//// 
//// 
//// 
//// 
//// 
//});