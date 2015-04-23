/**
 * Running JS functions defined in other files
 * in the right order, but first read language
 * and architecture info.
 */

function runInBackground(runner, initialValue, timeout) {
	return $.Deferred(function() {
		var self = this,
			value = initialValue,
			nextStep = function() {
				value = runner.step(value);
				if(!runner.finished(value))
					setTimeout(nextStep, timeout);
				else {
					self.resolve();
				}
			};
		setTimeout(nextStep, timeout);
	});
};

function retranslatePage(language) {
	$.get("LanguageAccessor", {lang: language}, function(language) {
		MW.Language = language;
		retranslateRegisters();
		retranslateSignals();
		for(key in MW.Language.UI){
			$("#" + key).html(MW.Language.UI[key]);
		}
	});
}

$(document).ready(function() {
	CentralUnit.hide();
	
	var memoryInitialized = initMemory();
	var settingsInitialized = initSettings();
	
	MW.initView();
	initRegisters();
	initSignals();
	retranslatePage("pl");
	initRunner();
	
	$.when(memoryInitialized, settingsInitialized).then(function() {
		restoreState();
		initInteractions();
		setTimeout(CentralUnit.show, 25);
	});
});
