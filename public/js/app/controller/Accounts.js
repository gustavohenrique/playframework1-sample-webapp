Ext.define('PoupaNiquel.controller.Accounts', {
	extend        : 'PoupaNiquel.controller.Common',
    myModelName   : 'Account',
    myStoreName   : 'Accounts',
    myPanelName   : 'accountsPanel',
    myGridSelector: 'accountsGrid',
    myTitle       : 'Accounts',
    
    init: function() {
    	this.control({
    		'#accountsGrid button[action=add]': {click: this.add},
    		'#accountsGrid button[action=delete]': {click: this.delete},
    	});
    },
    
    myGridPanel: Ext.create('PoupaNiquel.view.accounts.Grid', {
    	alias  : 'widget.' + this.myGridSelector,
    	plugins: Ext.create('Ext.grid.plugin.CellEditing'),
    	store  : Ext.create('PoupaNiquel.store.Accounts'),
    	id     : 'accountsGrid',
    }),
    
});