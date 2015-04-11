/**
 * Running JS functions.
 */
$(document).ready(function() {
	$.get("LanguageAccessor", {lang: "pl"}, function(data) {
		MW.Language = JSON.parse(data);
		$.get("ArchitectureInfo", function(data) {
			MW.Architecture = JSON.parse(data);
			MW.init();
			MW.initInteraction();
			MW.initSettings();
			MW.initRunner();
		});
	});
});
