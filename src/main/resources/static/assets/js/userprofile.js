$(document).ready(function() {
console.log("fhfhfhfhf");
	Date.prototype.Format = function customDate(fmt){
		  var o = {
                  "M+": this.getMonth() + 1,                 // MM
                  "d+": this.getDate(),                    // dd
                  "h+": this.getHours(),                   // hh
                  "m+": this.getMinutes(),                 // mm
                  "s+": this.getSeconds(),                 // ss
                  "q+": Math.floor((this.getMonth() + 3) / 3), // qq
                  "S": this.getMilliseconds()             // S
              };
              if (/(y+)/.test(fmt))
                  fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
              for (var k in o)
                  if (new RegExp("(" + k + ")").test(fmt))
                      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
              return fmt;
	}
		$.ajax({
			url : '/userapi/myprofile',
			
			//url: '/my-profile',
			type : 'get',
			success : function(value) {
				console.log(value);
	            var birthday=new Date(value.dateOfBirth).Format("dd/MM/yyyy");
				sessionStorage.setItem("fullname", value.name);
				$('.fullname').text(value.name);
				$('.email').text(value.email);
				$('.phone').text(value.phone);
				$('.team').text(value.team);
				$('.address').text(value.address);
				$('.dateOfBirth').text(birthday);
				$('.data-avatar').attr('src',"/assets/img/"+value.avatar);  
			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
		})
		
				$.ajax({
			url : '/userapi/sacontact',
			type : 'get',
			success : function(value) {
				console.log(value[0]);
				$('.saname1').text(value[0].name);
				$('.saphone1').text(value[0].phone);
				 $.each(value, function (key, contact) {
	                    var adminphone = contact.phone; // Change here
	                    console.log(adminphone);
	                    if(!key==0){    
	                    $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
	                    } 
	                    })
			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
		})
	});