const Main = (function() {

	var main = function() {
		SearchFormController.init('rule-search-form', function(data) {
			console.log(data);
			$.get("mustache/rules/rule-list.html", function(template) {
				$("#rules").html(Mustache.render(template, data));
			});
		});
	};

	return {
		main: main
	};
}());