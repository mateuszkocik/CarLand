$(document).ready(function(){
		$('#make').change(
				function(){
					$.getJSON("http://localhost:9090/sellmycar/models", {
						make : $(this).val(),
						ajax : 'true'
					}, function(data){
						
						var html = '<option disabled="disabled" selected="selected" value="none">Choose model</option>';
		                var len = data.length;
		                for ( var i = 0; i < len; i++) {
		                    html += '<option th:value="' + data[i] + '">'
		                            + data[i] + '</option>';
		                }
		                html += '</option>';
		                $('#model').html(html);
					});
				});
	});