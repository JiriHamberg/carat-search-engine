const Main = (function() {

	var main = function() {
    Helpers.registerHelpers();
    RuleContainer.init($("#rules"));
		SearchFormController.init('rule-search-form');
	};

	return {
		main: main
	};
}());
