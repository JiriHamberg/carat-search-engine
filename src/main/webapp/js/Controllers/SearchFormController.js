/*
	Module SearchFormController

	Utilities for the rule search form.

*/
const SearchFormController = (function () {

	const init = function(formId) {
		const $form = $("#" + formId);
		$form.on("submit", function(event) {
			event.preventDefault();

			var options = {};

			$("#" + formId + " input[type=number]").each(function() {
				options[$(this).attr('id')] = parseFloat($(this).val());
			});

      var excluded = [];

      $("#" + formId + " input[type=checkbox]:checked").each(function() {
        excluded.push($(this).val());
      });
      options.excluded = excluded;

			//$form.trigger('reset');
			fetchRules(options)
		});

	};

	const fetchRules = function(options) {
    $("#rule-list").empty(); //remove existing rule listing
		$("#rule-spinner").spin(); //run spinner while request is being processed
    //console.log(options);

    const successCallback = function(response) {
      $("#rule-spinner").data('spinner').stop(); //stop spinner

      const callback = function() {
        RateSelectorController.selectInitialBin();
      };

      RuleContainer.setRuleHeader(response, callback);
      RuleContainer.setRules(response, callback);
		};

    const errorCallback = function(response) {
      $("#rule-spinner").data('spinner').stop(); //stop spinner
			console.log("SearchFormController: failed to fetch data")
      alert("Backend responded with error code " + response.status);
    };

    RuleService.fetch(
      options,
      successCallback,
      errorCallback
    );

	};

	return {
		init: init
	};
})();
