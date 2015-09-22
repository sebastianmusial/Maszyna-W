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

$().ready(function() {
	CentralUnit.hide();
	
	var memoryInitialized = initMemory();
	var settingsInitialized = initSettings();
	
	MW.initView();
	initRegisters();
	initSignals();
	retranslatePage("pl");
	initRunner();
	closeMessageBox();
	
	/*
	 * Type:
	 * 0 - error
	 * 1 - success
	 * 2 - information
	 * 3 - warning
	 */
	var msgText = "lorem ipsum dolor sit amet enim. etiam ullamcorper. suspendisse a pellentesque dui non felis";
	msgBox(msgText, 0);
	
	$.when(memoryInitialized, settingsInitialized).then(function() {
		restoreState();
		initInteractions();
		setTimeout(CentralUnit.show, 25);
	});
	
	initCommands();
});
