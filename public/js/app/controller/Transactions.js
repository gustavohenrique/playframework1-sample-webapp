Ext.define('PoupaNiquel.controller.Transactions', {
    extend: 'Ext.app.Controller',

    views: ['FilterPanel', 'FilterCombo', 'TransactionGrid'],
    models: ['Account', 'Generic', 'Transaction'],
    stores: ['Accounts', 'Categories', 'Payees', 'Transactions'],
    
    init: function() {
    	this.control({
  	        'button[action=filter]': {
  	    	    click: this.filterTransaction
  	        },
  	        'button[action=clear]': {
	    	    click: this.clearFilter
	        }
      	})
    },
    
    openWindow: function() {
    	
    	if (Ext.ComponentManager.get('transactionsWindow') == null) {
    		
    		var tabs = new Array();
    		
    		this.getAccountsStore().each(function(record) {
	    		store = Ext.create('PoupaNiquel.store.Transactions');
	    		store.proxy.extraParams.accountId = record.data.id;
	    		store.accountId = record.data.id;
	    		store.load();
	    		
	    		buttons = [{
	    			text: 'Clear',
	    			action: 'clear',
	    			store: store
	    		}, {
	    			text: 'Filter',
	    			action: 'filter',
	    			store: store
	    		}];
	    		
	    		var tab = Ext.widget('panel', {
	    		    layout: 'border',
	    		    title: record.data.name,
	    		    items: [{
	    		    	region: 'west', xtype: 'filterpanel', title: 'Filter', buttons: buttons
	    		    }, {
	    		    	region: 'center', xtype: 'transactiongrid', title: 'Transactions', store: store
	    		    }]
	    		});
	    		
	    		tabs.push(tab);
	    	});
	    	
	    	var tabPanel = Ext.widget('tabpanel', {
	    		activeTab: 0,
	    		frame: false,
	    		items: tabs,
	    	});

	    	var win = Ext.create('widget.window', {
	    		id: 'transactionsWindow',
	    		constrain: true,
	            height: '70%',
	            width: '90%',
	            x: 20,
	            y: 20,
	            title: 'Transactions',
	            closable: true,
	            plain: true,
	            layout: 'fit',
	            items: tabPanel
	    	});
	    	
	    	var viewport = Ext.ComponentManager.get('viewportCenter');
	    	viewport.add(win);
	    	win.show();
    	}
    },
    
    filterTransaction: function(button) {
    	var store = button.store;
    	var filterPanel = button.ownerCt.ownerCt;
    	store.proxy.extraParams.accountId = store.accountId;
    	store.proxy.extraParams.startDate = filterPanel.child('datefield[name=startDate]').getRawValue();
    	store.proxy.extraParams.endDate = filterPanel.child('datefield[name=endDate]').getRawValue();
    	store.proxy.extraParams.category = filterPanel.child('combobox[name=category]').getValue();
    	store.proxy.extraParams.payee = filterPanel.child('combobox[name=payee]').getValue();
    	store.load();
    },
    
    clearFilter: function(button) {
    	var store = button.store;
    	var accountId = store.accountId;
    	store.proxy.extraParams = {};
    	store.proxy.extraParams.accountId = store.accountId;
    	store.load();
    	
    	var filterPanel = button.ownerCt.ownerCt;
    	filterPanel.getForm().reset();
    }
    
});