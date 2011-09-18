Ext.define('PoupaNiquel.view.TopPanel' ,{
	extend: 'Ext.panel.Panel',
    alias: 'widget.toppanel',
    
	tbar: [{
    	text: 'File',
    }, {
    	text: 'Help',
    }],
    bbar: [{
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'transactions',
    	iconCls: 'transactions-icon',
    	text: 'Transactions'
    }, {
    	cls:'x-btn-text-icon',
    	scale: 'large',
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
    }],

    initComponent: function() {
        return this.callParent(arguments);
    }
});