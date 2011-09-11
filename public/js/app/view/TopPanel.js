Ext.define('PoupaNiquel.view.TopPanel' ,{
	extend: 'Ext.panel.Panel',
    alias: 'widget.toppanel',
    
	tbar: [{
    	text: 'File',
    }, {
    	text: 'Edit',
    }, {
    	text: 'View',
    }, {
    	text: 'Help',
    }],
    bbar: [{
    	iconCls: 'transactions-icon',
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'transactions'
    }, {
    	iconCls: 'transactions-icon',
    	cls:'x-btn-text-icon',
    	scale: 'large'
    }, {
    	iconCls: 'transactions-icon',
    	cls:'x-btn-text-icon',
    	scale: 'large'
    }, {
    	iconCls: 'transactions-icon',
    	cls:'x-btn-text-icon',
    	scale: 'large'
    }],

    initComponent: function() {
        return this.callParent(arguments);
    }
});