Ext.define('PoupaNiquel.controller.Transactions', {
    extend: 'Ext.app.Controller',

    models: ['Account', 'Generic', 'Transaction'],
    stores: ['Accounts', 'Categories', 'Payees', 'Transactions'],
    views: ['transactions.FilterPanel', 'transactions.FilterComboBox', 'transactions.Grid', 'transactions.Edit'],
    
    refs: [{
    	ref: 'transactionGrid',
    	selector: 'transactionGrid'
    }],
    
    init: function() {
    	this.control({
  	        'button[action=filter]': {
  	    	    click: this.filter
  	        },
  	        'button[action=clear]': {
	    	    click: this.clear
	        },
	        'button[action=add]': {
            	click: this.edit
            },
            'button[action=edit]': {
            	click: this.edit
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
	    		    	region: 'west', xtype: 'filterPanel', title: 'Filter'
	    		    }, {
	    		    	region: 'center', xtype: 'transactionGrid', title: 'Transactions', store: store
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
    
    filter: function(button) {
    	var store = this.getTransactiongrid().getStore(),
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
    },
    
    edit: function(grid, record) {
        var edit = Ext.widget('transactionEdit').show();
        
        if (record) {
        	edit.down('form').loadRecord(record);
        }
    },
    
    edit: function(button) {
    	var grid = this.getTransactionGrid(),
	        record = grid.getSelectionModel().getSelection()[0],
            edit = Ext.widget('transactionEdit').show();
        
        if (record) {
        	edit.down('form').loadRecord(record);
        }
    },
    
    save: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            store = this.getTransactionGrid().getStore();
        
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
    
    delete: function(button) {
    	if (confirm('Are you sure?')) {
	    	var grid = this.getTransactionGrid(),
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