const Main = (function() {

	var main = function() {
		SearchFormController.init('rule-search-form', function(data) {
			console.log(data);
			$.get(contextPath + "/mustache/rules/rule-list.html", function(template) {
				const rendered = Mustache.render(template, data)
				console.log(rendered);
				$("#rules").html(rendered);
			});
		});
	};

	return {
		main: main
	};
}());