
console.log("fhf");


//$(document).ready(function() {
//      console.log("fhfhfhfhf");
	
//		$.ajax({
//			url : '/userapi/myprofile',
//			
//			//url: '/my-profile',
//			type : 'get',
//			success : function(value) {
//				console.log(value);
//	            var birthday=new Date(value.dateOfBirth).Format("dd/MM/yyyy");
//				sessionStorage.setItem("fullname", value.name);
//				$('.fullname').text(value.name);
//				$('.email').text(value.email);
//				$('.phone').text(value.phone);
//				$('.team').text(value.team);
//				$('.address').text(value.address);
//				$('.dateOfBirth').text(birthday);
//				$('.data-avatar').attr('src',"/assets/img/"+value.avatar);  
//			},
//			error : function(err) {
//				console.log(err);
//				alert(JSON.stringify(err));
//			}
//		})
////		
				$.ajax({
			url : '/listAllDevice',
			type : 'get',
			success : function(value) {
				console.log(value);
////				$('.saname1').text(value[0].name);
////				$('.saphone1').text(value[0].phone);
				 $.each(value, function (key, contact) {
//					 var name = contact.name;
//					 console.log("vnvnvnvnvnvnv" + name);
////	                    var adminphone = contact.phone; // Change here
////	                    console.log(adminphone);
					 console.log(contact.quantity);
	                    if(!key==0){    
//	                   // $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
//                        
                   $(".dev1").append("<tr><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_2\" required><label class=\"custom-control-label\" for=\"user_id_2\"></label></div></td><td><div class=\"icon icon-laptop_mac s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div><div>"+contact.name+"</div><div class=\"icon icon-laptop_mac s-36 mr-3 mt-1 float-left\"></div><span class=\"circle\"></span><div>"+contact.quantity+"</div></td></tr>");
					// $(".dev").append.("<tr class=\"dev1\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td><div class=\"icon icon-laptop_mac s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div><div class=\"dev3\">"+contact.name +"</div></td></tr>");
	                   //$(".dev1").append.("<tr class=\"a1\"><td class=\"b1\"><p>"+contact.quantity+"</p></td></tr>");
//                           data-target="#modaldetails"><span class="badge badge-success dev6">Details</span></a></td>
	                    //	 $(".dev").append("<tr class=\"a1\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td class=\"b\"><div class=\"icon icon-laptop_mac s-24 mr-3 float-left\"><span class=\"   circle\"></span></div><div class=\"thiet_bi1\"> " + contact.name +  "</div></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#step-22\"><i class=\"icon-pencil\"></i></a></td></tr>");	                                                    
//                                        
//                                        </tr>") 
	                    }
				 })
			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
		});
