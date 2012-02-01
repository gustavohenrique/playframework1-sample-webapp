Ext.define('PoupaNiquel.controller.Payees', {
    extend        : 'PoupaNiquel.controller.Common',
    myModelName   : 'Payee',
    myStoreName   : 'Payees',
    myPanelName   : 'payeesPanel',
    myGridSelector: 'payeesGrid',
    myTitle       : 'Payees',
    
    init: function() {
    	this.control({
    		'#payeesGrid button[action=add]': {click: this.add},
    		'#payeesGrid button[action=delete]': {click: this.delete},
    	});
    },
});