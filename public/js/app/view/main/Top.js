Ext.define('PoupaNiquel.view.main.Top', {
	extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainTop',
    
    items: [{
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'transactions',
    	iconCls: 'transactions-icon',
    	text: 'Transactions'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'accounts',
    	iconCls: 'accounts-icon',
    	text: 'Accounts'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'categories-icon',
    	text: 'Categories'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	iconCls: 'payees-icon',
    	text: 'Payees'
    }, '-', {
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'import',
    	iconCls: 'import-icon',
    	text: 'Import'
    }],

    initComponent: function() {
        return this.callParent(arguments);
    }
});