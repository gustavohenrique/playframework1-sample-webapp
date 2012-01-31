var editing = Ext.create('Ext.grid.plugin.CellEditing');

Ext.define('PoupaNiquel.controller.Transactions', {
    extend: 'Ext.app.Controller',

    models: ['Account', 'Category', 'Payee', 'Transaction'],
    stores: ['Accounts', 'Categories', 'Payees', 'Transactions'],
    views: ['transactions.FilterPanel', 'transactions.FilterComboBox', 'transactions.Grid', 'transactions.Edit', 'common.MdiWindow'],
    
    refs: [{
    	ref: 'transactionGrid',
    	selector: 'transactionGrid'
    }],
    
    init: function() {
    	this.control({
  	        'filterPanel button[action=filter]': {
  	    	    click: this.filter
  	        },
  	        'filterPanel button[action=clear]': {
	    	    click: this.clear
	        },
	        'transactionEdit button[action=save]': {
                click: this.save
            },
	        'transactionGrid button[action=add]': {
            	click: this.edit
            },
            'transactionGrid button[action=edit]': {
            	click: this.editTransaction
            },
            'transactionGrid button[action=delete]': {
            	click: this.delete
            },
//            'transactionGrid dataview': {
//                itemdblclick: this.edit
//            },
      	})
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
	    	panel = Ext.ComponentManager.get('transactionsPanel'),
	    	store = this.getTransactionsStore();
	
		if (panel == null) {
			
	   	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'transactionsPanel',
	            title: 'Transactions',
	            items: [{
	            	xtype: 'transactionGrid',
	            	store: store,
	            	plugins: Ext.create('Ext.grid.plugin.CellEditing')
	            }]
	    	});
	   	    
	   	    viewport.add(panel);
		}
		
		panel.show();
    },
    
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
    },
    
    edit: function(grid, record) {
        var edit = Ext.widget('transactionEdit').show();
        
        if (record) {
        	edit.down('form').loadRecord(record);
        }
    },
    
    editTransaction: function(button) {
    	var grid = button.up('transactionGrid'),
	        record = grid.getSelectionModel().getSelection()[0];
    	
    	this.edit(grid, record);
        
    },
    
    save: function(button) {
        var panel = button.up('panel'),
            form = panel.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            grid = this.getTransactionGrid(),
            store = grid.getStore();
        
        console.log(record);
		if (values.id > 0) {
			var category = Ext.create('PoupaNiquel.model.Category');
			category.id = values.categoryId;
			values.category = category;
			record.set(values);
		}
		else {
			record = Ext.create('PoupaNiquel.model.Transaction');
			record.set(values);
			record.setId(0);
			store.add(record);
		}
		panel.close();
        store.sync();
    },
    
    delete: function(button) {
    	var grid = button.up('transactionGrid'),
	        record = grid.getSelectionModel().getSelection(),
            store = grid.getStore();
    	
    	if (record && confirm('Are you sure?')) {
		    store.remove(record);
		    store.sync();
    	}
    },
    
});