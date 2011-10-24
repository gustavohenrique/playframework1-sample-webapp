Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Main', {
    extend: 'Ext.app.Controller',

    models: ['Account'],
    stores: ['Accounts'],
    views: ['main.Top'],
    
    init: function() {
    	this.control({
	      'button[action=transactions]': {
	        click: this.openTransactions
	      },
    	})
    },
    
    openTransactions: function() {
    	this.getController('Transactions').openWindow();
    }
});