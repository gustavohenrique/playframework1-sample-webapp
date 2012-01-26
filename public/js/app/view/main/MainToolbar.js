Ext.define('PoupaNiquel.view.main.MainToolbar', {
	extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainToolbar',
    
    items: [{
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'transactions-icon',
    	text: 'Transactions',
    	action: 'showPanel',
    	controller: 'Transactions'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'accounts-icon',
    	text: 'Accounts',
    	action: 'showPanel',
    	controller: 'Accounts'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'categories-icon',
    	text: 'Categories',
    	action: 'showPanel',
    	controller: 'Categories'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'payees-icon',
    	text: 'Payees',
    	action: 'showPanel',
    }, '-', {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'import-icon',
    	text: 'Import',
    	action: 'showPanel',
    	controller: 'Importers'
    }],

    initComponent: function() {
        return this.callParent(arguments);
    }
});