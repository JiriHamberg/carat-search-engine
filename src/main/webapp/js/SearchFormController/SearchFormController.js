/*
	Module SearchFormController

	Utilities for the rule search form.

*/
const SearchFormController = (function () {

	const init = function(formId, ruleCallback) {
		const $form = $("#" + formId);
		$form.on("submit", function(event) {
			event.preventDefault();

			var urlParams = {};

			$("#" + formId + " :input").each(function() {
				urlParams[this.name] = $(this).val();
			});
			$form.trigger('reset');
			fetchRules(urlParams, ruleCallback)
		});
	};

	const fetchRules = function(urlParams, ruleCallback) {
		$("#rules").spin(); //run spinner while request is being processed
    $.ajax({
			url: contextPath + "/spark-submit",
			type: "POST",
			dataType: "json",
			data: urlParams,
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
