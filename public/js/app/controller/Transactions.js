Ext.define('PoupaNiquel.controller.Transactions', {
    extend: 'Ext.app.Controller',

    views: ['FilterPanel', 'FilterCombo', 'TransactionGrid', 'TransactionForm'],
    models: ['Account', 'Generic', 'Transaction'],
    stores: ['Accounts', 'Categories', 'Payees', 'Transactions'],
    
    refs: [{
    	ref: 'transactiongrid',
    	selector: 'transactiongrid'
    }],
    
    init: function() {
    	this.control({
  	        'button[action=filter]': {
  	    	    click: this.filterTransaction
  	        },
  	        'button[action=clear]': {
	    	    click: this.clearFilter
	        },
	        'button[action=add]': {
            	click: this.edit
            },
            'button[action=delete]': {
            	click: this.remove
            },
            'button[action=save]': {
                click: this.save
            },
            'dataview': {
                itemdblclick: this.edit
            },
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
	    		
	    		var tab = Ext.widget('panel', {
	    		    layout: 'border',
	    		    title: record.data.name,
	    		    items: [{
	    		    	region: 'west', xtype: 'filterpanel', title: 'Filter'
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
    	var store = this.getTransactiongrid().getStore(),
    	    filterPanel = button.ownerCt.ownerCt;
    	
    	store.proxy.extraParams.accountId = store.accountId;
    	store.proxy.extraParams.startDate = filterPanel.child('datefield[name=startDate]').getRawValue();
    	store.proxy.extraParams.endDate = filterPanel.child('datefield[name=endDate]').getRawValue();
    	store.proxy.extraParams.category = filterPanel.child('combobox[name=category]').getValue();
    	store.proxy.extraParams.payee = filterPanel.child('combobox[name=payee]').getValue();
    	store.load();
    },
    
    clearFilter: function(button) {
    	var filterPanel = button.ownerCt.ownerCt,
    	    store = this.getTransactiongrid().getStore(),
	        accountId = store.accountId;
    	
    	filterPanel.getForm().reset();
    	store.proxy.extraParams = {};
    	store.proxy.extraParams.accountId = store.accountId;
    	store.load();
    },
    
    edit: function(grid, record) {
        var edit = Ext.widget('transactionform').show();
        
        if (record) {
        	edit.down('form').loadRecord(record);
        }
    },
    
    save: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            store = this.getTransactiongrid().getStore();
        
		if (values.id > 0){
			record.set(values);
		}
		else {
			record = Ext.create('PoupaNiquel.model.Transaction');
			record.set(values);
			record.setId(0);
			store.add(record);
		}
		win.close();
        store.sync();
    },
    
    remove: function(button) {
    	if (confirm('Are you sure?')) {
	    	var grid = this.getTransactiongrid(),
	    	    record = grid.getSelectionModel().getSelection()[0],
	            store = grid.getStore();
	    	/*
                record = grid.store.getAt(pos.row);
	    	var grid = this.getTransactiongrid();
	    	var record = grid.getSelectionModel().getSelection()[0]; 
	    	var store = grid.getStore();
    	    //var record = store.getAt(pos.row);
	    	*/
                
		    store.remove(record);
		    store.sync();
    	}
    },
    
});