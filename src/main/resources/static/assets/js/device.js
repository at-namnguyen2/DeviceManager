$(document).ready(function() {
	listDevice(); //function1
	
	 $("#modaldetails").on("hidden.bs.modal", function(){
		    $(".detail-table").html("");		    
		    $(".Step1").click();
		});
	
	
	//function1
	function listDevice(){
		 $(".Step1").click();
		$.ajax({
			url : '/listAllDevice',
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, device) {
					 $(".device-table").append("<tr><td class =\"deviceId\" hidden=\"\" >"+device.id+"</td>" 
					 		+"<td><div class=\"custom-control custom-checkbox\">"
 +"<input type=\"checkbox\" name=\"device_id\" class=\"custom-control-input checkSingle\" id=\"device_id\" required>"
 +"<label class=\"custom-control-label\" for=\"device_id\"></label></div></td>"
 +"<td><div class=\"device_icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
 +"<div><div class=\"pt-2\"><strong class=\"deviceName\">"+device.name+"</strong></div></div></td>"
 +"<td class=\"quantity\">"+device.quantity+"</td>"
 +"<td class=\"price (USD)\">"+device.price+"</td>"
 +"<td><a class=\"\" href=\"modaldetails\"data-toggle=\"modal\" data-target=\"#modaldetails\">"
 +"<span class=\"badge badge-primary viewDetail\">Details</span></a></td></tr>");	
					 convertToIconDevice(device.catalog);
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
		var json = {};
		json["id"] = $row.find(".deviceId").text();
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
				 $.each(value, function (key, detail) {
//					 var datetime =new Date(detail.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
						var datetime = moment(detail.updatedate).format('DD-MM-YYYY , HH:mm:ss');
						$(".detail-table").append("<tr class=\""+key+"\"></tr>");	
						$("."+key+"").append("<td class =\"detailId\" hidden=\"\" >"+detail.id+"</td>"
							 +"<td><div class=\"custom-control custom-checkbox\">"
				+"<input type=\"checkbox\" class=\"custom-control-input checkSingle\"id=\"user_id_1\"required>"
				+"<label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td>"
			+"<td><div class=\"detail-icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
			+"<div><div><strong class=\"productId\">"+detail.productid+"</strong></div></div></td><td>"+datetime+"</td>");
					 if(detail.working === false){
						 $("."+key+"").append("<td class=\"working\"><span class=\" icon icon-circle s-12  mr-2 text-dark\"></span> Inactive</td>");
					 } else {
						 $("."+key+"").append("<td class=\"working\"><span class=\" icon icon-circle s-12  mr-2 text-success\"></span> Active</td>");
					 }
			 if(detail.status === 1){ $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-primary\">Normal</span></td>");
			 } else if(detail.status === 2){  $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-warning \">Error</span></td>");			
			 } else { $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-danger \">Break</span></td>");
			 }
			 $("."+key+"").append("<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
						+"<a class=\"\" href=\"#step-22\"><i class=\"editDetail icon-pencil\"></i></a></td><td class=\"deviceName\" hidden=\"\">"+detail.devicename+"</td><td class=\"catalogName\" hidden=\"\">"+detail.catalogname+"</td></tr>");			 
			convertToIconDetail(detail.catalogname);
			
				 });
			},
			error : function(err) {
				console.log(err);	 
			}
		})
	})
	
	$(".detail-table").on('click','.editDetail',function(e) {	
	
		var $row = $(this).closest("tr");

		var detailId = $row.find(".detailId").text();
		var productId = $row.find(".productId").text();
		var status = $row.find(".status").text();
		var working = $row.find(".working").text();
		var deviceName = $row.find(".deviceName").text();
		var cataloName = $row.find(".catalogName").text();
		console.log(cataloName);
		$('#deviceName').val(deviceName);
		$('#detail-icon').removeAttr('class');
		$('#detail-icon').removeAttr('class',cataloName);
		$('#productId').val(productId);
		$('#catalogName').val(cataloName);
		
		 $('#SwitchWorking').prop("checked", true);
//		$('#SwitchWorking').prop

		if(status ==="Normal"){ 
$('#SwitchStatusNormal').prop("checked", true); $('#SwitchStatusBreak').prop("checked", false); $('#SwitchStatusError').prop("checked", false);}
		else if(status ==="Break"){
$('#SwitchStatusNormal').prop("checked", false); $('#SwitchStatusBreak').prop("checked", true); $('#SwitchStatusError').prop("checked", false);}
		else if(status ==="Error"){ 
$('#SwitchStatusNormal').prop("checked", false); $('#SwitchStatusBreak').prop("checked", false); $('#SwitchStatusError').prop("checked", true);}
		if(working ===1){ $('#SwitchWorking').prop("checked", true);}

	})
	

	
			 $('#SwitchStatusNormal').change(function() {
				 $('#SwitchStatusBreak').prop("checked", false);
				 $('#SwitchStatusError').prop("checked", false);     
			    });
	 $('#SwitchStatusBreak').change(function() {
		 $('#SwitchStatusNormal').prop("checked", false);
		 $('#SwitchStatusError').prop("checked", false);     
	    });	 
	 $('#SwitchStatusError').change(function() {
			 $('#SwitchStatusBreak').prop("checked", false);
			 $('#SwitchStatusNormal').prop("checked", false);     
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
		 if(catalog === "Laptop"){$('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Keyboard"){$('.detail-icon').addClass('icon-keyboard');}
		 else if(catalog === "Monitor"){$('.detail-icon').addClass('icon-desktop_mac');}	
		 else if(catalog === "Desktop Mac"){$('.detail-icon').addClass('icon-desktop_mac');}
		 else if(catalog === "UPS"){$('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Mouse"){$('.detail-icon').addClass('icon-mouse');}
		 else {$('.detail-icon').addClass('icon-laptop_mac');}
	}

});