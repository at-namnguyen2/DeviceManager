
$(document).ready(function() {
	$(".title-top").text("Request");
	getPagePending();
	
	
	  $('#txtdevice').typeahead({
          source: function (query, result) {
        	  console.log(query);
    		  $('.alertdevice').attr('hidden',"");
              $.ajax({
                  url: "/filterdetailsnotused",
					data: 'key=' + query,            
                  dataType: "json",
                  type: "POST",
                  success: function (data) {
                	    //var table = $('#tablefilter').DataTable();
                        //able.clear();
                        $('.tablefilter').html("");
                        $('#tablefilter').removeAttr('hidden',"");
                        
                	  console.log("hihi"+data);
                	  $.each(data, function (key, device) {
     					 var datetime =new Date(device.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
     					 console.log(datetime);
     					SetTableAllo(device);
     					 console.log(device);
     	                
     	                    })
                	  
                  },
          	error : function(err) {
                $('.tablefilter').html("");
                $('#tablefilter').attr('hidden',"");
          		 $('.alertdevice').removeAttr('hidden',"");
          
    		}
              });
          }
      });
	  
	  
	  var jsondevice = "";
	  $(".modaldevice").on("hidden.bs.modal", function(){
		  $('.infoprofile').attr('hidden',"");
		  $('.modalallo').attr('hidden',"");
		  $('.modalreturn').attr('hidden',"");
			$('.contentarea').attr('hidden',"");
			$('.replya').attr('href',"#step-2y");
			$('.denya').attr('href',"#step-2y");
//		    $(".modal-body").html("");
		});
	  

	  	function getPagePending(){
			$.ajax({
				url : '/getpagepending',
				data:{
					size:5
				},
				type : 'get',
				success : function(value) {
					if(value == 1){
						$(".tablerequest").html("");
			        	PendingRequest(0);
					}
					console.log("pending"+value);
					$('#pagination-pending').twbsPagination({
				        totalPages: value,
				        visiblePages: 3,
				        next: 'Next',
				        prev: 'Prev',
				        onPageClick: function (event, page) {
				        	$(".tablerequest").html("");
				        	PendingRequest(page-1);
				        }
				    });
					 },
				error : function(err) {
					console.log(err);
				
				}
			})
				
		}	  
	  
	function PendingRequest (p){
	$.ajax({
		url : '/requestpending',
		data:{
			page: p,
			size: 5			
		},
		type : 'get',
		success : function(value) {
			var count = 0;
			console.log(value);
			 $.each(value, function (key, request) {
				 var datetime =new Date(request.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
				 count = count+1;
				 var block = request.content.split("content:");
				 console.log(block[1]);
				 var dayago = $.timeago(request.updatedate);
				 console.log("hihi"+datetime)
				 console.log("hihi"+dayago);
				 var Allocation = "Allocation";
				 var Return = "Return";
				 var UpdateInfo = "Update Info";
				 $(".tablerequest").append("<tr class=\""+key+"\"></tr>");
				 $("."+key+"").append("<td>"
                 +" <div class=\"avatar avatar-md mr-3 mt-1 float-left\">"
                 +"<span class=\"avatar-letter avatar-letter-a  avatar-md circle\"></span></div>"
                 +"<div class=\"email0\"><div class=\"fullname\"><strong>"+request.fullname+"</strong></div>"
                 +"<small class=\"email\">"+request.email+"</small></div></td>"       
                 +"<td class=\"team\">"+request.team+"</td><td><span>"
                 +"<i class=\"icon icon-data_usage\"></i>"+dayago+"</span><br><span class=\"datetime\">"
                 +"<i class=\"icon icon-timer datetime\"></i>"+datetime+"</span></td>");
            	 if(request.type == Allocation){
    					 $("."+key+"").append("<td><a class=\"detail-info\" href=\"#modalCreateMessage\" data-toggle=\"modal\" data-target=\"#modalCreateMessage\" >"
    							 +"<span class=\"r-3 badge badge-success type \">"+request.type+"</span></a></td>");
                       } else if(request.type == Return){
                    	 $("."+key+"").append("<td><a class=\"detail-info\" href=\"#modalCreateMessage\" data-toggle=\"modal\" data-target=\"#modalCreateMessage\" >"
      							 +"<span class=\"r-3 badge badge-danger type \">"+request.type+"</span></a></td>");
                       } else {
                    	 $("."+key+"").append("<td><a class=\"detail-info\" href=\"#modalCreateMessage\" data-toggle=\"modal\" data-target=\"#modalCreateMessage\" >"
      							 +"<span class=\"r-3 badge badge-warning type \">"+request.type+"</span></a></td>");
                       }	
                 $("."+key+"").append("<td hidden=\"\" class=\"id\">"+request.id+"</td>"
                 +"<td hidden=\"\"class=\"content1\">"+block[0]+"</td>"
                 +"<td hidden=\"\" class=\"content2\">"+block[1]+"</td>"
                 +"<td hidden=\"\" class=\"empId\">"+request.empId+"</td>");
                    			
                    })
                    $(".count-pending").text(count);
		},
		error : function(err) {
			console.log(err);	
		}
	})

	}
	var content = "";
	var status ="";
	var type ="";
	var id ="";
	var email ="";
	$(".table-pending").on('click','.detail-info',function(e) {
		$('.back').click();
		var $row = $(this).closest("tr");
		var profile = {}
		id = $row.find(".id").text();
		email =$row.find(".email").text();
	if($row.find(".type").text() === "Update Info"){
		

		$('.infoprofile').removeAttr('hidden',"");
		content = $row.find(".content1").text();
		type = "Update Info";
		profile = JSON.parse(content);
		//setprofile function
		setprofile(profile);
		$('.contentarea').val("Your info update request is");
	
	} else if($row.find(".type").text() === "Allocation"){
		type = "Allocation";
		
		console.log("check: "+$row.find(".team").text());
		$('.idM2').text($row.find(".empId").text()) ;
		$('.fullnameM2').text($row.find(".fullname").text()) ;
		$('.emailM2').text($row.find(".email").text()) ;
		$('.datetimeM2').text($row.find(".datetime").text()) ; 
		$('.teamM2').text("team: "+$row.find(".team").text()) ; 
		$('.content1M2').text("Device: "+$row.find(".content1").text()) ;
		$('.content2M2').text("content"+$row.find(".content2").text()) ;
		$('.modalallo').removeAttr('hidden',"");
		$('.contentarea').val("Your device allocation request is");
		$('.replya').attr('href',"#step-3y");
		$('.denya').attr('href',"#step-3y");
	} else {
		type = "Return";
		console.log("check: "+$row.find(".team").text());
		$('.idM2').text($row.find(".empId").text()) ;
		$('.fullnameM2').text($row.find(".fullname").text()) ;
		$('.emailM2').text($row.find(".email").text()) ;
		$('.datetimeM2').text($row.find(".datetime").text()) ; 
		$('.teamM2').text("team: "+$row.find(".team").text()) ; 
		$('.content1M2').text("Device: "+$row.find(".content1").text()) ;
		$('.content2M2').text("content"+$row.find(".content2").text()) ;
		$('.contentarea').val("Your device refund request is ");
		$('.modalreturn').removeAttr('hidden',"");
		var json = {};
		json["employeeId"] = id;
		
	}
	
		   // Find the row
	
//		 $('#count-notify').text(count);
//		 $('#count-header').text("You have "+count+" notifications");
		
	})
	
	$('.approved').click(function(){
		status = "Approved";

		if(type === "Allocation"){
		
			$('.row-type').attr('hidden',"");
			$('.row-next').removeAttr('hidden',"");	

		} else {
			$('.row-type').attr('hidden',"");
			$('.row-submit').removeAttr('hidden',"");
			$('.contentarea').removeAttr('hidden',"");
		}
		$('.contentarea').val($('.contentarea').val()+"Approved");

	})
		$('.deny').click(function(){
			status = "Cancel";
			$('.row-type').attr('hidden',"");
			$('.contentarea').val($('.contentarea').val()+"canceled");
			$('.contentarea').removeAttr('hidden',"");
			$('.row-submit').removeAttr('hidden',"");
		
	})
			$('.reply').click(function(){
			status = "Reply Pending";
			$('.contentarea').val("");
			$('.contentarea').removeAttr('hidden',"");
			$('.row-type').attr('hidden',"");
			$('.row-submit').removeAttr('hidden',"");
		
	})
		$('.back').click(function(){
		
			$('.row-next').attr('hidden',"");
			$('.row-submit').attr('hidden',"");
			$('.row-type').removeAttr('hidden',"");
		
	})
		
		$(".tablefilter").on('click','.allocate',function(e) {	
		var $row = $(this).closest("tr");
		var json = {}
		json["detailId"] = $row.find(".detailId").text();
//		json["deviceName"] =$row.find(".nameDevice").text();
//		json["productId"] =$row.find(".productId").text();
		var dateDeliverReceive = moment(dateDeliverReceive).format('YYYY-MM-DDThh:mm:ss.000+0000');
		json["employeeId"] = $('#idA').text();
		json["email"] = $('#emailA').text();
		json["dateDeliverReceive"] = dateDeliverReceive;
		console.log("checl"+JSON.stringify(json));
		jsondevice = JSON.stringify(json);
		$('.step3').trigger('click');
		$('.contentarea').removeAttr('hidden',"");
		$('.row-next').attr('hidden',"");
		$('.row-submit').removeAttr('hidden',"");

		
	})
	
	
	$('.submit').click(function(){
			$('.row-submit').attr('hidden',"");
			$('.row-type').removeAttr('hidden',"");
			json = {};
			 json["id"] = id;
			 json["email"] = email;
			 json["content"] = content;
			 json["contentReply"] = $('.contentarea').val();
			 json["type"] = type;
			 json["status"] = status;
			var datajson = JSON.stringify(json);
//			 contentReply
				$.ajax({
				url : '/resolve',
				  type: 'PUT',
					contentType : "application/json; charset=utf-8",
					data : datajson,
					dataType : 'json',
				success : function(value) {
					alert();
					$('.tablerequest').html("");
					getPagePending();
				},
				error : function(err) {
					console.log(err);
				
				}
			})
		 
			 
		if(type === "Allocation" && status === "Approved"){
			$.ajax({
			url : '/adddevdere',
			  type: 'POST',
				contentType : "application/json; charset=utf-8",
				data : jsondevice,
				dataType : 'json',
			success : function(value) {
				console.log(value);
			},
			error : function(err) {
				console.log(err);
	
			}
		})
		} else if(type == "Return" && status == "Approved") {
			console.log("check"+jsondevice);
			$.ajax({
				url : '/setreturn',
				  type: 'POST',
					contentType : "application/json; charset=utf-8",
					data : jsondevice,
					dataType : 'json',
				success : function(value) {
					console.log(value);
				},
				error : function(err) {
					console.log(err);
		
				}
			})
		}
				$('.tablerequest').html("");
				getPagePending();	
				$('.contentarea').attr('hidden',"");
				 $('.modaldevice').modal('toggle');
	})
	
	$('#v-pills-history-tab').click(function(){
//		getPageHistory();
	})
	getPageHistory();
	function getPageHistory(){
//		alert();
		$.ajax({
			url : '/getpagehistory',
			data:{
				size:5
			},
			type : 'get',
			success : function(value) {
//				alert(value);
				if(value ==1){
//					alert();
				 	$(".tablehistory").html("");
		        	historyRequest(page-1);
				}
				console.log("history"+value);
				$('#pagination-history').twbsPagination({
			        totalPages: value,
			        visiblePages: 3,
			        next: 'Next',
			        prev: 'Prev',
			        onPageClick: function (event, page) {
			        	$(".tablehistory").html("");
			        	historyRequest(page-1);
			        }
			    });
				 },
			error : function(err) {
				console.log(err);
			
			}
		})
			
	}	
	
//request history	
	function historyRequest (p){
		$.ajax({
			url : '/requesthistory',
			data:{
				page: p,
				size: 5	
			},		
			type : 'get',
			success : function(value) {
				var count = 0;
				console.log(value);
				 $.each(value, function (key, request) {
					 var Allocation = "Allocation";
					 var Return = "Return";
					 var UpdateInfo = "Update Info";
					 var datetime =new Date(request.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
					 var dayago = $.timeago(request.updatedate);
					 console.log("hihi"+datetime)
					 console.log("hihi"+dayago);
					 $(".tablehistory").append("<tr class=\"history"+key+"\"></tr>");
					 $(".history"+key+"").append("<td>"
	               +"<div class=\"avatar avatar-md mr-3 mt-1 float-left\">"
	               +"<span class=\"avatar-letter avatar-letter-a  avatar-md circle\"></span></div>"
	               + "<div><div><strong>"+request.fullname+"</strong></div>" 
	               +"<small>"+request.email+"</small></div></td>"       
	               +"<td>"+request.email+"</td><td><span>"
	               +"<i class=\"icon icon-data_usage\"></i>"+dayago+"</span><br><span>"
	               +"<i class=\"icon icon-timer\"></i>"+datetime+"</span></td>");
			if(request.type == Allocation){
    		$(".history"+key+"").append("<td> <a href=\"#\" data-toggle=\"modal\" data-target=\"#\" >"
	        +"<span class=\"r-3 badge badge-success \">"+request.type+"</span></a></td>");
                       } else if(request.type == Return){
            $(".history"+key+"").append("<td> <a href=\"#\" data-toggle=\"modal\" data-target=\"#\" >"
            +"<span class=\"r-3 badge badge-danger \">"+request.type+"</span></a></td>");
                       } else {
            $(".history"+key+"").append("<td> <a href=\"#\" data-toggle=\"modal\" data-target=\"#\" >"
            +"<span class=\"r-3 badge badge-warning \">"+request.type+"</span></a></td>");
                       }	
			$(".history"+key+"").append("<td hidden=\"\">"+request.id+"</td>"
	               +"<td hidden=\"\">"+request.content+"</td>");
					
					 $('.count-notify').text(count);
					 $('.count-header').text("You have "+count+" notifications");

	                    })
			},
			error : function(err) {
				console.log(err);
			
			}
		})
	}		

	
	//set profile edit
	function setprofile(profile){
		 var datetime =new Date(profile.birthDay).Format("dd/MM/yyyy");
		 $('.idM1').text(profile.id);
		 $('.fullnameM1').val(profile.fullname);
		 $('.birthDayM1').val(datetime);
		 $('.emailM1').val(profile.email);
		 $('.teamM1').val(profile.team);
		 if(profile.gender === true){
			 $('.genderM').prop("checked", true);
		 } else {
			 $('.genderF').prop("checked", false);
		 }
		 $('.data-avatar').val(profile.avatar);
		 $('.phoneM1').val(profile.phone);
		 $('.addressM1').val(profile.address);
	}
	
	//set table allocation
	function SetTableAllo(device){
		$('.tablefilter').append("<tr>"
                                           +		 "<td>"                                        
                                          +		 "<input name=\"selectId\" type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"tailId\" required=\"\"><label class=\"custom-control-label\" for=\"user_id_1\"></label> "                               
                                           	+	 "</td>"
                                        	+ "<td class =\"detailId\" hidden=\"\" >"+device.id+"</td>"	
                                           	+	 "<td>"
                                                +	"<div class=\"icon icon-laptop_mac s-36 mr-3 mt-1 float-left\">" 
                                               	+	 "</div> "                                        
                                                   +" <div>"
                                                   +    " <strong class =\"nameDevice\">"+device.devicename+"</strong>"
                                                   + "</div>"
                                            	+"</td>"
                                           		+ "<td class =\"productId\">"+device.productid+"</td>"	
                                           		+ "<td class =\"updatedate\">"+device.updatedate+"</td>"		
												+"<td>"	
												+"<a href=\"#step-3y\"><span class=\"badge badge-success allocate\">Allocate</span></a></td>"
                                       		+" </tr>");
	}
	
	
	
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
	
});