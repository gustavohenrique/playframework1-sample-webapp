Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Main', {
    extend: 'Ext.app.Controller',

    views: ['TopPanel'],
    models: ['Account'],
    stores: ['Accounts'],
    
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