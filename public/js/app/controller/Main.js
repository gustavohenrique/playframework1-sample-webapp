Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Main', {
    extend: 'Ext.app.Controller',

    views: ['main.Top'],
    
    init: function() {
    	this.control({
	        'button[action=transactions]': {
	            click: this.openTransactions
	        },
	        'button[action=accounts]': {
		        click: this.openAccounts
		    }
    	})
    },
    
    openTransactions: function() {
    	this.getController('Transactions').showPanel();
    },
    
    openAccounts: function() {
    	this.getController('Accounts').showPanel();
    }
});