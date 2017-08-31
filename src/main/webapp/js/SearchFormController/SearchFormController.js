/*
	Module SearchFormController

	Utilities for the rule search form.

*/
const SearchFormController = (function () {

	const init = function(formId, ruleCallback) {
		const $form = $("#" + formId);
		$form.on("submit", function(event) {
			event.preventDefault();

			var options = {};

			$("#" + formId + " input").each(function() {
				options[$(this).attr('id')] = parseFloat($(this).val());
			});
			$form.trigger('reset');
			fetchRules(options, ruleCallback)
		});
	};

	const fetchRules = function(options, ruleCallback) {
    $("#rules").empty(); //remove existing rule listing
		$("#rules").spin(); //run spinner while request is being processed
    console.log(options);
    $.ajax({
			url: contextPath + "/spark-submit",
			type: "POST",
			dataType: "json",
      contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(options),
			success: function(response) {
        $("#rules").data('spinner').stop(); //stop spinner
				ruleCallback(response);
			},
			error: function(xhr) {
        $("#rules").data('spinner').stop(); //stop spinner
				console.log("SearchFormController: failed to fetch data")
        alert("Backend responded with error code " + xhr.status);
			}
		});
	};

	return {
		init: init
	};
})();
