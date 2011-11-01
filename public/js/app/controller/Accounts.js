Ext.define('PoupaNiquel.controller.Accounts', {
    extend: 'Ext.app.Controller',

    models: ['Account'],
    stores: ['Accounts'],
    views: ['accounts.Edit'],
    
    init: function() {
    	this.control({
	        'button[action=add]': {
            	click: this.edit
            },
            'button[action=edit]': {
            	click: this.editTransaction
            },
            'button[action=delete]': {
            	click: this.delete
            },
            'button[action=save]': {
                click: this.save
            },
            'dataview': {
                itemdblclick: this.edit
            },
      	})
    },
    
    
    
});