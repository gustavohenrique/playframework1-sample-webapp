var store = Ext.create('PoupaNiquel.store.Transactions');

Ext.define('PoupaNiquel.controller.Transactions', {
	extend        : 'PoupaNiquel.controller.Common',
    myModelName   : 'Transaction',
    myStoreName   : 'Transactions',
    myPanelName   : 'transactionsPanel',
    myGridSelector: 'transactionsGrid',
    myTitle       : 'Transactions',
    
    init: function() {
    	this.control({
    		'#transactionsGrid button[action=add]': {click: this.add},
    		'#transactionsGrid button[action=delete]': {click: this.delete},
    	});
    },
    
    myGridPanel: Ext.create('PoupaNiquel.view.transactions.Grid', {
    	id     : 'transactionsGrid',
    	alias  : 'widget.' + this.myGridSelector,
    	store  : store,
    	plugins: Ext.create('Ext.grid.plugin.RowEditing', {
    		listeners: {
    			'edit': function() {
    				store.sync();
    			}
    		}
    	}),
    }),
    
    /*
    filter: function(button) {
    	var store = this.getTransactionGrid().getStore(),
    	    filterPanel = button.ownerCt.ownerCt;
    	
    	store.proxy.extraParams.accountId = store.accountId;
    	store.proxy.extraParams.startDate = filterPanel.child('datefield[name=startDate]').getRawValue();
    	store.proxy.extraParams.endDate = filterPanel.child('datefield[name=endDate]').getRawValue();
    	store.proxy.extraParams.category = filterPanel.child('combobox[name=category]').getValue();
    	store.proxy.extraParams.payee = filterPanel.child('combobox[name=payee]').getValue();
    	store.load();
    },
    
    clear: function(button) {
    	var filterPanel = button.ownerCt.ownerCt,
    	    store = this.getTransactionGrid().getStore(),
	        accountId = store.accountId;
    	
    	filterPanel.getForm().reset();
    	store.proxy.extraParams = {};
    	store.proxy.extraParams.accountId = store.accountId;
    	store.load();
    }, */
    
});