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
		$.ajax({
			url: "spark-submit",
			type: "POST",
			data: urlParams,
			success: function(response) {
				ruleCallback(response);
			},
			error: function(xhr) {
				console.log("SearchFormController: failed to fetch data")
			}
		});
	};

	return {
		init: init
	};
})();