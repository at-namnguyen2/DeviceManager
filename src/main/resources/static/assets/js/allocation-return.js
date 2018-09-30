$(document).ready(function() {
	getPageAllo();
	
	function getPageAllo(){
		$.ajax({
			url : '/getPageAllo',
			data:{
				size:5
			},
			type : 'get',
			success : function(value) {
				console.log(value);
				$('#pagination-allo').twbsPagination({
			        totalPages: value,
			        visiblePages: 3,
			        next: 'Next',
			        prev: 'Prev',
			        onPageClick: function (event, page) {
			        	$("#tbAllo").html("");
			        	listaAllocation(page-1);
			        }
			    });
				 },
			error : function(err) {
				console.log(err);
			
			}
		})
			
	}
	//function1
	function listaAllocation(p){
//		 $(".Step1").click();

		$.ajax({
			url : '/getdevallo',
			data:{
				page: p,
				size: 5
				
			},
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, all) {
					 $("#tbAllo").append("<tr><td><div class=\"custom-control custom-checkbox\">"
+"<input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required>" 
+"<label class=\"custom-control-label\" for=\"user_id_1\"></label>\</div></td>"
+"<td><a href=\"panel-page-profile.html\" class=\"avatar avatar-lg\">" 
+"<img class=\"avatar1\" src=\""+all.avatar+"\" alt=\"\"></a></td>"
+"<td><h6>"+all.employeeName+"</h6><small class=\"text-muted\">"+all.email+"</small></td>"
+"<td>"+all.team+"</td><td>"+all.deviceName+"</td><td>"+all.productId+"</td><td>"+all.dateDeliverReceive+"</td>"
+"<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
+"<a href=\"#step-22\"><i class=\"font-weight-bold\">return</i></a></td></tr>");	
	                    })
				 },
			error : function(err) {
				console.log(err);
			
			}
		})
	}

	
	
	
	function getPageHistory(){
		$.ajax({
			url : '/getPageHistory',
			data:{
				size:5
			},
			type : 'get',
			success : function(value) {
				console.log(value);
				alert("huhu");
				var l = 3;
				if(value =1 ){
					$("#table-history").html("");
					listHistory(0);
				}
				$('#pagination-return').twbsPagination({
			        totalPages: value,
			        visiblePages: 3,
			        next: 'Next',
			        prev: 'Prev',
			
			        onPageClick: function (event, page) {
			        	alert();
			        	$("#table-history").html("");
			        	listHistory(page-1);
			        }
			    });
				 },
			error : function(err) {
				console.log(err);
			
			}
		})
			
	}
	//function1
	function listHistory(p){
//		 $(".Step1").click();
		alert("hihi");
		$.ajax({
			url : '/getdevhistory',
			data:{
				page: p,
				size: 5
				
			},
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, all) {
					 $("#table-history").append("<tr><td><div class=\"custom-control custom-checkbox\">"
+"<input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_2\" required>" 
+"<label class=\"custom-control-label\" for=\"user_id_2\"></label>\</div></td>"
+"<td><a href=\"panel-page-profile.html\" class=\"avatar avatar-lg\">" 
+"<img class=\"avatar1\" src=\""+all.avatar+"\" alt=\"\"></a></td>"
+"<td><h6>"+all.employeeName+"</h6><small class=\"text-muted\">"+all.email+"</small></td>"
+"<td>"+all.team+"</td><td>"+all.deviceName+"</td><td>"+all.productId+"</td><td>"+all.dateReturn+"</td>"
+"</td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#modaladddevicesdr\">"
+"<i class=\"icon-pencil mr-3\"></i></a> <a href=\"#step-22\"><i class=\"icon-delete\"></i></a></td></tr>");	
	                    })
				 },
			error : function(err) {
				console.log(err);
			
			}
		})
	}
	
	
	
	//refurn devicedetails by device
	$(".device-table").on('click','.viewDetail',function(e) {	
		var $row = $(this).closest("tr");
		var deviceId = $row.find(".deviceId").text();
		 $(".detail-table").html("");
		callApiGetDetail(deviceId);
	})
	
	$(".detail-table").on('click','.editDetail',function(e) {	
		
		var $row = $(this).closest("tr");
		var iddevice = $row.find(".idDevice").text();
		var detailId = $row.find(".detailId").text();
		var productId = $row.find(".productId").text();
		var status = $row.find(".status").text();
		var deviceName = $row.find(".deviceName").text();
		var cataloName = $row.find(".catalogName").text();
		convertToIconDetail(cataloName);
		console.log(cataloName);
		$('#deviceName').val(deviceName);
		$('#productId').val(productId);
		$('#catalogName').val(cataloName);
		$('#id_detaildevice').val(detailId);
		$('#id_device').val(iddevice);
		if(status ==="Working"){ 
$('#SwitchStatusWorking').prop("checked", true); $('#SwitchStatusError').prop("checked", false); $('#SwitchStatusNotUse').prop("checked", false);}
		else if(status ==="Error"){
$('#SwitchStatusWorking').prop("checked", false); $('#SwitchStatusError').prop("checked", true); $('#SwitchStatusNotUse').prop("checked", false);}
		else if(status ==="Not Use"){ 
$('#SwitchStatusWorking').prop("checked", false); $('#SwitchStatusError').prop("checked", false); $('#SwitchStatusNotUse').prop("checked", true);}

	})
	

	
			 $('#devices-history').click(function() {
				 alert();
				 getPageHistory();
			    });

	
	
	 $('#SwitchStatusError').change(function() {
		 $('#SwitchStatusWorking').prop("checked", false);
		 $('#SwitchStatusNotUse').prop("checked", false);     
	    });	 
	 $('#SwitchStatusNotUse').change(function() {
			 $('#SwitchStatusError').prop("checked", false);
			 $('#SwitchStatusWorking').prop("checked", false);     
		    });
	
	 $('.Step1').click(function() {
		 var iddevice = $("#id_device").val();
			$(".detail-table").html("");
		 callApiGetDetail(iddevice);    
	    });
	 
	function convertToIconDevice(catalog){
		 if(catalog === "Laptop"){$('.device_icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Keyboard"){$('.device_icon').addClass('icon-keyboard');}
		 else if(catalog === "Monitor"){$('.device_icon').addClass('icon-desktop_mac');}	
		 else if(catalog === "Desktop Mac"){$('.device_icon').addClass('icon-desktop_mac');}
		 else if(catalog === "UPS"){$('.device_icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Mouse"){$('.device_icon').addClass('icon-mouse');}
		 else {$('.device_icon').addClass('icon-laptop_mac');}
	}
	function convertToIconDetail(catalog){
		 $('.detail-icon').removeClass('icon-laptop_mac');
		 $('.detail-icon').removeClass('icon-keyboard');
		  $('.detail-icon').removeClass('icon-desktop_mac');
		   $('.detail-icon').removeClass('icon-mouse');
		 if(catalog === "Laptop"){
//			 $('#detail-icon').addClass('icon-laptop_mac');
			 $('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Keyboard"){
			 $('.detail-icon').addClass('icon-keyboard');}
		 else if(catalog === "Monitor"){$('.detail-icon').addClass('icon-desktop_mac');}	
		 else if(catalog === "Desktop Mac"){$('.detail-icon').addClass('icon-desktop_mac');}
		 else if(catalog === "UPS"){$('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Mouse"){$('.detail-icon').addClass('icon-mouse');}
		 else {$('.detail-icon').addClass('icon-laptop_mac');}
	}

	function callApiGetDetail(deviceId){
	
		var json = {};
		json["id"] = deviceId;
		var jsondevice = JSON.stringify(json);
		$.ajax({
			url : '/Getdevicedetails',
			  type: 'POST',
				contentType : "application/json; charset=utf-8",
				data : jsondevice,
				dataType : 'json',
			success : function(value) {
//				alert(value);
				console.log(JSON.stringify(value));
				 var working = 0;
				 var notuse = 0;
				 var error = 0;
				 $.each(value, function (key, detail) {
					
//					 var datetime =new Date(detail.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
						var datetime = moment(detail.updatedate).format('DD-MM-YYYY , HH:mm:ss');
						$(".detail-table").append("<tr class=\""+key+"\"></tr>");	
						$("."+key+"").append("<td class =\"detailId\" hidden=\"\" >"+detail.id+"</td>"
							 +"<td><div class=\"custom-control custom-checkbox\">"
				+"<input type=\"checkbox\" class=\"custom-control-input checkSingle\" required>"
				+"<label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td>"
			+"<td><div class=\"detail-icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
			+"<div><div><strong class=\"productId\">"+detail.productid+"</strong></div></div></td><td>"+datetime+"</td>");
					 
			 if(detail.status === 1){
				 working = working +1;
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-success\">Working</span></td>");
			 } else if(detail.status === 2){
				error = error +1
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-danger \">Error</span></td>");			
			 } else { 
				 notuse = notuse +1;
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-dark \">Not Use</span></td>");
			 }
			 $("."+key+"").append("<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
						+"<a class=\"\" href=\"#step-22\"><i class=\"editDetail icon-pencil\"></i></a></td><td class=\"deviceName\" hidden=\"\">"+detail.devicename+"</td><td class=\"catalogName\" hidden=\"\">"+detail.catalogname+"</td><td class=\"idDevice\" hidden=\"\">"+json.id+"</td></tr>");			 
			convertToIconDetail(detail.catalogname);
		
				 });
				 $("#workinglb").text(working);
				 $("#notuselb").text(notuse);
				 $("#errorlb").text(error);
			},
			error : function(err) {
				console.log(err);	 
			}
		})
	}
	
	  $('#auto-search-employee').typeahead({
          source: function (query, result) {
        	  console.log(query);
    		  $('.alertEmployee').attr('hidden',"");
              $.ajax({
                  url: "/filteremployee",
					data: 'key=' + query,            
                  dataType: "json",
                  type: "POST",
                  success: function (data) {
                	    //var table = $('#tablefilter').DataTable();
                        //able.clear();
                        $('.tablefilter-emp').html("");
                        $('#tablefilter-emp').removeAttr('hidden',"");
                        
                	  console.log("hihi"+data);
                	  $.each(data, function (key, device) {
//     					 var datetime =new Date(device.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
//     					 console.log(datetime);
//     					SetTableAllo(device);
     					 console.log(device);
     	                
     	                    })
                	  
//						result($.map(data, function (item) {
//							return item;
//                      }));
                  },
          	error : function(err) {
                $('.tablefilter').html("");
                $('#tablefilter').attr('hidden',"");
          		 $('.alertdevice').removeAttr('hidden',"");
          
    		}
              });
          }
      });

});