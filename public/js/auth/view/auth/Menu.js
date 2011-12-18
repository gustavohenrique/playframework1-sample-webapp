Ext.define('PoupaNiquel.view.auth.Menu', {
	extend: 'Ext.panel.Panel',
    alias: 'widget.authMenu',
    
    bbar: [{
    	cls:'x-btn-text-icon',
    	scale: 'large',
    	action: 'login',
    	iconCls: 'transactions-icon',
    	text: 'Login'
    }],

    initComponent: function() {
        return this.callParent(arguments);
    }
});